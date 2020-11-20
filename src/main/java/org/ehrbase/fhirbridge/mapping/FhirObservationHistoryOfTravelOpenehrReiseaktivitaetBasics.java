package org.ehrbase.fhirbridge.mapping;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.ReiseaktivitaetComposition;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.ReiseaktivitatObservation;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

import java.time.temporal.TemporalAccessor;

public class FhirObservationHistoryOfTravelOpenehrReiseaktivitaetBasics {

    FhirObservationHistoryOfTravelOpenehrReiseaktivitaetBasics(){}

    public static ReiseaktivitaetComposition setCompositionDefaults(ReiseaktivitaetComposition composition) {
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT); //required by model
        composition.setComposer(new PartySelf());

        return composition;
    }

    public static TemporalAccessor getFhirEffectiveDateTime(Observation fhirObservation) {
        return fhirObservation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();
    }

}
