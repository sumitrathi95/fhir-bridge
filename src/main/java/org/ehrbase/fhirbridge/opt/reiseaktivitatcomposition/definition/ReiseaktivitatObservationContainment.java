package org.ehrbase.fhirbridge.opt.reiseaktivitatcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.MathFunctionDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.ReiseDefiningcode;

public class ReiseaktivitatObservationContainment extends Containment {
  public SelectAqlField<ReiseaktivitatObservation> REISEAKTIVITAT_OBSERVATION = new AqlFieldImp<ReiseaktivitatObservation>(ReiseaktivitatObservation.class, "", "ReiseaktivitatObservation", ReiseaktivitatObservation.class, this);

  public SelectAqlField<ReiseDefiningcode> REISE_DEFININGCODE = new AqlFieldImp<ReiseDefiningcode>(ReiseaktivitatObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code", "reiseDefiningcode", ReiseDefiningcode.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ReiseaktivitatObservation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<ReiseaktivitatBestimmtesReisezielCluster> BESTIMMTES_REISEZIEL = new ListAqlFieldImp<ReiseaktivitatBestimmtesReisezielCluster>(ReiseaktivitatObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0010]", "bestimmtesReiseziel", ReiseaktivitatBestimmtesReisezielCluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ReiseaktivitatObservation.class, "/protocol[at0007]/items[at0021]", "erweiterung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> ABREISEDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(ReiseaktivitatObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0009]/value|value", "abreisedatumValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> ZUSATZLICHE_REISEDETAILS = new ListAqlFieldImp<Cluster>(ReiseaktivitatObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0025]", "zusatzlicheReisedetails", Cluster.class, this);

  public SelectAqlField<TemporalAmount> WIDTH_VALUE = new AqlFieldImp<TemporalAmount>(ReiseaktivitatObservation.class, "/data[at0001]/events[at0002]/width|value", "widthValue", TemporalAmount.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(ReiseaktivitatObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ReiseaktivitatObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(ReiseaktivitatObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> RUCKREISEDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(ReiseaktivitatObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0019]/value|value", "ruckreisedatumValue", TemporalAccessor.class, this);

  public SelectAqlField<MathFunctionDefiningcode> MATH_FUNCTION_DEFININGCODE = new AqlFieldImp<MathFunctionDefiningcode>(ReiseaktivitatObservation.class, "/data[at0001]/events[at0002]/math_function|defining_code", "mathFunctionDefiningcode", MathFunctionDefiningcode.class, this);

  private ReiseaktivitatObservationContainment() {
    super("openEHR-EHR-OBSERVATION.travel_event.v0");
  }

  public static ReiseaktivitatObservationContainment getInstance() {
    return new ReiseaktivitatObservationContainment();
  }
}
