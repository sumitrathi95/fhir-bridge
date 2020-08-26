package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.opt.blutdruckcomposition.BlutdruckComposition;
import org.ehrbase.fhirbridge.opt.blutdruckcomposition.definition.BlutdruckObservation;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nedap.archie.rm.generic.PartySelf;


public class FhirObservationBloodPressureOpenehrBloodPressure {

    private static final Logger logger = LoggerFactory.getLogger(FhirConditionOpenehrDiagnose.class);

    private FhirObservationBloodPressureOpenehrBloodPressure() {
    }

    public static BlutdruckComposition map(Observation observation) {

        BlutdruckComposition bloodPressureComposition = new BlutdruckComposition();

        BlutdruckObservation bloodPressureObservation = new BlutdruckObservation();
        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();

        try {
            // set systolic BP
            double systolicBPvalue = observation.getComponent().get(0).getValueQuantity().getValue().doubleValue();
            String systolicBPunit = observation.getComponent().get(0).getValueQuantity().getUnit();

            bloodPressureObservation.setSystolischMagnitude(systolicBPvalue);
            bloodPressureObservation.setSystolischUnits(systolicBPunit);

            // set diastolic BP
            double diastolicBPvalue = observation.getComponent().get(1).getValueQuantity().getValue().doubleValue();
            String diastolicBPunit = observation.getComponent().get(1).getValueQuantity().getUnit();

            bloodPressureObservation.setDiastolischMagnitude(diastolicBPvalue);
            bloodPressureObservation.setDiastolischUnits(diastolicBPunit);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        bloodPressureObservation.setSubject(new PartySelf()); // TODO: check on how to assign subject
        bloodPressureObservation.setLanguage(Language.EN); // FIXME: we need to grab the language from the template

        bloodPressureComposition.setBlutdruck(bloodPressureObservation);

        // ======================================================================================
        // Required fields by API
        bloodPressureComposition.setLanguage(Language.EN); // FIXME: we need to grab the language from the template
        bloodPressureComposition.setLocation("test");
        bloodPressureComposition.setSettingDefiningcode(SettingDefiningcode.EMERGENCY_CARE);
        bloodPressureComposition.setTerritory(Territory.DE);
        bloodPressureComposition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        bloodPressureComposition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return bloodPressureComposition;

    }

}
