package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.occupation_record.v1")
public class BeschaftigungCluster {
  @Path("/items[at0005]/value|defining_code")
  private BerufsbereichDefiningcode berufsbereichDefiningcode;

  @Path("/items[at0004]")
  private List<Cluster> angabenZurOrganisation;

  @Path("/items[at0018]")
  private List<Cluster> zusatzlicheAngaben;

  public void setBerufsbereichDefiningcode(BerufsbereichDefiningcode berufsbereichDefiningcode) {
     this.berufsbereichDefiningcode = berufsbereichDefiningcode;
  }

  public BerufsbereichDefiningcode getBerufsbereichDefiningcode() {
     return this.berufsbereichDefiningcode ;
  }

  public void setAngabenZurOrganisation(List<Cluster> angabenZurOrganisation) {
     this.angabenZurOrganisation = angabenZurOrganisation;
  }

  public List<Cluster> getAngabenZurOrganisation() {
     return this.angabenZurOrganisation ;
  }

  public void setZusatzlicheAngaben(List<Cluster> zusatzlicheAngaben) {
     this.zusatzlicheAngaben = zusatzlicheAngaben;
  }

  public List<Cluster> getZusatzlicheAngaben() {
     return this.zusatzlicheAngaben ;
  }
}
