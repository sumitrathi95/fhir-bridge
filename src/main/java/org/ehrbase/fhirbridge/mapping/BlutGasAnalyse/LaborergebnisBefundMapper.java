package org.ehrbase.fhirbridge.mapping.BlutGasAnalyse;

import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.KohlendioxidpartialdruckMapper;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.PhWertMapper;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.SauerstoffpartialdruckMapper;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.SauerstoffsaettigungMapper;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.*;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

class LaborergebnisBefundMapper {

    private final Observation oxygenPartialPressure;
    private final Observation pH;
    private final Observation carbonDioxidePartialPressure;
    private final Observation oxygenSaturation;
    private final Observation bloodGasPanel;

    public LaborergebnisBefundMapper(Observation bloodGasPanel, Observation oxygenPartialPressure, Observation pH, Observation carbonDioxidePartialPressure, Observation oxygenSaturation) {
        this.oxygenPartialPressure = oxygenPartialPressure;
        this.pH = pH;
        this.carbonDioxidePartialPressure = carbonDioxidePartialPressure;
        this.oxygenSaturation = oxygenSaturation;
        this.bloodGasPanel = bloodGasPanel;
    }

    public LaborergebnisObservation map() {

        LaborergebnisObservation laborergebnisObservation = new LaborergebnisObservation();
        laborergebnisObservation.setLabortestBezeichnungDefiningcode(mapLabortestBezeichnung(bloodGasPanel));

        KohlendioxidpartialdruckMapper kohlendioxidpartialdruckMapper = new KohlendioxidpartialdruckMapper(carbonDioxidePartialPressure);
        laborergebnisObservation.setKohlendioxidpartialdruck(kohlendioxidpartialdruckMapper.map());

        SauerstoffpartialdruckMapper sauerstoffpartialdruckMapper = new SauerstoffpartialdruckMapper(oxygenPartialPressure);
        laborergebnisObservation.setSauerstoffpartialdruck(sauerstoffpartialdruckMapper.map());

        PhWertMapper phWertMapper = new PhWertMapper(pH);
        laborergebnisObservation.setPhWert(phWertMapper.map());

        SauerstoffsaettigungMapper sauerstoffsaettigungMapper = new SauerstoffsaettigungMapper(oxygenSaturation);
        laborergebnisObservation.setSauerstoffsattigung(sauerstoffsaettigungMapper.map());

        return laborergebnisObservation;
    }

    private LabortestBezeichnungDefiningcode mapLabortestBezeichnung(Observation fhirObservation){
        LabortestBezeichnungDefiningcode gasPanelBlood = LabortestBezeichnungDefiningcode.GAS_PANEL_BLOOD;
        LabortestBezeichnungDefiningcode gasPanelArterial = LabortestBezeichnungDefiningcode.GAS_PANEL_ARTERIAL_BLOOD;
        LabortestBezeichnungDefiningcode gasPanelCapillary = LabortestBezeichnungDefiningcode.GAS_PANEL_CAPILLARY_BLOOD;

        for (Coding coding:fhirObservation.getCode().getCoding()) {
            String code = coding.getCode();
            if(code.equals(gasPanelBlood.getCode())){
                return gasPanelBlood;
            }else if(code.equals(gasPanelArterial.getCode())){
                return gasPanelArterial;
            }else if(code.equals(gasPanelCapillary.getCode())){
                return gasPanelCapillary;
            }
        }
        throw new IllegalArgumentException("The coding of the LabortestBezeichnung: "+fhirObservation.getCode().getCoding()+" cannot be mapped, needs to be either blood (LOINC code 24338-6)" +
                ", arterial blood (24336-0) or capillary blood (24337-8), check JSON at path Observation.code.coding");
    }

}
