package org.ehrbase.fhirbridge.mapping.BlutGasAnalyse;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.KohlendioxidpartialdruckMapper;
import org.ehrbase.fhirbridge.mapping.FhirConditionOpenehrDiagnose;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.StatusDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FHIRObservationBloodGasOpenehrBlutgasAnalyse {

    private FHIRObservationBloodGasOpenehrBlutgasAnalyse() {
    }

    public static BefundDerBlutgasanalyseComposition map(Observation bloodGasPanel, Observation oxygenPartialPressure,
                                                         Observation pH, Observation carbonDioxidePartialPressure, Observation oxygenSaturation) {
        BefundDerBlutgasanalyseComposition befundDerBlutgasanalyseComposition = new BefundDerBlutgasanalyseComposition();

        befundDerBlutgasanalyseComposition.setStatusDefiningcode(mapStatus(bloodGasPanel));
        befundDerBlutgasanalyseComposition.setKategorieValue(mapKategorie(bloodGasPanel));

        LaborergebnisBefundMapper fhirObservationOpenehrLaborergebnisBefund = new LaborergebnisBefundMapper(bloodGasPanel, oxygenPartialPressure, pH, carbonDioxidePartialPressure, oxygenSaturation);
        befundDerBlutgasanalyseComposition.setLaborergebnis(fhirObservationOpenehrLaborergebnisBefund.map());

        //Mandatory Stuff
        befundDerBlutgasanalyseComposition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        befundDerBlutgasanalyseComposition.setLocation("test"); //FIXME: sensible value
        befundDerBlutgasanalyseComposition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        befundDerBlutgasanalyseComposition.setTerritory(Territory.DE);
        befundDerBlutgasanalyseComposition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        befundDerBlutgasanalyseComposition.setStartTimeValue(bloodGasPanel.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());

        befundDerBlutgasanalyseComposition.setComposer(new PartySelf()); //FIXME: sensible value

        return befundDerBlutgasanalyseComposition;
    }

    //TODO match codes not strings
    private static StatusDefiningcode mapStatus(Observation fhirObservation) {
        switch (fhirObservation.getStatusElement().getCode()) {
            case "registered":
                return StatusDefiningcode.REGISTRIERT;
            case "final":
                return StatusDefiningcode.FINAL;
            case "amended":
                return StatusDefiningcode.GEANDERT;
            case "preliminary":
                return StatusDefiningcode.VORLAUFIG;
            default:
                throw new IllegalStateException("Invalid Code " + fhirObservation.getStatusElement().getCode() + "" +
                        " for mapping of 'status', valid codes are: registered, final, amended and preliminary");
        }
    }

    //TODO can be a list of CodeableConcepts, but openEHR field is just a string for now i appended all
    private static String mapKategorie(Observation fhirObservation) {
        StringBuilder categories = new StringBuilder();
        for (CodeableConcept codeableConcept : fhirObservation.getCategory()
        ) {
            categories.append(codeableConcept.getText());
        }
        return categories.toString();
    }


}


