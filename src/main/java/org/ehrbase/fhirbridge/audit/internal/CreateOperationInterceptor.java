package org.ehrbase.fhirbridge.audit.internal;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.BaseServerResponseException;
import org.hl7.fhir.r4.model.AuditEvent;
import org.springframework.util.Assert;

@Interceptor
public class CreateOperationInterceptor {

    private final InternalAuditContext internalAuditContext;

    public CreateOperationInterceptor(InternalAuditContext internalAuditContext) {
        Assert.notNull(internalAuditContext, "InternalAuditContext must not be null");
        this.internalAuditContext = internalAuditContext;
    }

    @Hook(Pointcut.SERVER_PROCESSING_COMPLETED_NORMALLY)
    public void handleRequest(RequestDetails requestDetails) {
        if (isCreateOperation(requestDetails)) {
            internalAuditContext.audit(
                    new AuditEventBuilder()
                            .outcome(AuditEvent.AuditEventOutcome._0)
                            .getAuditEvent());
        }
    }

    @Hook(Pointcut.SERVER_HANDLE_EXCEPTION)
    public void handleException(RequestDetails requestDetails, BaseServerResponseException exception) {
        if (isCreateOperation(requestDetails)) {
            internalAuditContext.audit(
                    new AuditEventBuilder()
                            .outcome(AuditEvent.AuditEventOutcome._12)
                            .getAuditEvent());
        }
    }

    private boolean isCreateOperation(RequestDetails requestDetails) {
        return requestDetails.getRestOperationType() == RestOperationTypeEnum.CREATE;
    }
}
