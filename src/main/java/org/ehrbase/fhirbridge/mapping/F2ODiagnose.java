package org.ehrbase.fhirbridge.mapping;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.DiagnoseComposition;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.definition.AtiopathogeneseSchweregradChoice;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.definition.AtiopathogeneseSchweregradDvcodedtext;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.definition.DiagnoseEvaluation;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.definition.SchweregradDefiningcode;
import org.ehrbase.fhirbridge.opt.kennzeichnungerregernachweissarscov2composition.KennzeichnungErregernachweisSARSCoV2Composition;
import org.ehrbase.fhirbridge.opt.kennzeichnungerregernachweissarscov2composition.definition.KennzeichnungErregernachweisEvaluation;
import org.ehrbase.fhirbridge.opt.shareddefinition.*;
import org.hl7.fhir.r4.model.*;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * FHIR 2 openEHR - Diagnose
 */
public class F2ODiagnose {

    static public DiagnoseComposition map(Condition fhirCondition) throws Exception {

        DiagnoseComposition composition = new DiagnoseComposition();

        // ========================================================================================
        // FHIR values
        DateTimeType fhirOnsetDateTime = fhirCondition.getOnsetDateTimeType();
        Coding fhirSeverity = fhirCondition.getSeverity().getCoding().get(0);
        Coding fhirDiagnosis = fhirCondition.getCode().getCoding().get(0);


        // mapping to openEHR
        DiagnoseEvaluation evaluation = new DiagnoseEvaluation();
        evaluation.setLanguage(Language.EN);
        evaluation.setSubject(new PartySelf());
        evaluation.setZuletztAktualisiertValueTree("last update");
        evaluation.setZuletztAktualisiertValue(OffsetDateTime.now());

        // severity
        SchweregradDefiningcode openEHRSeverity;
        if (!fhirSeverity.getSystem().equalsIgnoreCase("http://snomed.info/sct"))
        {
            throw new Exception("severity code system should be http://snomed.info/sct, found "+ fhirSeverity.getSystem());
        }
        switch (fhirSeverity.getCode())
        {
            case "24484000":
                openEHRSeverity = SchweregradDefiningcode.SCHWER;
            break;
            case "6736007":
                openEHRSeverity = SchweregradDefiningcode.MAIG;
            break;
            case "255604002":
                openEHRSeverity = SchweregradDefiningcode.LEICHT;
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + fhirSeverity.getCode());
        }

        AtiopathogeneseSchweregradDvcodedtext severityCoded = new AtiopathogeneseSchweregradDvcodedtext();
        severityCoded.setSchweregradDefiningcode(openEHRSeverity);
        evaluation.setSchweregrad(severityCoded);


        // der diagnose
        // the OPT uses only ICD10 codes from the German version of ICD
        // https://www.hl7.org/fhir/icd.html
        DerDiagnoseDefiningcode openEHRDiagnosis;
        if (!fhirDiagnosis.getSystem().equalsIgnoreCase("http://fhir.de/CodeSystem/dimdi/icd-10-gm"))
        {
            throw new Exception("code.system should be http://fhir.de/CodeSystem/dimdi/icd-10-gm but found"+ fhirDiagnosis.getSystem());
        }
        switch (fhirDiagnosis.getCode())
        {
            case "B97.2":
                openEHRDiagnosis = DerDiagnoseDefiningcode.B972;
            break;
            case "U07.1":
                openEHRDiagnosis = DerDiagnoseDefiningcode.U071;
            break;
            case "U07.2":
                openEHRDiagnosis = DerDiagnoseDefiningcode.U072;
            break;
            case "B34.2":
                openEHRDiagnosis = DerDiagnoseDefiningcode.B342;
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + fhirDiagnosis.getCode());
        }
        evaluation.setDerDiagnoseDefiningcode(openEHRDiagnosis);


        // date onset
        evaluation.setDerErstdiagnoseValue(fhirOnsetDateTime.getValueAsCalendar().toZonedDateTime());
        evaluation.setDerErstdiagnoseValueZeitpunktDesAuftretens("date onset");


        // body site
        if (fhirCondition.getBodySite().size() == 1)
        {
            String bodySiteName = fhirCondition.getBodySite().get(0).getCoding().get(0).getDisplay();
            evaluation.setKorperstelleValue("body site");
            evaluation.setKorperstelleValueStructure(bodySiteName);
        }


        composition.setDiagnose(evaluation);

        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.EN);
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.EMERGENCYCARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(OffsetDateTime.now());

// https://github.com/ehrbase/ehrbase_client_library/issues/31
//        PartyProxy composer = new PartyIdentified();
//        composition.setComposer(composer);

        composition.setComposer(new PartySelf());

        return composition;
    }
}