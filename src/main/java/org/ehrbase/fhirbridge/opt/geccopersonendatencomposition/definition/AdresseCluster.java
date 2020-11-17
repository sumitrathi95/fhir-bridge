package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.address_cc.v0")
public class AdresseCluster {
  @Path("/items[at0014]/value|value")
  private String postleitzahlValue;

  @Path("/items[at0006]/value|defining_code")
  private TypDerAdresseDefiningcode typDerAdresseDefiningcode;

  @Path("/items[at0001]/value|defining_code")
  private VerwendungDefiningcode verwendungDefiningcode;

  @Path("/items[at0011]")
  private List<AdresseZeileElement> zeile;

  @Path("/items[at0010]/value|value")
  private String textValue;

  @Path("/items[at0012]/value|value")
  private String stadtValue;

  @Path("/items[at0013]/value|value")
  private String bezirkValue;

  @Path("/items[at0015]/value|value")
  private String landValue;

  @Path("/items[at0016]/value|value")
  private TemporalAccessor beginnDerGultigkeitsdauerValue;

  @Path("/items[at0017]/value|value")
  private TemporalAccessor endeDerGultigkeitsdauerValue;

  public void setPostleitzahlValue(String postleitzahlValue) {
     this.postleitzahlValue = postleitzahlValue;
  }

  public String getPostleitzahlValue() {
     return this.postleitzahlValue ;
  }

  public void setTypDerAdresseDefiningcode(TypDerAdresseDefiningcode typDerAdresseDefiningcode) {
     this.typDerAdresseDefiningcode = typDerAdresseDefiningcode;
  }

  public TypDerAdresseDefiningcode getTypDerAdresseDefiningcode() {
     return this.typDerAdresseDefiningcode ;
  }

  public void setVerwendungDefiningcode(VerwendungDefiningcode verwendungDefiningcode) {
     this.verwendungDefiningcode = verwendungDefiningcode;
  }

  public VerwendungDefiningcode getVerwendungDefiningcode() {
     return this.verwendungDefiningcode ;
  }

  public void setZeile(List<AdresseZeileElement> zeile) {
     this.zeile = zeile;
  }

  public List<AdresseZeileElement> getZeile() {
     return this.zeile ;
  }

  public void setTextValue(String textValue) {
     this.textValue = textValue;
  }

  public String getTextValue() {
     return this.textValue ;
  }

  public void setStadtValue(String stadtValue) {
     this.stadtValue = stadtValue;
  }

  public String getStadtValue() {
     return this.stadtValue ;
  }

  public void setBezirkValue(String bezirkValue) {
     this.bezirkValue = bezirkValue;
  }

  public String getBezirkValue() {
     return this.bezirkValue ;
  }

  public void setLandValue(String landValue) {
     this.landValue = landValue;
  }

  public String getLandValue() {
     return this.landValue ;
  }

  public void setBeginnDerGultigkeitsdauerValue(TemporalAccessor beginnDerGultigkeitsdauerValue) {
     this.beginnDerGultigkeitsdauerValue = beginnDerGultigkeitsdauerValue;
  }

  public TemporalAccessor getBeginnDerGultigkeitsdauerValue() {
     return this.beginnDerGultigkeitsdauerValue ;
  }

  public void setEndeDerGultigkeitsdauerValue(TemporalAccessor endeDerGultigkeitsdauerValue) {
     this.endeDerGultigkeitsdauerValue = endeDerGultigkeitsdauerValue;
  }

  public TemporalAccessor getEndeDerGultigkeitsdauerValue() {
     return this.endeDerGultigkeitsdauerValue ;
  }
}
