package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.opt.shareddefinition.KrankheitsanzeichensDefiningcode;
import org.ehrbase.fhirbridge.opt.symptomcomposition.SymptomComposition;
import org.ehrbase.fhirbridge.opt.symptomcomposition.definition.VorliegendesSymptomObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class FhirConditionSymptomPresentOpenehrSymptom {

    private static final Logger logger = LoggerFactory.getLogger(FhirConditionOpenehrDiagnose.class);

    private static final Map<String, KrankheitsanzeichensDefiningcode> krankheitszeichenMap = new HashMap<>();

    static {
        for (KrankheitsanzeichensDefiningcode krankheitszeichen : KrankheitsanzeichensDefiningcode.values()) {
            krankheitszeichenMap.put(krankheitszeichen.getCode(), krankheitszeichen);
        }
    }

    private FhirConditionSymptomPresentOpenehrSymptom() {
    }

    public static SymptomComposition map(Condition condition) {

        SymptomComposition composition = new SymptomComposition();

        VorliegendesSymptomObservation vorliegendesSymptom = new VorliegendesSymptomObservation();


        Coding coding = condition.getCode().getCoding().get(0);

        KrankheitsanzeichensDefiningcode krankheit = null;

        // Neue Systeme werden eingepflegt sobald sie in KrankheitsanzeichenDefiningcode definiert sind.
        if (coding.getSystem().equals("http://snomed.info/sct"))
        {
            krankheit = krankheitszeichenMap.get(coding.getCode());
        }

        if (krankheit == null)
        {
            throw new UnprocessableEntityException("Unbekanntes Krankheitszeichen.");
        }

        vorliegendesSymptom.setKrankheitsanzeichensDefiningcode(krankheit);

        composition.setVorliegendesSymptom(vorliegendesSymptom);

        vorliegendesSymptom.setBeginnDerEpisodeValue(
                condition.getOnsetDateTimeType().getValueAsCalendar().toZonedDateTime());
        vorliegendesSymptom.setTimeValue(condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());
        vorliegendesSymptom.setUhrzeitDesRuckgangsValue(
                condition.getAbatementDateTimeType().getValueAsCalendar().toZonedDateTime());

        // Anatomische Lokalisations und Schwere der Krankheit fehlen, da hier noch auf die Enum-Artigen
        // Terminologie Klassen gewartet wird.

        return new SymptomComposition();

    }

}
