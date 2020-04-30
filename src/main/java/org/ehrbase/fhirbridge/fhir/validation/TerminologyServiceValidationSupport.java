package org.ehrbase.fhirbridge.fhir.validation;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.ConceptValidationOptions;
import ca.uhn.fhir.context.support.IValidationSupport;
import ca.uhn.fhir.context.support.ValueSetExpansionOptions;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import ca.uhn.fhir.util.ParametersUtil;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.common.hapi.validation.support.BaseValidationSupport;
import org.hl7.fhir.instance.model.api.IBaseParameters;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class TerminologyServiceValidationSupport extends BaseValidationSupport implements IValidationSupport {

    private final Logger logger = LoggerFactory.getLogger(TerminologyServiceValidationSupport.class);

    private final IGenericClient client;

    public TerminologyServiceValidationSupport(FhirContext fhirContext, String serverBaseUrl) {
        super(fhirContext);
        client = fhirContext.newRestfulGenericClient(serverBaseUrl);
    }

    @Override
    public ValueSetExpansionOutcome expandValueSet(IValidationSupport theRootValidationSupport, @Nullable ValueSetExpansionOptions theExpansionOptions, @Nonnull IBaseResource theValueSetToExpand) {
        return null;
    }

    @Override
    public List<IBaseResource> fetchAllConformanceResources() {
        return null;
    }

    @Override
    public IBaseResource fetchCodeSystem(String theSystem) {
        return null;
    }

    @Override
    public LookupCodeResult lookupCode(IValidationSupport theRootValidationSupport, String theSystem, String theCode) {
        return null;
    }

    @Override
    public boolean isValueSetSupported(IValidationSupport theRootValidationSupport, String theValueSetUrl) {
        return false;
    }

    @Override
    public boolean isCodeSystemSupported(IValidationSupport theRootValidationSupport, String theSystem) {
        Bundle response = client.search()
                .forResource(CodeSystem.class)
                .where(CodeSystem.URL.matches().value(theSystem))
                .returnBundle(Bundle.class)
                .execute();
        boolean result = (response.getTotal() > 0);
        logger.debug("Code system {} {} supported by the remote server ({})", theSystem, result ? "is" : "is not", client.getServerBase());
        return result;
    }

    @Override
    public IBaseResource fetchValueSet(String theValueSetUrl) {
        return null;
    }

    @Override
    public CodeValidationResult validateCode(IValidationSupport theRootValidationSupport, ConceptValidationOptions theOptions,
                                             String theCodeSystem, String theCode, String theDisplay, String theValueSetUrl) {

        IBaseParameters requestParams = ParametersUtil.newInstance(getFhirContext());
        ParametersUtil.addParameterToParametersUri(getFhirContext(), requestParams, "system", theCodeSystem);
        ParametersUtil.addParameterToParametersString(getFhirContext(), requestParams, "code", theCode);

        try {
            Parameters responseParams = client
                    .operation()
                    .onType(CodeSystem.class)
                    .named("lookup")
                    .withParameters(requestParams)
                    .returnResourceType(Parameters.class)
                    .execute();

            StringType name = (StringType) responseParams.getParameter("name");
            StringType version = (StringType) responseParams.getParameter("version");
            StringType display = (StringType) responseParams.getParameter("display");
            return new CodeValidationResult()
                    .setCode(theCode)
                    .setCodeSystemName(name.getValue())
                    .setCodeSystemVersion(version.getValue())
                    .setDisplay(display.getValue());
        } catch (ResourceNotFoundException e) {
            return new CodeValidationResult()
                    .setSeverity(IssueSeverity.ERROR)
                    .setMessage("Code not found in code system");
        }
    }

    @Override
    public CodeValidationResult validateCodeInValueSet(IValidationSupport theRootValidationSupport, ConceptValidationOptions theOptions,
                                                       String theCodeSystem, String theCode, String theDisplay, @Nonnull IBaseResource theValueSet) {
        if (StringUtils.isNotBlank(theCodeSystem)) {
            IBaseParameters requestParams = ParametersUtil.newInstance(getFhirContext());
            ParametersUtil.addParameterToParametersUri(getFhirContext(), requestParams, "system", theCodeSystem);
            ParametersUtil.addParameterToParametersString(getFhirContext(), requestParams, "code", theCode);
            ParametersUtil.addParameterToParameters(getFhirContext(), requestParams, "valueSet", theValueSet);

            Parameters responseParams = client
                    .operation()
                    .onType(ValueSet.class)
                    .named("validate-code")
                    .withParameters(requestParams)
                    .returnResourceType(Parameters.class)
                    .execute();

            BooleanType result = (BooleanType) responseParams.getParameter("result");
            if (result.booleanValue()) {
                StringType display = (StringType) responseParams.getParameter("display");
                return new CodeValidationResult()
                        .setCode(theCode)
                        .setDisplay(display.getValue());
            } else {
                StringType message = (StringType) responseParams.getParameter("message");
                return new CodeValidationResult()
                        .setSeverity(IssueSeverity.ERROR)
                        .setMessage(message.getValue());
            }
        }

        return null;
    }
}
