package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.ehrbase.fhirbridge.mapping.FhirToOpenehr;
import org.ehrbase.laborbefund.laborbefundcomposition.LaborbefundComposition;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;
import org.hl7.fhir.r4.model.Observation;

public class ObservationResourceProvider implements IResourceProvider {

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createObservation(@ResourceParam Observation observation) {
        IdType id = new IdType(1L);

        observation.setId(id);
        observation.getMeta().setVersionId("1");
        observation.getMeta().setLastUpdatedElement(InstantType.withCurrentTime());

        // test map FHIR to openEHR
        try {
            LaborbefundComposition composition = FhirToOpenehr.map(observation);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new MethodOutcome(id)
                .setCreated(true)
                .setResource(observation);
    }

    @Override
    public Class<Observation> getResourceType() {
        return Observation.class;
    }
}
