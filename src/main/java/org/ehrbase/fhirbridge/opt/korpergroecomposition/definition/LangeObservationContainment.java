package org.ehrbase.fhirbridge.opt.korpergroecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

public class LangeObservationContainment extends Containment {
  public SelectAqlField<LangeObservation> LANGE_OBSERVATION = new AqlFieldImp<LangeObservation>(LangeObservation.class, "", "LangeObservation", LangeObservation.class, this);

  public SelectAqlField<Double> LANGE_MAGNITUDE = new AqlFieldImp<Double>(LangeObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude", "langeMagnitude", Double.class, this);

  public SelectAqlField<String> LANGE_UNITS = new AqlFieldImp<String>(LangeObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units", "langeUnits", String.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(LangeObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<Cluster> GERAT = new AqlFieldImp<Cluster>(LangeObservation.class, "/protocol[at0007]/items[at0011]", "gerat", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(LangeObservation.class, "/protocol[at0007]/items[at0022]", "erweiterung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(LangeObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(LangeObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(LangeObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  private LangeObservationContainment() {
    super("openEHR-EHR-OBSERVATION.height.v2");
  }

  public static LangeObservationContainment getInstance() {
    return new LangeObservationContainment();
  }
}
