package org.ehrbase.fhirbridge.audit.internal;

import org.hl7.fhir.r4.model.AuditEvent;

public class AuditEventBuilder {

    private final AuditEvent auditEvent;

    public AuditEventBuilder() {
        auditEvent = new AuditEvent();
    }

    public AuditEventBuilder outcome(AuditEvent.AuditEventOutcome outcome) {
        auditEvent.setOutcome(outcome);
        return this;
    }

    public AuditEvent getAuditEvent() {
        return auditEvent;
    }
}
