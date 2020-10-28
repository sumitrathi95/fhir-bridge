package org.ehrbase.fhirbridge.mapping.Questionnaire;

import org.ehrbase.fhirbridge.mapping.Questionnaire.Sections.Anamnesis;
import org.ehrbase.fhirbridge.mapping.Questionnaire.Sections.GeneralInformation;
import org.ehrbase.fhirbridge.mapping.Questionnaire.Sections.Medication;
import org.ehrbase.fhirbridge.mapping.Questionnaire.Sections.Symptoms;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

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

        mapSections(questionnaireResponse, d4LQuestionnaireComposition);

        return d4LQuestionnaireComposition;
    }

    protected D4LQuestionnaireComposition mapSections(QuestionnaireResponse questionnaireResponse, D4LQuestionnaireComposition d4LQuestionnaireComposition) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent item : questionnaireResponse.getItem()) {
            switch (item.getLinkId()) {
                case P:
                    generalInformation.map(item.getItem());
                    break;
                case C:
                    generalInformation.mapContactWithInfected(item.getItem());
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
        return populateD4lQuestionnaireComposition(d4LQuestionnaireComposition);

    }

    private D4LQuestionnaireComposition populateD4lQuestionnaireComposition(D4LQuestionnaireComposition d4LQuestionnaireComposition) {
        d4LQuestionnaireComposition.setMandatoryFields();
        d4LQuestionnaireComposition.setAllgemeineAngaben(generalInformation.toComposition());
        d4LQuestionnaireComposition.setMedikamenteImpfungen(medication.toComposition());
        d4LQuestionnaireComposition.setSymptome(symptoms.toComposition());
        d4LQuestionnaireComposition.setVorGrunderkrankungen(anamnesis.toComposition());
        return d4LQuestionnaireComposition;
    }


}