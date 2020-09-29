package org.ehrbase.fhirbridge.fhir.provider.Bundle;

import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.Composition;

public class MappedComposition {

    final String subjectId;
    final Composition composition;

    public MappedComposition(Composition composition, String subjectId) {
        this.subjectId = subjectId;
        this.composition = composition;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public Composition getComposition() {
        return composition;
    }
}
