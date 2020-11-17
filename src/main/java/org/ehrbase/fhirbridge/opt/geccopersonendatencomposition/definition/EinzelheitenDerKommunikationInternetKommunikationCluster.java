package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class EinzelheitenDerKommunikationInternetKommunikationCluster {
  @Path("/items[at0021]/value|value")
  private String senderValue;

  @Path("/items[at0009]/value")
  @Choice
  private EinzelheitenDerKommunikationAdresseChoice adresse;

  public void setSenderValue(String senderValue) {
     this.senderValue = senderValue;
  }

  public String getSenderValue() {
     return this.senderValue ;
  }

  public void setAdresse(EinzelheitenDerKommunikationAdresseChoice adresse) {
     this.adresse = adresse;
  }

  public EinzelheitenDerKommunikationAdresseChoice getAdresse() {
     return this.adresse ;
  }
}
