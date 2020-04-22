package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.jaxrs.server.AbstractJaxRsResourceProvider;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.stereotype.Component;

@Component
public class ObservationResourceProvider extends AbstractJaxRsResourceProvider<Observation> {

    public ObservationResourceProvider(FhirContext ctx) {
        super(ctx);
    }

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createObservation(@ResourceParam Observation observation) {
        IdType id = new IdType(1L);
        observation.setId(id);

        MethodOutcome methodOutcome = new MethodOutcome(id);
        methodOutcome.setResource(observation);
        return methodOutcome;
    }

    @Override
    public Class<Observation> getResourceType() {
        return Observation.class;
    }
}
