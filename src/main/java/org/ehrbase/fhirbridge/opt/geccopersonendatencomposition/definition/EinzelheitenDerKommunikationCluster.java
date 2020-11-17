package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.telecom_details.v0")
public class EinzelheitenDerKommunikationCluster {
  @Path("/items[at0020]")
  private List<EinzelheitenDerKommunikationInternetKommunikationCluster> internetKommunikation;

  @Path("/items[at0010]")
  private List<EinzelheitenDerKommunikationModusElement> modus;

  @Path("/items[at0001]")
  private List<EinzelheitenDerKommunikationKontaktdatenCluster> kontaktdaten;

  public void setInternetKommunikation(
      List<EinzelheitenDerKommunikationInternetKommunikationCluster> internetKommunikation) {
     this.internetKommunikation = internetKommunikation;
  }

  public List<EinzelheitenDerKommunikationInternetKommunikationCluster> getInternetKommunikation() {
     return this.internetKommunikation ;
  }

  public void setModus(List<EinzelheitenDerKommunikationModusElement> modus) {
     this.modus = modus;
  }

  public List<EinzelheitenDerKommunikationModusElement> getModus() {
     return this.modus ;
  }

  public void setKontaktdaten(List<EinzelheitenDerKommunikationKontaktdatenCluster> kontaktdaten) {
     this.kontaktdaten = kontaktdaten;
  }

  public List<EinzelheitenDerKommunikationKontaktdatenCluster> getKontaktdaten() {
     return this.kontaktdaten ;
  }
}
