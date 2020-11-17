package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class EinzelheitenDerKommunikationModusElement {
  @Path("/value|defining_code")
  private Definingcode definingcode;

  public void setDefiningcode(Definingcode definingcode) {
     this.definingcode = definingcode;
  }

  public Definingcode getDefiningcode() {
     return this.definingcode ;
  }
}
