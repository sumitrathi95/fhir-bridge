package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.mapping.F2ODiagnose;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.DiagnoseComposition;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Resource provider for Condition
 */
@Component
public class ConditionResourceProvider extends AbstractResourceProvider {

    private final Logger logger = LoggerFactory.getLogger(ConditionResourceProvider.class);

    public ConditionResourceProvider(FhirContext fhirContext, EhrbaseService service) {
        super(fhirContext);
        this.service = service;
    }

    private final EhrbaseService service;

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

        // TODO: we don't have a profile for the diagnostic report to filter
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
