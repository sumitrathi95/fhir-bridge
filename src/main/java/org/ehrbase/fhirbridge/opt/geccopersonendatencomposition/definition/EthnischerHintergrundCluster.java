package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.ethnischer_hintergrund.v0")
public class EthnischerHintergrundCluster {
  @Path("/items[at0002]/value|defining_code")
  private EthnischerHintergrundDefiningcode ethnischerHintergrundDefiningcode;

  @Path("/items[at0003]")
  private List<Cluster> details;

  public void setEthnischerHintergrundDefiningcode(
      EthnischerHintergrundDefiningcode ethnischerHintergrundDefiningcode) {
     this.ethnischerHintergrundDefiningcode = ethnischerHintergrundDefiningcode;
  }

  public EthnischerHintergrundDefiningcode getEthnischerHintergrundDefiningcode() {
     return this.ethnischerHintergrundDefiningcode ;
  }

  public void setDetails(List<Cluster> details) {
     this.details = details;
  }

  public List<Cluster> getDetails() {
     return this.details ;
  }
}
