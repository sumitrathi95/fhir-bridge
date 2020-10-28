package org.ehrbase.fhirbridge.mapping.Questionnaire.Sections;

import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.VorGrunderkrankungenSection;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.util.List;

public class Anamnesis extends QuestionnaireSection {
    private String chronicLungDisease;
    private String diabetes;
    private String heartDisease;
    private String obesity;

    private static final String D0 = "D0";
    private static final String D1 = "D1";
    private static final String D2 = "D2";
    private static final String D3 = "D3";

    protected void mapChronicLungDisease(String chronicLungDisease) {
        this.chronicLungDisease = chronicLungDisease;
    }

    protected void mapDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    protected void mapHeartDisease(String heartDisease) {
        this.heartDisease = heartDisease;
    }

    protected void mapObesity(String obesity) {
        this.obesity = obesity;
    }


    private void extractAnamnesis(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            switch (question.getLinkId()) {
                case D0:
                    this.mapChronicLungDisease((String) getValueCode(question));
                    break;
                case D1:
                    this.mapDiabetes((String) getValueCode(question));
                    break;
                case D2:
                    this.mapHeartDisease((String) getValueCode(question));
                    break;
                case D3:
                    this.mapObesity((String) getValueCode(question));
                    break;
                default:
                    throw new IllegalArgumentException("LinkId " + question.getLinkId() + " undefined");
            }
        }
    }

    @Override
    public void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {

    }

    @Override
   public VorGrunderkrankungenSection toComposition() {
        return null;
    }
}