package org.ehrbase.fhirbridge.audit.internal;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.BaseServerResponseException;
import org.hl7.fhir.r4.model.AuditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

@Interceptor
public class RestOperationCreateAuditInterceptor implements MessageSourceAware {

    private final Logger logger = LoggerFactory.getLogger(RestOperationCreateAuditInterceptor.class);

    private final InternalAuditContext internalAuditContext;

    private MessageSourceAccessor messages;

    public RestOperationCreateAuditInterceptor(InternalAuditContext internalAuditContext) {
        Assert.notNull(internalAuditContext, "InternalAuditContext must not be null");
        this.internalAuditContext = internalAuditContext;
    }

    @Hook(Pointcut.SERVER_PROCESSING_COMPLETED_NORMALLY)
    public void handleRequest(RequestDetails requestDetails) {
        if (isCreateOperation(requestDetails)) {
            audit(AuditEvent.AuditEventOutcome._0, messages.getMessage("audit.resourceCreated",
                    new String[]{requestDetails.getResourceName()}), requestDetails);
        }
    }

    @Hook(Pointcut.SERVER_HANDLE_EXCEPTION)
    public void handleException(RequestDetails requestDetails, BaseServerResponseException exception) {
        if (isCreateOperation(requestDetails)) {
            audit(AuditEvent.AuditEventOutcome._12, exception.getMessage(), requestDetails);
        }
    }

    private boolean isCreateOperation(RequestDetails requestDetails) {
        return requestDetails.getRestOperationType() == RestOperationTypeEnum.CREATE;
    }

    private void audit(AuditEvent.AuditEventOutcome outcome, String outcomeDesc, RequestDetails requestDetails) {
        AuditEventBuilder builder = new AuditEventBuilder(AuditEventType.REST, internalAuditContext.getSource())
                .setAction(AuditEvent.AuditEventAction.C)
                .setOutcome(outcome)
                .setOutcomeDesc(outcomeDesc);
        if (requestDetails.getResource() != null) {
            builder.addEntity(requestDetails.getResource());
        }
        AuditEvent auditEvent = builder.getAuditEvent();
        internalAuditContext.audit(auditEvent);
        logger.debug("Created AuditEvent: {}", auditEvent);
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        messages = new MessageSourceAccessor(messageSource);
    }
}
