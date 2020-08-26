package org.ehrbase.fhirbridge.mapping;

import org.ehrbase.fhirbridge.opt.blutdruckcomposition.BlutdruckComposition;
import org.ehrbase.fhirbridge.opt.blutdruckcomposition.definition.BlutdruckObservation;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FhirObservationBloodPressureOpenehrBloodPressure {

    private static final Logger logger = LoggerFactory.getLogger(FhirConditionOpenehrDiagnose.class);

    private FhirObservationBloodPressureOpenehrBloodPressure() {
    }

    public static BlutdruckComposition map(Observation observation) {

        BlutdruckComposition bloodPressureComposition = new BlutdruckComposition();

        BlutdruckObservation bloodPressureObservation = new BlutdruckObservation();

        double systolicBPvalue = observation.getComponent().get(0).getValueQuantity().getValue().doubleValue();
        String systolicBPunit = observation.getComponent().get(0).getValueQuantity().getUnit();

        bloodPressureObservation.setSystolischMagnitude(systolicBPvalue);
        bloodPressureObservation.setSystolischUnits(systolicBPunit);
        bloodPressureComposition.setBlutdruck(bloodPressureObservation);


        return bloodPressureComposition;

    }

}
