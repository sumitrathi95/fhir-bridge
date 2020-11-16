package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.*;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.*;
import org.ehrbase.fhirbridge.opt.shareddefinition.*;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * FHIR 2 openEHR - respiration rate
 */
public class FhirObservationHistoryOfTravelOpenehrReiseaktivitaet {

    private static final Logger logger = LoggerFactory.getLogger(FhirObservationHistoryOfTravelOpenehrReiseaktivitaet.class);

    private FhirObservationHistoryOfTravelOpenehrReiseaktivitaet() {}

    private static String loinc_url = "http://loinc.org";
    private static String loinc_DateTravelStarted = "82752-7";
    private static String loinc_DateOfDepartureFromTravelDestination = "91560-3";
    private static String loinc_CityOfTravel = "94653-3";
    private static String loinc_StateOfTravel = "82754-3";
    private static String loinc_CountryOfTravel = "94651-7";

    //source http://fhir.ch/ig/ch-ems/ValueSet-yes-no-unknown.html
    private static String snomed_yes = "373066001";
    private static String snomed_no = "373067005";
    private static String snomed_unknown = "261665006";

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

    private static ReiseaktivitaetComposition mapTravel_yes(Observation fhirObservation)
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

        // TravelStartDate
        observation_travel.setAbreisedatumValue(
                fhirObservation.getComponent().get(0).getValueDateTimeType().getValueAsCalendar().toZonedDateTime());

        // TravelEndDate
        observation_travel.setRuckreisedatumValue(
                fhirObservation.getComponent().get(4).getValueDateTimeType().getValueAsCalendar().toZonedDateTime());

        // TravelDestination
        for (int i = 0; i < fhirObservation.getComponent().size(); i++) {

            String urlOfCodingSystem;
            String codeOfConcept;

            urlOfCodingSystem = fhirObservation.getComponent().get(i).getCode().getCoding().get(0).getSystem();
            codeOfConcept = fhirObservation.getComponent().get(i).getCode().getCoding().get(0).getCode();

            // check for loinc code
            if (!urlOfCodingSystem.equals(loinc_url)) {
                throw new UnprocessableEntityException("Expected loinc-code, but got '" + urlOfCodingSystem + "' instead ");
            }
            java.time.ZonedDateTime dateTime = null;
            String contentString = null;
            //do the mapping concrete
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
                observation_travel.setBundeslandRegionDefiningcode(BundeslandRegionDefiningcode.createByCode(contentString));
                Bundesland
            }
            else if (codeOfConcept.equals(loinc_CountryOfTravel)) {
                contentString = fhirObservation.getComponent().get(i).getValueCodeableConcept().getCoding().get(0).getCode();
                observation_travel.setLandDefiningcode(LandDefiningcode.createByCode(contentString));
            }
            else {
                throw new UnprocessableEntityException("Expected loinc-code for history of travel, but got '" + codeOfConcept + "' instead ");
            }
        }

        // store cluster in the list
        destination.add(cluster);

        // Destination of travel
        observation_travel.setBestimmtesReiseziel(destination); // Why?

    } catch (Exception e) {
        e.printStackTrace();
        throw new UnprocessableEntityException(e.getMessage());
    }

        composition.setReiseaktivitat(observation_travel);
    // admin entry : travel history
    // each node needs a value

    //template fields should be optional
    //null for template for codes without value
    // ask Julian for mandatory value

    // null favour openEHR in template aktivieren

    // Required fields by API
        composition = setDefaults(composition);
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return composition;
    }

    private static ReiseaktivitaetComposition mapTravel_no(Observation fhirObservation)
    {
        ReiseaktivitaetComposition composition = new ReiseaktivitaetComposition();
        KeineReiseaktivitatEvaluation evaulation_noTravel = new KeineReiseaktivitatEvaluation();
    }

    private static ReiseaktivitaetComposition mapTravel_unknown(Observation fhirObservation)
    {
        ReiseaktivitaetComposition composition = new ReiseaktivitaetComposition();
        UnbekannteReiseaktivitatEvaluation evalution_unknownTravel = new UnbekannteReiseaktivitatEvaluation();
    }

    public static ReiseaktivitaetComposition map(Observation fhirObservation) {


        // check for general travel state
        try {
            String system = fhirObservation.getValueCodeableConcept().getCoding().get(0).getSystem();
            String code = fhirObservation.getValueCodeableConcept().getCoding().get(0).getCode();

            if (code.equals(snomed_yes)) {
                return mapTravel_yes(fhirObservation);
            } else if (code.equals(snomed_no)) {
                return mapTravel_no(fhirObservation);
            } else if (code.equals(snomed_unknown)) {
                return mapTravel_unknown(fhirObservation);
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
