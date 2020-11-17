package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.death_details.v1")
public class AngabenZumTodCluster {
  @Path("/items[at0001]/value|value")
  private TemporalAccessor sterbedatumValue;

  @Path("/items[at0005]")
  private List<Cluster> spezifischeAngaben;

  public void setSterbedatumValue(TemporalAccessor sterbedatumValue) {
     this.sterbedatumValue = sterbedatumValue;
  }

  public TemporalAccessor getSterbedatumValue() {
     return this.sterbedatumValue ;
  }

  public void setSpezifischeAngaben(List<Cluster> spezifischeAngaben) {
     this.spezifischeAngaben = spezifischeAngaben;
  }

  public List<Cluster> getSpezifischeAngaben() {
     return this.spezifischeAngaben ;
  }
}
