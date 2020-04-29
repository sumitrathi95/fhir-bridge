package org.ehrbase.fhirbridge.fhir.validation;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.Constants;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.utilities.validation.ValidationMessage;

public class TerminologyServerValidationSupport extends AbstractValidationSupport {

    private final FhirContext context;

    private final IGenericClient client;

    public TerminologyServerValidationSupport(FhirContext context, String serverBaseUrl) {
        this.context = context;
        client = context.getRestfulClientFactory().newGenericClient(serverBaseUrl);
    }

    @Override
    public boolean isValueSetSupported(FhirContext theContext, String theValueSetUrl) {
        Bundle response = client.search()
                .forResource(ValueSet.class)
                .where(ValueSet.URL.matches().value(theValueSetUrl))
                .returnBundle(Bundle.class)
                .execute();
        return (response.getTotal() > 0);
    }

    @Override
    public boolean isCodeSystemSupported(FhirContext theContext, String theSystem) {
        Bundle response = client.search()
                .forResource(CodeSystem.class)
                .where(CodeSystem.URL.matches().value(theSystem))
                .returnBundle(Bundle.class)
                .execute();
        return (response.getTotal() > 0);
    }

    @Override
    public CodeValidationResult validateCode(FhirContext theContext, String theCodeSystem, String theCode, String theDisplay, String theValueSetUrl) {
        if (StringUtils.isBlank(theCode) || StringUtils.equals(theCodeSystem, Constants.CODESYSTEM_VALIDATE_NOT_NEEDED)) {
            return null;
        }

        CodeValidationResult result;
        if (StringUtils.isNotBlank(theValueSetUrl)) {
            result = validateCode(theValueSetUrl, theCodeSystem, theCode);
        } else {
            result = lookup(theCodeSystem, theCode);
        }

        return result;
    }

    public CodeValidationResult validateCode(String valueSet, String system, String code) {
        Parameters reqParams = new Parameters();
        reqParams.addParameter("system", new UriType(system));
        reqParams.addParameter("code", code);
        reqParams.addParameter("url", new UriType(valueSet));

        Parameters respParams = client
                .operation()
                .onType(ValueSet.class)
                .named("validate-code")
                .withParameters(reqParams)
                .returnResourceType(Parameters.class)
                .execute();

        BooleanType result = (BooleanType) respParams.getParameter("result");
        if (result.booleanValue()) {
            CodeSystem.ConceptDefinitionComponent definition = new CodeSystem.ConceptDefinitionComponent(new CodeType(code));
            StringType display = (StringType) respParams.getParameter("display");
            return new CodeValidationResult(null, null, definition, display.getValue());
        } else {
            StringType message = (StringType) respParams.getParameter("message");
            return new CodeValidationResult(ValidationMessage.IssueSeverity.WARNING, message.getValue());
        }
    }

    public CodeValidationResult lookup(String system, String code) {
        Parameters reqParams = new Parameters();
        reqParams.addParameter("system", new UriType(system));
        reqParams.addParameter("code", code);

        try {
            Parameters respParams = client
                    .operation()
                    .onType(CodeSystem.class)
                    .named("lookup")
                    .withParameters(reqParams)
                    .returnResourceType(Parameters.class)
                    .execute();

            CodeSystem.ConceptDefinitionComponent definition = new CodeSystem.ConceptDefinitionComponent(new CodeType(code));
            StringType display = (StringType) respParams.getParameter("display");
            StringType name = (StringType) respParams.getParameter("name");
            StringType version = (StringType) respParams.getParameter("version");
            CodeValidationResult result = new CodeValidationResult(null, null, definition, display.getValue());
            result.setCodeSystemName(name.getValue());
            result.setCodeSystemVersion(version.getValue());
            return result;
        } catch (ResourceNotFoundException e) {
            return new CodeValidationResult(ValidationMessage.IssueSeverity.ERROR, e.getMessage());
        }
    }
}
