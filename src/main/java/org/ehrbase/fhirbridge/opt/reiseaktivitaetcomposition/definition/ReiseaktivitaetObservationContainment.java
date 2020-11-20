package org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.MathFunctionDefiningcode;

public class ReiseaktivitaetObservationContainment extends Containment {
  public SelectAqlField<ReiseaktivitaetObservation> REISEAKTIVITAET_OBSERVATION = new AqlFieldImp<ReiseaktivitaetObservation>(ReiseaktivitaetObservation.class, "", "ReiseaktivitaetObservation", ReiseaktivitaetObservation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ReiseaktivitaetObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<LandDefiningcode> LAND_DEFININGCODE = new AqlFieldImp<LandDefiningcode>(ReiseaktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0010]/items[at0011]/value|defining_code", "landDefiningcode", LandDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ZUSATZLICHE_REISEDETAILS = new ListAqlFieldImp<Cluster>(ReiseaktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0025]", "zusatzlicheReisedetails", Cluster.class, this);

  public SelectAqlField<BundeslandRegionDefiningcode> BUNDESLAND_REGION_DEFININGCODE = new AqlFieldImp<BundeslandRegionDefiningcode>(ReiseaktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0010]/items[at0012]/value|defining_code", "bundeslandRegionDefiningcode", BundeslandRegionDefiningcode.class, this);

  public SelectAqlField<String> STADT_VALUE = new AqlFieldImp<String>(ReiseaktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0010]/items[at0013]/value|value", "stadtValue", String.class, this);

  public ListSelectAqlField<Cluster> ZUSATZLICHE_ANGABEN_ZUM_ZIELORT = new ListAqlFieldImp<Cluster>(ReiseaktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0010]/items[at0024]", "zusatzlicheAngabenZumZielort", Cluster.class, this);

  public SelectAqlField<ReiseDefiningcode> REISE_DEFININGCODE = new AqlFieldImp<ReiseDefiningcode>(ReiseaktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code", "reiseDefiningcode", ReiseDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ReiseaktivitaetObservation.class, "/protocol[at0007]/items[at0021]", "erweiterung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> ABREISEDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(ReiseaktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0009]/value|value", "abreisedatumValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAmount> VALUE = new AqlFieldImp<TemporalAmount>(ReiseaktivitaetObservation.class, "/data[at0001]/events[at0002]/width|value", "value", TemporalAmount.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(ReiseaktivitaetObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ReiseaktivitaetObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(ReiseaktivitaetObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> RUCKREISEDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(ReiseaktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0019]/value|value", "ruckreisedatumValue", TemporalAccessor.class, this);

  public SelectAqlField<MathFunctionDefiningcode> MATH_FUNCTION_DEFININGCODE = new AqlFieldImp<MathFunctionDefiningcode>(ReiseaktivitaetObservation.class, "/data[at0001]/events[at0002]/math_function|defining_code", "mathFunctionDefiningcode", MathFunctionDefiningcode.class, this);

  private ReiseaktivitaetObservationContainment() {
    super("openEHR-EHR-OBSERVATION.travel_event.v0");
  }

  public static ReiseaktivitaetObservationContainment getInstance() {
    return new ReiseaktivitaetObservationContainment();
  }
}
