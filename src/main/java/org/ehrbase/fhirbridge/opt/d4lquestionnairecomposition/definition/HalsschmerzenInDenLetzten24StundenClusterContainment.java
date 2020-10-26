package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Boolean;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class HalsschmerzenInDenLetzten24StundenClusterContainment extends Containment {
  public SelectAqlField<HalsschmerzenInDenLetzten24StundenCluster> HALSSCHMERZEN_IN_DEN_LETZTEN24_STUNDEN_CLUSTER = new AqlFieldImp<HalsschmerzenInDenLetzten24StundenCluster>(HalsschmerzenInDenLetzten24StundenCluster.class, "", "HalsschmerzenInDenLetzten24StundenCluster", HalsschmerzenInDenLetzten24StundenCluster.class, this);

  public ListSelectAqlField<Cluster> KRANKHEITSANZEICHEN = new ListAqlFieldImp<Cluster>(HalsschmerzenInDenLetzten24StundenCluster.class, "/items[at0063]", "krankheitsanzeichen", Cluster.class, this);

  public SelectAqlField<String> NAME_DES_SYMPTOMS_KRANKHEITSANZEICHENS_VALUE = new AqlFieldImp<String>(HalsschmerzenInDenLetzten24StundenCluster.class, "/items[at0001]/value|value", "nameDesSymptomsKrankheitsanzeichensValue", String.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_DETAILS = new ListAqlFieldImp<Cluster>(HalsschmerzenInDenLetzten24StundenCluster.class, "/items[at0153]", "spezifischeDetails", Cluster.class, this);

  public SelectAqlField<Boolean> VORHANDEN_VALUE = new AqlFieldImp<Boolean>(HalsschmerzenInDenLetzten24StundenCluster.class, "/items[at0035]/value|value", "vorhandenValue", Boolean.class, this);

  public ListSelectAqlField<Cluster> VORANGEGANGENE_EPISODEN = new ListAqlFieldImp<Cluster>(HalsschmerzenInDenLetzten24StundenCluster.class, "/items[at0146]", "vorangegangeneEpisoden", Cluster.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_ANATOMISCHE_LOKALISATION = new ListAqlFieldImp<Cluster>(HalsschmerzenInDenLetzten24StundenCluster.class, "/items[at0147]", "spezifischeAnatomischeLokalisation", Cluster.class, this);

  private HalsschmerzenInDenLetzten24StundenClusterContainment() {
    super("openEHR-EHR-CLUSTER.symptom_sign.v1");
  }

  public static HalsschmerzenInDenLetzten24StundenClusterContainment getInstance() {
    return new HalsschmerzenInDenLetzten24StundenClusterContainment();
  }
}
