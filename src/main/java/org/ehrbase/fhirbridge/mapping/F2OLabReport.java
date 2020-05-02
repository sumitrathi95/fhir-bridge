package org.ehrbase.fhirbridge.mapping;

import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.Observation;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.*;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.definition.*;
import org.hl7.fhir.r4.model.Quantity;

import com.nedap.archie.rm.generic.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * FHIR to openEHR - Laboratory report
 */
public class F2OLabReport {

    static public LaborbefundComposition map(Observation fhirObservation) throws Exception {

        LaborbefundComposition composition = new LaborbefundComposition();


        // ========================================================================================
        // value quantity is expected
        Quantity fhir_value = null;
        BigDecimal fhir_value_numeric = null;


        try {
            fhir_value = fhirObservation.getValueQuantity();
            fhir_value_numeric = fhir_value.getValue();
        } catch (Exception e) {
            System.out.println("---> "+ e.getMessage());
        }

        if (fhir_value_numeric == null)
        {
            throw new Exception("Value is required in FHIR Observation and should be Quantity");
        }

        // mapping to openEHR
        LaboranalytResultatAnalytResultatDvquantity result_value = new LaboranalytResultatAnalytResultatDvquantity();
        result_value.setAnalytResultatMagnitude(fhir_value_numeric.doubleValue());
        result_value.setAnalytResultatUnits(fhir_value.getUnit());

        // =======================================================================================
        // rest of the structure to build the composition with the value taken from FHIR
        LaboranalytResultatCluster result_cluster = new LaboranalytResultatCluster();
        result_cluster.setAnalytResultat(result_value);
        result_cluster.setAnalytResultatValue("result"); // this is the ELEMENT.name

        StandortJedesEreignisPointEvent result_event = new StandortJedesEreignisPointEvent();
        result_event.setTimeValue(OffsetDateTime.now()); // mandatory
        List items = new ArrayList();
        items.add(result_cluster);
        result_event.setLaboranalytResultat(items);

        LaborergebnisObservation result_obs = new LaborergebnisObservation();
        List events = new ArrayList();
        events.add(result_event);
        result_obs.setJedesEreignis(events);
        result_obs.setOriginValue(OffsetDateTime.now()); // mandatory
        result_obs.setLanguage(Language.EN);
        result_obs.setSubject(new PartySelf());

        List observations = new ArrayList();
        observations.add(result_obs);
        composition.setLaborergebnis(observations);

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