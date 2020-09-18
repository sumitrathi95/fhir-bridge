package org.ehrbase.fhirbridge.mapping;


import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import org.ehrbase.fhirbridge.opt.sofacomposition.SOFAComposition;
import org.ehrbase.fhirbridge.opt.sofacomposition.definition.SOFAScoreObservation;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FhirObservationSofaScoreOpenehrSofa {
    private static final Logger logger = LoggerFactory.getLogger(FhirObservationSofaScoreOpenehrSofa.class);

    private static DvOrdinal ATEMFREQUENZ_SCORE_1 = new DvOrdinal(1L,
            new DvCodedText("at0016", new CodePhrase("local")));

    private static DvOrdinal ATEMFREQUENZ_SCORE_2 = new DvOrdinal(2L,
            new DvCodedText("at0017", new CodePhrase("local")));

    private static DvOrdinal ATEMFREQUENZ_SCORE_3 = new DvOrdinal(3L,
            new DvCodedText("at0018", new CodePhrase("local")));

    private static DvOrdinal ATEMFREQUENZ_SCORE_4 = new DvOrdinal(4L,
            new DvCodedText("at0019", new CodePhrase("local")));

    private static DvOrdinal NERVENSYSTEM_SCORE_1 = new DvOrdinal(1L,
            new DvCodedText("at0020", new CodePhrase("local")));

    private static DvOrdinal NERVENSYSTEM_SCORE_2 = new DvOrdinal(2L,
            new DvCodedText("at0021", new CodePhrase("local")));
    private static DvOrdinal NERVENSYSTEM_SCORE_3 = new DvOrdinal(3L,
            new DvCodedText("at0022", new CodePhrase("local")));
    private static DvOrdinal NERVENSYSTEM_SCORE_4 = new DvOrdinal(4L,
            new DvCodedText("at0023", new CodePhrase("local")));

    private FhirObservationSofaScoreOpenehrSofa() {
    }

    public static SOFAComposition map(Observation fhirObservation) {


        SOFAComposition composition = new SOFAComposition();

        SOFAScoreObservation sofaScore = new SOFAScoreObservation();

        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();

        String sofaScoreCode = fhirObservation.getCode().getCoding().get(0).getCode();

        String atemtaetigkeitCode = fhirObservation.getComponent().get(0).getValueCodeableConcept().
                getCoding().get(0).getCode();

        if (atemtaetigkeitCode.equals("resp1")) {
            sofaScore.setAtemtatigkeit(ATEMFREQUENZ_SCORE_1);
        } else if (atemtaetigkeitCode.equals("resp2")) {
            sofaScore.setAtemtatigkeit(ATEMFREQUENZ_SCORE_2);
        } else if (atemtaetigkeitCode.equals("resp3")) {
            sofaScore.setAtemtatigkeit(ATEMFREQUENZ_SCORE_3);
        } else if (atemtaetigkeitCode.equals("resp4")) {
            sofaScore.setAtemtatigkeit(ATEMFREQUENZ_SCORE_4);
        }


        String nervensystemCode = fhirObservation.getComponent().get(1)
                .getValueCodeableConcept().getCoding().get(0).getCode();

        if (nervensystemCode.equals("ns1")) {
            sofaScore.setZentralesNervensystem(NERVENSYSTEM_SCORE_1);
        } else if (nervensystemCode.equals("ns2")) {
            sofaScore.setZentralesNervensystem(NERVENSYSTEM_SCORE_2);
        } else if (nervensystemCode.equals("ns3")) {
            sofaScore.setZentralesNervensystem(NERVENSYSTEM_SCORE_3);
        } else if (nervensystemCode.equals("ns4")) {
            sofaScore.setZentralesNervensystem(NERVENSYSTEM_SCORE_4);
        }


        fhirObservation.getCode().getCoding().get(0).getCode();
        // sofaScore.setLeberfunktion(3);

        Long sofaScoreCodeLong = Long.parseLong(sofaScoreCode);

        sofaScore.setSofaScoreMagnitude(sofaScoreCodeLong);

        composition.setSofaScore(sofaScore);

        return composition;

    }

}
