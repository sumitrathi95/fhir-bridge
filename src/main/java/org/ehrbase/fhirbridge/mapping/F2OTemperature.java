package org.ehrbase.fhirbridge.mapping;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.fhir.provider.ObservationResourceProvider;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.IntensivmedizinischesMonitoringKorpertemperaturComposition;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturBeliebigesEreignisPointEvent;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturObservation;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.LaborbefundComposition;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.definition.LaboranalytResultatAnalytResultatDvquantity;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.definition.LaboranalytResultatCluster;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.definition.StandortJedesEreignisPointEvent;
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
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * FHIR 2 openEHR - body temperature
 */
public class F2OTemperature {

    private static final Logger logger = LoggerFactory.getLogger(F2OTemperature.class);

    static public IntensivmedizinischesMonitoringKorpertemperaturComposition map(Observation fhirObservation) throws Exception {

        IntensivmedizinischesMonitoringKorpertemperaturComposition composition = new IntensivmedizinischesMonitoringKorpertemperaturComposition();


        // ========================================================================================
        // value quantity is expected
        Quantity fhirValue = null;
        BigDecimal fhirValueNumeric = null;
        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();


        try {
            fhirValue = fhirObservation.getValueQuantity();
            fhirValueNumeric = fhirValue.getValue();
        } catch (Exception e) {
            logger.error("---> "+ e.getMessage());
        }

        if (fhirValueNumeric == null)
        {
            throw new Exception("Value is required in FHIR Observation and should be Quantity");
        }

        // mapping to openEHR
        KorpertemperaturBeliebigesEreignisPointEvent tempEvent = new KorpertemperaturBeliebigesEreignisPointEvent();
        //tempEvent.setTimeValue(OffsetDateTime.now());
        tempEvent.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
        tempEvent.setTemperaturMagnitude(fhirValueNumeric.doubleValue());
        tempEvent.setTemperaturUnits(fhirValue.getUnit());


        KorpertemperaturObservation tempObs = new KorpertemperaturObservation();
        List events = new ArrayList();
        events.add(tempEvent);
        tempObs.setBeliebigesEreignis(events);
        tempObs.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
        tempObs.setLanguage(Language.EN);
        tempObs.setSubject(new PartySelf());

        List observations = new ArrayList();
        observations.add(tempObs);
        composition.setKorpertemperatur(observations);

        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.EN);
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.EMERGENCYCARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(OffsetDateTime.now());

// https://github.com/ehrbase/ehrbase_client_library/issues/31
//        PartyProxy composer = new PartyIdentified();
//        composition.setComposer(composer);

        composition.setComposer(new PartySelf());

        return composition;
    }
}