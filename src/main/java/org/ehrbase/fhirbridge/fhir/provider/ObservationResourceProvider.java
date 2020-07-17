package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.MethodOutcome;

import ca.uhn.fhir.rest.param.*;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record2;
import org.ehrbase.client.openehrclient.VersionUid;

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


import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.Date.from;


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
        @RequiredParam(name=Patient.SP_IDENTIFIER) TokenParam subjectId,
        @OptionalParam(name=Observation.SP_DATE) DateRangeParam dateRange,
        @OptionalParam(name=Observation.SP_VALUE_QUANTITY) QuantityParam qty
    )
    {
        logger.info("SEARCH OBS! "+ profile);
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
                     + "e/ehr_status/subject/external_ref/id/value = '"+ subjectId.getValue() +"'",
                     IntensivmedizinischesMonitoringKorpertemperaturComposition.class);

            List<Record1<IntensivmedizinischesMonitoringKorpertemperaturComposition>> results = new ArrayList<Record1<IntensivmedizinischesMonitoringKorpertemperaturComposition>>();
            */
            // Workaround for not getting the composition uid in the result (https://github.com/ehrbase/openEHR_SDK/issues/44)
            String aql =
                "SELECT c, c/uid/value "+
                "FROM EHR e CONTAINS COMPOSITION c CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.body_temperature.v2] "+
                "WHERE c/archetype_details/template_id/value = 'Intensivmedizinisches Monitoring Korpertemperatur' AND "+
                "e/ehr_status/subject/external_ref/id/value = '"+ subjectId.getValue() +"'";

            if (dateRange != null)
            {
                // with date range we can also receive just one bound
                if (dateRange.getLowerBound() != null)
                    aql += " AND '"+ dateRange.getLowerBound().getValueAsString() + "' <= c/context/start_time/value";

                if (dateRange.getUpperBound() != null)
                    aql += " AND c/context/start_time/value <= '"+ dateRange.getUpperBound().getValueAsString() +"'";
            }

            if (qty != null)
            {
                ParamPrefixEnum prefix = qty.getPrefix();
                String operator = "";
                if (prefix == null) operator = "=";
                else {
                    switch (prefix) {
                        case EQUAL:
                            operator = "=";
                            break;
                        case LESSTHAN:
                            operator = "<";
                            break;
                        case GREATERTHAN:
                            operator = ">";
                            break;
                        case LESSTHAN_OR_EQUALS:
                            operator = "<=";
                            break;
                        case GREATERTHAN_OR_EQUALS:
                            operator = ">=";
                            break;
                        default:
                            operator = "=";
                    }
                }

                if (!operator.isBlank())
                    aql += " AND o/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude "+ operator +" "+ qty.getValue();
            }

            Query<Record2<IntensivmedizinischesMonitoringKorpertemperaturComposition, String>> query =
                Query.buildNativeQuery(aql, IntensivmedizinischesMonitoringKorpertemperaturComposition.class, String.class);

            List<Record2<IntensivmedizinischesMonitoringKorpertemperaturComposition, String>> results = new ArrayList<>();

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
                    observation.getEffectiveDateTimeType().setValue(from(((OffsetDateTime)temporal).toInstant()));


                    // observations [0] . events [0] . value -> observation . value
                    event = (KorpertemperaturBeliebigesEreignisPointEvent)compo.getKorpertemperatur().get(0).getBeliebigesEreignis().get(0);
                    observation.getValueQuantity().setValue(event.getTemperaturMagnitude());
                    observation.getValueQuantity().setUnit(event.getTemperaturUnits());


                    // set patient
                    observation.getSubject().setReference("Patient/"+ subjectId.getValue());


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
                    //logger.info(compo.getVersionUid()); // this is nul...

                    // adds observation to the result
                    result.add(observation);
                }


                logger.info("Results: {}", results.size());
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
                "e/ehr_status/subject/external_ref/id/value = '"+ subjectId.getValue() +"'"
            );

            List<Record> results = new ArrayList<Record>();
            */

            String aql =
                "SELECT c, c/uid/value "+
                "FROM EHR e CONTAINS COMPOSITION c CONTAINS EVALUATION eval[openEHR-EHR-EVALUATION.flag_pathogen.v0] "+
                "WHERE c/archetype_details/template_id/value = 'Kennzeichnung Erregernachweis SARS-CoV-2' AND "+
                "e/ehr_status/subject/external_ref/id/value = '"+ subjectId.getValue() +"'";

            if (dateRange != null)
            {
                // with date range we can also receive just one bound
                if (dateRange.getLowerBound() != null)
                    aql += " AND '"+ dateRange.getLowerBound().getValueAsString() + "' <= c/context/start_time/value";

                if (dateRange.getUpperBound() != null)
                    aql += " AND c/context/start_time/value <= '"+ dateRange.getUpperBound().getValueAsString() +"'";
            }

            Query<Record2<KennzeichnungErregernachweisSARSCoV2Composition, String>> query =
                Query.buildNativeQuery(aql, KennzeichnungErregernachweisSARSCoV2Composition.class, String.class);

            //List<Record> results = new ArrayList<Record>();
            List<Record2<KennzeichnungErregernachweisSARSCoV2Composition, String>> results = new ArrayList<>();

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

                    /* not working because results are not populated when using Record
                    uid = (String)record.value(0);
                    effective_time = (TemporalAccessor)record.value(1);
                    */

                    logger.info("Record: {}", record); // org.ehrbase.client.aql.record.RecordImp
                    logger.info("Record values {}", record.values().length); // using Record instead of Record2 gives 0
                    logger.info("Record fields {}", record.fields().length); // using Record instead of Record2 gives 0


                    // Map back compo -> fhir observation
                    observation = new Observation();


                    // mapping back to FHIR

                    // evaluation time -> effective_time
                    temporal = compo.getKennzeichnungErregernachweis().getZeitpunktDerKennzeichnungValue();
                    observation.getEffectiveDateTimeType().setValue(from(((OffsetDateTime)temporal).toInstant()));

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
                    observation.getSubject().setReference("Patient/"+ subjectId.getValue());

                    observation.setStatus(Observation.ObservationStatus.FINAL);

                    observation.getMeta().addProfile(Profile.CORONARIRUS_NACHWEIS_TEST.getUrl());


                    // FIXME: we are also not storing referenceRange


                    // FIXME: all FHIR resources need an ID, we are not storing specific IDs for the observations in openEHR,
                    observation.setId(uid); // workaround
                    //observation.setId(UUID.randomUUID().toString());


                    // adds observation to the result
                    result.add(observation);
                }


                logger.info("Results {}", results.size());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (profile.getValue().equals(Profile.OBSERVATION_LAB.getUrl()))
        {

            String aql =
                "SELECT c, c/uid/value "+
                "FROM EHR e CONTAINS COMPOSITION c "+
                "WHERE c/archetype_details/template_id/value = 'Laborbefund' AND "+
                "e/ehr_status/subject/external_ref/id/value = '"+ subjectId.getValue() +"'";

            /* getting 400 from this query, tried to get the cluster to compare with the date range param since that is the real effectiveTime of the resource, not the compo time.
            String aql =
                    "SELECT c, c/uid/value "+
                            "FROM EHR e CONTAINS COMPOSITION c CONTAINS CLUSTER cluster[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1] "+
                            "WHERE c/archetype_details/template_id/value = 'Laborbefund' AND "+
                            "e/ehr_status/subject/external_ref/id/value = '"+ subjectId.getValue() +"'";
            */

            if (dateRange != null)
            {
                // with date range we can also receive just one bound
                if (dateRange.getLowerBound() != null)
                {
                    // this is for filtering against the effective time in the cluster but the query above doesn't work
                    //aql += " AND '"+ dateRange.getLowerBound().getValueAsString() + "' <= cluster/items[at0006]/value/value";
                    aql += " AND '" + dateRange.getLowerBound().getValueAsString() + "' <= c/context/start_time/value";
                }

                if (dateRange.getUpperBound() != null)
                {
                    //aql += " AND cluster/items[at0006]/value/value <= '"+ dateRange.getUpperBound().getValueAsString() +"'";
                    aql += " AND c/context/start_time/value <= '" + dateRange.getUpperBound().getValueAsString() + "'";
                }
            }

            Query<Record2<LaborbefundComposition, String>> query =
                Query.buildNativeQuery(aql, LaborbefundComposition.class, String.class);

            List<Record2<LaborbefundComposition, String>> results = new ArrayList<>();

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
                    observation.getEffectiveDateTimeType().setValue(from(((OffsetDateTime)temporal).toInstant()));

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
                    observation.getSubject().setReference("Patient/"+ subjectId.getValue());

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
            logger.info("Profile not supported {} ", profile.getValue());
        }

        return result;
    }

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createObservation(@ResourceParam Observation observation)
    {
        checkProfiles(observation);

        // Patient/xxx => xxx
        String subjectIdValue = null;
        String ehrId = null;
        UUID ehrUid = null;
        try
        {
            subjectIdValue = observation.getSubject().getReference().split("/")[1];
            ehrId = service.ehrIdBySubjectId(subjectIdValue);
            if (ehrId != null)
            {
                ehrUid = UUID.fromString(ehrId);
            }
            else
            {
                throw new ResourceNotFoundException("EHR for patient "+ subjectIdValue +" doesn't exists");
            }
        }
        catch (Exception e)
        {
            throw new UnprocessableEntityException("Can't get the patient ID from the resource", e);
        }

        if (ProfileUtils.hasProfile(observation, Profile.OBSERVATION_LAB))
        {
            logger.info(">>>>>>>>>>>>>>>>>>> OBSERVATION LAB {}", observation.getIdentifier().get(0).getValue());

            try
            {
                // test map FHIR to openEHR
                LaborbefundComposition composition = F2OLabReport.map(observation);

                //UUID ehrId = service.createEhr(); // <<< reflections error!
                VersionUid versionUid = service.saveLab(ehrUid, composition);
                logger.info("Composition created with UID {} for FHIR profile {}", versionUid, Profile.OBSERVATION_LAB);
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
            }
        }
        else if (ProfileUtils.hasProfile(observation, Profile.CORONARIRUS_NACHWEIS_TEST))
        {
            logger.info(">>>>>>>>>>>>>>>>>>>> OBSERVATION COVID");

            // Map CoronavirusNachweisTest to openEHR
            try {
                // test map FHIR to openEHR
                KennzeichnungErregernachweisSARSCoV2Composition composition = F2OSarsTestResult.map(observation);

                //UUID ehrId = service.createEhr(); // <<< reflections error!
                VersionUid versionUid = service.saveTest(ehrUid, composition);
                logger.info("Composition created with UID {} for FHIR profile {}", versionUid, Profile.BODY_TEMP);
            } catch (Exception e) {

                logger.error(e.getMessage());
            }

        }
        else if (ProfileUtils.hasProfile(observation, Profile.BODY_TEMP))
        {
            logger.info(">>>>>>>>>>>>>>>>>> OBSERVATION TEMP");

            // Map BodyTemp to openEHR
            try
            {
                // test map FHIR to openEHR
                IntensivmedizinischesMonitoringKorpertemperaturComposition composition = F2OTemperature.map(observation);

                //UUID ehrId = service.createEhr(); // <<< reflections error!
                VersionUid versionUid = service.saveTemp(ehrUid, composition);

                logger.info("Composition created with UID "+ versionUid.toString() +" for FHIR profile "+ Profile.BODY_TEMP);
            }
            catch (Exception e)
            {
                logger.error(e.getMessage());
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
