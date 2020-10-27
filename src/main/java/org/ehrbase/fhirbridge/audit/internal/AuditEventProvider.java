package org.ehrbase.fhirbridge.audit.internal;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.jpa.searchparam.SearchParameterMap;
import ca.uhn.fhir.rest.annotation.Count;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.annotation.Sort;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.param.DateParam;
import ca.uhn.fhir.rest.param.ReferenceAndListParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.AuditEvent;
import org.springframework.util.Assert;

/**
 * Resource provider for AuditEvent
 */
public class AuditEventProvider implements IResourceProvider {

    private final IFhirResourceDao<AuditEvent> auditEventDao;

    public AuditEventProvider(IFhirResourceDao<AuditEvent> auditEventDao) {
        Assert.notNull(auditEventDao, "AuditEventDao must not be null");
        this.auditEventDao = auditEventDao;
    }

    @Override
    public Class<AuditEvent> getResourceType() {
        return AuditEvent.class;
    }

    @Read
    public AuditEvent read(@IdParam IIdType id) {
        return auditEventDao.read(id);
    }

    @Search
    public IBundleProvider search(
            @OptionalParam(name = AuditEvent.SP_ACTION) TokenParam action,
            @OptionalParam(name = AuditEvent.SP_DATE) DateParam date,
            @OptionalParam(name = AuditEvent.SP_ENTITY) ReferenceAndListParam entity,
            @OptionalParam(name = AuditEvent.SP_OUTCOME) TokenParam outcome,
            @OptionalParam(name = AuditEvent.SP_TYPE) TokenParam type,
            @Sort SortSpec sort,
            @Count Integer count) {
        SearchParameterMap searchParameters = new SearchParameterMap();
        searchParameters.add(AuditEvent.SP_ACTION, action);
        searchParameters.add(AuditEvent.SP_DATE, date);
        searchParameters.add(AuditEvent.SP_ENTITY, entity);
        searchParameters.add(AuditEvent.SP_OUTCOME, outcome);
        searchParameters.add(AuditEvent.SP_TYPE, type);
        searchParameters.setSort(sort);
        searchParameters.setCount(count);
        return auditEventDao.search(searchParameters);
    }
}
