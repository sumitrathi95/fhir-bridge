package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class EinzelheitenDerKommunikationAdresseDvtext implements EinzelheitenDerKommunikationAdresseChoice {
  @Path("|value")
  private String adresseValue;

  public void setAdresseValue(String adresseValue) {
     this.adresseValue = adresseValue;
  }

  public String getAdresseValue() {
     return this.adresseValue ;
  }
}
