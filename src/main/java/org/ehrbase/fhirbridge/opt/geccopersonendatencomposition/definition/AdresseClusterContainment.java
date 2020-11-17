package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AdresseClusterContainment extends Containment {
  public SelectAqlField<AdresseCluster> ADRESSE_CLUSTER = new AqlFieldImp<AdresseCluster>(AdresseCluster.class, "", "AdresseCluster", AdresseCluster.class, this);

  public SelectAqlField<String> POSTLEITZAHL_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0014]/value|value", "postleitzahlValue", String.class, this);

  public SelectAqlField<TypDerAdresseDefiningcode> TYP_DER_ADRESSE_DEFININGCODE = new AqlFieldImp<TypDerAdresseDefiningcode>(AdresseCluster.class, "/items[at0006]/value|defining_code", "typDerAdresseDefiningcode", TypDerAdresseDefiningcode.class, this);

  public SelectAqlField<VerwendungDefiningcode> VERWENDUNG_DEFININGCODE = new AqlFieldImp<VerwendungDefiningcode>(AdresseCluster.class, "/items[at0001]/value|defining_code", "verwendungDefiningcode", VerwendungDefiningcode.class, this);

  public ListSelectAqlField<AdresseZeileElement> ZEILE = new ListAqlFieldImp<AdresseZeileElement>(AdresseCluster.class, "/items[at0011]", "zeile", AdresseZeileElement.class, this);

  public SelectAqlField<String> TEXT_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0010]/value|value", "textValue", String.class, this);

  public SelectAqlField<String> STADT_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0012]/value|value", "stadtValue", String.class, this);

  public SelectAqlField<String> BEZIRK_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0013]/value|value", "bezirkValue", String.class, this);

  public SelectAqlField<String> LAND_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0015]/value|value", "landValue", String.class, this);

  public SelectAqlField<TemporalAccessor> BEGINN_DER_GULTIGKEITSDAUER_VALUE = new AqlFieldImp<TemporalAccessor>(AdresseCluster.class, "/items[at0016]/value|value", "beginnDerGultigkeitsdauerValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ENDE_DER_GULTIGKEITSDAUER_VALUE = new AqlFieldImp<TemporalAccessor>(AdresseCluster.class, "/items[at0017]/value|value", "endeDerGultigkeitsdauerValue", TemporalAccessor.class, this);

  private AdresseClusterContainment() {
    super("openEHR-EHR-CLUSTER.address_cc.v0");
  }

  public static AdresseClusterContainment getInstance() {
    return new AdresseClusterContainment();
  }
}
