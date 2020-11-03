package org.ehrbase.fhirbridge.mapping.Questionnaire.Sections;

import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.SymptomeSection;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

public class Symptoms extends QuestionnaireSection {
    private static final String S0 = "S0";
    private static final String S1 = "S1";
    private static final String S3 = "S3";
    private static final String S4 = "S4";
    private static final String S5 = "S5";
    private static final String S6 = "S6";
    private static final String S7 = "S7";
    private static final String S8 = "S8";
    private static final String S9 = "S9";
    private static final String SA = "SA";
    private static final String SB = "SB";
    private static final String SC = "SC";
    private static final String SZ = "SZ";

    private Boolean fewer24h;
    private Boolean fewer4days;
    private Boolean chills;
    private Boolean tired;
    private Boolean bodyAches;
    private Boolean persistentCoughing;
    private Boolean runningNose;
    private Boolean diarrhea;
    private Boolean soreThroat;
    private Boolean headache;
    private Boolean outOfBreath;
    private Boolean tasteSmellLoss;
    private TemporalAccessor sinceWhenSymptoms;

    public Symptoms(Date authored) {
        super(authored);
    }

    @Override
    public void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            switch (question.getLinkId()) {
                case S0:
                    this.mapFewer24h(getBooleanValueCode(question));
                    break;
                case S1:
                    this.mapFewer4days(getBooleanValueCode(question));
                    break;
                case S3:
                    this.mapChills(getBooleanValueCode(question));
                    break;
                case S4:
                    this.mapTired(getBooleanValueCode(question));
                    break;
                case S5:
                    this.mapBodyAches(getBooleanValueCode(question));
                    break;
                case S6:
                    this.mapPersistentCoughing(getBooleanValueCode(question));
                    break;
                case S7:
                    this.mapRunningNose(getBooleanValueCode(question));
                    break;
                case S8:
                    this.mapDiarrhea(getBooleanValueCode(question));
                    break;
                case S9:
                    this.mapSoreThroat(getBooleanValueCode(question));
                    break;
                case SA:
                    this.mapHeadache(getBooleanValueCode(question));
                    break;
                case SB:
                    this.mapOutOfBreath(getBooleanValueCode(question));
                    break;
                case SC:
                    this.mapTasteSmellLoss(getBooleanValueCode(question));
                    break;
                case SZ:
                    this.mapSinceWhenSymptoms(getValueDate(question));
                    break;
                default:
                    throw new IllegalArgumentException("LinkId " + question.getLinkId() + " undefined");
            }
        }
    }

    private void mapFewer24h(Boolean fewer24h) {
        this.fewer24h = fewer24h;
    }

    private void mapFewer4days(Boolean fewer4days) {
        this.fewer4days = fewer4days;
    }

    private void mapChills(Boolean chills) {
        this.chills = chills;
    }

    private void mapTired(Boolean tired) {
        this.tired = tired;
    }

    private void mapBodyAches(Boolean bodyAches) {
        this.bodyAches = bodyAches;
    }

    private void mapPersistentCoughing(Boolean persistentCoughing) {
        this.persistentCoughing = persistentCoughing;
    }

    private void mapRunningNose(Boolean runningNose) {        this.runningNose = runningNose;    }

    private void mapDiarrhea(Boolean diarrhea) {
        this.diarrhea = diarrhea;
    }

    private void mapSoreThroat(Boolean soreThroat) {      this.soreThroat = soreThroat;    }

    private void mapHeadache(Boolean headache) {
        this.headache = headache;
    }

    private void mapOutOfBreath(Boolean outOfBreath) {
        this.outOfBreath = outOfBreath;
    }

    private void mapTasteSmellLoss(Boolean tasteSmellLoss) {
        this.tasteSmellLoss = tasteSmellLoss;
    }

    private void mapSinceWhenSymptoms(TemporalAccessor sinceWhenSymptoms) {
        this.sinceWhenSymptoms = sinceWhenSymptoms;
    }

    @Override
    public SymptomeSection toComposition() {
        return null;
    }
}