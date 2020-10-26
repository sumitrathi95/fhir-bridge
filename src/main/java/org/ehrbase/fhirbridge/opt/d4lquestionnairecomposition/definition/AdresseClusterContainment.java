package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AdresseClusterContainment extends Containment {
  public SelectAqlField<AdresseCluster> ADRESSE_CLUSTER = new AqlFieldImp<AdresseCluster>(AdresseCluster.class, "", "AdresseCluster", AdresseCluster.class, this);

  public SelectAqlField<ArtDefiningcode> ART_DEFININGCODE = new AqlFieldImp<ArtDefiningcode>(AdresseCluster.class, "/items[at0001]/items[at0006]/value|defining_code", "artDefiningcode", ArtDefiningcode.class, this);

  public SelectAqlField<String> POSTLEITZAHL_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0001]/items[at0004]/value|value", "postleitzahlValue", String.class, this);

  private AdresseClusterContainment() {
    super("openEHR-EHR-CLUSTER.address.v0");
  }

  public static AdresseClusterContainment getInstance() {
    return new AdresseClusterContainment();
  }
}
