package org.ehrbase.fhirbridge.audit;

import org.ehrbase.fhirbridge.audit.atna.AtnaProperties;
import org.ehrbase.fhirbridge.audit.internal.InternalAuditProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({AtnaProperties.class, InternalAuditProperties.class})
public class AuditConfiguration {
}
