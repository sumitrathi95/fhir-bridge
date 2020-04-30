package org.ehrbase.fhirbridge.fhir.validation;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNull;

public class TerminologyServiceValidationSupport implements BaseValidationSupport, MessageSourceAware {

    private final Logger logger = LoggerFactory.getLogger(TerminologyServiceValidationSupport.class);

    private final IGenericClient client;

    private MessageSourceAccessor messages;

    public TerminologyServiceValidationSupport(IGenericClient client) {
        this.client = client;
    }

    @Override
    public boolean isCodeSystemSupported(FhirContext theContext, String theSystem) {
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
    public CodeValidationResult validateCode(FhirContext theContext, String theCodeSystem, String theCode, String theDisplay, String theValueSetUrl) {
        if (StringUtils.isNotBlank(theValueSetUrl)) {
            return null;
        }
        return lookup(theCodeSystem, theCode);
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
            return new CodeValidationResult(ValidationMessage.IssueSeverity.ERROR, messages.getMessage("error.codeNotFound", new Object[]{code, system}));
        }
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
