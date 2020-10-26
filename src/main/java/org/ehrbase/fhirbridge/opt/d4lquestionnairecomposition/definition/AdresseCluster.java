package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.address.v0")
public class AdresseCluster {
  @Path("/items[at0001]/items[at0006]/value|defining_code")
  private ArtDefiningcode artDefiningcode;

  @Path("/items[at0001]/items[at0004]/value|value")
  private String postleitzahlValue;

  public void setArtDefiningcode(ArtDefiningcode artDefiningcode) {
     this.artDefiningcode = artDefiningcode;
  }

  public ArtDefiningcode getArtDefiningcode() {
     return this.artDefiningcode ;
  }

  public void setPostleitzahlValue(String postleitzahlValue) {
     this.postleitzahlValue = postleitzahlValue;
  }

  public String getPostleitzahlValue() {
     return this.postleitzahlValue ;
  }
}
