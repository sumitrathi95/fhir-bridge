package org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers;

import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.SauerstoffpartialdruckCluster;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.UntersuchterAnalytDefiningcode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class SauerstoffpartialdruckMapper extends LaboratoryTestAnalyteMapper{
    public SauerstoffpartialdruckMapper(Observation fhirObservation) {
        super(fhirObservation);
    }

    public SauerstoffpartialdruckCluster map() {
        SauerstoffpartialdruckCluster sauerstoffpartialdruckCluster = new SauerstoffpartialdruckCluster();
        sauerstoffpartialdruckCluster.setErgebnisStatusValue(mapErgebnisStatus());
        sauerstoffpartialdruckCluster.setAnalytResultatUnits("mmHg");
        sauerstoffpartialdruckCluster.setUntersuchterAnalytDefiningcode(mapUntersuchterAnalyt());
        sauerstoffpartialdruckCluster.setErgebnisStatusValue(mapValue());
        return sauerstoffpartialdruckCluster;
    }

    @Override
    protected UntersuchterAnalytDefiningcode mapUntersuchterAnalyt() {
        UntersuchterAnalytDefiningcode oxygenBlood = UntersuchterAnalytDefiningcode.OXYGEN_IN_BLOOD;
        UntersuchterAnalytDefiningcode oxygenArterial= UntersuchterAnalytDefiningcode.OXYGEN_IN_ARTERIAL_BLOOD;
        UntersuchterAnalytDefiningcode oxygenCapillary = UntersuchterAnalytDefiningcode.OXYGEN_IN_CAPILLARY_BLOOD;

        for (Coding coding : fhirObservation.getCode().getCoding()) {
            String code = coding.getCode();
            if (code.equals(oxygenBlood.getCode())) {
                return oxygenBlood;
            } else if (code.equals(oxygenArterial.getCode())) {
                return oxygenArterial;
            } else if (code.equals(oxygenCapillary.getCode())) {
                return oxygenCapillary;
            }
        }
        throw new IllegalArgumentException("The coding of the Untersuchter Analyte cannot be mapped, since code " + fhirObservation.getCode().getCoding() + " is unknown");
    }
}

