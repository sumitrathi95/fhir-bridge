package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.*;
import org.ehrbase.fhirbridge.opt.schwangerschaftsstatuscomposition.*;
import org.ehrbase.fhirbridge.opt.schwangerschaftsstatuscomposition.definition.*;
import com.nedap.archie.rm.generic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import static java.util.Date.from;

/**
 * FHIR to openEHR - Laboratory report
 */
public class FhirObservationPregnancyStatusOpenehrPregnancyStatus {

    private static final Logger logger = LoggerFactory.getLogger(FhirObservationPregnancyStatusOpenehrPregnancyStatus.class);

    private FhirObservationPregnancyStatusOpenehrPregnancyStatus() {}

    /**
     * this maps a single lab observation to a composition, the map(DiagnosticReport) method maps a
     * DiagnosticReport with a direct contained Observation to a composition.
     * @param fhirObservation the FHIR Observation resource received in the API.
     * @return the Composition defined by the laborbefund template.
     */
    public static SchwangerschaftsstatusComposition map(Observation fhirObservation) {

        SchwangerschaftsstatusComposition composition = new SchwangerschaftsstatusComposition();

        // map start time
        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();

        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        // TODO: map other context status
        // Can't map because of https://github.com/ehrbase/openEHR_SDK/issues/84

        composition.setSchwangerschaftsstatus(mapObservation(fhirObservation));


        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.DE);
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.EMERGENCY_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(OffsetDateTime.now());

        PartyIdentified composer = new PartyIdentified();
        DvIdentifier identifier = new DvIdentifier();
        identifier.setId(fhirObservation.getPerformer().get(0).getReference());
        composer.addIdentifier(identifier);
        composition.setComposer(composer);

        return composition;
    }
    


    /**
     * this maps a DiagnosticReport with a direct contained Observation to an
     * openEHR composition generated for the Laborbefund template.
     * @param fhirDiagnosticReport the DiagnosticReport FHIR resource received in the API
     * @return the Composition defined by the laborbefund template
     */
    /*
    public static SchwangerschaftsstatusComposition map(DiagnosticReport fhirDiagnosticReport) {

        SchwangerschaftsstatusComposition composition = new SchwangerschaftsstatusComposition();

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
        composition.setSettingDefiningcode(SettingDefiningcode.EMERGENCY_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(fhirDiagnosticReport.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());


        // FIXME: https://github.com/ehrbase/ehrbase_client_library/issues/31
        //        PartyProxy composer = new PartyIdentified();
        //        composition.setComposer(composer);

        composition.setComposer(new PartySelf());

        return composition;
    }
    */


    /**
     * Maps a FHIR Observation to an openEHR LaboranalytResultatCluster generated from the Laborbefund template.
     * @param fhirObservation the FHIR Observation resource received in the API.
     * @return the cluster defined in the OPT that maps to the FHIR observation
     */
    
    private static SchwangerschaftsstatusObservation mapObservation(Observation fhirObservation)
    {
        SchwangerschaftsstatusObservation observation = new SchwangerschaftsstatusObservation();

        // mandatory fields
        observation.setSubject(new PartySelf());
        observation.setLanguage(Language.DE);

        // map origin/time
        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();

        observation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        observation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());


        // map observation status
        Coding statusCode = fhirObservation.getValueCodeableConcept().getCoding().get(0);

        // TODO: this only considers LOINC cases
        switch (statusCode.getCode())
        {
            case "LA15173-0": // pregnant
                observation.setStatusDefiningcode(StatusDefiningcode.SCHWANGER);
            break;
            case "LA26683-5": // not pregmant
                observation.setStatusDefiningcode(StatusDefiningcode.NICHT_SCHWANGER);
            break;
            case "LA4489-6": // unknown
                observation.setStatusDefiningcode(StatusDefiningcode.UNBEKANNT);
            break;
            default:
                throw new UnprocessableEntityException("Status code "+ statusCode.getCode() +" is not supported");
        }

        return observation;
    }
}