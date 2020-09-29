package org.ehrbase.fhirbridge.fhir.provider.Bundle;

import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.provider.ObservationResourceProvider;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.Composition;
import org.hl7.fhir.r4.model.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public abstract class SupportedBundle {
   protected final Bundle bundle;
   protected final static Logger logger = LoggerFactory.getLogger(ObservationResourceProvider.class);

   protected SupportedBundle(Bundle bundle) {
      this.bundle = bundle;
   }

   public abstract ArrayList<MappedComposition> processBundle();

   public abstract void createdLog(VersionUid versionUid);
}
