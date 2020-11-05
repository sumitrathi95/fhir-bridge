package org.ehrbase.fhirbridge.fhir.resource.bundle;

import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;

public class MappedBundleComposition {

    final String subjectId;
    final BefundDerBlutgasanalyseComposition composition;

    public MappedBundleComposition(BefundDerBlutgasanalyseComposition composition, String subjectId) {
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
