package org.ehrbase.fhirbridge.mapping.BlutGasAnalyse;

import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.KohlendioxidpartialdruckMapper;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.PhWertMapper;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.SauerstoffpartialdruckMapper;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.SauerstoffsaettigungMapper;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.*;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

class LaborergebnisBefundMapper {


    private final Observation fhirObservation;

    public LaborergebnisBefundMapper(Observation fhirObservation) {
        this.fhirObservation = fhirObservation;
    }

    public LaborergebnisObservation map() {

        LaborergebnisObservation laborergebnisObservation = new LaborergebnisObservation();
        laborergebnisObservation.setLabortestBezeichnungDefiningcode(mapLabortestBezeichnung(fhirObservation));

        fhirObservation.getHasMember().get(0).getReference(); //TODO how to call for internal resources/profiles since this is a reference

        KohlendioxidpartialdruckMapper kohlendioxidpartialdruckMapper = new KohlendioxidpartialdruckMapper(fhirObservation);
        laborergebnisObservation.setKohlendioxidpartialdruck(kohlendioxidpartialdruckMapper.map());

        SauerstoffpartialdruckMapper sauerstoffpartialdruckMapper = new SauerstoffpartialdruckMapper(fhirObservation);
        laborergebnisObservation.setSauerstoffpartialdruck(sauerstoffpartialdruckMapper.map());

        PhWertMapper phWertMapper = new PhWertMapper(fhirObservation);
        laborergebnisObservation.setPhWert(phWertMapper.map());

        SauerstoffsaettigungMapper sauerstoffsaettigungMapper = new SauerstoffsaettigungMapper(fhirObservation);
        laborergebnisObservation.setSauerstoffsattigung(sauerstoffsaettigungMapper.map());

        return laborergebnisObservation;
    }

    //Terminology server ?
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
