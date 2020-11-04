package org.ehrbase.fhirbridge.fhir;

import org.hl7.fhir.r4.model.ResourceType;

import java.util.Objects;

/**
 * List of profiles supported by the FHIR Bridge
 */
public enum Profile {

    // DiagnosticReport Profiles

    DIAGNOSTIC_REPORT_LAB("https://www.medizininformatik-initiative.de/fhir/core/modul-labor/StructureDefinition/DiagnosticReportLab", ResourceType.DiagnosticReport),

    // Observation Profiles

    BODY_TEMP("http://hl7.org/fhir/StructureDefinition/bodytemp", ResourceType.Observation),

    FIO2("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/FiO2", ResourceType.Observation),

    BLOOD_PRESSURE("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure", ResourceType.Observation),

    HEART_RATE("http://hl7.org/fhir/StructureDefinition/heartrate", ResourceType.Observation),

    PATIENT_IN_ICU("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/patient-in-icu", ResourceType.Observation),

    CORONARIRUS_NACHWEIS_TEST("https://charite.infectioncontrol.de/fhir/core/StructureDefinition/CoronavirusNachweisTest", ResourceType.Observation),

    PREGNANCY_STATUS("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pregnancy-status", ResourceType.Observation),
    
    OBSERVATION_LAB("https://www.medizininformatik-initiative.de/fhir/core/modul-labor/StructureDefinition/ObservationLab", ResourceType.Observation),

    SOFA_SCORE("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/sofa-score", ResourceType.Observation),

    CLINICAL_FRAILTY_SCALE("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/frailty-score", ResourceType.Observation),

    BODY_HEIGHT("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/body-height", ResourceType.Observation);

    private final String url;

    private final ResourceType type;

    Profile(String url, ResourceType type) {
        this.url = url;
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public ResourceType getType() {
        return type;
    }

    @Override
    public String toString() {
        return url;
    }

    public static Profile resolve(String url) {
        for (Profile profile : values()) {
            if (Objects.equals(profile.url, url)) {
                return profile;
            }
        }
        return null;
    }
}
