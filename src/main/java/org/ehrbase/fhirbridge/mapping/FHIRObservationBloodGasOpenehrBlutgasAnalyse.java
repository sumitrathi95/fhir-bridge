package org.ehrbase.fhirbridge.mapping;

import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.StatusDefiningcode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FHIRObservationBloodGasOpenehrBlutgasAnalyse {

    private static final Logger logger = LoggerFactory.getLogger(FhirConditionOpenehrDiagnose.class);

    private FHIRObservationBloodGasOpenehrBlutgasAnalyse() {
    }

    public BefundDerBlutgasanalyseComposition map(Observation fhirObservation) {
        BefundDerBlutgasanalyseComposition befundDerBlutgasanalyseComposition = new BefundDerBlutgasanalyseComposition();

        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();

        befundDerBlutgasanalyseComposition.setStatusDefiningcode(mapStatus(fhirObservation));
        befundDerBlutgasanalyseComposition.setKategorieValue(mapKategorie(fhirObservation));

        befundDerBlutgasanalyseComposition.setLaborergebnis(FhirDiagnosticReportOpenehrLabResults.map(fhirObservation.));


        return befundDerBlutgasanalyseComposition;

    }

    private StatusDefiningcode mapStatus(Observation fhirObservation){
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
    private String mapKategorie(Observation fhirObservation){
        StringBuilder categories = new StringBuilder();
        for (CodeableConcept codeableConcept : fhirObservation.getCategory()
             ) {
            categories.append(codeableConcept.getText());
        }
        return categories.toString();
    }


}


