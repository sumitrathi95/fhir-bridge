package org.ehrbase.fhirbridge.opt.geccolaborbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ProLaboranalytKommentarElement {
  @Path("/value|value")
  private String value;

  public void setValue(String value) {
     this.value = value;
  }

  public String getValue() {
     return this.value ;
  }
}
