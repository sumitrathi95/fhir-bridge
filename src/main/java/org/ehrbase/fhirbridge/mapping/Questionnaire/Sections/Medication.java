package org.ehrbase.fhirbridge.mapping.Questionnaire.Sections;

import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.MedikamenteImpfungenSection;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.util.List;

public class Medication extends QuestionnaireSection {

    private static final String M0 = "M0";
    private static final String M1 = "M1";
    private static final String M2 = "M2";


    private Boolean steroids;
    private Boolean immunosuppressants;
    private Boolean vaccinatedFlu;


    private void extractMedication(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            switch (question.getLinkId()) {
                case M0:
                    this.mapSteroids(getBooleanValueCode(question));
                    break;
                case M1:
                    this.mapImmunosuppressants(getBooleanValueCode(question));
                    break;
                case M2:
                    this.mapVaccinatedFlu(getBooleanValueCode(question));
                    break;
                default:
                    throw new IllegalArgumentException("LinkId " + question.getLinkId() + " undefined");
            }
        }
    }

    protected void mapSteroids(Boolean steroids) {
        this.steroids = steroids;
    }

    protected void mapImmunosuppressants(Boolean immunosuppressants) {
        this.immunosuppressants = immunosuppressants;
    }

    protected void mapVaccinatedFlu(Boolean vaccinatedFlu) {
        this.vaccinatedFlu = vaccinatedFlu;
    }



    @Override
    public void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {

    }

    @Override
    public MedikamenteImpfungenSection toComposition() {
        return null;
    }
}
