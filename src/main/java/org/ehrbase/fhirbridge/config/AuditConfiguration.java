package org.ehrbase.fhirbridge.config;

import org.openehealth.ipf.commons.audit.AuditContext;
import org.openehealth.ipf.commons.audit.DefaultAuditContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditConfiguration {

    @Bean
    public AuditContext auditContext() {
        DefaultAuditContext auditContext = new DefaultAuditContext();
        auditContext.setAuditRepositoryHost("localhost");
        auditContext.setAuditRepositoryPort(9876);
        return auditContext;
    }
}
