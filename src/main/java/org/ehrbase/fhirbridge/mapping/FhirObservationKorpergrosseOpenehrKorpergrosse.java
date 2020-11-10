package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.korpergrossecomposition.KorpergrosseComposition;
import org.ehrbase.fhirbridge.opt.korpergrossecomposition.KorpergrosseCompositionContainment;
import org.ehrbase.fhirbridge.opt.korpergrossecomposition.definition.GrosseLangeObservation;
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
public class FhirObservationKorpergrosseOpenehrKorpergrosse {


    private static final Logger logger = LoggerFactory.getLogger(FhirObservationKorpergrosseOpenehrKorpergrosse.class);

    private FhirObservationKorpergrosseOpenehrKorpergrosse() {}

    public static KorpergrosseComposition map(Observation fhirObservation) {

        KorpergrosseComposition composition = new KorpergrosseComposition();
        GrosseLangeObservation observation = new GrosseLangeObservation();

        DateTimeType  fhirEffectiveDateTime = null;
        try {

            // default for every observation
            fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();
            observation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
            observation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
            observation.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
            observation.setSubject(new PartySelf());

            // special mapping content
            observation.setGrosseLangeUnits(fhirObservation.getValueQuantity().getCode());
            observation.setGrosseLangeMagnitude(fhirObservation.getValueQuantity().getValue().doubleValue());

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
        composition.setComposer(new PartySelf()); // FIXME: id ausdenken oder weglassen?

        return composition;
    }
}
