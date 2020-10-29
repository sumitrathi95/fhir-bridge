package org.ehrbase.fhirbridge.mapping.BlutGasAnalyse;

import org.ehrbase.fhirbridge.fhir.provider.Bundle.BloodGasPanelBundle;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.StatusDefiningcode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class FHIRObservationBloodGasOpenehrBlutgasAnalyse {

    private FHIRObservationBloodGasOpenehrBlutgasAnalyse() {
    }

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

    private static String mapKategorie(Observation fhirObservation) {
        Optional<String> categoryCode;
        for (CodeableConcept codingEntry : fhirObservation.getCategory()) {
            categoryCode = getObservationCategory(codingEntry);
            if (categoryCode.isPresent()) {
                return categoryCode.get();
            }
        }
        throw new IllegalArgumentException("Category code is not defined in blood gas panel, therefore the bundle is incomplete. Please add category observation category to the panel");

    }

    private static Optional<String> getObservationCategory(CodeableConcept codings) {
        for (Coding categoryEntry : codings.getCoding()) {
            if (isObservationCategory(categoryEntry)) {
                return Optional.of(categoryEntry.getCode());
            }
        }
        return Optional.empty();
    }


    private static boolean isObservationCategory(Coding categories) {
        return categories.getSystem().equals("http://terminology.hl7.org/CodeSystem/observation-category");
    }

    public static BefundDerBlutgasanalyseComposition map(BloodGasPanelBundle bloodGasPanelBundle) {
        Observation bloodGasPanel = bloodGasPanelBundle.getBloodGasPanel();

        BefundDerBlutgasanalyseComposition befundDerBlutgasanalyseComposition = new BefundDerBlutgasanalyseComposition();

        befundDerBlutgasanalyseComposition.setStatusDefiningcode(mapStatus(bloodGasPanel));
        befundDerBlutgasanalyseComposition.setKategorieValue(mapKategorie(bloodGasPanel));
        befundDerBlutgasanalyseComposition.setMandatoryFields();
        befundDerBlutgasanalyseComposition.setStartTimeValue(bloodGasPanel.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());

        befundDerBlutgasanalyseComposition.setLaborergebnis(LaborergebnisBefundMapper.map(bloodGasPanelBundle));


        return befundDerBlutgasanalyseComposition;
    }
}


