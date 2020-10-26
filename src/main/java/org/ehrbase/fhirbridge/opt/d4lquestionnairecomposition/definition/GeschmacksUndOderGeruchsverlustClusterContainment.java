package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Boolean;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class GeschmacksUndOderGeruchsverlustClusterContainment extends Containment {
  public SelectAqlField<GeschmacksUndOderGeruchsverlustCluster> GESCHMACKS_UND_ODER_GERUCHSVERLUST_CLUSTER = new AqlFieldImp<GeschmacksUndOderGeruchsverlustCluster>(GeschmacksUndOderGeruchsverlustCluster.class, "", "GeschmacksUndOderGeruchsverlustCluster", GeschmacksUndOderGeruchsverlustCluster.class, this);

  public ListSelectAqlField<Cluster> KRANKHEITSANZEICHEN = new ListAqlFieldImp<Cluster>(GeschmacksUndOderGeruchsverlustCluster.class, "/items[at0063]", "krankheitsanzeichen", Cluster.class, this);

  public SelectAqlField<String> NAME_DES_SYMPTOMS_KRANKHEITSANZEICHENS_VALUE = new AqlFieldImp<String>(GeschmacksUndOderGeruchsverlustCluster.class, "/items[at0001]/value|value", "nameDesSymptomsKrankheitsanzeichensValue", String.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_DETAILS = new ListAqlFieldImp<Cluster>(GeschmacksUndOderGeruchsverlustCluster.class, "/items[at0153]", "spezifischeDetails", Cluster.class, this);

  public SelectAqlField<Boolean> VORHANDEN_VALUE = new AqlFieldImp<Boolean>(GeschmacksUndOderGeruchsverlustCluster.class, "/items[at0035]/value|value", "vorhandenValue", Boolean.class, this);

  public ListSelectAqlField<Cluster> VORANGEGANGENE_EPISODEN = new ListAqlFieldImp<Cluster>(GeschmacksUndOderGeruchsverlustCluster.class, "/items[at0146]", "vorangegangeneEpisoden", Cluster.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_ANATOMISCHE_LOKALISATION = new ListAqlFieldImp<Cluster>(GeschmacksUndOderGeruchsverlustCluster.class, "/items[at0147]", "spezifischeAnatomischeLokalisation", Cluster.class, this);

  private GeschmacksUndOderGeruchsverlustClusterContainment() {
    super("openEHR-EHR-CLUSTER.symptom_sign.v1");
  }

  public static GeschmacksUndOderGeruchsverlustClusterContainment getInstance() {
    return new GeschmacksUndOderGeruchsverlustClusterContainment();
  }
}
