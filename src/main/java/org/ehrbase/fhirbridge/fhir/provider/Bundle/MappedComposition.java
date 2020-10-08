package org.ehrbase.fhirbridge.fhir.provider.Bundle;

import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;

public class MappedComposition {

    final String subjectId;
    final BefundDerBlutgasanalyseComposition composition;

    public MappedComposition(BefundDerBlutgasanalyseComposition composition, String subjectId) {
        this.subjectId = subjectId;
        this.composition = composition;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public BefundDerBlutgasanalyseComposition getComposition() {
        return composition;
    }
}
