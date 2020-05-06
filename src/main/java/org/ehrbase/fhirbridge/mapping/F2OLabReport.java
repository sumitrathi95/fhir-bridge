package org.ehrbase.fhirbridge.mapping;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.*;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.definition.*;
import org.hl7.fhir.r4.model.Quantity;

import com.nedap.archie.rm.generic.*;
import org.hl7.fhir.r4.model.ResourceType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hl7.fhir.r4.model.DiagnosticReport.*;
import static org.hl7.fhir.r4.model.DiagnosticReport.DiagnosticReportStatus.*;

/**
 * FHIR to openEHR - Laboratory report
 */
public class F2OLabReport {

    /**
     * this maps a single lab observation to a composition, the map(DiagnosticReport) method maps a
     * DiagnosticReport with a direct contained Observation to a composition.
     * @param fhirObservation
     * @return
     * @throws Exception
     */
    static public LaborbefundComposition map(Observation fhirObservation) throws Exception {

        LaborbefundComposition composition = new LaborbefundComposition();

        LaboranalytResultatCluster resultCluster = mapObservation(fhirObservation);

        StandortJedesEreignisPointEvent resultEvent = new StandortJedesEreignisPointEvent();
        resultEvent.setTimeValue(OffsetDateTime.now()); // mandatory
        List items = new ArrayList();
        items.add(resultCluster);
        resultEvent.setLaboranalytResultat(items);

        LaborergebnisObservation resultObs = new LaborergebnisObservation();
        List events = new ArrayList();
        events.add(resultEvent);
        resultObs.setJedesEreignis(events);
        resultObs.setOriginValue(OffsetDateTime.now()); // mandatory
        resultObs.setLanguage(Language.EN);
        resultObs.setSubject(new PartySelf());

        List observations = new ArrayList();
        observations.add(resultObs);
        composition.setLaborergebnis(observations);

        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.EN);
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.EMERGENCYCARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(OffsetDateTime.now());

// https://github.com/ehrbase/ehrbase_client_library/issues/31
//        PartyProxy composer = new PartyIdentified();
//        composition.setComposer(composer);

        composition.setComposer(new PartySelf());

        return composition;
    }


    /**
     * this maps a DiagnosticReport with a direct contained Observation to an
     * openEHR composition generated for the Laborbefund template.
     * @param fhirDiagnosticReport
     * @return
     * @throws Exception
     */
    static public LaborbefundComposition map(DiagnosticReport fhirDiagnosticReport) throws Exception {

        LaborbefundComposition composition = new LaborbefundComposition();

        // one contained Observation is expected
        if (fhirDiagnosticReport.getContained().size() != 1)
        {
            throw new Exception("One contained Observation was expected "+ fhirDiagnosticReport.getContained().size() +" were received in DiagnosticReport "+ fhirDiagnosticReport.getId());
        }
        if (fhirDiagnosticReport.getContained().get(0).getResourceType() != ResourceType.Observation)
        {
            throw new Exception("One contained Observation was expected, contained is there but is not Observation, it is "+ fhirDiagnosticReport.getContained().get(0).getResourceType().toString());
        }

        Observation fhirObservation = (Observation)fhirDiagnosticReport.getContained().get(0);

        LaboranalytResultatCluster resultCluster = mapObservation(fhirObservation);

        StandortJedesEreignisPointEvent resultEvent = new StandortJedesEreignisPointEvent();
        resultEvent.setTimeValue(OffsetDateTime.now()); // mandatory
        resultEvent.setLabortestBezeichnungValue(fhirDiagnosticReport.getCode().getText());
        resultEvent.setLabortestBezeichnungValueTree("test name");
        resultEvent.setSchlussfolgerungValueTree(fhirDiagnosticReport.getConclusion());
        resultEvent.setSchlussfolgerungValue("conclusion");

        GesamtteststatusDefiningcode openEHRStatus = null;
        // FHIR value set: https://simplifier.net/packages/simplifier.core.r4.valuesets/4.0.0/files/18799
        // The openEHR template only accepts the 3 codes below
        switch (fhirDiagnosticReport.getStatus())
        {
            case FINAL:
                openEHRStatus = GesamtteststatusDefiningcode.FINAL;
            break;
            case REGISTERED:
                openEHRStatus = GesamtteststatusDefiningcode.REGISTRIERT;
            break;
            case CANCELLED:
                openEHRStatus = GesamtteststatusDefiningcode.ABGEBROCHEN;
            break;
            default:
                openEHRStatus = GesamtteststatusDefiningcode.REGISTRIERT;
        }
        resultEvent.setGesamtteststatusDefiningcode(openEHRStatus);
        resultEvent.setGesamtteststatusValue("test status");

        List items = new ArrayList();
        items.add(resultCluster);
        resultEvent.setLaboranalytResultat(items);


        StandortDetailsDerTestanforderungCluster testRequestDetails = new StandortDetailsDerTestanforderungCluster();
        DvIdentifier receiverOrderIdentifier = new DvIdentifier();
        receiverOrderIdentifier.setId(fhirDiagnosticReport.getIdentifier().get(0).getValue());
        receiverOrderIdentifier.setType(fhirDiagnosticReport.getIdentifier().get(0).getSystem());
        testRequestDetails.setAuftragsIdEmpfanger(receiverOrderIdentifier);


        LaborergebnisObservation resultObs = new LaborergebnisObservation();

        List events = new ArrayList();
        events.add(resultEvent);
        resultObs.setJedesEreignis(events);

        List testRequestDetailsList = new ArrayList();
        testRequestDetailsList.add(testRequestDetails);
        resultObs.setDetailsDerTestanforderung(testRequestDetailsList);

        resultObs.setOriginValue(OffsetDateTime.now()); // mandatory
        resultObs.setLanguage(Language.EN);
        resultObs.setSubject(new PartySelf());


        List observations = new ArrayList();
        observations.add(resultObs);
        composition.setLaborergebnis(observations);


        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.EN);
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.EMERGENCYCARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(OffsetDateTime.now());


// https://github.com/ehrbase/ehrbase_client_library/issues/31
//        PartyProxy composer = new PartyIdentified();
//        composition.setComposer(composer);

        composition.setComposer(new PartySelf());

        return composition;
    }


        /**
         * Maps a FHIR Observation to an openEHR LaboranalytResultatCluster generated from the Laborbefund template.
         * @param fhirObservation
         * @return
         */
    static private LaboranalytResultatCluster mapObservation(Observation fhirObservation) throws Exception {

        // ========================================================================================
        // value quantity is expected
        Quantity fhir_value = null;
        BigDecimal fhir_value_numeric = null;

        try {
            fhir_value = fhirObservation.getValueQuantity();
            fhir_value_numeric = fhir_value.getValue();
        } catch (Exception e) {
            System.out.println("---> "+ e.getMessage());
        }

        if (fhir_value_numeric == null)
        {
            throw new Exception("Value is required in FHIR Observation and should be Quantity");
        }

        // mapping to openEHR
        LaboranalytResultatAnalytResultatDvquantity resultValue = new LaboranalytResultatAnalytResultatDvquantity();
        resultValue.setAnalytResultatMagnitude(fhir_value_numeric.doubleValue());
        resultValue.setAnalytResultatUnits(fhir_value.getUnit());

        // =======================================================================================
        // rest of the structure to build the composition with the value taken from FHIR
        LaboranalytResultatCluster resultCluster = new LaboranalytResultatCluster();
        resultCluster.setAnalytResultat(resultValue);
        resultCluster.setAnalytResultatValue("result"); // this is the ELEMENT.name

        return resultCluster;
    }
}