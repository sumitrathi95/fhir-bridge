package org.ehrbase.fhirbridge.mapping;

import org.hl7.fhir.r4.model.Observation;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.*;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.definition.*;
import org.hl7.fhir.r4.model.Quantity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FhirToOpenehr {

    static public LaborbefundComposition map(Observation fhirObsservation) throws Exception {

        LaborbefundComposition composition = new LaborbefundComposition();


        // ========================================================================================
        // value quantity is expected
        Quantity fhir_value = null;
        BigDecimal fhir_value_numeric = null;


        try {
            fhir_value = fhirObsservation.getValueQuantity();
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

        StandortJedesEreignisPointEvent result_event = new StandortJedesEreignisPointEvent();
        List items = new ArrayList();
        items.add(result_cluster);
        result_event.setLaboranalytResultat(items);

        LaborergebnisObservation result_obs = new LaborergebnisObservation();
        List events = new ArrayList();
        events.add(result_event);
        result_obs.setJedesEreignis(events);

        List observations = new ArrayList();
        observations.add(result_obs);
        composition.setLaborergebnis(observations);

        return composition;
    }
}