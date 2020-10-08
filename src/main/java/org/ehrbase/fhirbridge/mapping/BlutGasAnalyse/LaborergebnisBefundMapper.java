package org.ehrbase.fhirbridge.mapping.BlutGasAnalyse;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.KohlendioxidpartialdruckMapper;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.PhWertMapper;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.SauerstoffpartialdruckMapper;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.LaboratoryAnalyteMappers.SauerstoffsaettigungMapper;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition.*;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

        laborergebnisObservation.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        laborergebnisObservation.setOriginValue(bloodGasPanel.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime()); // mandatory
        laborergebnisObservation.setTimeValue(bloodGasPanel.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        laborergebnisObservation.setLanguage(Language.DE);// FIXME: we need to grab the language from the template
        laborergebnisObservation.setSubject(new PartySelf());


//        KohlendioxidpartialdruckCluster kohlendioxidpartialdruckCluster = new KohlendioxidpartialdruckCluster();
//        kohlendioxidpartialdruckCluster.setErgebnisStatusValue("");
//        kohlendioxidpartialdruckCluster.setAnalytResultatUnits("");
//        kohlendioxidpartialdruckCluster.setUntersuchterAnalytDefiningcode(UntersuchterAnalytDefiningcode3.CARBON_DIOXIDE_PARTIAL_PRESSURE_IN_ARTERIAL_BLOOD);
//        kohlendioxidpartialdruckCluster.setAnalytResultatMagnitude(12.0);
//        List<Cluster> analyseergebnisDetails = new List<Cluster>() {
//            @Override
//            public int size() {
//                return 0;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public boolean contains(Object o) {
//                return false;
//            }
//
//            @Override
//            public Iterator<Cluster> iterator() {
//                return null;
//            }
//
//            @Override
//            public Object[] toArray() {
//                return new Object[0];
//            }
//
//            @Override
//            public <T> T[] toArray(T[] ts) {
//                return null;
//            }
//
//            @Override
//            public boolean add(Cluster cluster) {
//                return false;
//            }
//
//            @Override
//            public boolean remove(Object o) {
//                return false;
//            }
//
//            @Override
//            public boolean containsAll(Collection<?> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(Collection<? extends Cluster> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(int i, Collection<? extends Cluster> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean removeAll(Collection<?> collection) {
//                return false;
//            }
//
//            @Override
//            public boolean retainAll(Collection<?> collection) {
//                return false;
//            }
//
//            @Override
//            public void clear() {
//
//            }
//
//            @Override
//            public Cluster get(int i) {
//                return null;
//            }
//
//            @Override
//            public Cluster set(int i, Cluster cluster) {
//                return null;
//            }
//
//            @Override
//            public void add(int i, Cluster cluster) {
//
//            }
//
//            @Override
//            public Cluster remove(int i) {
//                return null;
//            }
//
//            @Override
//            public int indexOf(Object o) {
//                return 0;
//            }
//
//            @Override
//            public int lastIndexOf(Object o) {
//                return 0;
//            }
//
//            @Override
//            public ListIterator<Cluster> listIterator() {
//                return null;
//            }
//
//            @Override
//            public ListIterator<Cluster> listIterator(int i) {
//                return null;
//            }
//
//            @Override
//            public List<Cluster> subList(int i, int i1) {
//                return null;
//            }
//        };
//        kohlendioxidpartialdruckCluster.setAnalyseergebnisDetails(analyseergebnisDetails );

        KohlendioxidpartialdruckMapper kohlendioxidpartialdruckMapper = new KohlendioxidpartialdruckMapper(carbonDioxidePartialPressure);
        laborergebnisObservation.setKohlendioxidpartialdruck(new KohlendioxidpartialdruckCluster());


//
//        SauerstoffpartialdruckMapper sauerstoffpartialdruckMapper = new SauerstoffpartialdruckMapper(oxygenPartialPressure);
//        laborergebnisObservation.setSauerstoffpartialdruck(sauerstoffpartialdruckMapper.map());
//
//        PhWertMapper phWertMapper = new PhWertMapper(pH);
//        laborergebnisObservation.setPhWert(phWertMapper.map());
//
//        SauerstoffsaettigungMapper sauerstoffsaettigungMapper = new SauerstoffsaettigungMapper(oxygenSaturation);
//        laborergebnisObservation.setSauerstoffsattigung(sauerstoffsaettigungMapper.map());

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
