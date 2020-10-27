package org.ehrbase.fhirbridge.audit.internal;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Reference;

import java.util.Date;

public class AuditEventBuilder {

    private final AuditEvent auditEvent;

    public AuditEventBuilder(Coding type, AuditEvent.AuditEventSourceComponent source) {
        auditEvent = new AuditEvent()
                .setType(type)
                .setRecorded(new Date())
                .setSource(source);
    }

    public AuditEventBuilder setAction(AuditEvent.AuditEventAction action) {
        auditEvent.setAction(action);
        return this;
    }

    public AuditEventBuilder setOutcome(AuditEvent.AuditEventOutcome outcome) {
        auditEvent.setOutcome(outcome);
        return this;
    }

    public AuditEventBuilder setOutcomeDesc(String outcomeDesc) {
        auditEvent.setOutcomeDesc(outcomeDesc);
        return this;
    }

    public AuditEventBuilder addEntity(IBaseResource resource) {
        auditEvent.addEntity(new AuditEvent.AuditEventEntityComponent()
                .setWhat(new Reference(resource.getIdElement())));
        return this;
    }

    public AuditEvent getAuditEvent() {
        return auditEvent;
    }
}
