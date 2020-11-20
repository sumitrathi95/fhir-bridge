package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.ReiseaktivitaetComposition;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.BundeslandRegionDefiningcode;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.LandDefiningcode;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.ReiseDefiningcode;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.ReiseaktivitatObservation;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.MathFunctionDefiningcode;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

import java.time.Period;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;

import org.ehrbase.fhirbridge.mapping.FhirObservationHistoryOfTravelOpenehrReiseaktivitaetBasics;



public class FhirObservationHistoryOfTravelOpenehrReiseaktivitaetTravelYes {

    private FhirObservationHistoryOfTravelOpenehrReiseaktivitaetTravelYes(){}

    private static final String loinc_url = "http://loinc.org";
    private static final String loinc_DateTravelStarted = "82752-7";
    private static final String loinc_DateOfDepartureFromTravelDestination = "91560-3";
    private static final String loinc_CityOfTravel = "94653-3";
    private static final String loinc_StateOfTravel = "82754-3";
    private static final String loinc_CountryOfTravel = "94651-7";

    public static ReiseaktivitaetComposition map(Observation fhirObservation, ReiseDefiningcode reiseCode) {

        ReiseaktivitatObservation observation_travel = new ReiseaktivitatObservation();
        ReiseaktivitaetComposition composition = null;

        try {
            observation_travel.setReiseDefiningcode(reiseCode);
            observation_travel = mapDateTime(observation_travel, fhirObservation);
            observation_travel = mapInternalEvents(observation_travel, fhirObservation);
            composition = createComposition(fhirObservation, observation_travel);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnprocessableEntityException(e.getMessage());
        }
        return composition;
    }

    private static ReiseaktivitatObservation mapDateTime(ReiseaktivitatObservation observation_travel, Observation fhirObservation) {

        // default for every observation
        TemporalAccessor datetime = FhirObservationHistoryOfTravelOpenehrReiseaktivitaetBasics.getFhirEffectiveDateTime(fhirObservation);
        observation_travel.setTimeValue(datetime);
        observation_travel.setOriginValue(datetime); // mandatory
        observation_travel.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        observation_travel.setSubject(new PartySelf());

        return observation_travel;
    }

    private static ReiseaktivitaetComposition createComposition(Observation fhirObservation, ReiseaktivitatObservation observation_travel) {

        ReiseaktivitaetComposition composition = new ReiseaktivitaetComposition();
        composition.setReiseaktivitat(observation_travel);

        // Required fields by API
        composition = FhirObservationHistoryOfTravelOpenehrReiseaktivitaetBasics.setCompositionDefaults(composition);
        composition.setStartTimeValue(fhirObservation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());

        return composition;
    }

    private static ReiseaktivitatObservation mapInternalEvents(ReiseaktivitatObservation observation_travel, Observation fhirObservation) {

        observation_travel.setMathFunctionDefiningcode(MathFunctionDefiningcode.TOTAL);
        observation_travel.setWidthValue(Period.ofDays(3));

        for (int i = 0; i < fhirObservation.getComponent().size(); i++) {
            Observation.ObservationComponentComponent codingObject = getCodingObject(fhirObservation, i);
            observation_travel = mapIntervalEvent(observation_travel, codingObject);
        }

        return observation_travel;
    }

    private static ReiseaktivitatObservation mapIntervalEvent(ReiseaktivitatObservation observation_travel, Observation.ObservationComponentComponent codingObject) {

        String codeOfConcept = getCodeOfConcept(codingObject);

        if (codeOfConcept.equals(loinc_DateTravelStarted)) {
            observation_travel.setAbreisedatumValue(getDateTime(codingObject));
        } else if (codeOfConcept.equals(loinc_DateOfDepartureFromTravelDestination)) {
            observation_travel.setRuckreisedatumValue(getDateTime(codingObject));
        } else if (codeOfConcept.equals(loinc_CityOfTravel)) {
            observation_travel.setStadtValue(getString(codingObject));
        } else if (codeOfConcept.equals(loinc_StateOfTravel)) {
            observation_travel.setBundeslandRegionDefiningcode(getBundeslandByCode(codingObject));
        } else if (codeOfConcept.equals(loinc_CountryOfTravel)) {
            observation_travel.setLandDefiningcode(getLandByCode(codingObject));
        } else {
            throw new UnprocessableEntityException("Expected loinc-code for history of travel, but got '" + codeOfConcept + "' instead ");
        }
        return observation_travel;
    }

    private static Observation.ObservationComponentComponent getCodingObject(Observation fhirObservation, int i) {
        return fhirObservation.getComponent().get(i);
    }

    private static void checkForLoincCode(Observation.ObservationComponentComponent codingObject) {
        String urlOfCodingSystem = codingObject.getCode().getCoding().get(0).getSystem();

        if (!urlOfCodingSystem.equals(loinc_url)) {
            throw new UnprocessableEntityException("Expected loinc-code, but got '" + urlOfCodingSystem + "' instead ");
        }
    }

    private static String getCodeOfConcept(Observation.ObservationComponentComponent codingObject) {
        return codingObject.getCode().getCoding().get(0).getCode();
    }

    private static ZonedDateTime getDateTime(Observation.ObservationComponentComponent codingObject) {
        return codingObject.getValueDateTimeType().getValueAsCalendar().toZonedDateTime();
    }

    private static String getString(Observation.ObservationComponentComponent codingObject) {
        return codingObject.getValue().toString();
    }

    private static String getContentCode(Observation.ObservationComponentComponent codingObject) {
        return codingObject.getValueCodeableConcept().getCoding().get(0).getCode();
    }

    private static LandDefiningcode getLandByCode(Observation.ObservationComponentComponent codingObject) {

        String input_code = getContentCode(codingObject);

        LandDefiningcode ret = null;
        int numCoresspondingItems = 0;

        //identify
        for (LandDefiningcode c : LandDefiningcode.values()) {
            if (input_code.equals(c.getCode())) {
                numCoresspondingItems = numCoresspondingItems + 1;
                ret = c;
            }
        }

        if (numCoresspondingItems != 1) {
            throw new UnprocessableEntityException("There is no code for '" + input_code + "' defined in LandDefiningcode.java");
        }
        return (ret);
    }

    private static BundeslandRegionDefiningcode getBundeslandByCode(Observation.ObservationComponentComponent codingObject) {
        BundeslandRegionDefiningcode ret = null;
        int numCoresspondingItems = 0;
        String input_code = getContentCode(codingObject);

        //identify
        for (BundeslandRegionDefiningcode c : BundeslandRegionDefiningcode.values()) {
            if (input_code.equals(c.getCode())) {
                numCoresspondingItems = numCoresspondingItems + 1;
                ret = c;
            }
        }

        if (numCoresspondingItems != 1) {
            throw new UnprocessableEntityException("There is no code for '" + input_code + "' defined in BundeslandRegionDefiningcode.java");
        }
        return (ret);
    }
}
