package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.param.UriParam;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.aql.record.Record2;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.mapping.F2ODiagnose;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.DiagnoseComposition;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.definition.AtiopathogeneseSchweregradDvcodedtext;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.definition.DiagnoseEvaluation;
import org.ehrbase.fhirbridge.opt.kennzeichnungerregernachweissarscov2composition.KennzeichnungErregernachweisSARSCoV2Composition;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Resource provider for Condition
 */
@Component
public class ConditionResourceProvider extends AbstractResourceProvider {

    private final Logger logger = LoggerFactory.getLogger(ConditionResourceProvider.class);

    @Autowired
    public ConditionResourceProvider(FhirContext fhirContext, EhrbaseService service) {
        super(fhirContext);
        this.service = service;
    }

    private final EhrbaseService service;

    private Condition getConditionFromCompo(DiagnoseComposition compo, String uid)
    {
        Condition condition = new Condition();

        TemporalAccessor temporal;
        String text;
        Coding coding;

        // mapping back to FHIR

        // the severity code stored in openEHR is the atcode of the constraint, is not the SNOMED code
        // this is because the OPT was designed this way and the generated code from the client lib
        // generates a ENUM with those codes.

        // severity code
        text = ((AtiopathogeneseSchweregradDvcodedtext)compo.getDiagnose().getSchweregrad()).getSchweregradDefiningcode().getCode();

        // transforms atcodes in snomed codes
        switch (text)
        {
            case "at0049": // TODO: the enum classes need a method to create the Enum from the code value to avoid hardcoding
                text = "24484000";
                break;
            case "at0048":
                text = "6736007";
                break;
            case "at0047":
                text = "255604002";
                break;
        }

        coding = condition.getSeverity().addCoding();
        coding.setCode(text);
        coding.setSystem("http://snomed.info/sct");

        // diagnose code
        text = compo.getDiagnose().getDerDiagnoseDefiningcode().getCode();
        coding = condition.getCode().addCoding();
        coding.setCode(text);
        coding.setSystem("http://fhir.de/CodeSystem/dimdi/icd-10-gm");

        // date onset
        temporal = compo.getDiagnose().getDerErstdiagnoseValue();
        condition.getOnsetDateTimeType().setValue(Date.from(((OffsetDateTime)temporal).toInstant()));

        // body site
        text = compo.getDiagnose().getKorperstelleValueStructure();
        condition.addBodySite().addCoding().setDisplay(text);


        // FIXME: all FHIR resources need an ID, we are not storing specific IDs for the observations in openEHR,
        condition.setId(uid);

        return condition;
    }

    @Read()
    public Condition getConditionById(@IdParam IdType identifier)
    {
        Condition result = new Condition();

        //System.out.println(identifier.getIdPart());
        // identifier.getValue() is the Resource/theId

        Query<Record1<DiagnoseComposition>> query = Query.buildNativeQuery(
        "SELECT c "+
                "FROM EHR e CONTAINS COMPOSITION c "+
                "WHERE c/archetype_details/template_id/value = 'Diagnose' AND "+
                "c/uid/value = '"+ identifier.getIdPart() +"'",
            DiagnoseComposition.class
        );

        List<Record1<DiagnoseComposition>> results;

        try
        {
            results = service.getClient().aqlEndpoint().execute(query);

            String uid = identifier.getValue();
            DiagnoseComposition compo;

            if (results.size() == 0)
            {
                throw new ResourceNotFoundException("Resource not found"); // causes 404
            }

            compo = results.get(0).value1();

            result = getConditionFromCompo(compo, uid);
        }
        catch (ResourceNotFoundException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /*
        // ...populate...
        retVal.addIdentifier().setSystem("urn:mrns").setValue("12345");
        retVal.addName().setFamily("Smith").addGiven("Tester").addGiven("Q");
        */


        return result;
    }

    @Search
    public List<Condition> getAllConditions(
            @OptionalParam(name="_profile") UriParam profile,
            @RequiredParam(name=Condition.SP_SUBJECT+'.'+ Patient.SP_IDENTIFIER) TokenParam subject_id
    )
    {
        System.out.println("SEARCH CONDITION! profile: " + profile);
        List<Condition> result = new ArrayList<Condition>();

        // *************************************************************************************
        // We don't have a profile to ask for, we will try to map from a Diagnose composition
        // to a general condition.
        // *************************************************************************************

        Query<Record2<DiagnoseComposition, String>> query = Query.buildNativeQuery(
            "SELECT c, c/uid/value "+
                "FROM EHR e CONTAINS COMPOSITION c "+
                "WHERE c/archetype_details/template_id/value = 'Diagnose' AND "+
                "e/ehr_status/subject/external_ref/id/value = '"+ subject_id.getValue() +"'",
            DiagnoseComposition.class, String.class
        );

        List<Record2<DiagnoseComposition, String>> results;

        try
        {
            results = service.getClient().aqlEndpoint().execute(query);

            String uid;
            DiagnoseComposition compo;
            Condition condition;

            for (Record2<DiagnoseComposition, String> record: results)
            {
                compo = record.value1();
                uid = record.value2();

                condition = getConditionFromCompo(compo, uid);

                result.add(condition);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        // ****************************************************
        // test if we can get partial structures from the query
        // this is getting NPE from the server https://github.com/ehrbase/ehrbase/issues/270
        /*
        Query<Record2<DiagnoseEvaluation, String>> test_query = Query.buildNativeQuery(
                "SELECT eval, c/uid/value "+
                        "FROM EHR e CONTAINS COMPOSITION c CONTAINS eval[openEHR-EHR-EVALUATION.problem_diagnosis.v1] "+
                        "WHERE c/archetype_details/template_id/value = 'Diagnose' AND "+
                        "e/ehr_status/subject/external_ref/id/value = '"+ subject_id.getValue() +"'",
                DiagnoseEvaluation.class, String.class
        );

        List<Record2<DiagnoseEvaluation, String>> test_results = service.getClient().aqlEndpoint().execute(test_query);

        String uid;
        DiagnoseEvaluation eval;

        for (Record2<DiagnoseEvaluation, String> test_record: test_results)
        {
            eval = test_record.value1();
            uid = test_record.value2();

            System.out.println(eval.toString());
            System.out.println(uid);
        }
        */
        // ****************************************************

        return result;
    }

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createCondition(@ResourceParam Condition condition) throws Exception {

        // Patient/xxx => xxx
        String subjectIdValue = null;
        String ehr_id = null;
        UUID ehr_uid = null;
        try
        {
            subjectIdValue = condition.getSubject().getReference().split("/")[1];
            ehr_id = service.ehrIdBySubjectId(subjectIdValue);
            if (ehr_id != null)
            {
                ehr_uid = UUID.fromString(ehr_id);
            }
            else
            {
                logger.error("EHR for patient "+ subjectIdValue +" doesn't exists");
            }
        }
        catch (Exception e)
        {
            throw new Exception("Can't get the patient ID from the resource");
        }

        // *************************************************************************************
        // TODO: we don't have a profile for the diagnostic report to filter
        // *************************************************************************************

        try {
            System.out.println("----------------------------------------");
            // test map FHIR to openEHR
            DiagnoseComposition composition = F2ODiagnose.map(condition);
            //UUID ehr_id = service.createEhr(); // <<< reflections error!
            VersionUid versionUid = service.saveDiagnosis(ehr_uid, composition);
            System.out.println("Composition created with UID "+ versionUid.toString() +" for FHIR profile "+ Profile.OBSERVATION_LAB);

        } catch (Exception e) {
            e.printStackTrace();
        }

        condition.setId(new IdType(1L));
        condition.getMeta().setVersionId("1");
        condition.getMeta().setLastUpdatedElement(InstantType.withCurrentTime());

        return new MethodOutcome()
                .setCreated(true)
                .setResource(condition);
    }

    @Override
    public Class<Condition> getResourceType() {
        return Condition.class;
    }
}
