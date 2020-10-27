package org.ehrbase.fhirbridge.audit.atna;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.BaseServerResponseException;
import org.openehealth.ipf.commons.audit.AuditContext;
import org.openehealth.ipf.commons.audit.codes.EventOutcomeIndicator;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

@Interceptor
public class RestOperationAtnaInterceptor implements MessageSourceAware {

    private final AuditContext auditContext;

    private MessageSourceAccessor messages;

    public RestOperationAtnaInterceptor(AuditContext auditContext) {
        Assert.notNull(auditContext, "AuditContext must not be null");
        this.auditContext = auditContext;
    }

    @Hook(Pointcut.SERVER_PROCESSING_COMPLETED_NORMALLY)
    public void handleRequest(RequestDetails requestDetails) {
        auditContext.audit(
                new RestOperationAuditMessageBuilder(
                        EventOutcomeIndicator.Success,
                        messages.getMessage("audit.restOperationExecuted", new Object[]{requestDetails.getRestOperationType()}),
                        requestDetails.getRestOperationType())
                        .getMessage());
    }

    @Hook(Pointcut.SERVER_HANDLE_EXCEPTION)
    public void handleException(RequestDetails requestDetails, BaseServerResponseException exception) {
        auditContext.audit(
                new RestOperationAuditMessageBuilder(
                        EventOutcomeIndicator.MajorFailure,
                        exception.getMessage(),
                        requestDetails.getRestOperationType())
                        .getMessage());
    }

    @Override
    public void setMessageSource(@NonNull MessageSource messageSource) {
        messages = new MessageSourceAccessor(messageSource);
    }
}
