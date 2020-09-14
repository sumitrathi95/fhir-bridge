package org.ehrbase.fhirbridge.mapping;


import org.ehrbase.fhirbridge.opt.sofacomposition.SOFAComposition;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FhirObservationSofaScoreOpenehrSofa {
    private static final Logger logger = LoggerFactory.getLogger(FhirObservationSofaScoreOpenehrSofa.class);

    private FhirObservationSofaScoreOpenehrSofa() {
    }

    public static SOFAComposition map(Observation fhirObservation) {


        SOFAComposition composition = new SOFAComposition();



        return composition;

    }

}
