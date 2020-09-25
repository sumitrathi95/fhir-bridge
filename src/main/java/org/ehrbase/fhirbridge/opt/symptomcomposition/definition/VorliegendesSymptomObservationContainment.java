package org.ehrbase.fhirbridge.opt.symptomcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.KrankheitsanzeichensDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

public class VorliegendesSymptomObservationContainment extends Containment {
  public SelectAqlField<VorliegendesSymptomObservation> VORLIEGENDES_SYMPTOM_OBSERVATION = new AqlFieldImp<VorliegendesSymptomObservation>(VorliegendesSymptomObservation.class, "", "VorliegendesSymptomObservation", VorliegendesSymptomObservation.class, this);

  public SelectAqlField<CodePhrase> SCHWEREGRAD_DEFININGCODE = new AqlFieldImp<CodePhrase>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0021]/value|defining_code", "schweregradDefiningcode", CodePhrase.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_ANATOMISCHE_LOKALISATION = new ListAqlFieldImp<Cluster>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0147]", "spezifischeAnatomischeLokalisation", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(VorliegendesSymptomObservation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<Cluster> VORANGEGANGENE_EPISODEN = new ListAqlFieldImp<Cluster>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0146]", "vorangegangeneEpisoden", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(VorliegendesSymptomObservation.class, "/data[at0190]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> EXTENSION_EN = new ListAqlFieldImp<Cluster>(VorliegendesSymptomObservation.class, "/protocol[at0193]/items[at0194]", "extensionEn", Cluster.class, this);

  public SelectAqlField<KrankheitsanzeichensDefiningcode> KRANKHEITSANZEICHENS_DEFININGCODE = new AqlFieldImp<KrankheitsanzeichensDefiningcode>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0001]/value|defining_code", "krankheitsanzeichensDefiningcode", KrankheitsanzeichensDefiningcode.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/time|value", "timeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_DETAILS = new ListAqlFieldImp<Cluster>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0153]", "spezifischeDetails", Cluster.class, this);

  public ListSelectAqlField<VorliegendesSymptomAnatomischeLokalisationElement> ANATOMISCHE_LOKALISATION = new ListAqlFieldImp<VorliegendesSymptomAnatomischeLokalisationElement>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0151]", "anatomischeLokalisation", VorliegendesSymptomAnatomischeLokalisationElement.class, this);

  public ListSelectAqlField<Cluster> KRANKHEITSANZEICHEN = new ListAqlFieldImp<Cluster>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0063]", "krankheitsanzeichen", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(VorliegendesSymptomObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TemporalAccessor> BEGINN_DER_EPISODE_VALUE = new AqlFieldImp<TemporalAccessor>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0152]/value|value", "beginnDerEpisodeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> UHRZEIT_DES_RUCKGANGS_VALUE = new AqlFieldImp<TemporalAccessor>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0161]/value|value", "uhrzeitDesRuckgangsValue", TemporalAccessor.class, this);

  private VorliegendesSymptomObservationContainment() {
    super("openEHR-EHR-OBSERVATION.symptom_sign.v0");
  }

  public static VorliegendesSymptomObservationContainment getInstance() {
    return new VorliegendesSymptomObservationContainment();
  }
}
