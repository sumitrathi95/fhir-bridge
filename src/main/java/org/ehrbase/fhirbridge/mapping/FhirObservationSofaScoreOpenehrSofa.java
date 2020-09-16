package org.ehrbase.fhirbridge.mapping;


import org.ehrbase.fhirbridge.opt.sofacomposition.SOFAComposition;
import org.ehrbase.fhirbridge.opt.sofacomposition.definition.SOFAScoreObservation;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FhirObservationSofaScoreOpenehrSofa {
    private static final Logger logger = LoggerFactory.getLogger(FhirObservationSofaScoreOpenehrSofa.class);

    private FhirObservationSofaScoreOpenehrSofa() {
    }

    public static SOFAComposition map(Observation fhirObservation) {


        SOFAComposition composition = new SOFAComposition();

        String sofaScoreCode = fhirObservation.getCode().getCoding().get(0).getCode();

        SOFAScoreObservation sofaScore = new SOFAScoreObservation();

        fhirObservation.getCode().getCoding().get(0).getCode();
        // sofaScore.setLeberfunktion(3);

        Long sofaScoreCodeLong = Long.parseLong(sofaScoreCode);

        sofaScore.setSofaScoreMagnitude(sofaScoreCodeLong);

        composition.setSofaScore(sofaScore);

        return composition;

    }

}
