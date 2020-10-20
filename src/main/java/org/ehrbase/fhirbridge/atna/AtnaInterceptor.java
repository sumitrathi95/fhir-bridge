package org.ehrbase.fhirbridge.atna;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.servlet.ServletRequestDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Interceptor
public class AtnaInterceptor {

    private final Logger logger = LoggerFactory.getLogger(AtnaInterceptor.class);

//    private final AuditContext auditContext;
//
//    public AtnaInterceptor(AuditContext auditContext) {
//        this.auditContext = auditContext;
//    }

    @Hook(Pointcut.SERVER_PROCESSING_COMPLETED)
    public void logRequests(RequestDetails requestDetails, ServletRequestDetails servletRequestDetails) {
        logger.info("Request of type {} with request ID: {}", requestDetails.getOperation(), requestDetails.getRequestId());
    }
}
