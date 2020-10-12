package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.audit.AuditService;
import org.ehrbase.fhirbridge.mapping.FhirProcedureOpenehrProcedure;
import org.ehrbase.fhirbridge.opt.prozedurcomposition.ProzedurComposition;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;
import org.hl7.fhir.r4.model.Procedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Resource provider for Condition
 */
@Component
public class ProcedureResourceProvider extends AbstractResourceProvider {

    private final Logger logger = LoggerFactory.getLogger(ProcedureResourceProvider.class);

    private final IFhirResourceDao<Procedure> procedureDao;

    public ProcedureResourceProvider(FhirContext fhirContext, EhrbaseService ehrbaseService, AuditService auditService,
                                     IFhirResourceDao<Procedure> procedureDao) {
        super(fhirContext, ehrbaseService, auditService);
        this.procedureDao = procedureDao;
    }

    /*
    @Read()
    @SuppressWarnings("unused")
    public Procedure getProcedureById(@IdParam IdType identifier)
    {
        Procedure result = new Procedure();

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

            DiagnoseComposition compo;

            if (results.isEmpty())
            {
                throw new ResourceNotFoundException("Resource not found"); // causes 404
            }

            compo = results.get(0).value1();

            // COMPOSITION => FHIR Condition
            result = FhirConditionOpenehrDiagnose.map(compo);
        }
        catch (ResourceNotFoundException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Search
    @SuppressWarnings("unused")
    public List<Procedure> getAllProcedures(
            @OptionalParam(name="_profile") UriParam profile,
            @RequiredParam(name=Patient.SP_IDENTIFIER) TokenParam subjectId,
            @OptionalParam(name=Condition.SP_RECORDED_DATE) DateRangeParam dateRange,
            @OptionalParam(name=Condition.SP_CODE) TokenParam code
    )
    {
        logger.info("SEARCH CONDITION! subjectId: {}", subjectId);
        List<Condition> result = new ArrayList<>();

        // *************************************************************************************
        // We don't have a profile to ask for, we will try to map from a Diagnose composition
        // to a general condition.
        // *************************************************************************************

        String aql =
            "SELECT c "+
            "FROM EHR e CONTAINS COMPOSITION c "+
            "WHERE c/archetype_details/template_id/value = 'Diagnose' AND "+
            "e/ehr_status/subject/external_ref/id/value = '"+ subjectId.getValue() +"'";

        // filters
        if (dateRange != null)
        {
            // with date range we can also receive just one bound
            if (dateRange.getLowerBound() != null)
                aql += " AND '"+ dateRange.getLowerBound().getValueAsString() + "' <= c/context/start_time/value";

            if (dateRange.getUpperBound() != null)
                aql += " AND c/context/start_time/value <= '"+ dateRange.getUpperBound().getValueAsString() +"'";
        }

        if (code != null)
        {
            logger.info("code {}", code.getValue());
            String openEHRDiagnosis;
            switch (code.getValue())
            {
                case "B97.2":
                    openEHRDiagnosis = DerDiagnoseDefiningcode.B972.getCode();
                break;
                case "U07.1":
                    openEHRDiagnosis = DerDiagnoseDefiningcode.U071.getCode();
                break;
                case "U07.2":
                    openEHRDiagnosis = DerDiagnoseDefiningcode.U072.getCode();
                break;
                case "B34.2":
                    openEHRDiagnosis = DerDiagnoseDefiningcode.B342.getCode();
                break;
                default:
                    throw new IllegalStateException("Unexpected value: " + code.getValue());
            }

            aql += " AND eval/data[at0001]/items[at0002]/value/defining_code/code_string = '"+ openEHRDiagnosis +"'";
            //aql += " WHERE eval/data[at0001]/items[at0002]/value/defining_code/code_string = '"+ openEHRDiagnosis +"'";
        }


        // execute the query
        Query<Record1<DiagnoseComposition>> query = Query.buildNativeQuery(aql, DiagnoseComposition.class);

        List<Record1<DiagnoseComposition>> results;

        try
        {
            results = service.getClient().aqlEndpoint().execute(query);

            DiagnoseComposition compo;
            Condition condition;

            for (Record1<DiagnoseComposition> record: results)
            {
                compo = record.value1();

                logger.info("compo.uid is {}", compo.getVersionUid());

                // COMPOSITION => FHIR Condition
                condition = FhirConditionOpenehrDiagnose.map(compo);

                result.add(condition);
            }
        }
        catch (Exception e)
        {
            throw new InternalErrorException("There was a problem retrieving the results", e);
        }

        return result;
    }
*/
    @Create
    public MethodOutcome createProcedure(@ResourceParam Procedure procedure) {
        procedureDao.create(procedure);
        auditService.registerCreateResourceSuccessEvent(procedure);

        // will throw exceptions and block the request if the patient doesn't have an EHR
        UUID ehrUid = getEhrUidForSubjectId(procedure.getSubject().getReference().split(":")[2]);

        // *************************************************************************************
        // TODO: we don't have a profile for the diagnostic report to filter
        // *************************************************************************************

        try {
            // FHIR Condition => COMPOSITION
            ProzedurComposition composition = FhirProcedureOpenehrProcedure.map(procedure);
            VersionUid versionUid = ehrbaseService.saveProcedure(ehrUid, composition);
            logger.info("Composition created with UID {}", versionUid);
            auditService.registerMapResourceEvent(AuditEvent.AuditEventOutcome._0, "Success", procedure);
        } catch (Exception e) {
            auditService.registerMapResourceEvent(AuditEvent.AuditEventOutcome._8, e.getMessage(), procedure);
            throw new UnprocessableEntityException("There was a problem saving the composition" + e.getMessage(), e);
        }

        return new MethodOutcome()
                .setCreated(true)
                .setResource(procedure);
    }

    @Override
    public Class<Procedure> getResourceType() {
        return Procedure.class;
    }
}
