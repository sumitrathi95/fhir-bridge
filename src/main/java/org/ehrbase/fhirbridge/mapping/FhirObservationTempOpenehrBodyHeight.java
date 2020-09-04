package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.korpergrossecomposition.KorpergrosseComposition;
import org.ehrbase.fhirbridge.opt.korpergrossecomposition.definition.GrosseLangeObservation;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;

/**
 * FHIR 2 openEHR - korpergrosse
 */
public class FhirObservationTempOpenehrBodyHeight {

    private static final Logger logger = LoggerFactory.getLogger(FhirObservationTempOpenehrBodyHeight.class);

    private FhirObservationTempOpenehrBodyHeight() {}

    public static KorpergrosseComposition map(Observation fhirObservation) {

        KorpergrosseComposition composition = new KorpergrosseComposition();
        GrosseLangeObservation observation = new GrosseLangeObservation();

        // value quantity is expected
        Quantity fhirValue = null;
        BigDecimal fhirValueNumeric = null;
        DateTimeType fhirEffectiveDateTime = null;

        try {
            fhirValue = fhirObservation.getValueQuantity();
            fhirValueNumeric = fhirValue.getValue();
            fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();
            logger.debug("Value numeric: {}", fhirValueNumeric);

            observation.setGrosseLangeMagnitude(fhirValueNumeric.doubleValue());
            observation.setGrosseLangeUnits(fhirValue.getUnit());
            observation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
            observation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
            observation.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
            observation.setSubject(new PartySelf());

        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        composition.setGrosseLange(observation);

        // Required fields by API
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        composition.setComposer(new PartySelf());

        return composition;
    }
}