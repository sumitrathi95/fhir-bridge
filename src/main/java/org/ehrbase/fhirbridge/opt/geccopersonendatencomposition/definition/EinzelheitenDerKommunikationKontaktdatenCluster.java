package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class EinzelheitenDerKommunikationKontaktdatenCluster {
  @Path("/items[at0004]/value|defining_code")
  private KontakttypDefiningcode kontakttypDefiningcode;

  @Path("/items[at0002]/value|value")
  private String unstrukturierteKontaktadresseValue;

  @Path("/items[at0003]/items[at0019]/value|value")
  private String durchwahlValue;

  @Path("/items[at0003]/items[at0006]/value|value")
  private String ortsvorwahlValue;

  @Path("/items[at0003]/items[at0005]/value|value")
  private String landesvorwahlValue;

  @Path("/items[at0003]/items[at0007]/value|value")
  private String nummerValue;

  public void setKontakttypDefiningcode(KontakttypDefiningcode kontakttypDefiningcode) {
     this.kontakttypDefiningcode = kontakttypDefiningcode;
  }

  public KontakttypDefiningcode getKontakttypDefiningcode() {
     return this.kontakttypDefiningcode ;
  }

  public void setUnstrukturierteKontaktadresseValue(String unstrukturierteKontaktadresseValue) {
     this.unstrukturierteKontaktadresseValue = unstrukturierteKontaktadresseValue;
  }

  public String getUnstrukturierteKontaktadresseValue() {
     return this.unstrukturierteKontaktadresseValue ;
  }

  public void setDurchwahlValue(String durchwahlValue) {
     this.durchwahlValue = durchwahlValue;
  }

  public String getDurchwahlValue() {
     return this.durchwahlValue ;
  }

  public void setOrtsvorwahlValue(String ortsvorwahlValue) {
     this.ortsvorwahlValue = ortsvorwahlValue;
  }

  public String getOrtsvorwahlValue() {
     return this.ortsvorwahlValue ;
  }

  public void setLandesvorwahlValue(String landesvorwahlValue) {
     this.landesvorwahlValue = landesvorwahlValue;
  }

  public String getLandesvorwahlValue() {
     return this.landesvorwahlValue ;
  }

  public void setNummerValue(String nummerValue) {
     this.nummerValue = nummerValue;
  }

  public String getNummerValue() {
     return this.nummerValue ;
  }
}
