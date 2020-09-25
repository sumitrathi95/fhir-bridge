package org.ehrbase.fhirbridge.opt.symptomcomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.Definingcode;

@Entity
public class UnbekanntesSymptomAussageUberDieFehlendeInformationElement {
  @Path("/value|defining_code")
  private Definingcode definingcode;

  public void setDefiningcode(Definingcode definingcode) {
     this.definingcode = definingcode;
  }

  public Definingcode getDefiningcode() {
     return this.definingcode ;
  }
}
