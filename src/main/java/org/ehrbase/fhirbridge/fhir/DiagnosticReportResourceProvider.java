package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.jaxrs.server.AbstractJaxRsResourceProvider;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticReportResourceProvider extends AbstractJaxRsResourceProvider<DiagnosticReport> {

    public DiagnosticReportResourceProvider(FhirContext ctx) {
        super(ctx);
    }

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createDiagnosticReport(@ResourceParam DiagnosticReport diagnosticReport) {
        IdType id = new IdType(1L);

        diagnosticReport.setId(id);
        diagnosticReport.getMeta().setVersionId("1");
        diagnosticReport.getMeta().setLastUpdatedElement(InstantType.withCurrentTime());

        return new MethodOutcome(id)
                .setCreated(true)
                .setResource(diagnosticReport);
    }

    @Override
    public Class<DiagnosticReport> getResourceType() {
        return DiagnosticReport.class;
    }
}
