package org.ehrbase.fhirbridge.fhir.provider.Bundle;

import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.hl7.fhir.r4.model.Bundle;

import java.util.ArrayList;

/**
 * Bundle for unspecific Bundles who just contain a set of resources
 */
public class UnspecificBundle extends SupportedBundle {

    public UnspecificBundle(Bundle bundle) {
        super(bundle);
    }

    @Override
    public ArrayList<MappedComposition> processBundle() {
        SupportedBundle.logger.info(">>>>>>>>>>>>>>>>>> PROCESS UNSPECIFIC BUNDLE");

        return null;
    }

    @Override
    public void createdLog(VersionUid versionUid) {

    }
}
