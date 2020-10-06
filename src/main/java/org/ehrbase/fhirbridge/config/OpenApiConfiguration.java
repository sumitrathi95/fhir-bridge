package org.ehrbase.fhirbridge.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    private final BuildProperties buildProperties;

    public OpenApiConfiguration(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public OpenAPI openAPI() {
        // @formatter:off
        return new OpenAPI()
            .info(new Info()
                .title("FHIR-Bridge API")
                .description("FHIR-Bridge REST API documentation")
                .version(buildProperties.getVersion()))
            .externalDocs(new ExternalDocumentation()
                .description("EHRbase Documentation")
                .url("https://ehrbase.readthedocs.io/en/latest/index.html"));
        // @formatter:on
    }
}
