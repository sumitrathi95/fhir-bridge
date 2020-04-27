package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;
import org.springframework.stereotype.Component;

/**
 * Resource provider for DiagnosticReport
 */
@Component
public class DiagnosticReportResourceProvider extends AbstractResourceProvider {

    public DiagnosticReportResourceProvider(FhirContext fhirContext) {
        super(fhirContext);
    }

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createDiagnosticReport(@ResourceParam DiagnosticReport diagnosticReport) {
        checkProfiles(diagnosticReport);

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
