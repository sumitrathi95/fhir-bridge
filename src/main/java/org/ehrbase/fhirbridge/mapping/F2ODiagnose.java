package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.DiagnoseComposition;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.definition.AtiopathogeneseSchweregradDvcodedtext;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.definition.DiagnoseEvaluation;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.definition.SchweregradDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.*;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;

/**
 * FHIR 2 openEHR - Diagnose
 */
public class F2ODiagnose {

    private static final Logger logger = LoggerFactory.getLogger(F2ODiagnose.class);

    private F2ODiagnose(){}

    public static DiagnoseComposition map(Condition fhirCondition) {

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
            throw new UnprocessableEntityException("severity code system should be http://snomed.info/sct, found "+ fhirSeverity.getSystem());
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
                throw new UnprocessableEntityException("Unexpected value: " + fhirSeverity.getCode());
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
            throw new UnprocessableEntityException("code.system should be http://fhir.de/CodeSystem/dimdi/icd-10-gm but found"+ fhirDiagnosis.getSystem());
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

        // check if the condition has a recorded date, if not, use the onset
        DateTimeType aDate = fhirCondition.getRecordedDateElement();
        logger.debug("recorded is {}", aDate);
        if (aDate.isEmpty())
        {
            logger.debug("recorded date is null trying onset");
            aDate = fhirCondition.getOnsetDateTimeType();
            logger.debug("onset is {}", aDate);
        }
        composition.setStartTimeValue(aDate.getValueAsCalendar().toZonedDateTime());

        // https://github.com/ehrbase/ehrbase_client_library/issues/31
        //        PartyProxy composer = new PartyIdentified();
        //        composition.setComposer(composer);

        composition.setComposer(new PartySelf());

        return composition;
    }
}