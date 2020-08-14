package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.fhir.ProfileUtils;
import org.ehrbase.fhirbridge.mapping.FhirDiagnosticReportOpenehrLabResults;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.LaborbefundComposition;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Resource provider for DiagnosticReport
 */
@Component
public class DiagnosticReportResourceProvider extends AbstractResourceProvider {

    private final Logger logger = LoggerFactory.getLogger(DiagnosticReportResourceProvider.class);

    @Autowired
    public DiagnosticReportResourceProvider(FhirContext fhirContext, EhrbaseService service) {
        super(fhirContext, service);
    }

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createDiagnosticReport(@ResourceParam DiagnosticReport diagnosticReport) {

        checkProfiles(diagnosticReport);

        logger.info(">>>>>>>>>>>>>>>>>> DIAGNOSTIC REPORT {}", diagnosticReport.getIdentifier().get(0).getValue());
        logger.info(">>>>>>>>>>>>>>>>>> CONTAINED {}", diagnosticReport.getContained().size());
        logger.info(">>>>>>>>>>>>>>>>>> PATIENT {}", diagnosticReport.getSubject().getReference()); // Patient/XXXX
        /*
        System.out.println(">>>>>>>>>>>>>>>>>> PATIENT " + diagnosticReport.getSubject().getType()); // null
        System.out.println(">>>>>>>>>>>>>>>>>> PATIENT " + diagnosticReport.getSubject().getIdentifier().getValue()); // null
        System.out.println(">>>>>>>>>>>>>>>>>> PATIENT " + diagnosticReport.getSubject().getDisplay()); // null
        */

        // will throw exceptions and block the request if the patient doesn't have an EHR
        UUID ehrUid = getEhrUidForSubjectId(diagnosticReport.getSubject().getReference().split("/")[1]);


        if (ProfileUtils.hasProfile(diagnosticReport, Profile.DIAGNOSTIC_REPORT_LAB)) {
            try {
                LaborbefundComposition composition = FhirDiagnosticReportOpenehrLabResults.map(diagnosticReport);
                //UUID ehr_id = service.createEhr(); // <<< reflections error!
                VersionUid versionUid = service.saveLab(ehrUid, composition);
                logger.info("Composition created with UID {} for FHIR profile {}", versionUid, Profile.DIAGNOSTIC_REPORT_LAB);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        diagnosticReport.setId(new IdType(1L));
        diagnosticReport.getMeta().setVersionId("1");
        diagnosticReport.getMeta().setLastUpdatedElement(InstantType.withCurrentTime());

        return new MethodOutcome()
                .setCreated(true)
                .setResource(diagnosticReport);
    }

    @Override
    public Class<DiagnosticReport> getResourceType() {
        return DiagnosticReport.class;
    }

    @Override
    public boolean isDefaultProfileSupported() {
        return false;
    }
}
