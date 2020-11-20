package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.ReiseaktivitaetComposition;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.AussageUberDenAusschlussDefiningcode;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.KeineReiseaktivitatEvaluation;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.ProblemDiagnoseDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

import java.time.temporal.TemporalAccessor;

public class FhirObservationHistoryOfTravelOpenehrReiseaktivitaetTravelNo {

    FhirObservationHistoryOfTravelOpenehrReiseaktivitaetTravelNo()
    {

    }

    public static ReiseaktivitaetComposition map(Observation fhirObservation, AussageUberDenAusschlussDefiningcode reiseCode) {
        ReiseaktivitaetComposition composition = new ReiseaktivitaetComposition();
        KeineReiseaktivitatEvaluation evaluation_noTravel = new KeineReiseaktivitatEvaluation();

        try {
            // default for every observation
            evaluation_noTravel.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
            evaluation_noTravel.setSubject(new PartySelf());

            // special mapping content
            evaluation_noTravel.setAussageUberDenAusschlussDefiningcode(reiseCode);
            evaluation_noTravel.setProblemDiagnoseDefiningcode(ProblemDiagnoseDefiningcode.HISTORY_OF_TRAVEL);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnprocessableEntityException(e.getMessage());
        }
        composition.setKeineReiseaktivitat(evaluation_noTravel);

        // Required fields by API
        composition = FhirObservationHistoryOfTravelOpenehrReiseaktivitaetBasics.setCompositionDefaults(composition);
        TemporalAccessor timedate = FhirObservationHistoryOfTravelOpenehrReiseaktivitaetBasics.getFhirEffectiveDateTime(fhirObservation);
        composition.setStartTimeValue(timedate);

        return composition;
    }

}
