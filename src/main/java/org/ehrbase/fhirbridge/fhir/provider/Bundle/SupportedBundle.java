package org.ehrbase.fhirbridge.fhir.provider.Bundle;

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.provider.ObservationResourceProvider;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class SupportedBundle extends Bundle {
   protected final Bundle bundle;
   protected final static Logger logger = LoggerFactory.getLogger(ObservationResourceProvider.class);

   protected SupportedBundle(Bundle bundle) {
      this.bundle = bundle;
   }

   public abstract MappedBundleComposition processBundle();

   public abstract void createdLog(VersionUid versionUid);

   String getEhrUID() {
      String ehrUID = "";
      for (Bundle.BundleEntryComponent bundle : bundle.getEntry()) {
         if (checkIfPatientRessourceNotPresent(bundle)) {
            Observation observation = (Observation) bundle.getResource();
            ehrUID = getEntryEhrUID(ehrUID, observation);
         }
      }
      return ehrUID;
   }

   private String getEntryEhrUID(String ehrUID, Observation observation){
      if (ehrUID.equals("") ||  referencesSameEhrUID(ehrUID, observation)) {
         return observation.getSubject().getReference().split(":")[2];
      }
      throw new InternalErrorException("The subject Ids of the profile within the Bundle reference different Patient. A Blood Gas Panel must refer to one identical Patient!");

   }

   private boolean referencesSameEhrUID(String ehrUID, Observation observation){
      return ehrUID.equals(observation.getSubject().getReference().split(":")[2]);
   }

   private boolean checkIfPatientRessourceNotPresent(BundleEntryComponent bundle) {
      if (!bundle.getResource().getResourceType().toString().equals("Patient")) {
         return true;
      }
      throw new InternalErrorException("Patient as a resource is not allowed to be contained !");
   }
}
