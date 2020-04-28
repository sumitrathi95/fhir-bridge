package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.mapping.FhirToOpenehr;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.LaborbefundComposition;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.stereotype.Component;


/**
 * Resource provider for Observation
 */
@Component
public class ObservationResourceProvider extends AbstractResourceProvider {

    public ObservationResourceProvider(FhirContext fhirContext) {
        super(fhirContext);
    }


    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createObservation(@ResourceParam Observation observation) {
        checkProfiles(observation);

        // test map FHIR to openEHR
        try {
            System.out.println("----------------------------------------");

            LaborbefundComposition composition = FhirToOpenehr.map(observation);

            // try to setup the rest client
            /*
            File templatesFolder = new File("templates");
            TemplateProvider templateProvider = new FileBasedTemplateProvider(templatesFolder.toPath());
            DefaultRestClient client = new DefaultRestClient(new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/")), templateProvider);
             */
            //templateProvider.listTemplateIds().stream().forEach(t -> client.templateEndpoint().ensureExistence(t));

            /*
            UUID ehr = client.ehrEndpoint().createEhr();
            System.out.println("New EHR uid: "+ ehr.toString());
             */

            /*
            CompositionEndpoint compositionEndpoint = client.compositionEndpoint(ehr);
            LaborbefundComposition representation = client.compositionEndpoint(ehr).mergeCompositionEntity(composition);

            if (representation != null)
            {
                System.out.println("Composition created "+ representation.getVersionUid().getUuid());
            }
            */

        } catch (Exception e) {
            e.printStackTrace();
        }

        observation.setId(new IdType(1L));
        observation.getMeta().setVersionId("1");
        observation.getMeta().setLastUpdatedElement(InstantType.withCurrentTime());

        return new MethodOutcome()
                .setCreated(true)
                .setResource(observation);
    }

    @Override
    public Class<Observation> getResourceType() {
        return Observation.class;
    }

    @Override
    public boolean isDefaultProfileSupported() {
        return false;
    }
}
