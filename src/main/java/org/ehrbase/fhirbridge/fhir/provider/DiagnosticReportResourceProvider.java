package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.fhir.ProfileUtils;
import org.ehrbase.fhirbridge.mapping.F2OLabReport;
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
        super(fhirContext);
        this.service = service;
    }

    private final EhrbaseService service;

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createDiagnosticReport(@ResourceParam DiagnosticReport diagnosticReport) throws Exception {
        checkProfiles(diagnosticReport);

        logger.info(">>>>>>>>>>>>>>>>>> DIAGNOSTIC REPORT "+ diagnosticReport.getIdentifier().get(0).getValue());
        logger.info(">>>>>>>>>>>>>>>>>> CONTAINED " + diagnosticReport.getContained().size());
        logger.info(">>>>>>>>>>>>>>>>>> PATIENT " + diagnosticReport.getSubject().getReference()); // Patient/XXXX
        /*
        System.out.println(">>>>>>>>>>>>>>>>>> PATIENT " + diagnosticReport.getSubject().getType()); // null
        System.out.println(">>>>>>>>>>>>>>>>>> PATIENT " + diagnosticReport.getSubject().getIdentifier().getValue()); // null
        System.out.println(">>>>>>>>>>>>>>>>>> PATIENT " + diagnosticReport.getSubject().getDisplay()); // null
        */

        // Patient/xxx => xxx
        String subjectIdValue = null;
        String ehr_id = null;
        UUID ehr_uid = null;
        try
        {
            subjectIdValue = diagnosticReport.getSubject().getReference().split("/")[1];
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


        //System.out.println(">>>>>>>>>>>>>>>>>> PATIENT " + subjectIdValue);

        if (ProfileUtils.hasProfile(diagnosticReport, Profile.DIAGNOSTIC_REPORT_LAB)) {
            try {
                LaborbefundComposition composition = F2OLabReport.map(diagnosticReport);
                //UUID ehr_id = service.createEhr(); // <<< reflections error!
                VersionUid versionUid = service.saveLab(ehr_uid, composition);
                logger.info("Composition created with UID " + versionUid.toString() + " for FHIR profile " + Profile.BODY_TEMP);
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
