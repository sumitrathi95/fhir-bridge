package org.ehrbase.fhirbridge.fhir.audit;

import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.rest.annotation.Count;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.AuditEvent;
import org.springframework.stereotype.Component;

/**
 * Resource provider for AuditEvent
 */
@Component
public class AuditEventResourceProvider implements IResourceProvider {

    private final AuditService auditService;

    public AuditEventResourceProvider(AuditService auditService) {
        this.auditService = auditService;
    }

//    @Search
//    public IBundleProvider search(
//            @OptionalParam(name = AuditEvent.SP_ACTION) TokenParam action,
//            @OptionalParam(name = AuditEvent.SP_OUTCOME) TokenParam outcome,
//            @Sort SortSpec sort,
//            @Count Integer count) {
//        SearchParameterMap params = new SearchParameterMap();
//        params.add(AuditEvent.SP_ACTION, action);
//        params.add(AuditEvent.SP_OUTCOME, outcome);
//        params.setSort(sort);
//        params.setCount(count);
//        return auditService.searchAuditEvent(params);
//    }

    @Read
    public AuditEvent read(@IdParam IIdType id) {
        return auditService.getAuditEvent(id);
    }

    @Override
    public Class<AuditEvent> getResourceType() {
        return AuditEvent.class;
    }
}
