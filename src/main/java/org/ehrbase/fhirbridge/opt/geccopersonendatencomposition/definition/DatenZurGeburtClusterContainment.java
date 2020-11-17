package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import java.time.temporal.Temporal;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class DatenZurGeburtClusterContainment extends Containment {
  public SelectAqlField<DatenZurGeburtCluster> DATEN_ZUR_GEBURT_CLUSTER = new AqlFieldImp<DatenZurGeburtCluster>(DatenZurGeburtCluster.class, "", "DatenZurGeburtCluster", DatenZurGeburtCluster.class, this);

  public SelectAqlField<CodePhrase> KODIERUNG_FUR_MEHRLINGSGEBURTEN_DEFININGCODE = new AqlFieldImp<CodePhrase>(DatenZurGeburtCluster.class, "/items[at0003]/value|defining_code", "kodierungFurMehrlingsgeburtenDefiningcode", CodePhrase.class, this);

  public SelectAqlField<Temporal> GEBURTSDATUM_VALUE = new AqlFieldImp<Temporal>(DatenZurGeburtCluster.class, "/items[at0001]/value|value", "geburtsdatumValue", Temporal.class, this);

  public SelectAqlField<Cluster> LANDERSPEZIFISCHE_DATEN = new AqlFieldImp<Cluster>(DatenZurGeburtCluster.class, "/items[at0006]", "landerspezifischeDaten", Cluster.class, this);

  private DatenZurGeburtClusterContainment() {
    super("openEHR-EHR-CLUSTER.person_birth_data_iso.v0");
  }

  public static DatenZurGeburtClusterContainment getInstance() {
    return new DatenZurGeburtClusterContainment();
  }
}
