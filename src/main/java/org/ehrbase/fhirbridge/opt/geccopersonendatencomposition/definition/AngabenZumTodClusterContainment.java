package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AngabenZumTodClusterContainment extends Containment {
  public SelectAqlField<AngabenZumTodCluster> ANGABEN_ZUM_TOD_CLUSTER = new AqlFieldImp<AngabenZumTodCluster>(AngabenZumTodCluster.class, "", "AngabenZumTodCluster", AngabenZumTodCluster.class, this);

  public SelectAqlField<TemporalAccessor> STERBEDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(AngabenZumTodCluster.class, "/items[at0001]/value|value", "sterbedatumValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_ANGABEN = new ListAqlFieldImp<Cluster>(AngabenZumTodCluster.class, "/items[at0005]", "spezifischeAngaben", Cluster.class, this);

  private AngabenZumTodClusterContainment() {
    super("openEHR-EHR-CLUSTER.death_details.v1");
  }

  public static AngabenZumTodClusterContainment getInstance() {
    return new AngabenZumTodClusterContainment();
  }
}
