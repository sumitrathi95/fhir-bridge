package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.*;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.*;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.definition.*;

import com.nedap.archie.rm.generic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(F2OLabReport.class);

    private F2OLabReport() {}

    /**
     * this maps a single lab observation to a composition, the map(DiagnosticReport) method maps a
     * DiagnosticReport with a direct contained Observation to a composition.
     * @param fhirObservation the FHIR Observation resource received in the API.
     * @return the Composition defined by the laborbefund template.
     */
    public static LaborbefundComposition map(Observation fhirObservation) {

        LaborbefundComposition composition = new LaborbefundComposition();

        LaboranalytResultatCluster resultCluster = mapObservation(fhirObservation);

        StandortJedesEreignisPointEvent resultEvent = new StandortJedesEreignisPointEvent();
        resultEvent.setTimeValue(OffsetDateTime.now()); // mandatory
        List<LaboranalytResultatCluster> items = new ArrayList<>();
        items.add(resultCluster);
        resultEvent.setLaboranalytResultat(items);

        LaborergebnisObservation resultObs = new LaborergebnisObservation();
        List<StandortJedesEreignisChoice> events = new ArrayList<>();
        events.add(resultEvent);
        resultObs.setJedesEreignis(events);
        resultObs.setOriginValue(OffsetDateTime.now()); // mandatory
        resultObs.setLanguage(Language.EN);
        resultObs.setSubject(new PartySelf());

        List<LaborergebnisObservation> observations = new ArrayList<>();
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

        // FIXME: https://github.com/ehrbase/ehrbase_client_library/issues/31
        //        PartyProxy composer = new PartyIdentified();
        //        composition.setComposer(composer);

        composition.setComposer(new PartySelf());

        return composition;
    }


    /**
     * this maps a DiagnosticReport with a direct contained Observation to an
     * openEHR composition generated for the Laborbefund template.
     * @param fhirDiagnosticReport the DiagnosticReport FHIR resource received in the API
     * @return the Composition defined by the laborbefund template
     */
    public static LaborbefundComposition map(DiagnosticReport fhirDiagnosticReport) {

        LaborbefundComposition composition = new LaborbefundComposition();

        logger.debug("Contained size: {}", fhirDiagnosticReport.getContained().size());

        // one contained Observation is expected
        if (fhirDiagnosticReport.getContained().size() != 1)
        {
            throw new UnprocessableEntityException("One contained Observation was expected "+ fhirDiagnosticReport.getContained().size() +" were received in DiagnosticReport "+ fhirDiagnosticReport.getId());
        }
        if (fhirDiagnosticReport.getContained().get(0).getResourceType() != ResourceType.Observation)
        {
            throw new UnprocessableEntityException("One contained Observation was expected, contained is there but is not Observation, it is "+ fhirDiagnosticReport.getContained().get(0).getResourceType().toString());
        }

        Observation fhirObservation = (Observation)fhirDiagnosticReport.getContained().get(0);

        LaboranalytResultatCluster resultCluster = mapObservation(fhirObservation);

        StandortJedesEreignisPointEvent resultEvent = new StandortJedesEreignisPointEvent();
        resultEvent.setTimeValue(fhirObservation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
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

        List<LaboranalytResultatCluster> items = new ArrayList<>();
        items.add(resultCluster);
        resultEvent.setLaboranalytResultat(items);


        StandortDetailsDerTestanforderungCluster testRequestDetails = new StandortDetailsDerTestanforderungCluster();
        DvIdentifier receiverOrderIdentifier = new DvIdentifier();
        receiverOrderIdentifier.setId(fhirDiagnosticReport.getIdentifier().get(0).getValue());
        receiverOrderIdentifier.setType(fhirDiagnosticReport.getIdentifier().get(0).getSystem());
        testRequestDetails.setAuftragsIdEmpfanger(receiverOrderIdentifier);


        LaborergebnisObservation resultObs = new LaborergebnisObservation();

        List<StandortJedesEreignisChoice> events = new ArrayList<>();
        events.add(resultEvent);
        resultObs.setJedesEreignis(events);

        List<StandortDetailsDerTestanforderungCluster> testRequestDetailsList = new ArrayList<>();
        testRequestDetailsList.add(testRequestDetails);
        resultObs.setDetailsDerTestanforderung(testRequestDetailsList);

        resultObs.setOriginValue(fhirObservation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        resultObs.setLanguage(Language.EN); // FIXME: the lang should be retrieved from the template
        resultObs.setSubject(new PartySelf());


        List<LaborergebnisObservation> observations = new ArrayList<>();
        observations.add(resultObs);
        composition.setLaborergebnis(observations);


        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.EN); // FIXME: the lang should be retrieved from the template
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.EMERGENCYCARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(fhirDiagnosticReport.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());


        // FIXME: https://github.com/ehrbase/ehrbase_client_library/issues/31
        //        PartyProxy composer = new PartyIdentified();
        //        composition.setComposer(composer);

        composition.setComposer(new PartySelf());

        return composition;
    }


    /**
     * Maps a FHIR Observation to an openEHR LaboranalytResultatCluster generated from the Laborbefund template.
     * @param fhirObservation the FHIR Observation resource received in the API.
     * @return the cluster defined in the OPT that maps to the FHIR observation
     */
    private static LaboranalytResultatCluster mapObservation(Observation fhirObservation) {

        // ========================================================================================
        // value quantity is expected
        Quantity fhirValue = null;
        BigDecimal fhirValueNumeric = null;
        String fhirCodeName = null;
        DateTimeType fhirEffectiveDateTime = null;

        try {
            fhirValue = fhirObservation.getValueQuantity();
            fhirValueNumeric = fhirValue.getValue();
            fhirCodeName = fhirObservation.getCode().getCoding().get(0).getDisplay();
            fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        if (fhirValueNumeric == null)
        {
            throw new UnprocessableEntityException("Value is required in FHIR Observation and should be Quantity");
        }
        if (fhirEffectiveDateTime == null)
        {
            throw new UnprocessableEntityException("effectiveDateTime is required in FHIR Observation");
        }
        if (fhirCodeName == null)
        {
            throw new UnprocessableEntityException("code is required in FHIR Observation");
        }

        // mapping to openEHR
        LaboranalytResultatAnalytResultatDvquantity resultValue = new LaboranalytResultatAnalytResultatDvquantity();
        resultValue.setAnalytResultatMagnitude(fhirValueNumeric.doubleValue());
        resultValue.setAnalytResultatUnits(fhirValue.getUnit());

        // =======================================================================================
        // rest of the structure to build the composition with the value taken from FHIR
        LaboranalytResultatCluster resultCluster = new LaboranalytResultatCluster();
        resultCluster.setAnalytResultat(resultValue);
        resultCluster.setAnalytResultatValue("result"); // this is the ELEMENT.name
        resultCluster.setUntersuchterAnalytValue(fhirCodeName);
        resultCluster.setUntersuchterAnalytValueName("Analyte name");
        resultCluster.setZeitpunktErgebnisStatusValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        resultCluster.setZeitpunktErgebnisStatusValueName("Result status time");

        return resultCluster;
    }
}