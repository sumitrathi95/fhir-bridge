package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.narrative.DefaultThymeleafNarrativeGenerator;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.CorsInterceptor;
import ca.uhn.fhir.rest.server.interceptor.RequestValidatingInterceptor;
import org.ehrbase.fhirbridge.FhirBridgeException;
import org.ehrbase.fhirbridge.fhir.provider.AbstractResourceProvider;
import org.ehrbase.fhirbridge.fhir.validation.TerminologyServerValidationSupport;
import org.hl7.fhir.r4.hapi.ctx.DefaultProfileValidationSupport;
import org.hl7.fhir.r4.hapi.validation.CachingValidationSupport;
import org.hl7.fhir.r4.hapi.validation.FhirInstanceValidator;
import org.hl7.fhir.r4.hapi.validation.PrePopulatedValidationSupport;
import org.hl7.fhir.r4.hapi.validation.ValidationSupportChain;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.cors.CorsConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Configuration
public class FhirConfiguration {

    private final Logger logger = LoggerFactory.getLogger(FhirConfiguration.class);

    private final FhirBridgeProperties fhirBridgeProperties;

    private final ResourcePatternResolver resourceLoader;

    private final ListableBeanFactory beanFactory;

    public FhirConfiguration(FhirBridgeProperties fhirBridgeProperties, ResourcePatternResolver resourceLoader, ListableBeanFactory beanFactory) {
        this.fhirBridgeProperties = fhirBridgeProperties;
        this.resourceLoader = resourceLoader;
        this.beanFactory = beanFactory;
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
        server.registerProviders(beanFactory.getBeansOfType(AbstractResourceProvider.class).values());
        server.registerInterceptor(requestValidatingInterceptor());
        server.registerInterceptor(corsValidatingInterceptor());
        return server;
    }

    @Bean
    public RequestValidatingInterceptor requestValidatingInterceptor() {
        ValidationSupportChain chain = new ValidationSupportChain();
        chain.addValidationSupport(new DefaultProfileValidationSupport());
        chain.addValidationSupport(prePopulatedValidationSupport());
        chain.addValidationSupport(terminologyValidationSupport());

        FhirInstanceValidator module = new FhirInstanceValidator(new CachingValidationSupport(chain));
        module.setErrorForUnknownProfiles(true);
//        module.setNoTerminologyChecks(true);

        RequestValidatingInterceptor interceptor = new RequestValidatingInterceptor();
        interceptor.addValidatorModule(module);
        return interceptor;
    }

    @Bean
    public CorsInterceptor corsValidatingInterceptor() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("x-fhir-starter");
        config.addAllowedHeader("Origin");
        config.addAllowedHeader("Accept");
        config.addAllowedHeader("X-Requested-With");
        config.addAllowedHeader("Content-Type");

        config.addAllowedOrigin("*");

        config.addExposedHeader("Location");
        config.addExposedHeader("Content-Location");
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // Create the interceptor and register it
        return new CorsInterceptor(config);
    }

    @Bean
    public PrePopulatedValidationSupport prePopulatedValidationSupport() {
        PrePopulatedValidationSupport validationSupport = new PrePopulatedValidationSupport();
        IParser parser = fhirContext().newXmlParser();
        try {
            for (Resource resource : resourceLoader.getResources("classpath:/profiles/*")) {
                try (InputStream in = resource.getInputStream()) {
                    StructureDefinition structureDefinition = parser.parseResource(StructureDefinition.class, in);
                    validationSupport.addStructureDefinition(structureDefinition);
                    logger.info("Profile {} {{}} loaded for {}", structureDefinition.getName(), structureDefinition.getUrl(), structureDefinition.getType());
                }
            }
        } catch (IOException e) {
            throw new FhirBridgeException("Profiles initialization failed", e);
        }
        return validationSupport;
    }

    @Bean
    public TerminologyServerValidationSupport terminologyValidationSupport() {
        String serverBaseUrl = fhirBridgeProperties.getFhir().getValidation().getTerminology().getServerBaseUrl();
        return new TerminologyServerValidationSupport(fhirContext(), serverBaseUrl);
    }
}
