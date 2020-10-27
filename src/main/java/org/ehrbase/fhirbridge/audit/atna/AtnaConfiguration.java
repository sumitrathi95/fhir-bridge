package org.ehrbase.fhirbridge.audit.atna;

import org.openehealth.ipf.commons.audit.AuditContext;
import org.openehealth.ipf.commons.audit.DefaultAuditContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "fhir-bridge.audit.atna", name = "enabled")
public class AtnaConfiguration {

    @Bean
    public AuditContext auditContext(AtnaProperties properties) {
        DefaultAuditContext auditContext = new DefaultAuditContext();
        auditContext.setAuditRepositoryHostName(properties.getHostname());
        auditContext.setAuditRepositoryPort(properties.getPort());
        auditContext.setAuditEnabled(true);
        return auditContext;
    }

    @Bean
    public RestOperationAtnaInterceptor auditInterceptor(AuditContext auditContext) {
        return new RestOperationAtnaInterceptor(auditContext);
    }
}
