package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;
import org.springframework.stereotype.Component;

/**
 * Resource provider for Condition
 */
@Component
public class ConditionResourceProvider extends AbstractResourceProvider {

    public ConditionResourceProvider(FhirContext fhirContext) {
        super(fhirContext);
    }

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createCondition(@ResourceParam Condition condition) {

        condition.setId(new IdType(1L));
        condition.getMeta().setVersionId("1");
        condition.getMeta().setLastUpdatedElement(InstantType.withCurrentTime());

        return new MethodOutcome()
                .setCreated(true)
                .setResource(condition);
    }

    @Override
    public Class<Condition> getResourceType() {
        return Condition.class;
    }
}
