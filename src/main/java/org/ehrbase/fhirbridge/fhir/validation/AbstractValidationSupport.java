package org.ehrbase.fhirbridge.fhir.validation;

import ca.uhn.fhir.context.FhirContext;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.hapi.ctx.IValidationSupport;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.terminologies.ValueSetExpander;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Abstract class that implements IValidationSupport and provides default methods implementation
 */
@SuppressWarnings("java:S1168")
public class AbstractValidationSupport implements IValidationSupport {

    @Override
    public boolean isValueSetSupported(FhirContext theContext, String theValueSetUrl) {
        return false;
    }

    @Override
    public CodeValidationResult validateCodeInValueSet(FhirContext theContext, String theCodeSystem, String theCode, String theDisplay, @Nonnull IBaseResource theValueSet) {
        return null;
    }

    @Override
    public ValueSetExpander.ValueSetExpansionOutcome expandValueSet(FhirContext theContext, ValueSet.ConceptSetComponent theInclude) {
        return null;
    }

    @Override
    public List<StructureDefinition> fetchAllStructureDefinitions(FhirContext theContext) {
        return null;
    }

    @Override
    public CodeSystem fetchCodeSystem(FhirContext theContext, String uri) {
        return null;
    }

    @Override
    public ValueSet fetchValueSet(FhirContext theContext, String uri) {
        return null;
    }

    @Override
    public <T extends IBaseResource> T fetchResource(FhirContext theContext, Class<T> theClass, String theUri) {
        return null;
    }

    @Override
    public StructureDefinition fetchStructureDefinition(FhirContext theCtx, String theUrl) {
        return null;
    }

    @Override
    public boolean isCodeSystemSupported(FhirContext theContext, String theSystem) {
        return false;
    }

    @Override
    public StructureDefinition generateSnapshot(StructureDefinition theInput, String theUrl, String theWebUrl, String theProfileName) {
        return null;
    }

    @Override
    public List<IBaseResource> fetchAllConformanceResources(FhirContext theContext) {
        return null;
    }

    @Override
    public CodeValidationResult validateCode(FhirContext theContext, String theCodeSystem, String theCode, String theDisplay, String theValueSetUrl) {
        return null;
    }

    @Override
    public LookupCodeResult lookupCode(FhirContext theContext, String theSystem, String theCode) {
        return null;
    }
}
