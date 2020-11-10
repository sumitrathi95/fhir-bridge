package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.shareddefinition.*;
import org.ehrbase.fhirbridge.opt.symptomcomposition.SymptomComposition;
import org.ehrbase.fhirbridge.opt.symptomcomposition.definition.*;
import org.ehrbase.fhirbridge.opt.symptomcomposition.definition.AussageUberDenAusschlussDefiningcode;
import org.ehrbase.fhirbridge.opt.symptomcomposition.definition.Definingcode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class FHIRConditionSymptomOpenehrSymptom {

    private static final Logger logger = LoggerFactory.getLogger(FhirConditionOpenehrDiagnose.class);

    private static final Map<String, ProblemDiagnoseDefiningcode> krankheitszeichenMap = new HashMap<>();
    private static final Map<String, Definingcode> aussageUeberFehlendeInformationMap = new HashMap<>();

    static {
        for (ProblemDiagnoseDefiningcode krankheitszeichen : ProblemDiagnoseDefiningcode.values()) {
            krankheitszeichenMap.put(krankheitszeichen.getCode(), krankheitszeichen);
        }
    }

    static {
        for (Definingcode aussageUeberFehlendeInformation : Definingcode.values()) {
            aussageUeberFehlendeInformationMap.put(aussageUeberFehlendeInformation.getCode(), aussageUeberFehlendeInformation);
        }
    }


    private FHIRConditionSymptomOpenehrSymptom() {
    }

    public static SymptomComposition map(Condition condition) {

        SymptomComposition composition = new SymptomComposition();

        if (condition.getVerificationStatus().isEmpty()) {
            mapUnknown(condition, composition);
        } else if (condition.getVerificationStatus().getCoding().get(0).getCode().equals("confirmed")) {
            mapPresent(condition, composition);
        } else {
            mapAbsent(condition, composition);
        }

        composition.setStartTimeValue(condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());


        // ======================================================================================
        // Required fields by API

        composition.setStatusDefiningcode(StatusDefiningcode.FINAL);
        composition.setKategorieValue("Test");

        composition.setLanguage(Language.DE);
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setComposer(new PartySelf());


        return composition;

    }


    private static void mapPresent(Condition condition, SymptomComposition composition) {
        VorliegendesSymptomObservation vorliegendesSymptom = new VorliegendesSymptomObservation();

        try {

            Coding coding = condition.getCode().getCoding().get(0);

            ProblemDiagnoseDefiningcode krankheitszeichen = null;

            // Neue Systeme werden eingepflegt sobald sie in KrankheitsanzeichenDefiningcode definiert sind.
            if (coding.getSystem().equals("http://snomed.info/sct")) {
                krankheitszeichen = krankheitszeichenMap.get(coding.getCode());
            }

            if (krankheitszeichen == null) {
                throw new UnprocessableEntityException("Unbekanntes Krankheitszeichen.");
            }

            vorliegendesSymptom.setNameDesSymptomsKrankheitsanzeichensDefiningcode(krankheitszeichen);


            if (condition.getOnset() != null && !condition.getOnset().isEmpty())
            {
                vorliegendesSymptom.setBeginnDerEpisodeValue(
                        condition.getOnsetDateTimeType().getValueAsCalendar().toZonedDateTime());
            }

            vorliegendesSymptom.setTimeValue(condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());

            if (condition.getAbatement() != null && !condition.getAbatement().isEmpty()) {
                vorliegendesSymptom.setDatumUhrzeitDesRuckgangsValue(
                        condition.getAbatementDateTimeType().getValueAsCalendar().toZonedDateTime());
            }


            if (!condition.getBodySite().isEmpty()) {
                for (CodeableConcept bodySite : condition.getBodySite()) {

                    VorliegendesSymptomAnatomischeLokalisationElement lokalisation =
                            new VorliegendesSymptomAnatomischeLokalisationElement();
                    lokalisation.setValue(bodySite.getCoding().get(0).getCode());

                    vorliegendesSymptom.getAnatomischeLokalisation().add(lokalisation);
                }
            }

            if (condition.getSeverity() != null && !condition.getSeverity().isEmpty()) {
                vorliegendesSymptom.setSchweregradValue(condition.getSeverity().getCoding().get(0).getCode());
            }


            vorliegendesSymptom.setOriginValue(condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());

        } catch (Exception e) {
            throw new UnprocessableEntityException("Some parts of the condition did not contain the required elements. "
                    + e.getMessage(), e);
        }

        vorliegendesSymptom.setLanguage(Language.DE);
        vorliegendesSymptom.setSubject(new PartySelf());

        composition.setVorliegendesSymptom(vorliegendesSymptom);

    }

    private static void mapAbsent(Condition condition, SymptomComposition composition) {

        AusgeschlossenesSymptomEvaluation ausgeschlossenesSymptom = new AusgeschlossenesSymptomEvaluation();

        try {
            Coding coding = condition.getCode().getCoding().get(0);

            ProblemDiagnoseDefiningcode krankheitszeichen = null;

            if (coding.getSystem().equals("http://snomed.info/sct")) {
                krankheitszeichen = krankheitszeichenMap.get(coding.getCode());
            }

            if (krankheitszeichen == null) {
                throw new UnprocessableEntityException("Unbekanntes Diagnose/Problem.");
            }

            // TODO: Check why this throws an error
            //ausgeschlossenesSymptom.setProblemDiagnoseDefiningcode(krankheitszeichen);

        } catch (Exception e) {
            throw new UnprocessableEntityException("Some parts of the condition did not contain the required elements. "
                    + e.getMessage(), e);
        }


        // Only one value possible.
        ausgeschlossenesSymptom.setAussageUberDenAusschlussDefiningcode(AussageUberDenAusschlussDefiningcode.KNOWN_ABSENT_QUALIFIER_VALUE);


        ausgeschlossenesSymptom.setSubject(new PartySelf());
        ausgeschlossenesSymptom.setLanguage(Language.DE);

        composition.setAusgeschlossenesSymptom(ausgeschlossenesSymptom);

    }

    private static void mapUnknown(Condition condition, SymptomComposition composition) {

        UnbekanntesSymptomEvaluation unbekanntesSymptom = new UnbekanntesSymptomEvaluation();

        try {
            Coding coding = condition.getCode().getCoding().get(0);

            ProblemDiagnoseDefiningcode krankheitszeichen = null;

            // Neue Systeme werden eingepflegt sobald sie in KrankheitsanzeichenDefiningcode definiert sind.
            if (coding.getSystem().equals("http://snomed.info/sct")) {
                krankheitszeichen = krankheitszeichenMap.get(coding.getCode());
            }

            if (krankheitszeichen == null) {
                throw new UnprocessableEntityException("Unbekanntes <unbekanntes Symptom>");
            }

            unbekanntesSymptom.setUnbekanntesSymptomDefiningcode(krankheitszeichen);

        } catch (Exception e) {
            throw new UnprocessableEntityException("Some parts of the condition did not contain the required elements. "
                    + e.getMessage(), e);
        }

        // UnbekanntesSymptomAussage can only have one value.
        UnbekanntesSymptomAussageUberDieFehlendeInformationElement aussageUberDieFehlendeInformationElement = new UnbekanntesSymptomAussageUberDieFehlendeInformationElement();
        aussageUberDieFehlendeInformationElement.setDefiningcode(Definingcode.UNKNOWN_QUALIFIER_VALUE);
        unbekanntesSymptom.getAussageUberDieFehlendeInformation().add(aussageUberDieFehlendeInformationElement);

        composition.setUnbekanntesSymptom(unbekanntesSymptom);
    }
}