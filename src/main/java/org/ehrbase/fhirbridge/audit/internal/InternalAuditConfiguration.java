package org.ehrbase.fhirbridge.audit.internal;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.hl7.fhir.r4.model.AuditEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "fhir-bridge.audit.internal", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(InternalAuditProperties.class)
public class InternalAuditConfiguration {

    @Bean
    public InternalAuditContext internalAuditContext(IFhirResourceDao<AuditEvent> auditEventDao) {
        return new InternalAuditContext(auditEventDao);
    }

    @Bean
    public AuditEventProvider auditEventProvider(IFhirResourceDao<AuditEvent> auditEventDao) {
        return new AuditEventProvider(auditEventDao);
    }

    @Bean
    public CreateOperationInterceptor createOperationInternalAuditInterceptor(InternalAuditContext internalAuditContext) {
        return new CreateOperationInterceptor(internalAuditContext);
    }
}
