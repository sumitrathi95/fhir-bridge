package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.reiseaktivitatcomposition.ReiseaktivitatComposition;
import org.ehrbase.fhirbridge.opt.reiseaktivitatcomposition.definition.ReiseaktivitatObservation;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;

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
/*
            // TravelStartDate

            observation.setAbreisedatumValue(
                    fhirObservation.getComponent().get(0).getValueDateTimeType().getValueAsCalendar().toZonedDateTime()
            );

            // TravelEndDate
            observation.setRuckreisedatumValue(
                    fhirObservation.getComponent().get(4).getValueDateTimeType().getValueAsCalendar().toZonedDateTime()
            );
 */
            // Destination of travel
            //observation.setBestimmtesReiseziel(
            //        fhirObservation.getComponent().get(1).getValueCodeableConcept().getText()
            //);

            // ???
            //observation.setReiseDefiningcode();

            // travel details
            //observation.setZusatzlicheReisedetails();

        } catch (Exception e) {
            e.printStackTrace();
            throw new UnprocessableEntityException(e.getMessage());
        }

        composition.setReiseaktivitat(observation);

        // Required fields by API
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        composition.setComposer(new PartySelf()); // FIXME: id ausdenken oder weglassen?

        return composition;
    }
}
