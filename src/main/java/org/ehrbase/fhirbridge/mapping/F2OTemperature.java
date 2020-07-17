package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.IntensivmedizinischesMonitoringKorpertemperaturComposition;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturBeliebigesEreignisChoice;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturBeliebigesEreignisPointEvent;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturObservation;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * FHIR 2 openEHR - body temperature
 */
public class F2OTemperature {

    private static final Logger logger = LoggerFactory.getLogger(F2OTemperature.class);

    private F2OTemperature() {}

    public static IntensivmedizinischesMonitoringKorpertemperaturComposition map(Observation fhirObservation) {

        IntensivmedizinischesMonitoringKorpertemperaturComposition composition = new IntensivmedizinischesMonitoringKorpertemperaturComposition();

        // ========================================================================================
        // value quantity is expected
        Quantity fhirValue = null;
        BigDecimal fhirValueNumeric = null;
        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();


        try {
            fhirValue = fhirObservation.getValueQuantity();
            fhirValueNumeric = fhirValue.getValue();
            logger.debug("Value numeric: {}", fhirValueNumeric);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        if (fhirValueNumeric == null) {
            throw new UnprocessableEntityException("Value is required in FHIR Observation and should be Quantity");
        }

        // mapping to openEHR
        KorpertemperaturBeliebigesEreignisPointEvent tempEvent = new KorpertemperaturBeliebigesEreignisPointEvent();
        tempEvent.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
        tempEvent.setTemperaturMagnitude(fhirValueNumeric.doubleValue());
        tempEvent.setTemperaturUnits(fhirValue.getUnit());


        KorpertemperaturObservation tempObs = new KorpertemperaturObservation();
        List<KorpertemperaturBeliebigesEreignisChoice> events = new ArrayList<>();
        events.add(tempEvent);
        tempObs.setBeliebigesEreignis(events);
        tempObs.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
        tempObs.setLanguage(Language.EN); // FIXME: we need to grab the language from the template
        tempObs.setSubject(new PartySelf());

        List<KorpertemperaturObservation> observations = new ArrayList<>();
        observations.add(tempObs);
        composition.setKorpertemperatur(observations);

        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.EN); // FIXME: we need to grab the language from the template
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.EMERGENCYCARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        // FIXME: https://github.com/ehrbase/ehrbase_client_library/issues/31
        //        PartyProxy composer = new PartyIdentified();
        //        composition.setComposer(composer);

        composition.setComposer(new PartySelf());

        return composition;
    }
}