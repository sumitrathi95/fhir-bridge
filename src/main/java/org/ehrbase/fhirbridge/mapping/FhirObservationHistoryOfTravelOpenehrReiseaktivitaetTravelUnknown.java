package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.ReiseaktivitaetComposition;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.AussageUberDieFehlendeInformationDefiningcode;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.ProblemDiagnoseDefiningcode;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.UnbekannteReiseaktivitatEvaluation;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

import java.time.temporal.TemporalAccessor;

public class FhirObservationHistoryOfTravelOpenehrReiseaktivitaetTravelUnknown {

    FhirObservationHistoryOfTravelOpenehrReiseaktivitaetTravelUnknown(){
    }

    public static ReiseaktivitaetComposition map(Observation fhirObservation, AussageUberDieFehlendeInformationDefiningcode reiseCode) {
        ReiseaktivitaetComposition composition = new ReiseaktivitaetComposition();
        UnbekannteReiseaktivitatEvaluation evalution_unknownTravel = new UnbekannteReiseaktivitatEvaluation();

        try {
            evalution_unknownTravel.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
            evalution_unknownTravel.setSubject(new PartySelf());

            // special mapping content
            evalution_unknownTravel.setAussageUberDieFehlendeInformationDefiningcode(reiseCode);
            evalution_unknownTravel.setFehlendeInformationDefiningcode(ProblemDiagnoseDefiningcode.HISTORY_OF_TRAVEL);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnprocessableEntityException(e.getMessage());
        }
        composition.setUnbekannteReiseaktivitat(evalution_unknownTravel);

        // Required fields by API
        composition = FhirObservationHistoryOfTravelOpenehrReiseaktivitaetBasics.setCompositionDefaults(composition);
        TemporalAccessor timedate = FhirObservationHistoryOfTravelOpenehrReiseaktivitaetBasics.getFhirEffectiveDateTime(fhirObservation);
        composition.setStartTimeValue(timedate);

        return composition;
    }

}
