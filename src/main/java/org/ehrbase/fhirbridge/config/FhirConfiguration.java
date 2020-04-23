package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.RequestValidatingInterceptor;
import ca.uhn.fhir.spring.boot.autoconfigure.FhirRestfulServerCustomizer;
import org.hl7.fhir.r4.hapi.ctx.DefaultProfileValidationSupport;
import org.hl7.fhir.r4.hapi.validation.*;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class FhirConfiguration implements FhirRestfulServerCustomizer {

    private final FhirContext fhirContext;

    public FhirConfiguration(FhirContext fhirContext) {
        this.fhirContext = fhirContext;
    }

    @Override
    public void customize(RestfulServer server) {
        DefaultProfileValidationSupport defaultSupport = new DefaultProfileValidationSupport();
        SnapshotGeneratingValidationSupport snapshotSupport = new SnapshotGeneratingValidationSupport(fhirContext, defaultSupport);

        ValidationSupportChain supportChain = new ValidationSupportChain();
        supportChain.addValidationSupport(defaultSupport);
        supportChain.addValidationSupport(snapshotSupport);

        PrePopulatedValidationSupport prePopulatedSupport = new PrePopulatedValidationSupport();

        InputStream in = FhirConfiguration.class.getClassLoader().getResourceAsStream("profile/custom-resources.xml");
        Bundle bundle = fhirContext.newXmlParser().parseResource(Bundle.class, in);
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.getResource() instanceof StructureDefinition) {
                StructureDefinition structure = (StructureDefinition) entry.getResource();
                prePopulatedSupport.addStructureDefinition(supportChain.generateSnapshot(structure, structure.getUrl(),
                        null, structure.getName()));
            }
        }

        supportChain.addValidationSupport(prePopulatedSupport);

        CachingValidationSupport cachingSupport = new CachingValidationSupport(supportChain);
        FhirInstanceValidator instanceValidator = new FhirInstanceValidator(cachingSupport);
        RequestValidatingInterceptor interceptor = new RequestValidatingInterceptor();
        interceptor.addValidatorModule(instanceValidator);

        server.registerInterceptor(interceptor);
    }
}
