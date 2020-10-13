package org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers;

import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.*;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class SauerstoffsaettigungMapper extends LaboratoryTestAnalyteMapper{
    public SauerstoffsaettigungMapper(Observation fhirObservation) {
        super(fhirObservation);
    }

    public SauerstoffsattigungCluster map() {
        SauerstoffsattigungCluster sauerstoffsattigungCluster = new SauerstoffsattigungCluster();
        sauerstoffsattigungCluster.setErgebnisStatusValue(mapErgebnisStatus());
        sauerstoffsattigungCluster.setUntersuchterAnalytDefiningcode(mapUntersuchterAnalyt());
        sauerstoffsattigungCluster.setAnalytResultatUnits("%");
        sauerstoffsattigungCluster.setAnalytResultatMagnitude(mapValue());
        return sauerstoffsattigungCluster;
    }

    @Override
    UntersuchterAnalytDefiningcode4 mapUntersuchterAnalyt() {
        UntersuchterAnalytDefiningcode4 oxygenSaturationInBlood = UntersuchterAnalytDefiningcode4.OXYGEN_SATURATION_IN_BLOOD;
        UntersuchterAnalytDefiningcode4 oxygenSaturationInArterialBlood= UntersuchterAnalytDefiningcode4.OXYGEN_SATURATION_IN_ARTERIAL_BLOOD;

        for (Coding coding : fhirObservation.getCode().getCoding()) {
            String code = coding.getCode();
            if (code.equals(oxygenSaturationInBlood.getCode())) {
                return oxygenSaturationInBlood;
            } else if (code.equals(oxygenSaturationInArterialBlood.getCode())) {
                return oxygenSaturationInArterialBlood;
            }
        }
        throw new IllegalArgumentException("The coding of the Untersuchter Analyte cannot be mapped, since code " + fhirObservation.getCode().getCoding() + " is unknown");
    }

}
