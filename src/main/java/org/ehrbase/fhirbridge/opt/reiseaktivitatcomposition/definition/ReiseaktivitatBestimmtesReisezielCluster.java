package org.ehrbase.fhirbridge.opt.reiseaktivitatcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.BundeslandRegionDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.LandDefiningcode;

@Entity
public class ReiseaktivitatBestimmtesReisezielCluster {
  @Path("/items[at0024]")
  private List<Cluster> zusatzlicheAngabenZumZielort;

  @Path("/items[at0012]/value|defining_code")
  private BundeslandRegionDefiningcode bundeslandRegionDefiningcode;

  @Path("/items[at0011]/value|defining_code")
  private LandDefiningcode landDefiningcode;

  @Path("/items[at0013]/value|value")
  private String stadtValue;

  public void setZusatzlicheAngabenZumZielort(List<Cluster> zusatzlicheAngabenZumZielort) {
     this.zusatzlicheAngabenZumZielort = zusatzlicheAngabenZumZielort;
  }

  public List<Cluster> getZusatzlicheAngabenZumZielort() {
     return this.zusatzlicheAngabenZumZielort ;
  }

  public void setBundeslandRegionDefiningcode(
      BundeslandRegionDefiningcode bundeslandRegionDefiningcode) {
     this.bundeslandRegionDefiningcode = bundeslandRegionDefiningcode;
  }

  public BundeslandRegionDefiningcode getBundeslandRegionDefiningcode() {
     return this.bundeslandRegionDefiningcode ;
  }

  public void setLandDefiningcode(LandDefiningcode landDefiningcode) {
     this.landDefiningcode = landDefiningcode;
  }

  public LandDefiningcode getLandDefiningcode() {
     return this.landDefiningcode ;
  }

  public void setStadtValue(String stadtValue) {
     this.stadtValue = stadtValue;
  }

  public String getStadtValue() {
     return this.stadtValue ;
  }
}
