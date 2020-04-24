package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.narrative.DefaultThymeleafNarrativeGenerator;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.RequestValidatingInterceptor;
import org.ehrbase.fhirbridge.fhir.DiagnosticReportResourceProvider;
import org.ehrbase.fhirbridge.fhir.ObservationResourceProvider;
import org.hl7.fhir.r4.hapi.ctx.DefaultProfileValidationSupport;
import org.hl7.fhir.r4.hapi.validation.*;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FhirConfiguration {

    private final FhirBridgeProperties fhirBridgeProperties;

    private final ResourceLoader resourceLoader;

    public FhirConfiguration(FhirBridgeProperties fhirBridgeProperties, ResourceLoader resourceLoader) {
        this.fhirBridgeProperties = fhirBridgeProperties;
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public FhirContext fhirContext() {
        FhirContext context = FhirContext.forR4();
        if (fhirBridgeProperties.getFhir().isNarrativeGeneration()) {
            context.setNarrativeGenerator(new DefaultThymeleafNarrativeGenerator());
        }
        return context;
    }

    @Bean
    public ServletRegistrationBean<RestfulServer> fhirServletRegistration() {
        ServletRegistrationBean<RestfulServer> bean = new ServletRegistrationBean<>(fhirServlet(), fhirBridgeProperties.getFhir().getUrlMapping());
        bean.setLoadOnStartup(1);
        return bean;
    }

    @Bean
    public RestfulServer fhirServlet() {
        RestfulServer server = new RestfulServer(fhirContext());
        server.registerProvider(new ObservationResourceProvider());
        server.registerProvider(new DiagnosticReportResourceProvider());
        server.registerInterceptor(requestValidatingInterceptor());
        return server;
    }

    @Bean
    public RequestValidatingInterceptor requestValidatingInterceptor() {
        ValidationSupportChain supportChain = new ValidationSupportChain();

        DefaultProfileValidationSupport defaultProfileSupport = new DefaultProfileValidationSupport();
        supportChain.addValidationSupport(defaultProfileSupport);

        // TODO: Generate snapshots outside of the app?
        PrePopulatedValidationSupport prePopulatedSupport = new PrePopulatedValidationSupport();
        SnapshotGeneratingValidationSupport snapshotSupport = new SnapshotGeneratingValidationSupport(fhirContext(), defaultProfileSupport);
        try (InputStream input = resourceLoader.getResource("classpath:/profile/custom-resources.xml").getInputStream()) {
            Bundle bundle = fhirContext().newXmlParser().parseResource(Bundle.class, input);
            for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
                if (entry.getResource() instanceof StructureDefinition) {
                    StructureDefinition structure = (StructureDefinition) entry.getResource();
                    prePopulatedSupport.addStructureDefinition(snapshotSupport.generateSnapshot(structure, structure.getUrl(),
                            null, structure.getName()));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("An I/O exception has occurred while loading custom profiles");
        }
        supportChain.addValidationSupport(prePopulatedSupport);

        RequestValidatingInterceptor interceptor = new RequestValidatingInterceptor();
        interceptor.addValidatorModule(new FhirInstanceValidator(new CachingValidationSupport(supportChain)));
        return interceptor;
    }
}
