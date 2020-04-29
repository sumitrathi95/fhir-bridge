package org.ehrbase.fhirbridge.fhir.validation;

import ca.uhn.fhir.context.FhirContext;

public class TerminologyValidationSupport extends AbstractValidationSupport {

    private final FhirContext context;

    public TerminologyValidationSupport(FhirContext context) {
        this.context = context;
    }

    @Override
    public boolean isValueSetSupported(FhirContext theContext, String theValueSetUrl) {
        return true;
    }

    @Override
    public boolean isCodeSystemSupported(FhirContext theContext, String theSystem) {
        return true;
    }
}
