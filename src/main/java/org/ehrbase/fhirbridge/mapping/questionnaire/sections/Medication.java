package org.ehrbase.fhirbridge.mapping.questionnaire.sections;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.*;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.TransitionDefiningcode;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Medication extends QuestionnaireSection {

    private static final String M0 = "M0";
    private static final String M1 = "M1";
    private static final String M2 = "M2";


    private Optional<ArzneimittelverwaltungAction> arzneimittelverwaltungActionQuestion = Optional.empty();
    private Optional<ImmunsuppressivaEvaluation> immunsuppressivaEvaluationQuestion = Optional.empty();
    private Optional<KortisonEvaluation> kortisonEvaluationQuestion = Optional.empty();

    public Medication(TemporalAccessor authored) {
        super(authored);
    }


    @Override
    public void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            if (getValueCode(question).isPresent()) {
                extractMedication(question);
            }

        }
    }

    private void extractMedication(QuestionnaireResponse.QuestionnaireResponseItemComponent question) {
            switch (question.getLinkId()) {
                case M0:
                    this.mapSteroids(getQuestionLoincYesNoDontKnowToBoolean(question));
                    break;
                case M1:
                    this.mapImmunosuppressants(getQuestionLoincYesNoDontKnowToBoolean(question));
                    break;
                case M2:
                    this.mapVaccinatedFlu(getQuestionLoincYesNoToBoolean(question));
                    break;
                default:
                    throw new UnprocessableEntityException("LinkId " + question.getLinkId() + " undefined");
            }
    }

    protected void mapSteroids(Boolean useSteroids) {
        KortisonEvaluation kortisonEvaluation = new KortisonEvaluation();
        kortisonEvaluation.setLanguage(Language.DE);
        kortisonEvaluation.setSubject(new PartySelf());
        kortisonEvaluation.setAktuelleAnwendungValue(useSteroids);
        kortisonEvaluationQuestion = Optional.of(kortisonEvaluation);

    }

    protected void mapImmunosuppressants(Boolean useImmunosuppressants) {
        ImmunsuppressivaEvaluation immunsuppressivaEvaluation = new ImmunsuppressivaEvaluation();
        immunsuppressivaEvaluation.setAktuelleAnwendungValue(useImmunosuppressants);
        immunsuppressivaEvaluation.setLanguage(Language.DE);
        immunsuppressivaEvaluation.setSubject(new PartySelf());
        immunsuppressivaEvaluationQuestion = Optional.of(immunsuppressivaEvaluation);
    }

    protected void mapVaccinatedFlu(Boolean wasVaccinatedFlu) {
        ArzneimittelverwaltungAction arzneimittelverwaltungAction = new ArzneimittelverwaltungAction();
        arzneimittelverwaltungAction.setArzneimittelValue("Grippeimpfung");
        arzneimittelverwaltungAction.setLanguage(Language.DE);
        arzneimittelverwaltungAction.setSubject(new PartySelf());
        arzneimittelverwaltungAction.setTimeValue(authored);

        //TODO what if false
        arzneimittelverwaltungAction.setTransitionDefiningcode(TransitionDefiningcode.FINISH);

        arzneimittelverwaltungAction.setArzneimittelbehandlungIstAbgeschlossenDefiningcodeCurrentState(RezeptWurdeAusgefuhrtDefiningcode.COMPLETED);
        arzneimittelverwaltungAction.setArzneimittelbehandlungIstAbgeschlossenDefiningcodeCurrentState(RezeptWurdeAusgefuhrtDefiningcode.COMPLETED);


        arzneimittelverwaltungAction.setVergangeneImpfungSeitOktober2019Value(wasVaccinatedFlu);
        arzneimittelverwaltungActionQuestion = Optional.of(arzneimittelverwaltungAction);
    }



    @Override
    public MedikamenteImpfungenSection toComposition() {
        MedikamenteImpfungenSection medikamenteImpfungenSection = new MedikamenteImpfungenSection();
        List<ArzneimittelverwaltungAction> arzneimittelverwaltungActionsList = new ArrayList<>();
        arzneimittelverwaltungActionQuestion.ifPresent(arzneimittelverwaltungActionsList::add);
        medikamenteImpfungenSection.setArzneimittelverwaltung(arzneimittelverwaltungActionsList);

        immunsuppressivaEvaluationQuestion.ifPresent(medikamenteImpfungenSection::setImmunsuppressiva);
        kortisonEvaluationQuestion.ifPresent(medikamenteImpfungenSection::setKortison);
        return medikamenteImpfungenSection;

    }
}
