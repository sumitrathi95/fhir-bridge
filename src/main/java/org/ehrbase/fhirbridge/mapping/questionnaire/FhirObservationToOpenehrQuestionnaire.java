package org.ehrbase.fhirbridge.mapping.questionnaire;

import org.ehrbase.fhirbridge.mapping.questionnaire.sections.Anamnesis;
import org.ehrbase.fhirbridge.mapping.questionnaire.sections.GeneralInformation;
import org.ehrbase.fhirbridge.mapping.questionnaire.sections.Medication;
import org.ehrbase.fhirbridge.mapping.questionnaire.sections.Symptoms;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.temporal.TemporalAccessor;

public class FhirObservationToOpenehrQuestionnaire {
    // eigene ehrId
    private static final String P = "P";
    private static final String C = "C";
    private static final String S = "S";
    private static final String D = "D";
    private static final String M = "M";

    GeneralInformation generalInformation;
    Symptoms symptoms;
    Anamnesis anamnesis;
    Medication medication;

    public D4LQuestionnaireComposition map( QuestionnaireResponse questionnaireResponse) {
        D4LQuestionnaireComposition d4LQuestionnaireComposition = new D4LQuestionnaireComposition();
        initialiseSections(questionnaireResponse);
        mapSections(questionnaireResponse);
        d4LQuestionnaireComposition.setStartTimeValue(questionnaireResponse.getAuthoredElement().getValueAsCalendar().toZonedDateTime());

        return populateD4lQuestionnaireComposition(d4LQuestionnaireComposition);
    }

    private void initialiseSections(QuestionnaireResponse questionnaireResponse){
        TemporalAccessor authored = questionnaireResponse.getAuthoredElement().getValueAsCalendar().toZonedDateTime();
        this.generalInformation = new GeneralInformation(authored);
        this.symptoms = new Symptoms(authored);
        this.anamnesis = new Anamnesis(authored);
        this.medication = new Medication(authored);
    }

    private void mapSections(QuestionnaireResponse questionnaireResponse) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent item : questionnaireResponse.getItem()) {
            switch (item.getLinkId()) {
                case P:
                    generalInformation.map(item.getItem());
                    break;
                case C:
                    generalInformation.mapContactWithInfectedQuestion(item.getItem());
                    break;
                case S:
                    symptoms.map(item.getItem());
                    break;
                case D:
                    anamnesis.map(item.getItem());
                    break;
                case M:
                    medication.map(item.getItem());
                    break;
                default:
                    throw new IllegalArgumentException("LinkId " + item.getLinkId() + " undefined");
            }
        }
    }

    private D4LQuestionnaireComposition populateD4lQuestionnaireComposition(D4LQuestionnaireComposition d4LQuestionnaireComposition) {
        d4LQuestionnaireComposition.setMandatoryFields();
        d4LQuestionnaireComposition.setAllgemeineAngaben(generalInformation.toComposition());
        d4LQuestionnaireComposition.setSymptome(symptoms.toComposition());
        d4LQuestionnaireComposition.setVorGrunderkrankungen(anamnesis.toComposition());
        d4LQuestionnaireComposition.setMedikamenteImpfungen(medication.toComposition());
        return d4LQuestionnaireComposition;
    }


}