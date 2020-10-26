package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Boolean;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class FieberInDenLetzten4TagenClusterContainment extends Containment {
  public SelectAqlField<FieberInDenLetzten4TagenCluster> FIEBER_IN_DEN_LETZTEN4_TAGEN_CLUSTER = new AqlFieldImp<FieberInDenLetzten4TagenCluster>(FieberInDenLetzten4TagenCluster.class, "", "FieberInDenLetzten4TagenCluster", FieberInDenLetzten4TagenCluster.class, this);

  public SelectAqlField<SchweregradDefiningcode> SCHWEREGRAD_DEFININGCODE = new AqlFieldImp<SchweregradDefiningcode>(FieberInDenLetzten4TagenCluster.class, "/items[at0021]/value|defining_code", "schweregradDefiningcode", SchweregradDefiningcode.class, this);

  public ListSelectAqlField<Cluster> KRANKHEITSANZEICHEN = new ListAqlFieldImp<Cluster>(FieberInDenLetzten4TagenCluster.class, "/items[at0063]", "krankheitsanzeichen", Cluster.class, this);

  public SelectAqlField<String> NAME_DES_SYMPTOMS_KRANKHEITSANZEICHENS_VALUE = new AqlFieldImp<String>(FieberInDenLetzten4TagenCluster.class, "/items[at0001]/value|value", "nameDesSymptomsKrankheitsanzeichensValue", String.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_DETAILS = new ListAqlFieldImp<Cluster>(FieberInDenLetzten4TagenCluster.class, "/items[at0153]", "spezifischeDetails", Cluster.class, this);

  public SelectAqlField<Boolean> VORHANDEN_VALUE = new AqlFieldImp<Boolean>(FieberInDenLetzten4TagenCluster.class, "/items[at0035]/value|value", "vorhandenValue", Boolean.class, this);

  public ListSelectAqlField<Cluster> VORANGEGANGENE_EPISODEN = new ListAqlFieldImp<Cluster>(FieberInDenLetzten4TagenCluster.class, "/items[at0146]", "vorangegangeneEpisoden", Cluster.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_ANATOMISCHE_LOKALISATION = new ListAqlFieldImp<Cluster>(FieberInDenLetzten4TagenCluster.class, "/items[at0147]", "spezifischeAnatomischeLokalisation", Cluster.class, this);

  private FieberInDenLetzten4TagenClusterContainment() {
    super("openEHR-EHR-CLUSTER.symptom_sign.v1");
  }

  public static FieberInDenLetzten4TagenClusterContainment getInstance() {
    return new FieberInDenLetzten4TagenClusterContainment();
  }
}
