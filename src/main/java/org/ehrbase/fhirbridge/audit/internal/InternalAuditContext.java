package org.ehrbase.fhirbridge.audit.internal;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.hl7.fhir.r4.model.AuditEvent;

public class InternalAuditContext {

    private final IFhirResourceDao<AuditEvent> auditEventDao;

    public InternalAuditContext(IFhirResourceDao<AuditEvent> auditEventDao) {
        this.auditEventDao = auditEventDao;
    }

    public void audit(AuditEvent auditEvent) {
        auditEventDao.create(auditEvent);
    }
}
