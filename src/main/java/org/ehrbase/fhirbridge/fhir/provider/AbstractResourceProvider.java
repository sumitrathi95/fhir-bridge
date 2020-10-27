package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import ca.uhn.fhir.util.OperationOutcomeUtil;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.fhir.ProfileUtils;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.instance.model.api.IBaseResource;
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
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Abstract class for resource providers
 */
public abstract class AbstractResourceProvider<T extends IBaseResource> implements IResourceProvider, MessageSourceAware {

    protected final FhirContext fhirContext;

    protected final Class<T> type;

    protected final IFhirResourceDao<T> fhirResourceDao;

    protected final EhrbaseService ehrbaseService;

    protected MessageSourceAccessor messages;

    public AbstractResourceProvider(FhirContext fhirContext, Class<T> type, IFhirResourceDao<T> fhirResourceDao, EhrbaseService ehrbaseService) {
        this.fhirContext = fhirContext;
        this.type = type;
        this.fhirResourceDao = fhirResourceDao;
        this.ehrbaseService = ehrbaseService;
    }

    @Override
    public Class<T> getResourceType() {
        return type;
    }

    public boolean isDefaultProfileSupported() {
        return true;
    }

    public void checkProfiles(Resource resource) {
        ResourceType resourceType = resource.getResourceType();
        List<String> supportedProfiles = ProfileUtils.getSupportedProfiles(resourceType)
                .stream()
                .map(Profile::getUrl)
                .collect(Collectors.toList());
        List<String> profiles = resource.getMeta().getProfile().stream()
                .map(PrimitiveType::getValue)
                .collect(Collectors.toList());

        if (profiles.isEmpty() && !isDefaultProfileSupported()) {
            String message = messages.getMessage("error.defaultProfile", new Object[]{resourceType, supportedProfiles});
            OperationOutcome outcome = new OperationOutcome();
            OperationOutcomeUtil.addIssue(fhirContext, outcome, "fatal", message, resourceType.name(), "processing");
            throw new UnprocessableEntityException(fhirContext, outcome);
        } else if (Collections.disjoint(profiles, supportedProfiles)) {
            OperationOutcome outcome = new OperationOutcome();
            for (int i = 0; i < profiles.size(); i++) {
                String message = messages.getMessage("error.profileNotSupported", new Object[]{profiles.get(i), resourceType, supportedProfiles});
                String location = resourceType + ".meta.profile[" + i + "]";
                OperationOutcomeUtil.addIssue(fhirContext, outcome, "fatal", message, location, "processing");
            }
            throw new UnprocessableEntityException(fhirContext, outcome);
        }
    }

    public UUID getEhrUidForSubjectId(String subjectId) {
        try {
            String ehrId = ehrbaseService.ehrIdBySubjectId(subjectId);
            if (ehrId != null) {
                return UUID.fromString(ehrId); // validates the format
            } else {
                throw new ResourceNotFoundException("EHR for patient " + subjectId + " doesn't exists");
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UnprocessableEntityException("Couldn't get the EHR ID", e);
        }
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }
}
