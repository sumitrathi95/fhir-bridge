package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.shareddefinition.*;
import org.ehrbase.fhirbridge.opt.symptomcomposition.SymptomComposition;
import org.ehrbase.fhirbridge.opt.symptomcomposition.definition.VorliegendesSymptomObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
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

        try {

            Coding coding = condition.getCode().getCoding().get(0);

            KrankheitsanzeichensDefiningcode krankheit = null;

            // Neue Systeme werden eingepflegt sobald sie in KrankheitsanzeichenDefiningcode definiert sind.
            if (coding.getSystem().equals("http://snomed.info/sct")) {
                krankheit = krankheitszeichenMap.get(coding.getCode());
            }

            if (krankheit == null) {
                throw new UnprocessableEntityException("Unbekanntes Krankheitszeichen.");
            }

            vorliegendesSymptom.setKrankheitsanzeichensDefiningcode(krankheit);

            vorliegendesSymptom.setBeginnDerEpisodeValue(
                    condition.getOnsetDateTimeType().getValueAsCalendar().toZonedDateTime());
            vorliegendesSymptom.setTimeValue(condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());
            //vorliegendesSymptom.setUhrzeitDesRuckgangsValue(
            //        condition.getAbatementDateTimeType().getValueAsCalendar().toZonedDateTime());

            composition.setStartTimeValue(condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());
            vorliegendesSymptom.setOriginValue(condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());

        } catch(Exception e) {
            throw new UnprocessableEntityException("Some parts of the condition did not contain the required elements. "
                    + e.getMessage(), e);
        }

        // Anatomische Lokalisations und Schwere der Krankheit fehlen, da hier noch auf die Enum-Artigen
        // Terminologie Klassen gewartet wird.

        vorliegendesSymptom.setLanguage(Language.DE);
        vorliegendesSymptom.setSubject(new PartySelf());

        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.DE);
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setComposer(new PartySelf());

        composition.setVorliegendesSymptom(vorliegendesSymptom);

        return composition;

    }

}
