package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import ca.uhn.fhir.util.OperationOutcomeUtil;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.fhir.ProfileUtils;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.PrimitiveType;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract base class for resource providers
 */
public abstract class AbstractResourceProvider implements IResourceProvider, MessageSourceAware {

    private final FhirContext context;

    private MessageSourceAccessor messages;

    public AbstractResourceProvider(FhirContext context) {
        this.context = context;
    }

    public boolean isDefaultProfileSupported() {
        return true;
    }

    public void checkProfiles(Resource resource) {
        ResourceType type = resource.getResourceType();
        List<String> supportedProfiles = ProfileUtils.getSupportedProfiles(type).stream()
                .map(Profile::getUrl)
                .collect(Collectors.toList());
        List<String> profiles = resource.getMeta().getProfile().stream()
                .map(PrimitiveType::getValue)
                .collect(Collectors.toList());

        if (profiles.isEmpty() && !isDefaultProfileSupported()) {
            String message = messages.getMessage("error.defaultProfile", new Object[]{type, supportedProfiles});
            OperationOutcome outcome = new OperationOutcome();
            OperationOutcomeUtil.addIssue(context, outcome, "fatal", message, type.name(), "processing");
            throw new UnprocessableEntityException(context, outcome);
        } else if (Collections.disjoint(profiles, supportedProfiles)) {
            OperationOutcome outcome = new OperationOutcome();
            for (int i = 0; i < profiles.size(); i++) {
                String message = messages.getMessage("error.profileNotSupported", new Object[]{profiles.get(i), type, supportedProfiles});
                String location = type + ".meta.profile[" + i + "]";
                OperationOutcomeUtil.addIssue(context, outcome, "fatal", message, location, "processing");
            }
            throw new UnprocessableEntityException(context, outcome);
        }
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
