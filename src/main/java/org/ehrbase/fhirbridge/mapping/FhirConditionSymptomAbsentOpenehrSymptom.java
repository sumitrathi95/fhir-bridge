package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.shareddefinition.*;
import org.ehrbase.fhirbridge.opt.symptomcomposition.SymptomComposition;
import org.ehrbase.fhirbridge.opt.symptomcomposition.definition.AusgeschlossenesSymptomEvaluation;
import org.ehrbase.fhirbridge.opt.symptomcomposition.definition.VorliegendesSymptomObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class FhirConditionSymptomAbsentOpenehrSymptom {

    private static final Logger logger = LoggerFactory.getLogger(FhirConditionOpenehrDiagnose.class);

    private static final Map<String, DiagnoseDefiningcode> diagnosenMap = new HashMap<>();

    static {
        for (DiagnoseDefiningcode diagnose : DiagnoseDefiningcode.values()) {
            diagnosenMap.put(diagnose.getCode(), diagnose);
        }
    }

    private FhirConditionSymptomAbsentOpenehrSymptom() {
    }



    public static SymptomComposition map(Condition condition) {

        SymptomComposition composition = new SymptomComposition();
        AusgeschlossenesSymptomEvaluation ausgeschlossenesSymptom = new AusgeschlossenesSymptomEvaluation();

        Coding coding = condition.getCode().getCoding().get(0);

        DiagnoseDefiningcode diagnose = null;

        // Neue Systeme werden eingepflegt sobald sie in DiagnoseDefiningcode definiert sind.
        if (coding.getSystem().equals("http://snomed.info/sct")) {
            diagnose = diagnosenMap.get(coding.getCode());
        }

        if (diagnose == null) {
            throw new UnprocessableEntityException("Unbekanntes Diagnose.");
        }

        ausgeschlossenesSymptom.setDiagnoseDefiningcode(diagnose);
        ausgeschlossenesSymptom.setAussageUberDenAusschlussDefiningcode(AussageUberDenAusschlussDefiningcode.N410594000);

        composition.setStartTimeValue(condition.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());

        ausgeschlossenesSymptom.setSubject(new PartySelf());
        ausgeschlossenesSymptom.setLanguage(Language.DE);

        composition.setAusgeschlossenesSymptom(ausgeschlossenesSymptom);


        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.DE);
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setComposer(new PartySelf());

        return composition;

    }

}
