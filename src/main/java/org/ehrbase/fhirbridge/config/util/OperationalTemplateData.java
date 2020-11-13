package org.ehrbase.fhirbridge.config.util;

import java.io.InputStream;
import java.util.Arrays;

public enum OperationalTemplateData {

    CORONA_DIAGNOSIS("", "Diagnose.opt", "Diagnose"),
    CORONA_TEMP("", "Intensivmedizinisches Monitoring Korpertemperatur.opt", "Intensivmedizinisches Monitoring Korpertemperatur"),
    CLINICAL_FRAILTY_SCALE("", "KlinischeFrailty.opt", "Klinische Frailty-Skala"),
    CORONA_TEST("", "Kennzeichnung Erregernachweis SARS-CoV-2.opt", "Kennzeichnung Erregernachweis SARS-CoV-2"),
    CORONA_LAB("", "GECCO_Laborbefund.opt", "GECCO_Laborbefund"),

    PROCEDURE("", "Prozedur.opt", "Prozedur"),
    FI02("", "Beatmungswerte.opt", "Beatmungswerte"),
    CORONA_SOFA_SCORE("", "SOFA.opt", "SOFA"),
    CORONA_BLOOD_PRESSURE("", "Blutdruck.opt", "Blutdruck"),
    CORONA_PATIENT_IN_ICU("", "PatientAufICU.opt", "Patient auf ICU"),
    PREGNANCY_STATUS("", "Schwangerschaftsstatus.opt", "Schwangerschaftsstatus"),
    HEART_RATE("", "Herzfrequenz.opt", "Herzfrequenz"),
    BLOOD_GAS("", "Befund_der_Blutgasanalyse.opt", "Befund der Blutgasanalyse"),
    BODY_HEIGHT("","Koerpergroesse.opt","Körpergröße");


    private final String filename;
    private final String templateId;
    private final String description;

    OperationalTemplateData(String description, String filename, String templateId) {
        this.filename = filename;
        this.description = description;
        this.templateId = templateId;
    }

    public InputStream getStream() {
        return getClass().getResourceAsStream("/opt/" + filename);
    }

    public static OperationalTemplateData findByTemplateId(String templateId) {
        return Arrays.stream(OperationalTemplateData.values())
                .filter(o -> o.getTemplateId().equals(templateId))
                .findAny()
                .orElse(null);
    }

    public String getTemplateId() {
        return templateId;
    }
}
