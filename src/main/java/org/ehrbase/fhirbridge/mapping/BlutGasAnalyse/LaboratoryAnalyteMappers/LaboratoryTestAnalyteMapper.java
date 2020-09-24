package org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers;

import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.KohlendioxidpartialdruckCluster;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.SauerstoffpartialdruckCluster;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.StatusDefiningcode;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.UntersuchterAnalytDefiningcode;
import org.hl7.fhir.r4.model.Observation;

abstract class LaboratoryTestAnalyteMapper {
    protected final Observation fhirObservation;

    protected LaboratoryTestAnalyteMapper(Observation fhirObservation) {
        this.fhirObservation = fhirObservation;
    }

    //TODO duplicate
    //TODO in archtype these fields are in englisch, meanwhile on the top level they are german
    // here also a String is required instead of StatusCode
    protected String mapErgebnisStatus() {
        switch (fhirObservation.getStatusElement().getCode()) {
            case "registered":
                return StatusDefiningcode.REGISTRIERT.getValue();
            case "final":
                return StatusDefiningcode.FINAL.getValue();
            case "amended":
                return StatusDefiningcode.GEANDERT.getValue();
            case "preliminary":
                return StatusDefiningcode.VORLAUFIG.getValue();
            default:
                throw new IllegalStateException("Invalid Code " + fhirObservation.getStatusElement().getCode() + "" +
                        " for mapping of 'status', valid codes are: registered, final, amended and preliminary");
        }
    }

    protected String mapValue() {
        // FIXME in the profile it is Observation.value[x]:valueQuantity.value and NOT Observation.:valueQuantity.value
        //return fhirObservation.getValue().getValueQuantity().getValue();
        return fhirObservation.getValueQuantity().getValue().toString();
    }
    abstract UntersuchterAnalytDefiningcode mapUntersuchterAnalyt();

    //TODO introduce CLUSTER and Composition as classes in opt for return types

    //  protected abstract Cluster map();

}

