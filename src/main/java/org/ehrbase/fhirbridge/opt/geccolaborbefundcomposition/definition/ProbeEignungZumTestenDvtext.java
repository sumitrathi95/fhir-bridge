package org.ehrbase.fhirbridge.opt.geccolaborbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class ProbeEignungZumTestenDvtext implements ProbeEignungZumTestenChoice {
  @Path("|value")
  private String eignungZumTestenValue;

  public void setEignungZumTestenValue(String eignungZumTestenValue) {
     this.eignungZumTestenValue = eignungZumTestenValue;
  }

  public String getEignungZumTestenValue() {
     return this.eignungZumTestenValue ;
  }
}
