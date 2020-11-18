package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.datetime.DateTimeParsers;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.*;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.*;
import org.ehrbase.fhirbridge.opt.shareddefinition.*;
import org.hl7.fhir.r4.model.*;
import org.openehr.odin.TimeIntervalObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Period;
import java.time.chrono.ChronoZonedDateTime;
import java.time.chrono.Chronology;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.util.List;

/**
 * FHIR 2 openEHR - respiration rate
 */
public class FhirObservationHistoryOfTravelOpenehrReiseaktivitaet {

    private static final Logger logger = LoggerFactory.getLogger(FhirObservationHistoryOfTravelOpenehrReiseaktivitaet.class);

    private FhirObservationHistoryOfTravelOpenehrReiseaktivitaet() {}

    private static final String loinc_url = "http://loinc.org";
    private static final String loinc_DateTravelStarted = "82752-7";
    private static final String loinc_DateOfDepartureFromTravelDestination = "91560-3";
    private static final String loinc_CityOfTravel = "94653-3";
    private static final String loinc_StateOfTravel = "82754-3";
    private static final String loinc_CountryOfTravel = "94651-7";

    //source http://fhir.ch/ig/ch-ems/ValueSet-yes-no-unknown.html
    private static final String snomed_yes = "373066001";
    private static final String snomed_no = "373067005";
    private static final String snomed_unknown = "261665006";

    private static LandDefiningcode getLandByCode(String input_code) {
        LandDefiningcode ret = null;
        int numCoresspondingItems = 0;

        //identify
        for (LandDefiningcode c : LandDefiningcode.values()) {
            if (input_code.equals(c.getCode())) {
                numCoresspondingItems = numCoresspondingItems + 1;
                ret = c;
            }
        }

        if(numCoresspondingItems != 1)
        {
            throw new UnprocessableEntityException("There is no code for '"+input_code+"' defined in LandDefiningcode.java");
        }
        return (ret);
    }

    private static BundeslandRegionDefiningcode getBundeslandByCode(String input_code) {
        BundeslandRegionDefiningcode ret = null;
        int numCoresspondingItems = 0;

        //identify
        for (BundeslandRegionDefiningcode c : BundeslandRegionDefiningcode.values()) {
            if (input_code.equals(c.getCode())) {
                numCoresspondingItems = numCoresspondingItems + 1;
                ret = c;
            }
        }

        if(numCoresspondingItems != 1)
        {
            throw new UnprocessableEntityException("There is no code for '"+input_code+"' defined in BundeslandRegionDefiningcode.java");
        }
        return (ret);
    }

    private static ReiseaktivitaetComposition setDefaults(ReiseaktivitaetComposition composition)
    {
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT); //required by model
        composition.setComposer(new PartySelf());

        return composition;
    }

    private static void getObservationComponent(Observation fhirObservation)
    {
        for (Observation.ObservationComponentComponent component: fhirObservation.getComponent()) {

           mapObservationComponent(component);
        }
    }

    private static void mapObservationComponent(Observation.ObservationComponentComponent component){

    }

    private static void mapIntervalEvent(Observation.ObservationComponentComponent observationComponent, ReiseaktivitatObservation observation_travel)
    {
        observation_travel.setMathFunctionDefiningcode(MathFunctionDefiningcode.TOTAL);
        observation_travel.setWidthValue(Period.ofDays(3));
    }

    private static ReiseaktivitaetComposition mapTravel_yes(Observation fhirObservation, ReiseDefiningcode reiseCode)
    {
        ReiseaktivitaetComposition composition = new ReiseaktivitaetComposition();
        ReiseaktivitatObservation observation_travel = new ReiseaktivitatObservation();



    DateTimeType  fhirEffectiveDateTime = null;
        try {

        // default for every observation
        fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();
        observation_travel.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        observation_travel.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
        observation_travel.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        observation_travel.setSubject(new PartySelf());

        // special mapping content
        observation_travel.setReiseDefiningcode(reiseCode);

        // TravelDestination
            // for each fhirObservation.getComponent().get(i).
            // Alt Enter
            // Shift+f6 Refactor

        for (int i = 0; i < fhirObservation.getComponent().size(); i++) {

            String urlOfCodingSystem;
            String codeOfConcept;

            //getCodingObject()
            urlOfCodingSystem = fhirObservation.getComponent().get(i).getCode().getCoding().get(0).getSystem();
            codeOfConcept = fhirObservation.getComponent().get(i).getCode().getCoding().get(0).getCode();

            //observation_travel.setMathFunctionDefiningcode();
            mapIntervalEvent(fhirObservation.getComponent().get(i), observation_travel);

            // check for loinc code
            if (!urlOfCodingSystem.equals(loinc_url)) {
                throw new UnprocessableEntityException("Expected loinc-code, but got '" + urlOfCodingSystem + "' instead ");
            }
            java.time.ZonedDateTime dateTime = null;
            String contentString = null;
            //do the mapping concrete

            // getKalenderDatum
            //getString
            //getCode


            //getComponentEntry(int i)
            if (codeOfConcept.equals(loinc_DateTravelStarted)){
                dateTime = fhirObservation.getComponent().get(i).getValueDateTimeType().getValueAsCalendar().toZonedDateTime();
                observation_travel.setAbreisedatumValue(dateTime);
            }
            else if (codeOfConcept.equals(loinc_DateOfDepartureFromTravelDestination)){
                dateTime = fhirObservation.getComponent().get(i).getValueDateTimeType().getValueAsCalendar().toZonedDateTime();
                observation_travel.setRuckreisedatumValue(dateTime);
            }
            else if (codeOfConcept.equals(loinc_CityOfTravel)) {
                contentString = fhirObservation.getComponent().get(i).getValue().toString();
                observation_travel.setStadtValue(contentString);
            }
            else if (codeOfConcept.equals(loinc_StateOfTravel)) {
                contentString = fhirObservation.getComponent().get(i).getValueCodeableConcept().getCoding().get(0).getCode();
                observation_travel.setBundeslandRegionDefiningcode(getBundeslandByCode(contentString));
            }
            else if (codeOfConcept.equals(loinc_CountryOfTravel)) {
                contentString = fhirObservation.getComponent().get(i).getValueCodeableConcept().getCoding().get(0).getCode();
                observation_travel.setLandDefiningcode(getLandByCode(contentString));
            }
            else {
                throw new UnprocessableEntityException("Expected loinc-code for history of travel, but got '" + codeOfConcept + "' instead ");
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        throw new UnprocessableEntityException(e.getMessage());
    }

        composition.setReiseaktivitat(observation_travel);

    // Required fields by API
        composition = setDefaults(composition);
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return composition;
    }

    private static ReiseaktivitaetComposition mapTravel_no(Observation fhirObservation, AussageUberDenAusschlussDefiningcode reiseCode)
    {
        ReiseaktivitaetComposition composition = new ReiseaktivitaetComposition();
        KeineReiseaktivitatEvaluation evaluation_noTravel = new KeineReiseaktivitatEvaluation();

        DateTimeType  fhirEffectiveDateTime = null;
        try {

            // default for every observation
            fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();
            evaluation_noTravel.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
            evaluation_noTravel.setSubject(new PartySelf());

            // special mapping content
            evaluation_noTravel.setAussageUberDenAusschlussDefiningcode(reiseCode);
            evaluation_noTravel.setProblemDiagnoseDefiningcode(ProblemDiagnoseDefiningcode.HISTORY_OF_TRAVEL);
            }
         catch (Exception e) {
            e.printStackTrace();
            throw new UnprocessableEntityException(e.getMessage());
        }
        composition.setKeineReiseaktivitat(evaluation_noTravel);

        // Required fields by API
        composition = setDefaults(composition);
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return composition;
    }

    private static ReiseaktivitaetComposition mapTravel_unknown(Observation fhirObservation, AussageUberDieFehlendeInformationDefiningcode reiseCode)
    {
        ReiseaktivitaetComposition composition = new ReiseaktivitaetComposition();
        UnbekannteReiseaktivitatEvaluation evalution_unknownTravel = new UnbekannteReiseaktivitatEvaluation();

        DateTimeType  fhirEffectiveDateTime = null;
        try {

            // default for every observation
            fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();
            evalution_unknownTravel.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
            evalution_unknownTravel.setSubject(new PartySelf());

            // special mapping content
            evalution_unknownTravel.setAussageUberDieFehlendeInformationDefiningcode(reiseCode);
            evalution_unknownTravel.setFehlendeInformationDefiningcode(ProblemDiagnoseDefiningcode.HISTORY_OF_TRAVEL);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new UnprocessableEntityException(e.getMessage());
        }
        composition.setUnbekannteReiseaktivitat(evalution_unknownTravel);

        // Required fields by API
        composition = setDefaults(composition);
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return composition;
    }
    // optional
    //bloodgas panel
    public static ReiseaktivitaetComposition map(Observation fhirObservation) {

        String code = fhirObservation.getValueCodeableConcept().getCoding().get(0).getCode();
        // check for general travel state
        try {
            if (code.equals(snomed_yes)) {
                return mapTravel_yes(fhirObservation, ReiseDefiningcode.YES_QUALIFIER_VALUE);
            } else if (code.equals(snomed_no)) {
                return mapTravel_no(fhirObservation, AussageUberDenAusschlussDefiningcode.NO_QUALIFIER_VALUE);
            } else if (code.equals(snomed_unknown)) {
                return mapTravel_unknown(fhirObservation, AussageUberDieFehlendeInformationDefiningcode.UNKNOWN_QUALIFIER_VALUE) ;
            } else {
                throw new UnprocessableEntityException("Expected snomed-code for history of travel, but got '" + code + "' instead ");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new UnprocessableEntityException(e.getMessage());
        }
    }
}
