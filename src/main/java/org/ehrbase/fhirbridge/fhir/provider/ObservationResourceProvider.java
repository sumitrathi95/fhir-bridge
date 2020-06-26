package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.MethodOutcome;

import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.param.UriParam;
import com.ibm.icu.text.AlphabeticIndex;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.openehrclient.CompositionEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.client.templateprovider.FileBasedTemplateProvider;
import org.ehrbase.client.templateprovider.TemplateProvider;

import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.fhir.ProfileUtils;

import org.ehrbase.fhirbridge.mapping.F2OLabReport;
import org.ehrbase.fhirbridge.mapping.F2OSarsTestResult;
import org.ehrbase.fhirbridge.mapping.F2OTemperature;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.IntensivmedizinischesMonitoringKorpertemperaturComposition;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturBeliebigesEreignisPointEvent;
import org.ehrbase.fhirbridge.opt.kennzeichnungerregernachweissarscov2composition.KennzeichnungErregernachweisSARSCoV2Composition;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.LaborbefundComposition;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Resource provider for Observation
 */
@Component
public class ObservationResourceProvider extends AbstractResourceProvider {

    private final Logger logger = LoggerFactory.getLogger(ObservationResourceProvider.class);

    @Autowired
    public ObservationResourceProvider(FhirContext fhirContext, EhrbaseService service) {
        super(fhirContext);
        this.service = service;
    }

    private final EhrbaseService service;

    @Search
    // search with no params
    public List<Observation> getAllObservations(
        @OptionalParam(name="_profile") UriParam profile,
        @RequiredParam(name=Observation.SP_SUBJECT+'.'+Patient.SP_IDENTIFIER)TokenParam subject_id
    )
    {
        System.out.println("SEARCH OBS! "+ profile);
        List<Observation> result = new ArrayList<Observation>();

        if (profile.getValue().equals(Profile.BODY_TEMP.getUrl()))
        {
            // Query body temperatures

            // get all compositions for the body temperature template
            // TODO: filter by patient if the patient id parameter is used
            Query<Record1<IntensivmedizinischesMonitoringKorpertemperaturComposition>> query =
                Query.buildNativeQuery("SELECT c FROM EHR e CONTAINS COMPOSITION c where "
                     + "c/archetype_details/template_id/value = 'Intensivmedizinisches Monitoring Korpertemperatur' AND "
                     + "e/ehr_status/subject/external_ref/id/value = '"+ subject_id.getValue() +"'",
                     IntensivmedizinischesMonitoringKorpertemperaturComposition.class);

            List<Record1<IntensivmedizinischesMonitoringKorpertemperaturComposition>> results = new ArrayList<Record1<IntensivmedizinischesMonitoringKorpertemperaturComposition>>();
            try
            {
                results = service.getClient().aqlEndpoint().execute(query);

                IntensivmedizinischesMonitoringKorpertemperaturComposition compo;
                Observation observation;
                TemporalAccessor temporal;
                KorpertemperaturBeliebigesEreignisPointEvent event;
                Coding coding;

                for (Record1<IntensivmedizinischesMonitoringKorpertemperaturComposition> record: results)
                {
                    compo = record.value1();

                    // Map back compo -> fhir observation
                    observation = new Observation();


                    // observations [0] -> origin
                    temporal = compo.getKorpertemperatur().get(0).getOriginValue();
                    observation.getEffectiveDateTimeType().setValue(Date.from(((OffsetDateTime)temporal).toInstant()));


                    // observations [0] -> events [0] ->
                    event = (KorpertemperaturBeliebigesEreignisPointEvent)compo.getKorpertemperatur().get(0).getBeliebigesEreignis().get(0);
                    observation.getValueQuantity().setValue(event.getTemperaturMagnitude().doubleValue());
                    observation.getValueQuantity().setUnit(event.getTemperaturUnits());


                    // set patient
                    observation.getSubject().setReference("Patient/"+ subject_id.getValue());


                    // set codes that come hardcoded in the inbound resources
                    observation.getCategory().add(new CodeableConcept());
                    coding = observation.getCategory().get(0).addCoding();
                    coding.setSystem("http://terminology.hl7.org/CodeSystem/observation-category");
                    coding.setCode("vital-signs");

                    coding = observation.getCode().addCoding();
                    coding.setSystem("http://loing.org");
                    coding.setCode("8310-5");

                    observation.setStatus(Observation.ObservationStatus.FINAL);

                    observation.getMeta().addProfile("http://hl7.org/fhir/StructureDefinition/bodytemp");

                    observation.setId("bodytemp");



                    // FIXME: all FHIR resources need an ID, we are not storing specific IDs for the observations in openEHR,
                    // and if we return FHIR resources, the IDs we return should be consistent for instance if we want to
                    // provide a get by ID operation via the FHIR API.
                    observation.setId(UUID.randomUUID().toString());
                    //observation.setId(compo.getVersionUid().toString()); // the versionUid is null for the compo
                    //System.out.println(compo.getVersionUid()); // this is nul...

                    // adds observation to the result
                    result.add(observation);
                }

                System.out.println(results.size());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Not equal "+ profile.getValue() +" "+ Profile.BODY_TEMP.getUrl());
        }

        return result;
    }

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createObservation(@ResourceParam Observation observation) throws Exception
    {
        checkProfiles(observation);

        // Patient/xxx => xxx
        String subjectIdValue = null;
        String ehr_id = null;
        UUID ehr_uid = null;
        try
        {
            subjectIdValue = observation.getSubject().getReference().split("/")[1];
            ehr_id = service.ehrIdBySubjectId(subjectIdValue);
            if (ehr_id != null)
            {
                ehr_uid = UUID.fromString(ehr_id);
            }
            else
            {
                logger.error("EHR for patient "+ subjectIdValue +" doesn't exists");
                // TODO: check in HAPI FHIR how to return 404
            }
        }
        catch (Exception e)
        {
            throw new Exception("Can't get the patient ID from the resource");
        }

        // TODO: Do we need to handle the case where several profiles are defined and valid?
        if (ProfileUtils.hasProfile(observation, Profile.OBSERVATION_LAB))
        {
            System.out.println(">>>>>>>>>>>>>>>>>>> OBSERVATION LAB "+ observation.getIdentifier().get(0).getValue());

            try
            {
                // test map FHIR to openEHR
                LaborbefundComposition composition = F2OLabReport.map(observation);
                //UUID ehr_id = service.createEhr(); // <<< reflections error!
                VersionUid versionUid = service.saveLab(ehr_uid, composition);
                System.out.println("Composition created with UID "+ versionUid.toString() +" for FHIR profile "+ Profile.OBSERVATION_LAB);
            }
            catch (Exception e)
            {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        else if (ProfileUtils.hasProfile(observation, Profile.CORONARIRUS_NACHWEIS_TEST))
        {
            System.out.println(">>>>>>>>>>>>>>>>>>>> OBSERVATION COVID");

            // Map CoronavirusNachweisTest to openEHR
            try {
                // test map FHIR to openEHR
                KennzeichnungErregernachweisSARSCoV2Composition composition = F2OSarsTestResult.map(observation);
                //UUID ehr_id = service.createEhr(); // <<< reflections error!
                VersionUid versionUid = service.saveTest(ehr_uid, composition);
                System.out.println("Composition created with UID "+ versionUid.toString() +" for FHIR profile "+ Profile.BODY_TEMP);
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }

        }
        else if (ProfileUtils.hasProfile(observation, Profile.BODY_TEMP))
        {
            System.out.println(">>>>>>>>>>>>>>>>>> OBSERVATION TEMP");

            // Map BodyTemp to openEHR
            try
            {
                // test map FHIR to openEHR
                IntensivmedizinischesMonitoringKorpertemperaturComposition composition = F2OTemperature.map(observation);
                //UUID ehr_id = service.createEhr(); // <<< reflections error!
                VersionUid versionUid = service.saveTemp(ehr_uid, composition);
                System.out.println("Composition created with UID "+ versionUid.toString() +" for FHIR profile "+ Profile.BODY_TEMP);
            }
            catch (Exception e)
            {
                //e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

        observation.setId(new IdType(1L));
        observation.getMeta().setVersionId("1");
        observation.getMeta().setLastUpdatedElement(InstantType.withCurrentTime());

        return new MethodOutcome()
                .setCreated(true)
                .setResource(observation);
    }

    @Override
    public Class<Observation> getResourceType() {
        return Observation.class;
    }

    @Override
    public boolean isDefaultProfileSupported() {
        return false;
    }
}
