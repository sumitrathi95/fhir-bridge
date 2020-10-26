package org.ehrbase.fhirbridge.mapping.Questionnaire;

import org.ehrbase.fhirbridge.opt.CompositionAbstract;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.hl7.fhir.r4.model.DomainResource;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

public class FhirObservationToOpenehrQuestionnaire {
    // eigene ehrId

    public static CompositionAbstract map(DomainResource fhirResource) {
        QuestionnaireResponse questionnaireResponse = (QuestionnaireResponse) fhirResource;
        QuestionnaireResponseDAO questionnaireResponsePOJO = QuestionnaireResponseParser.parseToPojo(questionnaireResponse);
        D4LQuestionnaireComposition d4LQuestionnaireComposition = (D4LQuestionnaireComposition) generateRequiredFields(new D4LQuestionnaireComposition());
        D4LQuestionnaireGenerator d4LQuestionnaireGenerator = new D4LQuestionnaireGenerator();
        d4LQuestionnaireComposition =  d4LQuestionnaireGenerator.parse(questionnaireResponsePOJO, d4LQuestionnaireComposition);
        return d4LQuestionnaireComposition;
    }

}