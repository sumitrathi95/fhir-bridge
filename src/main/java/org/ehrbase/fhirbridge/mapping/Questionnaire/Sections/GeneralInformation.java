package org.ehrbase.fhirbridge.mapping.Questionnaire.Sections;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.AllgemeineAngabenSection;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.AlterObservation;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.AlterskategorieDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.OffsetDateTime;
import java.util.List;

public class GeneralInformation extends QuestionnaireSection{
    private final String P0 = "P0";
    private final String P2 = "P2";
    private final String P3 = "P3";
    private final String P4 = "P4";
    private final String P5 = "P5";
    private final String P6 = "P6";

    private final String C0 = "C0";


    private AlterObservation alterObservation;
    private String livingSituation;
    private Boolean privateCaregiver;
    private Boolean smoker;
    private Boolean pregnant;
    private String nurse;
    private Boolean contactWithInfected;


    @Override
    public void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        extractGeneralInformation(item);
    }

    private void extractGeneralInformation(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            switch (question.getLinkId()) {
                case P0:
                    mapAge((String) getValueCode(question));
                    break;
                case P2:
                    mapLivingSituation((String) getValueCode(question));
                    break;
                case P3:
                    mapPrivateCaregiver(getBooleanValueCode(question));
                    break;
                case P4:
                    mapNurse((String) getValueCode(question));
                    break;
                case P5:
                    mapSmoker(getBooleanValueCode(question));
                    break;
                case P6:
                    mapPregnant(getBooleanValueCode(question)); //This one not
                    break;
                default:
                    throw new IllegalArgumentException("LinkId " + question.getLinkId() + " undefined");

            }
        }
    }

    public void mapContactWithInfected(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item){}


    protected void mapAge(String age) {
        alterObservation = new AlterObservation();
        alterObservation.setLanguage(Language.EN);
        alterObservation.setSubject(new PartySelf());
        alterObservation.setTimeValue(OffsetDateTime.now());

        if(age.equals(AlterskategorieDefiningcode.JUNGER_ALS40.getCode())){
            this.alterObservation.setAlterskategorieDefiningcode(AlterskategorieDefiningcode.JUNGER_ALS40);
        }else if(age.equals(AlterskategorieDefiningcode.N6170.getCode())){
            this.alterObservation.setAlterskategorieDefiningcode(AlterskategorieDefiningcode.N6170);
        }else if(age.equals(AlterskategorieDefiningcode.UBER80.getCode())){
            this.alterObservation.setAlterskategorieDefiningcode(AlterskategorieDefiningcode.UBER80);
        }else if(age.equals(AlterskategorieDefiningcode.N7180.getCode())){
            this.alterObservation.setAlterskategorieDefiningcode(AlterskategorieDefiningcode.N7180);
        }else if(age.equals(AlterskategorieDefiningcode.N4050.getCode())){
            this.alterObservation.setAlterskategorieDefiningcode(AlterskategorieDefiningcode.N4050);
        }else if(age.equals(AlterskategorieDefiningcode.N5160.getCode())){
            this.alterObservation.setAlterskategorieDefiningcode(AlterskategorieDefiningcode.N5160);
        }else{
            throw new IllegalArgumentException("The code for age:"+ age +" cannot be mapped, plese enter a valid code e.g. 61-70");
        }
    }

    protected void mapLivingSituation(String housingSituation) {
        this.livingSituation = housingSituation;
    }

    protected void mapPrivateCaregiver(Boolean privateCaregiver) {
        this.privateCaregiver = privateCaregiver;
    }

    protected void mapSmoker(Boolean smoker) {
        this.smoker = smoker;
    }

    protected void mapPregnant(Boolean pregnant) {
        this.pregnant = pregnant;
    }

    protected void mapNurse(String nurse) {
        this.nurse = nurse;
    }

    protected void mapContactWithInfected(Boolean contactWithInfected) {
        this.contactWithInfected = contactWithInfected;
    }


    private void extractContactWithInfected(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item) {
        for (QuestionnaireResponse.QuestionnaireResponseItemComponent question : item) {
            switch (question.getLinkId()) {
                case C0:
                    Object value = getValueCode(question); //TODO workaround since unknown can be an answer
                    if (value instanceof String)
                        value = Boolean.FALSE;
                    this.mapContactWithInfected((Boolean) value);
                    break;
                default:
                    throw new IllegalArgumentException("LinkId " + question.getLinkId() + " undefined");
            }
        }
    }

    public Boolean getContactWithInfected() {
        return contactWithInfected;
    }


    @Override
    public AllgemeineAngabenSection toComposition() {
        AllgemeineAngabenSection allgemeineAngabenSection = new AllgemeineAngabenSection();
        allgemeineAngabenSection.setAlter(alterObservation);

        return allgemeineAngabenSection;
    }


}
