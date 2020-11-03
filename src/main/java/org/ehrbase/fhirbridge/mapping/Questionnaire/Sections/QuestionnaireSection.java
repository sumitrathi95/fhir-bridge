package org.ehrbase.fhirbridge.mapping.Questionnaire.Sections;

import org.ehrbase.fhirbridge.mapping.Questionnaire.CodingMapper;
import org.ehrbase.fhirbridge.opt.CompositionAbstract;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.model.QuestionnaireResponse;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public abstract class QuestionnaireSection {


    protected final Date authored;

    public QuestionnaireSection(Date authored) {
        this.authored = authored;
    }

    Object getValueCode(QuestionnaireResponse.QuestionnaireResponseItemComponent value) {
        try {
            Object code = value.getAnswer().get(0).getValueCoding().getCode();
            String codeString = "" + code;

            if (CodingMapper.isLoincCode(codeString))
                code = CodingMapper.decodeLOINCCode(codeString);

            if (CodingMapper.isFhirCode(codeString))
                code = CodingMapper.decodeFhirCode(codeString);

            return (code != null) ? code : " ";
        } catch (NullPointerException | FHIRException e) { //TODO fix to support every value (at the moment only codes or empty)
            return " ";
        }
    }


    Boolean getBooleanValueCode(QuestionnaireResponse.QuestionnaireResponseItemComponent value) {
        String code = (String) getValueCode(value);
        return CodingMapper.parseStringToBoolean(code);
    }


    LocalDate getValueDate(QuestionnaireResponse.QuestionnaireResponseItemComponent value) {
        return LocalDate.parse(value.getAnswer().get(0).getValueDateType().getValueAsString());
    }

    public abstract void map(List<QuestionnaireResponse.QuestionnaireResponseItemComponent> item);
    public abstract Object toComposition();

}
