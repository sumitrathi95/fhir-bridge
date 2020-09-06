package org.ehrbase.fhirbridge.mapping;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.datavalues.DataValue;
import org.ehrbase.fhirbridge.opt.blutdruckcomposition.BlutdruckComposition;
import org.ehrbase.fhirbridge.opt.blutdruckcomposition.definition.BlutdruckObservation;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.Element;
import java.util.ArrayList;
import java.util.List;


public class FhirObservationBloodPressureOpenehrBloodPressure {

    private static final Logger logger = LoggerFactory.getLogger(FhirConditionOpenehrDiagnose.class);

    private FhirObservationBloodPressureOpenehrBloodPressure() {
    }

    public static BlutdruckComposition map(Observation observation) {

        BlutdruckComposition bloodPressureComposition = new BlutdruckComposition();
        BlutdruckObservation bloodPressure = new BlutdruckObservation();

        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();

        try {
            // set systolic BP
            double systolicBPValue = observation.getComponent().get(0).getValueQuantity().getValue().doubleValue();
            String systolicBPUnit = observation.getComponent().get(0).getValueQuantity().getCode(); //mmHg, mm[Hg]

            bloodPressure.setSystolischMagnitude(systolicBPValue);
            bloodPressure.setSystolischUnits(systolicBPUnit);

            // set diastolic BP
            double diastolicBPValue = observation.getComponent().get(1).getValueQuantity().getValue().doubleValue();
            String diastolicBPUnit = observation.getComponent().get(1).getValueQuantity().getCode();

            bloodPressure.setDiastolischMagnitude(diastolicBPValue);
            bloodPressure.setDiastolischUnits(diastolicBPUnit);

            // set strukturierte Stelle der Messung
            List<Cluster> strukturierteStellederMessung = new ArrayList<>();
            // TODO: Fill with values? How to extract such values from List<Coding>
            bloodPressure.setStrukturierteStelleDerMessung(strukturierteStellederMessung);

        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        bloodPressure.setSubject(new PartySelf()); // TODO: check on how to assign subject
        bloodPressure.setLanguage(Language.EN); // FIXME: we need to grab the language from the template

        bloodPressure.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        bloodPressure.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());


        bloodPressureComposition.setBlutdruck(bloodPressure);

        // ======================================================================================
        // Required fields by API
        bloodPressureComposition.setLanguage(Language.EN); // FIXME: we need to grab the language from the template
        bloodPressureComposition.setLocation("test");
        bloodPressureComposition.setSettingDefiningcode(SettingDefiningcode.EMERGENCY_CARE);
        bloodPressureComposition.setTerritory(Territory.DE);
        bloodPressureComposition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);

        bloodPressureComposition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        bloodPressureComposition.setComposer(new PartySelf());

        return bloodPressureComposition;

    }

}
