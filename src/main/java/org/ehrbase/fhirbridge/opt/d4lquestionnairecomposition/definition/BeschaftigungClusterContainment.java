package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class BeschaftigungClusterContainment extends Containment {
  public SelectAqlField<BeschaftigungCluster> BESCHAFTIGUNG_CLUSTER = new AqlFieldImp<BeschaftigungCluster>(BeschaftigungCluster.class, "", "BeschaftigungCluster", BeschaftigungCluster.class, this);

  public SelectAqlField<BerufsbereichDefiningcode> BERUFSBEREICH_DEFININGCODE = new AqlFieldImp<BerufsbereichDefiningcode>(BeschaftigungCluster.class, "/items[at0005]/value|defining_code", "berufsbereichDefiningcode", BerufsbereichDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUR_ORGANISATION = new ListAqlFieldImp<Cluster>(BeschaftigungCluster.class, "/items[at0004]", "angabenZurOrganisation", Cluster.class, this);

  public ListSelectAqlField<Cluster> ZUSATZLICHE_ANGABEN = new ListAqlFieldImp<Cluster>(BeschaftigungCluster.class, "/items[at0018]", "zusatzlicheAngaben", Cluster.class, this);

  private BeschaftigungClusterContainment() {
    super("openEHR-EHR-CLUSTER.occupation_record.v1");
  }

  public static BeschaftigungClusterContainment getInstance() {
    return new BeschaftigungClusterContainment();
  }
}
