package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.MethodOutcome;

import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.param.UriParam;
import com.ibm.icu.text.AlphabeticIndex;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.aql.record.Record2;
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
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.definition.LaboranalytResultatAnalytResultatDvquantity;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.definition.LaboranalytResultatCluster;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.definition.StandortJedesEreignisPointEvent;
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
            /*
            Query<Record1<IntensivmedizinischesMonitoringKorpertemperaturComposition>> query =
                Query.buildNativeQuery("SELECT c FROM EHR e CONTAINS COMPOSITION c where "
                     + "c/archetype_details/template_id/value = 'Intensivmedizinisches Monitoring Korpertemperatur' AND "
                     + "e/ehr_status/subject/external_ref/id/value = '"+ subject_id.getValue() +"'",
                     IntensivmedizinischesMonitoringKorpertemperaturComposition.class);

            List<Record1<IntensivmedizinischesMonitoringKorpertemperaturComposition>> results = new ArrayList<Record1<IntensivmedizinischesMonitoringKorpertemperaturComposition>>();
            */
            // Workaround for not getting the composition uid in the result (https://github.com/ehrbase/openEHR_SDK/issues/44)
            Query<Record2<IntensivmedizinischesMonitoringKorpertemperaturComposition, String>> query =
                    Query.buildNativeQuery("SELECT c, c/uid/value FROM EHR e CONTAINS COMPOSITION c where "
                                    + "c/archetype_details/template_id/value = 'Intensivmedizinisches Monitoring Korpertemperatur' AND "
                                    + "e/ehr_status/subject/external_ref/id/value = '"+ subject_id.getValue() +"'",
                            IntensivmedizinischesMonitoringKorpertemperaturComposition.class, String.class);

            List<Record2<IntensivmedizinischesMonitoringKorpertemperaturComposition, String>> results = new ArrayList<Record2<IntensivmedizinischesMonitoringKorpertemperaturComposition, String>>();

            try
            {
                results = service.getClient().aqlEndpoint().execute(query);

                IntensivmedizinischesMonitoringKorpertemperaturComposition compo;
                String uid;
                Observation observation;
                TemporalAccessor temporal;
                KorpertemperaturBeliebigesEreignisPointEvent event;
                Coding coding;

                //for (Record1<IntensivmedizinischesMonitoringKorpertemperaturComposition> record: results)
                for (Record2<IntensivmedizinischesMonitoringKorpertemperaturComposition, String> record: results)
                {
                    compo = record.value1();
                    uid = record.value2();

                    // Map back compo -> fhir observation
                    observation = new Observation();


                    // observations [0] . origin => effective_time
                    temporal = compo.getKorpertemperatur().get(0).getOriginValue();
                    observation.getEffectiveDateTimeType().setValue(Date.from(((OffsetDateTime)temporal).toInstant()));


                    // observations [0] . events [0] . value -> observation . value
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

                    observation.getMeta().addProfile(Profile.BODY_TEMP.getUrl());

                    observation.setId("bodytemp");



                    // FIXME: all FHIR resources need an ID, we are not storing specific IDs for the observations in openEHR,
                    // and if we return FHIR resources, the IDs we return should be consistent for instance if we want to
                    // provide a get by ID operation via the FHIR API.
                    //observation.setId(UUID.randomUUID().toString());
                    observation.setId(uid); // workaround
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
        else if (profile.getValue().equals(Profile.CORONARIRUS_NACHWEIS_TEST.getUrl()))
        {
            // another approach, asking for the data points in AQL directly without retrieving the whole compo
            // this doesnt work https://github.com/ehrbase/openEHR_SDK/issues/45
            /*
            Query<Record> query = Query.buildNativeQuery(
                "SELECT c/uid/value as uid, eval/data[at0001]/items[at0015]/value/value as effective_time "+
                "FROM EHR e CONTAINS COMPOSITION c CONTAINS EVALUATION eval[openEHR-EHR-EVALUATION.flag_pathogen.v0] "+
                "WHERE c/archetype_details/template_id/value = 'Kennzeichnung Erregernachweis SARS-CoV-2' AND "+
                "e/ehr_status/subject/external_ref/id/value = '"+ subject_id.getValue() +"'"
            );

            List<Record> results = new ArrayList<Record>();
            */

            Query<Record2<KennzeichnungErregernachweisSARSCoV2Composition, String>> query = Query.buildNativeQuery(
            "SELECT c, c/uid/value "+
                    "FROM EHR e CONTAINS COMPOSITION c CONTAINS EVALUATION eval[openEHR-EHR-EVALUATION.flag_pathogen.v0] "+
                    "WHERE c/archetype_details/template_id/value = 'Kennzeichnung Erregernachweis SARS-CoV-2' AND "+
                    "e/ehr_status/subject/external_ref/id/value = '"+ subject_id.getValue() +"'",
                    KennzeichnungErregernachweisSARSCoV2Composition.class, String.class
            );

            //List<Record> results = new ArrayList<Record>();
            List<Record2<KennzeichnungErregernachweisSARSCoV2Composition, String>> results = new ArrayList<Record2<KennzeichnungErregernachweisSARSCoV2Composition, String>>();

            try
            {
                results = service.getClient().aqlEndpoint().execute(query);

                String uid;
                KennzeichnungErregernachweisSARSCoV2Composition compo;

                Observation observation;
                TemporalAccessor temporal;
                KorpertemperaturBeliebigesEreignisPointEvent event;
                Coding coding;

                for (Record2<KennzeichnungErregernachweisSARSCoV2Composition, String> record: results)
                //for (Record record: results)
                {
                    compo = record.value1();
                    uid = record.value2();

                    /*
                    uid = (String)record.value(0);
                    effective_time = (TemporalAccessor)record.value(1);
                    */
                    System.out.println(record); // org.ehrbase.client.aql.record.RecordImp
                    System.out.println(record.values().length); // using Record instead of Record2 gives 0
                    System.out.println(record.fields().length); // using Record instead of Record2 gives 0

                    // Map back compo -> fhir observation
                    observation = new Observation();


                    // mapping back to FHIR

                    // evaluation time -> effective_time
                    temporal = compo.getKennzeichnungErregernachweis().getZeitpunktDerKennzeichnungValue();
                    observation.getEffectiveDateTimeType().setValue(Date.from(((OffsetDateTime)temporal).toInstant()));

                    // FIXME: cant map the code back because the compo has a boolean derived from the code in the FHIR resource
                    if (compo.getKennzeichnungErregernachweis().isErregernachweisValue())
                    {
                        // This is not right, could not the value that came initially in the FHIR observation
                        coding = observation.getCode().addCoding();
                        coding.setSystem("http://loing.org");
                        coding.setCode("94532-9");
                        coding.setDisplay("SARS coronavirus+SARS-like coronavirus+SARS coronavirus 2+MERS coronavirus RNA [Presence] in Respiratory specimen by NAA with probe detection");
                    }


                    // set patient
                    observation.getSubject().setReference("Patient/"+ subject_id.getValue());

                    observation.setStatus(Observation.ObservationStatus.FINAL);

                    observation.getMeta().addProfile(Profile.CORONARIRUS_NACHWEIS_TEST.getUrl());


                    // FIXME: we are also not storing referenceRange


                    // FIXME: all FHIR resources need an ID, we are not storing specific IDs for the observations in openEHR,
                    observation.setId(uid); // workaround
                    //observation.setId(UUID.randomUUID().toString());


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
        else if (profile.getValue().equals(Profile.OBSERVATION_LAB.getUrl()))
        {
            Query<Record2<LaborbefundComposition, String>> query =
                Query.buildNativeQuery("SELECT c, c/uid/value FROM EHR e CONTAINS COMPOSITION c where "
                                    + "c/archetype_details/template_id/value = 'Laborbefund' AND "
                                    + "e/ehr_status/subject/external_ref/id/value = '"+ subject_id.getValue() +"'",
                            LaborbefundComposition.class, String.class);

            List<Record2<LaborbefundComposition, String>> results = new ArrayList<Record2<LaborbefundComposition, String>>();

            try
            {
                results = service.getClient().aqlEndpoint().execute(query);

                LaborbefundComposition compo;
                String uid;
                Observation observation;
                TemporalAccessor temporal;
                Coding coding;

                for (Record2<LaborbefundComposition, String> record: results)
                {
                    compo = record.value1();
                    uid = record.value2();

                    // Map back compo -> fhir observation
                    observation = new Observation();

                    LaboranalytResultatCluster cluster = ((StandortJedesEreignisPointEvent)compo.getLaborergebnis().get(0).getJedesEreignis().get(0)).getLaboranalytResultat().get(0);

                    // cluster . time -> observation . effective_date
                    temporal = cluster.getZeitpunktErgebnisStatusValue();
                    observation.getEffectiveDateTimeType().setValue(Date.from(((OffsetDateTime)temporal).toInstant()));

                    // cluster . value -> observation . value
                    LaboranalytResultatAnalytResultatDvquantity value = ((LaboranalytResultatAnalytResultatDvquantity)cluster.getAnalytResultat());
                    observation.getValueQuantity().setValue(value.getAnalytResultatMagnitude());
                    observation.getValueQuantity().setUnit(value.getAnalytResultatUnits());
                    observation.getValueQuantity().setSystem("http://unitsofmeasure.org");
                    observation.getValueQuantity().setCode(value.getAnalytResultatUnits());

                    // set codes that come hardcoded in the inbound resources

                    // observation . category
                    observation.getCategory().add(new CodeableConcept());
                    coding = observation.getCategory().get(0).addCoding();
                    coding.setSystem("http://terminology.hl7.org/CodeSystem/observation-category");
                    coding.setCode("laboratory");
                    coding = observation.getCategory().get(0).addCoding();
                    coding.setSystem("http://loing.org");
                    coding.setCode("26436-6");

                    // observation . code
                    coding = observation.getCode().addCoding();
                    coding.setSystem("http://loing.org");
                    coding.setCode("59826-8");
                    coding.setDisplay("Creatinine [Moles/volume] in Blood");
                    observation.getCode().setText("Kreatinin");

                    // set patient
                    observation.getSubject().setReference("Patient/"+ subject_id.getValue());

                    observation.setStatus(Observation.ObservationStatus.FINAL);

                    observation.getMeta().addProfile(Profile.OBSERVATION_LAB.getUrl());

                    observation.setId(uid);

                    // adds observation to the result
                    result.add(observation);
                }
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
