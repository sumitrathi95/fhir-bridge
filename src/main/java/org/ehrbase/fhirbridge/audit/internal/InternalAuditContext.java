package org.ehrbase.fhirbridge.audit.internal;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

public class InternalAuditContext {

    @Value("${spring.application.name}")
    private String sourceDisplay;

    private final IFhirResourceDao<AuditEvent> auditEventDao;

    public InternalAuditContext(IFhirResourceDao<AuditEvent> auditEventDao) {
        Assert.notNull(auditEventDao, "AuditEventDao must not be null");
        this.auditEventDao = auditEventDao;
    }

    public void audit(AuditEvent auditEvent) {
        auditEventDao.create(auditEvent);
    }

    public AuditEvent.AuditEventSourceComponent getSource() {
        return new AuditEvent.AuditEventSourceComponent()
                .setObserver(new Reference()
                        .setDisplay(sourceDisplay));
    }
}
