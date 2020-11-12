package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.reiseaktivitatcomposition.ReiseaktivitatComposition;
import org.ehrbase.fhirbridge.opt.reiseaktivitatcomposition.definition.ReiseaktivitatBestimmtesReisezielCluster;
import org.ehrbase.fhirbridge.opt.reiseaktivitatcomposition.definition.ReiseaktivitatObservation;
import org.ehrbase.fhirbridge.opt.shareddefinition.*;
import org.elasticsearch.cluster.routing.DelayedAllocationService;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.List;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.TerminologyId;

/**
 * FHIR 2 openEHR - respiration rate
 */
public class FhirObservationHistoryOfTravelOpenehrHistoryOfTravel {

    private static final Logger logger = LoggerFactory.getLogger(FhirObservationHistoryOfTravelOpenehrHistoryOfTravel.class);

    private FhirObservationHistoryOfTravelOpenehrHistoryOfTravel() {}

    private static String loinc_url = "http://loinc.org";
    private static String loinc_DateTravelStarted = "82752-7";
    private static String loinc_DateOfDepartureFromTravelDestination = "91560-3";
    private static String loinc_CityOfTravel = "94653-3";
    private static String loinc_StateOfTravel = "82754-3";
    private static String loinc_CountryOfTravel = "94651-7";

    public static ReiseaktivitatComposition map(Observation fhirObservation) {

        ReiseaktivitatComposition composition = new ReiseaktivitatComposition();
        ReiseaktivitatObservation observation = new ReiseaktivitatObservation();

        DateTimeType  fhirEffectiveDateTime = null;
        try {

            // default for every observation
            fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();
            observation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
            observation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
            observation.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
            observation.setSubject(new PartySelf());

            // special mapping content
            // TravelStartDate
            observation.setAbreisedatumValue(
                    fhirObservation.getComponent().get(0).getValueDateTimeType().getValueAsCalendar().toZonedDateTime()
            );

            // TravelEndDate
            observation.setRuckreisedatumValue(
                    fhirObservation.getComponent().get(4).getValueDateTimeType().getValueAsCalendar().toZonedDateTime()
            );

            // specific travel destination

            // list of destinations
            List<ReiseaktivitatBestimmtesReisezielCluster> destination = null;

            ReiseaktivitatBestimmtesReisezielCluster cluster = new ReiseaktivitatBestimmtesReisezielCluster();

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
                    observation.setAbreisedatumValue(dateTime);
                }
                else if (codeOfConcept.equals(loinc_DateOfDepartureFromTravelDestination)){
                   dateTime = fhirObservation.getComponent().get(i).getValueDateTimeType().getValueAsCalendar().toZonedDateTime();
                   observation.setRuckreisedatumValue(dateTime);
                }
                else if (codeOfConcept.equals(loinc_CityOfTravel)) {
                    contentString = fhirObservation.getComponent().get(i).getValue().toString();
                        cluster.setStadtValue(contentString);
                }
                else if (codeOfConcept.equals(loinc_StateOfTravel)) {
                    contentString = fhirObservation.getComponent().get(i).getValueCodeableConcept().getCoding().get(0).getCode();
                    cluster.setBundeslandRegionDefiningcode(BundeslandRegionDefiningcode.createByCode(contentString));
                }
                else if (codeOfConcept.equals(loinc_CountryOfTravel)) {
                    contentString = fhirObservation.getComponent().get(i).getValueCodeableConcept().getCoding().get(0).getCode();
                    cluster.setLandDefiningcode(LandDefiningcode.createByCode(contentString));
                }
                else {
                    throw new UnprocessableEntityException("Expected loinc-code for history of travel, but got '" + codeOfConcept + "' instead ");
                }
            }

            // store cluster in the list
            destination.add(cluster);

            // Destination of travel
            observation.setBestimmtesReiseziel(destination); // Why?

        } catch (Exception e) {
            e.printStackTrace();
            throw new UnprocessableEntityException(e.getMessage());
        }

        composition.setReiseaktivitat(observation);
        // admin entry : travel history
        // each node needs a value

        //template fields should be optional
        //null for template for codes without value
        // ask Julian for mandatory value

        // null favour openEHR in template aktivieren

        // Required fields by API
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT); //required by model
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        composition.setComposer(new PartySelf());

        return composition;
    }
}
