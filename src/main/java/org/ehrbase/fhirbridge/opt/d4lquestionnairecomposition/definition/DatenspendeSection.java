package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class DatenspendeSection {
  @Path("/items[openEHR-EHR-ACTION.informed_consent.v0]")
  private EinwilligungserklarungAction einwilligungserklarung;

  public void setEinwilligungserklarung(EinwilligungserklarungAction einwilligungserklarung) {
     this.einwilligungserklarung = einwilligungserklarung;
  }

  public EinwilligungserklarungAction getEinwilligungserklarung() {
     return this.einwilligungserklarung ;
  }
}
