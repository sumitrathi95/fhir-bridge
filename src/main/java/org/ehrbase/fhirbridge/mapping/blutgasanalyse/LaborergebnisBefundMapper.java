package org.ehrbase.fhirbridge.mapping.blutgasanalyse;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.fhir.resource.bundle.BloodGasPanelBundle;
import org.ehrbase.fhirbridge.mapping.blutgasanalyse.laboratoryanalytemappers.KohlendioxidpartialdruckMapper;
import org.ehrbase.fhirbridge.mapping.blutgasanalyse.laboratoryanalytemappers.PhWertMapper;
import org.ehrbase.fhirbridge.mapping.blutgasanalyse.laboratoryanalytemappers.SauerstoffpartialdruckMapper;
import org.ehrbase.fhirbridge.mapping.blutgasanalyse.laboratoryanalytemappers.SauerstoffsaettigungMapper;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.*;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.*;

class LaborergebnisBefundMapper {

    public static LaborergebnisObservation map(BloodGasPanelBundle bloodGasPanelBundle) {
        LaborergebnisObservation laborergebnisObservation = new LaborergebnisObservation();
        laborergebnisObservation.setLabortestBezeichnungDefiningcode(mapLabortestBezeichnung(bloodGasPanelBundle.getBloodGasPanel()));

        //TODO implement heritage to all classes e.g. ObservationAbstract etc. so we dont need this block
        laborergebnisObservation.setLanguage(Language.DE);
        laborergebnisObservation.setOriginValue(bloodGasPanelBundle.getBloodGasPanel().getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        laborergebnisObservation.setTimeValue(bloodGasPanelBundle.getBloodGasPanel().getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        laborergebnisObservation.setSubject(new PartySelf());

        mapCarbonDioxidePartialPressureIfPresent(laborergebnisObservation, bloodGasPanelBundle.getCarbonDioxidePartialPressure());

        mapOxygenPartialPressureIfPresent(laborergebnisObservation, bloodGasPanelBundle.getOxygenPartialPressure());

        mapPhIfPresent(laborergebnisObservation, bloodGasPanelBundle.getpH());

        mapOxygenSaturationIfPresent(laborergebnisObservation, bloodGasPanelBundle.getOxygenSaturation());

        return laborergebnisObservation;
    }

    private static void mapOxygenPartialPressureIfPresent(LaborergebnisObservation laborergebnisObservation, Optional<Observation> oxygenPartialPressure) {
        if(oxygenPartialPressure.isPresent()){
            SauerstoffpartialdruckMapper sauerstoffpartialdruckMapper = new SauerstoffpartialdruckMapper(oxygenPartialPressure.get());
            laborergebnisObservation.setSauerstoffpartialdruck(sauerstoffpartialdruckMapper.map());
        }

    }

    private static void mapCarbonDioxidePartialPressureIfPresent(LaborergebnisObservation laborergebnisObservation, Optional<Observation> carbonDioxidePartialPressure) {
        if(carbonDioxidePartialPressure.isPresent()){
            KohlendioxidpartialdruckMapper kohlendioxidpartialdruckMapper = new KohlendioxidpartialdruckMapper(carbonDioxidePartialPressure.get());
            laborergebnisObservation.setKohlendioxidpartialdruck(kohlendioxidpartialdruckMapper.map());
        }
    }

    private static void mapPhIfPresent(LaborergebnisObservation laborergebnisObservation, Optional<Observation> pH) {
        if(pH.isPresent()){
            PhWertMapper phWertMapper = new PhWertMapper(pH.get());
            laborergebnisObservation.setPhWert(phWertMapper.map());
        }
    }

    private static void mapOxygenSaturationIfPresent(LaborergebnisObservation laborergebnisObservation, Optional<Observation> oxygenSaturation){
        if(oxygenSaturation.isPresent()){
            SauerstoffsaettigungMapper sauerstoffsaettigungMapper = new SauerstoffsaettigungMapper(oxygenSaturation.get());
            laborergebnisObservation.setSauerstoffsattigung(sauerstoffsaettigungMapper.map());
        }
    }


    private static LabortestBezeichnungDefiningcode mapLabortestBezeichnung(Observation fhirObservation){
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


