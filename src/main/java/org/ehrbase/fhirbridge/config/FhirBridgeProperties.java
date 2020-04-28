package org.ehrbase.fhirbridge.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "fhir-bridge")
public class FhirBridgeProperties {

    private final Fhir fhir = new Fhir();

    public Fhir getFhir() {
        return fhir;
    }

    public static class Fhir {

        private boolean narrativeGeneration;

        private String urlMapping;

        private Validation validation = new Validation();

        public boolean isNarrativeGeneration() {
            return narrativeGeneration;
        }

        public void setNarrativeGeneration(boolean narrativeGeneration) {
            this.narrativeGeneration = narrativeGeneration;
        }

        public String getUrlMapping() {
            return urlMapping;
        }

        public void setUrlMapping(String urlMapping) {
            this.urlMapping = urlMapping;
        }

        public Validation getValidation() {
            return validation;
        }
    }

    public static class Validation {

        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
