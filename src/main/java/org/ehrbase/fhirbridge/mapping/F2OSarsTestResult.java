package org.ehrbase.fhirbridge.mapping;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.kennzeichnungerregernachweissarscov2composition.KennzeichnungErregernachweisSARSCoV2Composition;
import org.ehrbase.fhirbridge.opt.kennzeichnungerregernachweissarscov2composition.definition.KennzeichnungErregernachweisEvaluation;
import org.ehrbase.fhirbridge.opt.shareddefinition.*;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * FHIR 2 openEHR - SARS COV2 lab result
 */
public class F2OSarsTestResult {

    private F2OSarsTestResult() {}

    public static KennzeichnungErregernachweisSARSCoV2Composition map(Observation fhirObservation) {

        KennzeichnungErregernachweisSARSCoV2Composition composition = new KennzeichnungErregernachweisSARSCoV2Composition();

        // TODO: I'm not sure this is a complete list of "positive" results in LOINC, these are just "SARS"+"presence" search
        // https://search.loinc.org/searchLOINC/search.zul?query=sars+presence
        List<String> positiveResultLoincCodes = Arrays.asList(
                "33972-1",
                "33968-9",
                "33970-5",
                "33966-3",
                "33967-1",
                "33975-4",
                "33965-5",
                "33964-8",
                "41459-9",
                "42956-3",
                "41991-1",
                "60275-5",
                "60534-5",
                "41458-1",
                "94532-9"
        );


        // ========================================================================================
        // FHIR values
        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();
        String fhirValue = fhirObservation.getCode().getCoding().get(0).getDisplay(); // FIXME: the code and value should be assigned to the pathogen name node in the Compo, but the template binds just one specific value there (hardcoded)
        String fhirCode = fhirObservation.getCode().getCoding().get(0).getCode();
        String fhirTerminology = fhirObservation.getCode().getCoding().get(0).getSystem();


        // mapping to openEHR
        KennzeichnungErregernachweisEvaluation evaluation = new KennzeichnungErregernachweisEvaluation();
        evaluation.setLanguage(Language.EN);
        evaluation.setSubject(new PartySelf());
        evaluation.setZuletztAktualisiertValue(OffsetDateTime.now());
        evaluation.setZeitpunktDerKennzeichnungValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        boolean pathogenDetection = positiveResultLoincCodes.contains(fhirCode);
        evaluation.setErregernachweisValue(pathogenDetection); // FIXME: this needs to come from positive or negative result code from FHIR, I didn't validate the list of positive result codes.

        evaluation.setErregernachweisInDerKlinikValue(false); // FIXME: FHIR don't have enough data to know if the pathogen was detected in the clinic.

        // FIXME: Can't map with the code from FHIR since the code in the openEHR template is fixed
        ErregernameDefiningcode code = ErregernameDefiningcode.COV; //new ErregernameDefiningcode(fhirValue, null, fhirTerminology, fhirCode);
        evaluation.setErregernameDefiningcode(code);

        composition.setKennzeichnungErregernachweis(evaluation);

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
}