package org.ehrbase.fhirbridge.audit.internal;

import ca.uhn.fhir.model.api.annotation.DatatypeDef;
import org.hl7.fhir.r4.model.Coding;

@DatatypeDef(name = "Coding")
public class AuditEventType extends Coding {

    /**
     * Application Activity has taken place.
     */
    public static final AuditEventType APPLICATION_ACTIVITY = new AuditEventType(
            "http://dicom.nema.org/resources/ontology/DCM",
            "110100",
            "Application Activity");

    public AuditEventType(String system, String code, String display) {
        super(system, code, display);
    }

    /**
     * Execution of a RESTful operation as defined by FHIR.
     */
    public static final AuditEventType REST = new AuditEventType(
            "http://terminology.hl7.org/CodeSystem/audit-event-type",
            "rest",
            "RESTful Operation");
}
