package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.support.DefaultProfileValidationSupport;
import ca.uhn.fhir.context.support.IValidationSupport;
import ca.uhn.fhir.narrative.DefaultThymeleafNarrativeGenerator;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.CorsInterceptor;
import ca.uhn.fhir.rest.server.interceptor.RequestValidatingInterceptor;
import org.ehrbase.fhirbridge.FhirBridgeException;
import org.ehrbase.fhirbridge.fhir.provider.AbstractResourceProvider;
import org.hl7.fhir.common.hapi.validation.support.CachingValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.InMemoryTerminologyServerValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;
import org.hl7.fhir.common.hapi.validation.support.ValidationSupportChain;
import org.hl7.fhir.common.hapi.validation.validator.FhirInstanceValidator;
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
        chain.addValidationSupport(new DefaultProfileValidationSupport(fhirContext()));
        chain.addValidationSupport(new InMemoryTerminologyServerValidationSupport(fhirContext()));
        for (IValidationSupport validationSupport : beanFactory.getBeansOfType(IValidationSupport.class).values()) {
            chain.addValidationSupport(validationSupport);
        }

        FhirInstanceValidator validatorModule = new FhirInstanceValidator(new CachingValidationSupport(chain));
        validatorModule.setErrorForUnknownProfiles(true);
        validatorModule.setNoTerminologyChecks(!fhirBridgeProperties.getFhir().getValidation().getTerminology().isEnabled());

        RequestValidatingInterceptor interceptor = new RequestValidatingInterceptor();
        interceptor.addValidatorModule(validatorModule);
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
        PrePopulatedValidationSupport validationSupport = new PrePopulatedValidationSupport(fhirContext());
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

//    @Bean
//    @ConditionalOnProperty(name = "fhir-bridge.fhir.validation.terminology.enabled", havingValue = "true")
//    public TerminologyServiceValidationSupport terminologyServiceValidationSupport() {
//        String serverBaseUrl = fhirBridgeProperties.getFhir().getValidation().getTerminology().getServerBaseUrl();
//        return new TerminologyServiceValidationSupport(fhirContext(), serverBaseUrl);
//    }
}
