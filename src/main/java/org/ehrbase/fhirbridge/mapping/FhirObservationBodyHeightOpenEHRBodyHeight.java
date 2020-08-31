package org.ehrbase.fhirbridge.mapping;

//import org.ehrbase.fhirbridge.opt.covidbodyheightcomposition.COVIDBodyHeightComposition;
//import org.ehrbase.fhirbridge.opt.covidbodyheightcomposition.definition.KorpergroesseBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FhirObservationBodyHeightOpenEHRBodyHeight {

    private static final Logger logger = LoggerFactory.getLogger(FhirDiagnosticReportOpenehrLabResults.class);
    private FhirObservationBodyHeightOpenEHRBodyHeight() {}
    public static void /*COVIDBodyHeightComposition*/ map (Observation fhirObservation){
        /*
        Quantity valueFHIRQuantity = fhirObservation.getValueQuantity();
        DateTimeType effectiveDateTimeType = fhirObservation.getEffectiveDateTimeType();
        logger.info(valueFHIRQuantity.toString());
        logger.info(effectiveDateTimeType.toString());
        COVIDBodyHeightComposition composition = new COVIDBodyHeightComposition();
        KorpergroesseBeliebigesEreignisPointEvent korpergroesseBeliebigesEreignisPointEvent = new KorpergroesseBeliebigesEreignisPointEvent();
        //korpergewichtBeliebigesEreignisPointEvent.setTimeValue();
        //korpergroesseBeliebigesEreignisPointEvent.setGewichtUnits("kg");
        korpergroesseBeliebigesEreignisPointEvent.setHeightMagnitude(valueFHIRQuantity.getValue().doubleValue());
        return composition;
        */
    }

}
