package org.ehrbase.fhirbridge.fhir.validation;

import ca.uhn.fhir.context.FhirContext;
import org.hl7.fhir.r4.hapi.ctx.DefaultProfileValidationSupport;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.CodeType;

public class CustomDefaultProfileValidationSupport extends DefaultProfileValidationSupport {

    @Override
    public boolean isCodeSystemSupported(FhirContext theContext, String theSystem) {
        return false;
    }

    @Override
    public boolean isValueSetSupported(FhirContext theContext, String theValueSetUrl) {
        return false;
    }

    @Override
    public CodeValidationResult validateCode(FhirContext theContext, String theCodeSystem, String theCode, String theDisplay, String theValueSetUrl) {
        return new CodeValidationResult(new CodeSystem.ConceptDefinitionComponent(new CodeType(theCode)));
    }
}
