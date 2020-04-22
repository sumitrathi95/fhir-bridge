package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.jaxrs.server.AbstractJaxRsResourceProvider;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.validation.FhirValidator;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.IdType;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticReportResourceProvider extends AbstractJaxRsResourceProvider<DiagnosticReport> {

    private final FhirValidator validator;

    public DiagnosticReportResourceProvider(FhirContext ctx, FhirValidator validator) {
        super(ctx);
        this.validator = validator;
    }

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createDiagnosticReport(@ResourceParam DiagnosticReport diagnosticReport) {
//        ValidationResult result = validator.validateWithResult(diagnosticReport);
//        if (!result.isSuccessful()) {
//            throw new ValidationFailureException();
//        }

        IdType id = new IdType(1L);
        diagnosticReport.setId(id);

        MethodOutcome methodOutcome = new MethodOutcome(id);
        methodOutcome.setResource(diagnosticReport);
        return methodOutcome;
    }

    @Override
    public Class<DiagnosticReport> getResourceType() {
        return DiagnosticReport.class;
    }
}
