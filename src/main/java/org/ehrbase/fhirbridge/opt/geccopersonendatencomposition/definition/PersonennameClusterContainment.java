package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import java.lang.Boolean;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class PersonennameClusterContainment extends Containment {
  public SelectAqlField<PersonennameCluster> PERSONENNAME_CLUSTER = new AqlFieldImp<PersonennameCluster>(PersonennameCluster.class, "", "PersonennameCluster", PersonennameCluster.class, this);

  public SelectAqlField<DvInterval> GULTIGKEITSZEITRAUM = new AqlFieldImp<DvInterval>(PersonennameCluster.class, "/items[at0014]/value", "gultigkeitszeitraum", DvInterval.class, this);

  public SelectAqlField<NamensartDefiningcode> NAMENSART_DEFININGCODE = new AqlFieldImp<NamensartDefiningcode>(PersonennameCluster.class, "/items[at0006]/value|defining_code", "namensartDefiningcode", NamensartDefiningcode.class, this);

  public SelectAqlField<String> VORNAME_VALUE = new AqlFieldImp<String>(PersonennameCluster.class, "/items[at0002]/items[at0003]/value|value", "vornameValue", String.class, this);

  public SelectAqlField<Boolean> BEVORZUGTER_NAME_VALUE = new AqlFieldImp<Boolean>(PersonennameCluster.class, "/items[at0022]/value|value", "bevorzugterNameValue", Boolean.class, this);

  public SelectAqlField<String> NACHNAME_VALUE = new AqlFieldImp<String>(PersonennameCluster.class, "/items[at0002]/items[at0005]/value|value", "nachnameValue", String.class, this);

  public SelectAqlField<String> NAME_UNSTRUKTURIERT_VALUE = new AqlFieldImp<String>(PersonennameCluster.class, "/items[at0001]/value|value", "nameUnstrukturiertValue", String.class, this);

  public ListSelectAqlField<PersonennameWeitererVornameElement> WEITERER_VORNAME = new ListAqlFieldImp<PersonennameWeitererVornameElement>(PersonennameCluster.class, "/items[at0002]/items[at0004]", "weitererVorname", PersonennameWeitererVornameElement.class, this);

  public SelectAqlField<String> TITEL_VALUE = new AqlFieldImp<String>(PersonennameCluster.class, "/items[at0002]/items[at0017]/value|value", "titelValue", String.class, this);

  public SelectAqlField<String> SUFFIX_VALUE = new AqlFieldImp<String>(PersonennameCluster.class, "/items[at0002]/items[at0018]/value|value", "suffixValue", String.class, this);

  private PersonennameClusterContainment() {
    super("openEHR-EHR-CLUSTER.person_name.v0");
  }

  public static PersonennameClusterContainment getInstance() {
    return new PersonennameClusterContainment();
  }
}
