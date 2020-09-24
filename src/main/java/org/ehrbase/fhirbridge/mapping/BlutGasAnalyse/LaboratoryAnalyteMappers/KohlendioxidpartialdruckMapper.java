package org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers;

import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.KohlendioxidpartialdruckCluster;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.UntersuchterAnalytDefiningcode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.math.BigDecimal;

public class KohlendioxidpartialdruckMapper extends LaboratoryTestAnalyteMapper {

    public KohlendioxidpartialdruckMapper(Observation fhirObservation) {
        super(fhirObservation);
    }

    public KohlendioxidpartialdruckCluster map() {
        KohlendioxidpartialdruckCluster kohlendioxidpartialdruckCluster = new KohlendioxidpartialdruckCluster();
        kohlendioxidpartialdruckCluster.setErgebnisStatusValue(mapErgebnisStatus());
        kohlendioxidpartialdruckCluster.setUntersuchterAnalytDefiningcode(mapUntersuchterAnalyt());
        kohlendioxidpartialdruckCluster.setAnalytResultatUnits("mmHg");
        kohlendioxidpartialdruckCluster.setErgebnisStatusValue(mapValue());
        return kohlendioxidpartialdruckCluster;
    }
    @Override
    protected UntersuchterAnalytDefiningcode mapUntersuchterAnalyt() {
        UntersuchterAnalytDefiningcode carbonDioxideBlood = UntersuchterAnalytDefiningcode.CARBON_DIOXIDE_IN_BLOOD;
        UntersuchterAnalytDefiningcode carbonDioxideArterial= UntersuchterAnalytDefiningcode.CARBON_DIOXIDE_IN_ARTERIAL_BLOOD;
        UntersuchterAnalytDefiningcode carbonDioxideCapillary = UntersuchterAnalytDefiningcode.CARBON_DIOXIDE_IN_CAPILLARY_BLOOD;

        for (Coding coding : fhirObservation.getCode().getCoding()) {
            String code = coding.getCode();
            if (code.equals(carbonDioxideBlood.getCode())) {
                return carbonDioxideBlood;
            } else if (code.equals(carbonDioxideArterial.getCode())) {
                return carbonDioxideArterial;
            } else if (code.equals(carbonDioxideCapillary.getCode())) {
                return carbonDioxideCapillary;
            }
        }
        throw new IllegalArgumentException("The coding of the Untersuchter Analyte cannot be mapped, since code " + fhirObservation.getCode().getCoding() + " is unknown");
    }



}
