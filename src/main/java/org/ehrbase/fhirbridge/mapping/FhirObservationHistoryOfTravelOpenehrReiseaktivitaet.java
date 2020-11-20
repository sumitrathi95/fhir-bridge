package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.*;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.*;
import org.ehrbase.fhirbridge.opt.shareddefinition.*;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Period;
import java.time.ZonedDateTime;

/**
 * FHIR 2 openEHR - respiration rate
 */
public class FhirObservationHistoryOfTravelOpenehrReiseaktivitaet {

    private static final Logger logger = LoggerFactory.getLogger(FhirObservationHistoryOfTravelOpenehrReiseaktivitaet.class);

    private FhirObservationHistoryOfTravelOpenehrReiseaktivitaet() {
    }
    //source http://fhir.ch/ig/ch-ems/ValueSet-yes-no-unknown.html
    private static final String snomed_yes = "373066001";
    private static final String snomed_no = "373067005";
    private static final String snomed_unknown = "261665006";

    // Wie sollen die intern wissen, was passiert? Abfragen auf alle Loinc-Codes?
    /*private static void mapObservationComponents(Observation fhirObservation)
    {
        for (Observation.ObservationComponentComponent component: fhirObservation.getComponent()) {

           mapObservationComponent(component);
        }
    }*/

    private static String getSnomedCodeObservation(Observation fhirObservation) {
    return  fhirObservation.getValueCodeableConcept().getCoding().get(0).getCode();
    }

    public static ReiseaktivitaetComposition map(Observation fhirObservation) {

        String code = getSnomedCodeObservation(fhirObservation);
        // check for general travel state
        try {
            if (code.equals(snomed_yes)) {
                return FhirObservationHistoryOfTravelOpenehrReiseaktivitaetTravelYes.map(
                        fhirObservation, ReiseDefiningcode.YES_QUALIFIER_VALUE);
            } else if (code.equals(snomed_no)) {
                return FhirObservationHistoryOfTravelOpenehrReiseaktivitaetTravelNo.map(
                        fhirObservation, AussageUberDenAusschlussDefiningcode.NO_QUALIFIER_VALUE);
            } else if (code.equals(snomed_unknown)) {
                return FhirObservationHistoryOfTravelOpenehrReiseaktivitaetTravelUnknown.map(
                        fhirObservation, AussageUberDieFehlendeInformationDefiningcode.UNKNOWN_QUALIFIER_VALUE) ;
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