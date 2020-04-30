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
 * Base ValidationSupport interface that provides default methods
 */
@SuppressWarnings("java:S1168")
public interface BaseValidationSupport extends IValidationSupport {

    @Override
    default ValueSetExpander.ValueSetExpansionOutcome expandValueSet(FhirContext theContext, ValueSet.ConceptSetComponent theInclude) {
        return null;
    }

    @Override
    default List<StructureDefinition> fetchAllStructureDefinitions(FhirContext theContext) {
        return null;
    }

    @Override
    default CodeSystem fetchCodeSystem(FhirContext theContext, String uri) {
        return null;
    }

    @Override
    default ValueSet fetchValueSet(FhirContext theContext, String uri) {
        return null;
    }

    @Override
    default <T extends IBaseResource> T fetchResource(FhirContext theContext, Class<T> theClass, String theUri) {
        return null;
    }

    @Override
    default StructureDefinition fetchStructureDefinition(FhirContext theCtx, String theUrl) {
        return null;
    }

    @Override
    default boolean isCodeSystemSupported(FhirContext theContext, String theSystem) {
        return false;
    }

    @Override
    default StructureDefinition generateSnapshot(StructureDefinition theInput, String theUrl, String theWebUrl, String theProfileName) {
        return null;
    }

    @Override
    default List<IBaseResource> fetchAllConformanceResources(FhirContext theContext) {
        return null;
    }

    @Override
    default CodeValidationResult validateCode(FhirContext theContext, String theCodeSystem, String theCode, String theDisplay, String theValueSetUrl) {
        return null;
    }

    @Override
    default LookupCodeResult lookupCode(FhirContext theContext, String theSystem, String theCode) {
        return null;
    }

    @Override
    default boolean isValueSetSupported(FhirContext theContext, String theValueSetUrl) {
        return false;
    }

    @Override
    default CodeValidationResult validateCodeInValueSet(FhirContext theContext, String theCodeSystem, String theCode, String theDisplay, @Nonnull IBaseResource theValueSet) {
        return null;
    }
}
