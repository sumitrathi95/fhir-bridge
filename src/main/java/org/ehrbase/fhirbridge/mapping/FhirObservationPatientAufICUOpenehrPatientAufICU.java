package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.config.util.CommonData;
import org.ehrbase.fhirbridge.opt.geccolaborbefundcomposition.definition.UntersuchterAnalytDefiningcode;
import org.ehrbase.fhirbridge.opt.patientauficucomposition.PatientAufICUComposition;
import org.ehrbase.fhirbridge.opt.patientauficucomposition.definition.PatientAufDerIntensivstationObservation;
import org.ehrbase.fhirbridge.opt.patientauficucomposition.definition.StatusDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.*;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FhirObservationPatientAufICUOpenehrPatientAufICU {

    private FhirObservationPatientAufICUOpenehrPatientAufICU() {
    }

    private static final Map<String, WurdeDieAktivitatDurchgefuhrtDefiningcode> aktivitaetDurchgefuehrtDefiningcodeMap
            = new HashMap<>();

    static {
        for (WurdeDieAktivitatDurchgefuhrtDefiningcode code : WurdeDieAktivitatDurchgefuhrtDefiningcode.values()) {
            if (code.getTerminologyId().equals("SNOMED Clinical Terms")) {
                aktivitaetDurchgefuehrtDefiningcodeMap.put(code.getCode(), code);
            }
        }
    }

    public static PatientAufICUComposition map(Observation fhirObservation) {
        PatientAufICUComposition composition = new PatientAufICUComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(fhirObservation);
        composition.setFeederAudit(fa);

        Observation.ObservationStatus status = fhirObservation.getStatus();

        switch (status) {
            case FINAL:
                composition.setStatusDefiningcode(StatusDefiningcode.FINAL);
                break;
            default:
                throw new UnprocessableEntityException("Status has invalid code " + status.toCode());
        }

        mapObservation(composition, fhirObservation);

        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        //obligatory stuff block
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test"); //FIXME: sensible value
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setComposer(new PartySelf()); //FIXME: sensible value
        return composition;
    }

    private static void mapObservation(PatientAufICUComposition composition, Observation fhirObservation)
    {
        PatientAufDerIntensivstationObservation patientAufDerIntensivstation = new PatientAufDerIntensivstationObservation();
        patientAufDerIntensivstation.setNameDerAktivitatValue("Behandlung auf der Intensivstation");


        Coding coding = fhirObservation.getValueCodeableConcept().getCoding().get(0);

        Optional<WurdeDieAktivitatDurchgefuhrtDefiningcode> aktivitatDurchgefuhrtDefiningcode = Optional.empty();

        if (coding.getSystem().equals("http://snomed.info/sct")) {
            aktivitatDurchgefuhrtDefiningcode = Optional.ofNullable(aktivitaetDurchgefuehrtDefiningcodeMap.get(coding.getCode()));
        }

        if (aktivitatDurchgefuhrtDefiningcode.isPresent()) {
            patientAufDerIntensivstation.setWurdeDieAktivitatDurchgefuhrtDefiningcode(aktivitatDurchgefuhrtDefiningcode.get());
        } else {
            throw new UnprocessableEntityException("Aktivität durchgeführt has invalid code " + coding.getCode());
        }

        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();

        patientAufDerIntensivstation.setLanguage(Language.DE);
        patientAufDerIntensivstation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        patientAufDerIntensivstation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        patientAufDerIntensivstation.setSubject(new PartySelf());


        composition.setPatientAufDerIntensivstation(patientAufDerIntensivstation);
    }

}
