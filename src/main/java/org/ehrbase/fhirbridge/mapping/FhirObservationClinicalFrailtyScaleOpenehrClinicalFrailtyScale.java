package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.klinischefrailtyskalacomposition.KlinischeFrailtySkalaComposition;
import org.ehrbase.fhirbridge.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
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
public class FhirObservationClinicalFrailtyScaleOpenehrClinicalFrailtyScale {

    private static final Logger logger = LoggerFactory.getLogger(FhirObservationClinicalFrailtyScaleOpenehrClinicalFrailtyScale.class);

    private static DvOrdinal ClinFrailty_Beurteilung_1_SEHR_FIT = new DvOrdinal(1L,
            new DvCodedText("1", new CodePhrase(new TerminologyId("local"), "at0005")));
    private static DvOrdinal ClinFrailty_Beurteilung_2_DURCHSCHNITTLICH_AKTIV = new DvOrdinal(2L,
            new DvCodedText("2", new CodePhrase(new TerminologyId("local"), "at0006")));
    private static DvOrdinal ClinFrailty_Beurteilung_3_GUT_ZURECHTKOMMEND = new DvOrdinal(3L,
            new DvCodedText("3", new CodePhrase(new TerminologyId("local"), "at0007")));
    private static DvOrdinal ClinFrailty_Beurteilung_4_VULNERABEL = new DvOrdinal(4L,
            new DvCodedText("4", new CodePhrase(new TerminologyId("local"), "at0008")));
    private static DvOrdinal ClinFrailty_Beurteilung_5_GERINGGRADIG_FRAIL = new DvOrdinal(5L,
            new DvCodedText("5", new CodePhrase(new TerminologyId("local"), "at0009")));
    private static DvOrdinal ClinFrailty_Beurteilung_6_MITTELGRADIG_FRAIL = new DvOrdinal(6L,
            new DvCodedText("6", new CodePhrase(new TerminologyId("local"), "at0010")));
    private static DvOrdinal ClinFrailty_Beurteilung_7_AUSGEPRAGT_FRAIL = new DvOrdinal(7L,
            new DvCodedText("7", new CodePhrase(new TerminologyId("local"), "at0011")));
    private static DvOrdinal ClinFrailty_Beurteilung_8_EXTREM_FRAIL = new DvOrdinal(8L,
            new DvCodedText("8", new CodePhrase(new TerminologyId("local"), "at0012")));
    private static DvOrdinal ClinFrailty_Beurteilung_9_TERMINAL_ERKRANKT = new DvOrdinal(9L,
            new DvCodedText("9", new CodePhrase(new TerminologyId("local"), "at0013")));

    private FhirObservationClinicalFrailtyScaleOpenehrClinicalFrailtyScale() {}

    public static KlinischeFrailtySkalaComposition map(Observation fhirObservation) {

        KlinischeFrailtySkalaComposition composition = new KlinischeFrailtySkalaComposition();
        KlinischeFrailtySkalaCfsObservation observation = new KlinischeFrailtySkalaCfsObservation();

        // value quantity is expected
        Quantity fhirValue = null;
        BigDecimal fhirValueNumeric = null;
        DateTimeType fhirEffectiveDateTime = null;

        try {
            fhirValue = fhirObservation.getValueQuantity();
            fhirValueNumeric = fhirValue.getValue();
            fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();
            logger.debug("Value numeric: {}", fhirValueNumeric);

            String beurteilung = fhirObservation.getComponent().get(0).getValueCodeableConcept().getCoding().get(0).getCode();

            switch (beurteilung) {
                case "beu1":
                    observation.setBeurteilung(ClinFrailty_Beurteilung_1_SEHR_FIT);
                    break;
                case "beu2":
                    observation.setBeurteilung(ClinFrailty_Beurteilung_2_DURCHSCHNITTLICH_AKTIV);
                    break;
                case "beu3":
                    observation.setBeurteilung(ClinFrailty_Beurteilung_3_GUT_ZURECHTKOMMEND);
                    break;
                case "beu4":
                    observation.setBeurteilung(ClinFrailty_Beurteilung_4_VULNERABEL);
                    break;
                case "beu5":
                    observation.setBeurteilung(ClinFrailty_Beurteilung_5_GERINGGRADIG_FRAIL);
                    break;
                case "beu6":
                    observation.setBeurteilung(ClinFrailty_Beurteilung_6_MITTELGRADIG_FRAIL);
                    break;
                case "beu7":
                    observation.setBeurteilung(ClinFrailty_Beurteilung_7_AUSGEPRAGT_FRAIL);
                    break;
                case "beu8":
                    observation.setBeurteilung(ClinFrailty_Beurteilung_8_EXTREM_FRAIL);
                    break;
                case "beu9":
                    observation.setBeurteilung(ClinFrailty_Beurteilung_9_TERMINAL_ERKRANKT);
                    break;
                default:
                    logger.debug("Cannot match beurteilung\""+beurteilung+"\"");
                    break;
            }


            //observation.setMesswertMagnitude(fhirValueNumeric.doubleValue());
            //observation.setMesswertUnits(fhirValue.getUnit());
            observation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
            observation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
            observation.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
            observation.setSubject(new PartySelf());

        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        composition.setKlinischeFrailtySkalaCfs(observation);

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

