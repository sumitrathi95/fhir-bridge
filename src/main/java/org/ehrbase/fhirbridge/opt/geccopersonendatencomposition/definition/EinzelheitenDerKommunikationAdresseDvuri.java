package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import java.net.URI;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_URI")
public class EinzelheitenDerKommunikationAdresseDvuri implements EinzelheitenDerKommunikationAdresseChoice {
  @Path("|value")
  private URI adresseValue;

  public void setAdresseValue(URI adresseValue) {
     this.adresseValue = adresseValue;
  }

  public URI getAdresseValue() {
     return this.adresseValue ;
  }
}
