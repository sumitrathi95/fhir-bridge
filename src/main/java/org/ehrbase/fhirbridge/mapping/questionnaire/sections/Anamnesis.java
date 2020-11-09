package org.ehrbase.fhirbridge.mapping.questionnaire.sections;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.*;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;

public class Anamnesis extends QuestionnaireSection {
    private static final String D0 = "D0";
    private static final String D1 = "D1";
    private static final String D2 = "D2";
    private static final String D3 = "D3";

    private Optional<ChronischeLungenkrankheitEvaluation> chronischeLungenkrankheitEvaluationQuestion = Optional.empty();
    private Optional<DiabetesEvaluation> diabetesEvaluationQuestion = Optional.empty();
    private Optional<HerzerkrankungEvaluation> herzerkrankungEvaluationQuestion = Optional.empty();
    private Optional<AdipositasEvaluation> adipositasEvaluationQuestion = Optional.empty();

    public Anamnesis(TemporalAccessor authored) {
        super(authored);
    }


    @Override
    public void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            if (getValueCode(question).isPresent()) {
                extractAnamnesis(question);
            }

        }
    }


    private void extractAnamnesis(QuestionnaireResponse.QuestionnaireResponseItemComponent question) {
        try {
            switch (question.getLinkId()) {
                case D0:
                    mapChronicLungDisease(getQuestionValueCodeToString(question));
                    break;
                case D1:
                    mapDiabetes(getQuestionValueCodeToString(question));
                    break;
                 case D2:
                    this.mapHeartDisease(getQuestionValueCodeToString(question));
                    break;
                case D3:
                    this.mapObesity(getQuestionValueCodeToString(question));
                    break;
                default:
                    throw new UnprocessableEntityException("LinkId " + question.getLinkId() + " undefined");
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }



    protected void mapChronicLungDisease(String chronicLungDisease) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ChronischeLungenkrankheitEvaluation chronischeLungenkrankheitEvaluation = new ChronischeLungenkrankheitEvaluation();
        chronischeLungenkrankheitEvaluation.setLanguage(Language.DE);
        chronischeLungenkrankheitEvaluation.setSubject(new PartySelf());
        chronischeLungenkrankheitEvaluation.setNameDesProblemsDerDiagnoseValue("Chronische Lungenkrankheit");
        setVorhandenerDefiningCode(chronicLungDisease, chronischeLungenkrankheitEvaluation);
        chronischeLungenkrankheitEvaluationQuestion = Optional.of(chronischeLungenkrankheitEvaluation);
    }

    protected void mapDiabetes(String diabetes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        DiabetesEvaluation diabetesEvaluation = new DiabetesEvaluation();
        diabetesEvaluation.setLanguage(Language.DE);
        diabetesEvaluation.setSubject(new PartySelf());
        diabetesEvaluation.setNameDesProblemsDerDiagnoseValue("Diabetes");
        setVorhandenerDefiningCode(diabetes, diabetesEvaluation);
        diabetesEvaluationQuestion = Optional.of(diabetesEvaluation);
    }

    protected void mapHeartDisease(String heartDisease) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        HerzerkrankungEvaluation herzerkrankungEvaluation = new HerzerkrankungEvaluation();
        herzerkrankungEvaluation.setLanguage(Language.DE);
        herzerkrankungEvaluation.setSubject(new PartySelf());
        herzerkrankungEvaluation.setNameDesProblemsDerDiagnoseValue("Herzerkrankung");
        setVorhandenerDefiningCode(heartDisease, herzerkrankungEvaluation);
        herzerkrankungEvaluationQuestion = Optional.of(herzerkrankungEvaluation);
    }

    protected void mapObesity(String obesity) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        AdipositasEvaluation adipositasEvaluation = new AdipositasEvaluation();
        adipositasEvaluation.setLanguage(Language.DE);
        adipositasEvaluation.setSubject(new PartySelf());
        adipositasEvaluation.setNameDesProblemsDerDiagnoseValue("Adipositas");
        setVorhandenerDefiningCode(obesity, adipositasEvaluation);
        adipositasEvaluationQuestion = Optional.of(adipositasEvaluation);
    }


    private void setVorhandenerDefiningCode(String code, Object evaluationObject) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method vorhandenerDefiningCodeSetter = getSetMethodForVorhandenDefiningCode(evaluationObject);
        if(code.equals(VorhandenDefiningcode.JA.getCode())){
            vorhandenerDefiningCodeSetter.invoke(evaluationObject, VorhandenDefiningcode.JA);
        }else if(code.equals(VorhandenDefiningcode.NEIN.getCode())){
            vorhandenerDefiningCodeSetter.invoke(evaluationObject, VorhandenDefiningcode.NEIN);
        }else if(code.equals(VorhandenDefiningcode.ICH_WEISS_ES_NICHT.getCode())){
            vorhandenerDefiningCodeSetter.invoke(evaluationObject, VorhandenDefiningcode.ICH_WEISS_ES_NICHT);
        }else{
            throw new UnprocessableEntityException("The code " + code + " for Question: " + evaluationObject.getClass().getName().split("\\.")[6].replace("Evaluation", "") + " cannot be mapped, please enter a valid code valid codes are: Yes (LA33-6), No (LA32-8), dont know (LA12688-0)");
        }

    }

    private Method getSetMethodForVorhandenDefiningCode(Object evaluationObject) throws NoSuchMethodException {
        if (evaluationObject.getClass() == DiabetesEvaluation.class) {
            return DiabetesEvaluation.class.getDeclaredMethod("setVorhandenDefiningcode", VorhandenDefiningcode.class);
        }else if (evaluationObject.getClass() == ChronischeLungenkrankheitEvaluation.class){
            return ChronischeLungenkrankheitEvaluation.class.getDeclaredMethod("setVorhandenDefiningcode", VorhandenDefiningcode.class);
        }else if (evaluationObject.getClass() == HerzerkrankungEvaluation.class) {
            return HerzerkrankungEvaluation.class.getDeclaredMethod("setVorhandenDefiningcode", VorhandenDefiningcode.class);
        }else if (evaluationObject.getClass() == AdipositasEvaluation.class) {
            return AdipositasEvaluation.class.getDeclaredMethod("setVorhandenDefiningcode", VorhandenDefiningcode.class);
        }else{
            throw new NoSuchMethodException("Class "+evaluationObject.getClass().getCanonicalName()+" is not supported for this method, only the classes DiabetesEvaluation, ChronischeLungenkrankheitEvaluation, HerzerkrankungEvaluation and AdipositasEvaluation are");
        }
    }

    @Override
   public VorGrunderkrankungenSection toComposition() {
        VorGrunderkrankungenSection vorGrunderkrankungenSection = new VorGrunderkrankungenSection();
        chronischeLungenkrankheitEvaluationQuestion.ifPresent(vorGrunderkrankungenSection::setChronischeLungenkrankheit);
        diabetesEvaluationQuestion.ifPresent(vorGrunderkrankungenSection::setDiabetes);
        herzerkrankungEvaluationQuestion.ifPresent(vorGrunderkrankungenSection::setHerzerkrankung);
        adipositasEvaluationQuestion.ifPresent(vorGrunderkrankungenSection::setAdipositas);
        return vorGrunderkrankungenSection;
    }
}