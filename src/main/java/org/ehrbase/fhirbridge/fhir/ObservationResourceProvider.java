package org.ehrbase.fhirbridge.fhir;

import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Validate;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.ValidationModeEnum;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.openehrclient.CompositionEndpoint;
import org.ehrbase.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.client.templateprovider.FileBasedTemplateProvider;
import org.ehrbase.client.templateprovider.TemplateProvider;
import org.ehrbase.fhirbridge.mapping.FhirToOpenehr;
import org.ehrbase.laborbefund.laborbefundcomposition.LaborbefundComposition;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;
import org.hl7.fhir.r4.model.Observation;

import java.io.File;
import java.net.URI;
import java.util.UUID;

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
            System.out.println("----------------------------------------");

            LaborbefundComposition composition = FhirToOpenehr.map(observation);

            // try to setup the rest client
            File templatesFolder = new File("templates");
            TemplateProvider templateProvider = new FileBasedTemplateProvider(templatesFolder.toPath());
            DefaultRestClient client = new DefaultRestClient(new OpenEhrClientConfig(new URI("http://localhost:8080/ehrbase/rest/openehr/v1/")), templateProvider);
            //templateProvider.listTemplateIds().stream().forEach(t -> client.templateEndpoint().ensureExistence(t));

            UUID ehr = client.ehrEndpoint().createEhr();

            System.out.println("New EHR uid: "+ ehr.toString());

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

        return new MethodOutcome(id)
                .setCreated(true)
                .setResource(observation);
    }

    @Validate
    @SuppressWarnings("unused")
    public MethodOutcome validateObservation(@ResourceParam Observation observation,
                                             @Validate.Mode ValidationModeEnum mode,
                                             @Validate.Profile String profile) {

        if (!observation.hasValueQuantity()) {
            throw new UnprocessableEntityException("Value is required in FHIR Observation and should be Quantity");
        }

        return new MethodOutcome();
    }

    @Override
    public Class<Observation> getResourceType() {
        return Observation.class;
    }
}
