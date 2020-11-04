package org.ehrbase.fhirbridge.mapping.questionnaire.sections;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.*;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeneralInformation extends QuestionnaireSection {
    private static final String P0 = "P0";
    private static final String P2 = "P2";
    private static final String P3 = "P3";
    private static final String P4 = "P4";
    private static final String P5 = "P5";
    private static final String P6 = "P6";
    private static final String C0 = "C0";


    private Optional<AlterObservation> alterObservationQuestion = Optional.empty();
    private Optional<WohnsituationEvaluation> wohnsituationEvaluationQuestion = Optional.empty();
    private Optional<PflegetatigkeitEvaluation> pflegetatigkeitEvaluationQuestion = Optional.empty();
    private Optional<AusschlussPflegetatigkeitEvaluation> ausschlussPflegetatigkeit = Optional.empty();
    private Optional<AusschlussRauchenEvaluation> ausschlussRauchen = Optional.empty();
    private Optional<ZusammenfassungRauchverhaltenEvaluation> zusammenfassungRauchverhaltenEvaluationQuestion = Optional.empty();
    private Optional<StatusSchwangerschaftStillzeitEvaluation> statusSchwangerschaftStillzeitEvaluationQuestion = Optional.empty();
    private Optional<UmgCovid19KontaktObservation> contactWithInfectedQuestion = Optional.empty();

    public GeneralInformation(TemporalAccessor authored) {
        super(authored);
    }


    @Override
    public void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        extractGeneralInformation(item);
    }

    private void extractGeneralInformation(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            if (getValueCode(question).isPresent()) {
                mapGeneralInformationQuestions(question);
            }

        }
    }

    private void mapGeneralInformationQuestions(QuestionnaireResponse.QuestionnaireResponseItemComponent question) {
        switch (question.getLinkId()) {
            case P0:
                mapAge(questionValueCodeToString(question));
                break;
            case P2:
                mapWohnsituationEvaluation(questionValueCodeToString(question));
                break;
            case P3:
                mapPrivateCaregiver(getQuestionLoincYesNoToBoolean(question));
                break;
            case P4:
                mapNurse(getQuestionLoincYesNoToBoolean(question));
                break;
            case P5:
                mapSmoker(getQuestionLoincYesNoToBoolean(question));
                break;
            case P6:
                mapPregnant(questionValueCodeToString(question));
                break;
            default:
                throw new IllegalArgumentException("LinkId " + question.getLinkId() + " undefined");

        }
    }


    protected void mapAge(String age) {
        AlterObservation alterObservation = new AlterObservation();
        alterObservation.setLanguage(Language.DE);
        alterObservation.setSubject(new PartySelf());
        alterObservation.setTimeValue(super.authored);
        alterObservation.setOriginValue(super.authored);
        if (age.equals(AlterskategorieDefiningcode.JUNGER_ALS40.getCode())) {
            alterObservation.setAlterskategorieDefiningcode(AlterskategorieDefiningcode.JUNGER_ALS40);
        } else if (age.equals(AlterskategorieDefiningcode.N6170.getCode())) {
            alterObservation.setAlterskategorieDefiningcode(AlterskategorieDefiningcode.N6170);
        } else if (age.equals(AlterskategorieDefiningcode.UBER80.getCode())) {
            alterObservation.setAlterskategorieDefiningcode(AlterskategorieDefiningcode.UBER80);
        } else if (age.equals(AlterskategorieDefiningcode.N7180.getCode())) {
            alterObservation.setAlterskategorieDefiningcode(AlterskategorieDefiningcode.N7180);
        } else if (age.equals(AlterskategorieDefiningcode.N4050.getCode())) {
            alterObservation.setAlterskategorieDefiningcode(AlterskategorieDefiningcode.N4050);
        } else if (age.equals(AlterskategorieDefiningcode.N5160.getCode())) {
            alterObservation.setAlterskategorieDefiningcode(AlterskategorieDefiningcode.N5160);
        } else if (!age.equals("")) {
            throw new IllegalArgumentException("The code for age:" + age + " cannot be mapped, plese enter a valid code e.g. 61-70");
        }
        alterObservationQuestion = Optional.of(alterObservation);
    }

    protected void mapWohnsituationEvaluation(String housingSituation) {
        WohnsituationEvaluation wohnsituationEvaluation = new WohnsituationEvaluation();
        if (housingSituation.equals(WohnsituationDefiningcode.WOHNT_MIT_ANDEREN_ZUSAMMEN.getCode())) {
            wohnsituationEvaluation.setWohnsituationDefiningcode(WohnsituationDefiningcode.WOHNT_MIT_ANDEREN_ZUSAMMEN);
        } else if (housingSituation.equals(WohnsituationDefiningcode.ALLEIN_WOHNEND.getCode())) {
            wohnsituationEvaluation.setWohnsituationDefiningcode(WohnsituationDefiningcode.ALLEIN_WOHNEND);
        } else if (!housingSituation.equals("")) {
            throw new IllegalArgumentException("The code for Wohnungsituation:" + housingSituation + " cannot be mapped, please enter a valid code e.g. Wohnt mit anderen zusammen (LOINC: LA9996-5)");
        }
        wohnsituationEvaluationQuestion = Optional.of(wohnsituationEvaluation);
    }

    protected void mapPrivateCaregiver(Boolean isPrivateCaregiver) {
        PflegetatigkeitEvaluation pflegetatigkeitEvaluation = getPflegetatigkeitEvaluation();
        pflegetatigkeitEvaluation.setPrivatValue(isPrivateCaregiver);
        pflegetatigkeitEvaluationQuestion = Optional.of(pflegetatigkeitEvaluation);
    }


    protected void mapNurse(Boolean isNurse) {
        PflegetatigkeitEvaluation pflegetatigkeitEvaluation = getPflegetatigkeitEvaluation();
        pflegetatigkeitEvaluation.setBeruflichValue(isNurse);
        pflegetatigkeitEvaluationQuestion = Optional.of(pflegetatigkeitEvaluation);
    }

    private PflegetatigkeitEvaluation getPflegetatigkeitEvaluation() {
        return pflegetatigkeitEvaluationQuestion.orElseGet(this::createPflegetatigkeitEvaluation);
    }

    protected PflegetatigkeitEvaluation createPflegetatigkeitEvaluation() {
        PflegetatigkeitEvaluation pflegetatigkeitEvaluation = new PflegetatigkeitEvaluation();
        pflegetatigkeitEvaluation.setLanguage(Language.DE);
        pflegetatigkeitEvaluation.setSubject(new PartySelf());

        pflegetatigkeitEvaluation.setAnzahlDerGepflegtenPersonenValue("Mindestens eine Person");
        pflegetatigkeitEvaluation.setFrequenzDerPflegeValue("Mindestens einmal die Woche");

        PflegetatigkeitGrundFurDieTatigkeitElement pflegetatigkeitGrundFurDieTatigkeitElement = new PflegetatigkeitGrundFurDieTatigkeitElement();
        pflegetatigkeitGrundFurDieTatigkeitElement.setValue("alterbedingten Beschwerden, chronischen Erkrankungen oder Gebrechlichkeit");

        pflegetatigkeitEvaluation.setGrundFurDieTatigkeit(new ArrayList<>() {{
            add(pflegetatigkeitGrundFurDieTatigkeitElement);
        }});

        return pflegetatigkeitEvaluation;


    }

    private void checkPflegetatigkeitAusschluss() {
        if (pflegetatigkeitEvaluationQuestion.isPresent()) {
            if (!pflegetatigkeitEvaluationQuestion.get().isPrivatValue() && !pflegetatigkeitEvaluationQuestion.get().isBeruflichValue()) {
                pflegetatigkeitEvaluationQuestion = Optional.empty();
                setAusschlussPflegetatigkeitEvaluation();
            }
        }
    }

    private void setAusschlussPflegetatigkeitEvaluation() {
        AusschlussPflegetatigkeitEvaluation ausschlussPflegetatigkeitEvaluation = new AusschlussPflegetatigkeitEvaluation();
        ausschlussPflegetatigkeitEvaluation.setLanguage(Language.DE);
        ausschlussPflegetatigkeitEvaluation.setSubject(new PartySelf());
        ausschlussPflegetatigkeitEvaluation.setAussageUberDenAusschlussValue("Pflegetätigkeit");
        ausschlussPflegetatigkeit = Optional.of(ausschlussPflegetatigkeitEvaluation);
    }


    protected void mapSmoker(Boolean isSmoker) {
        if (isSmoker) {
            ZusammenfassungRauchverhaltenEvaluation zusammenfassungRauchverhaltenEvaluation = new ZusammenfassungRauchverhaltenEvaluation();
            zusammenfassungRauchverhaltenEvaluation.setLanguage(Language.DE);
            zusammenfassungRauchverhaltenEvaluation.setSubject(new PartySelf());
            zusammenfassungRauchverhaltenEvaluation.setAllgemeinerStatusDefiningcode(AllgemeinerStatusDefiningcode.JA);
            zusammenfassungRauchverhaltenEvaluationQuestion = Optional.of(zusammenfassungRauchverhaltenEvaluation);
        } else {
            AusschlussRauchenEvaluation ausschlussRauchenEvaluation = new AusschlussRauchenEvaluation();
            ausschlussRauchenEvaluation.setLanguage(Language.DE);
            ausschlussRauchenEvaluation.setSubject(new PartySelf());
            ausschlussRauchenEvaluation.setAussageUberDenAusschlussValue("Rauchen");
            ausschlussRauchenEvaluation.setAusgeschlosseneKategorieValue("Nebenwirkungen auszulösende Substanz/Allergen");
            ausschlussRauchen = Optional.of(ausschlussRauchenEvaluation);
        }

    }

    private Boolean pregnantCodeLoincToBoolean(String pregnantCode) {
        if (pregnantCode.equals("LA15173-0")) {
            return true;
        } else if (pregnantCode.equals("LA26683-5") || pregnantCode.equals("LA4489-6")) { //TODO second code is unknown which is treated as No. Platform also says these Values are not allowed
            return false;
        } else {
            throw new UnprocessableEntityException("The code for Pregnancy:" + pregnantCode + " cannot be mapped, please enter a valid code e.g. pregnant (LA15173-0), not pregnant (LA26683-5) or unknown(LA4489-6) )");
        }
    }

    protected void mapPregnant(String isPregnant) {
        StatusSchwangerschaftStillzeitEvaluation stillzeitEvaluation = new StatusSchwangerschaftStillzeitEvaluation();
        stillzeitEvaluation.setSchwangerValue(pregnantCodeLoincToBoolean(isPregnant));
        stillzeitEvaluation.setLanguage(Language.DE);
        stillzeitEvaluation.setSubject(new PartySelf());
        statusSchwangerschaftStillzeitEvaluationQuestion = Optional.of(stillzeitEvaluation);
    }


    public void mapContactWithInfectedQuestion(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            if (question.getLinkId().equals(C0) && getValueCode(question).isPresent()) {
                    mapContactWithInfected(getContactWithInfectedBoolean(question));
            } else {
                throw new IllegalArgumentException("LinkId " + question.getLinkId() + " undefined");
            }
        }
    }


    public void mapContactWithInfected(Boolean item) {
        UmgCovid19KontaktObservation umgcovid19KontaktObservation = new UmgCovid19KontaktObservation();
        umgcovid19KontaktObservation.setKontaktZurCovid19PatientValue(item);
        umgcovid19KontaktObservation.setLanguage(Language.DE);
        umgcovid19KontaktObservation.setSubject(new PartySelf());
        umgcovid19KontaktObservation.setOriginValue(this.authored);
        umgcovid19KontaktObservation.setTimeValue(this.authored);
        contactWithInfectedQuestion = Optional.of(umgcovid19KontaktObservation);
    }



    private Boolean getContactWithInfectedBoolean(QuestionnaireResponse.QuestionnaireResponseItemComponent question) {
        return contactWithInfectedToBoolean(getValueCode(question).get().toString());
    }

    private Boolean contactWithInfectedToBoolean(String code) {
        if (code.equals("LA33-6")) {
            return true;
        } else if (code.equals("LA32-8") || code.equals("LA12688-0")) {
            return false;
        } else {
            throw new UnprocessableEntityException("\"" + code + "\" cannot be mapped to boolean, has to be either LA33-6, LA33-8 or LA12688-0");
        }
    }

    @Override
    public AllgemeineAngabenSection toComposition() {
        AllgemeineAngabenSection allgemeineAngabenSection = new AllgemeineAngabenSection();
        alterObservationQuestion.ifPresent(allgemeineAngabenSection::setAlter);
        wohnsituationEvaluationQuestion.ifPresent(allgemeineAngabenSection::setWohnsituation);
        checkPflegetatigkeitAusschluss();
        pflegetatigkeitEvaluationQuestion.ifPresent(allgemeineAngabenSection::setPflegetatigkeit);
        ausschlussPflegetatigkeit.ifPresent(allgemeineAngabenSection::setAusschlussPflegetatigkeit);
        zusammenfassungRauchverhaltenEvaluationQuestion.ifPresent(allgemeineAngabenSection::setZusammenfassungRauchverhalten);
        ausschlussRauchen.ifPresent(allgemeineAngabenSection::setAusschlussRauchen);
        statusSchwangerschaftStillzeitEvaluationQuestion.ifPresent(allgemeineAngabenSection::setStatusSchwangerschaftStillzeit);
        contactWithInfectedQuestion.ifPresent(allgemeineAngabenSection::setUmgCovid19Kontakt);
        return allgemeineAngabenSection;
    }


}
