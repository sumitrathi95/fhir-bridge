package org.ehrbase.fhirbridge.audit.atna;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.BaseServerResponseException;
import org.openehealth.ipf.commons.audit.AuditContext;
import org.openehealth.ipf.commons.audit.codes.EventOutcomeIndicator;
import org.springframework.util.Assert;

@Interceptor
public class RestOperationAtnaInterceptor {

    private final AuditContext auditContext;

    public RestOperationAtnaInterceptor(AuditContext auditContext) {
        Assert.notNull(auditContext, "AuditContext must not be null");
        this.auditContext = auditContext;
    }

    @Hook(Pointcut.SERVER_PROCESSING_COMPLETED_NORMALLY)
    public void handleRequest(RequestDetails requestDetails) {
        auditContext.audit(
                new RestOperationAuditMessageBuilder(
                        EventOutcomeIndicator.Success,
                        "Success",
                        requestDetails.getRestOperationType())
                        .getMessage());
    }

    @Hook(Pointcut.SERVER_HANDLE_EXCEPTION)
    public void handleException(RequestDetails requestDetails, BaseServerResponseException exception) {
        auditContext.audit(
                new RestOperationAuditMessageBuilder(
                        EventOutcomeIndicator.MajorFailure,
                        "Error",
                        requestDetails.getRestOperationType())
                        .getMessage());
    }
}
