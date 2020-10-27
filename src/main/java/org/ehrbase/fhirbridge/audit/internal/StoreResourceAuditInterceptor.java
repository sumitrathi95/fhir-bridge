package org.ehrbase.fhirbridge.audit.internal;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.AuditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

@Interceptor
public class StoreResourceAuditInterceptor implements MessageSourceAware {

    private final Logger logger = LoggerFactory.getLogger(StoreResourceAuditInterceptor.class);

    private final InternalAuditContext internalAuditContext;

    private MessageSourceAccessor messages;

    public StoreResourceAuditInterceptor(InternalAuditContext internalAuditContext) {
        Assert.notNull(internalAuditContext, "InternalAuditContext must not be null");
        this.internalAuditContext = internalAuditContext;
    }

    @Hook(Pointcut.STORAGE_PRECOMMIT_RESOURCE_CREATED)
    public void handleRequest(IBaseResource resource, RequestDetails requestDetails) {
        AuditEvent auditEvent = new AuditEventBuilder(AuditEventType.APPLICATION_ACTIVITY, internalAuditContext.getSource())
                .setAction(AuditEvent.AuditEventAction.C)
                .setOutcome(AuditEvent.AuditEventOutcome._0)
                .setOutcomeDesc(messages.getMessage("audit.resourceSaved", new String[]{requestDetails.getResourceName()}))
                .addEntity(resource)
                .getAuditEvent();
        internalAuditContext.audit(auditEvent);
        logger.debug("Created AuditEvent: {}", auditEvent);
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        messages = new MessageSourceAccessor(messageSource);
    }
}
