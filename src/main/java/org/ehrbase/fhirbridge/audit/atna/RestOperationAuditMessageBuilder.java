package org.ehrbase.fhirbridge.audit.atna;

import ca.uhn.fhir.rest.api.RestOperationTypeEnum;
import org.openehealth.ipf.commons.audit.codes.EventActionCode;
import org.openehealth.ipf.commons.audit.codes.EventOutcomeIndicator;
import org.openehealth.ipf.commons.audit.event.BaseAuditMessageBuilder;
import org.openehealth.ipf.commons.audit.types.EventId;
import org.openehealth.ipf.commons.audit.types.EventType;
import org.openehealth.ipf.commons.audit.types.PurposeOfUse;

public class RestOperationAuditMessageBuilder extends BaseAuditMessageBuilder<RestOperationAuditMessageBuilder> {

    public RestOperationAuditMessageBuilder(EventOutcomeIndicator eventOutcomeIndicator, String eventOutcomeDescription,
                                            RestOperationTypeEnum restOperationType) {
        setEventIdentification(eventOutcomeIndicator,
                eventOutcomeDescription,
                resolveEventActionCode(restOperationType),
                EventId.of("rest", "Audit Event ID", "RESTful Operation"),
                EventType.of(restOperationType.getCode(), "FHIR Restful Interactions", restOperationType.getCode()),
                (PurposeOfUse[]) null);
    }

    private EventActionCode resolveEventActionCode(RestOperationTypeEnum type) {
        switch (type) {
            case CREATE:
                return EventActionCode.Create;
            case READ:
            case VREAD:
                return EventActionCode.Read;
            case UPDATE:
            case PATCH:
                return EventActionCode.Update;
            case DELETE:
                return EventActionCode.Delete;
            default:
                return EventActionCode.Execute;
        }
    }
}
