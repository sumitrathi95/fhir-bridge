package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class EthnischerHintergrundClusterContainment extends Containment {
  public SelectAqlField<EthnischerHintergrundCluster> ETHNISCHER_HINTERGRUND_CLUSTER = new AqlFieldImp<EthnischerHintergrundCluster>(EthnischerHintergrundCluster.class, "", "EthnischerHintergrundCluster", EthnischerHintergrundCluster.class, this);

  public SelectAqlField<EthnischerHintergrundDefiningcode> ETHNISCHER_HINTERGRUND_DEFININGCODE = new AqlFieldImp<EthnischerHintergrundDefiningcode>(EthnischerHintergrundCluster.class, "/items[at0002]/value|defining_code", "ethnischerHintergrundDefiningcode", EthnischerHintergrundDefiningcode.class, this);

  public ListSelectAqlField<Cluster> DETAILS = new ListAqlFieldImp<Cluster>(EthnischerHintergrundCluster.class, "/items[at0003]", "details", Cluster.class, this);

  private EthnischerHintergrundClusterContainment() {
    super("openEHR-EHR-CLUSTER.ethnischer_hintergrund.v0");
  }

  public static EthnischerHintergrundClusterContainment getInstance() {
    return new EthnischerHintergrundClusterContainment();
  }
}
