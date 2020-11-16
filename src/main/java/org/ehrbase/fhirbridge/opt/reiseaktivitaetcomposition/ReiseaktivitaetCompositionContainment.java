package org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.KeineReiseaktivitatEvaluation;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.ReiseaktivitatObservation;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.StatusDefiningcode;
import org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition.UnbekannteReiseaktivitatEvaluation;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;

public class ReiseaktivitaetCompositionContainment extends Containment {
  public SelectAqlField<ReiseaktivitaetComposition> REISEAKTIVITAET_COMPOSITION = new AqlFieldImp<ReiseaktivitaetComposition>(ReiseaktivitaetComposition.class, "", "ReiseaktivitaetComposition", ReiseaktivitaetComposition.class, this);

  public SelectAqlField<KeineReiseaktivitatEvaluation> KEINE_REISEAKTIVITAT = new AqlFieldImp<KeineReiseaktivitatEvaluation>(ReiseaktivitaetComposition.class, "/content[openEHR-EHR-EVALUATION.exclusion_specific.v1 and name/value='Keine Reiseaktivität']", "keineReiseaktivitat", KeineReiseaktivitatEvaluation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(ReiseaktivitaetComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(ReiseaktivitaetComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ReiseaktivitaetComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(ReiseaktivitaetComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<StatusDefiningcode> STATUS_DEFININGCODE = new AqlFieldImp<StatusDefiningcode>(ReiseaktivitaetComposition.class, "/context/other_context[at0001]/items[at0004]/value|defining_code", "statusDefiningcode", StatusDefiningcode.class, this);

  public SelectAqlField<String> KATEGORIE_VALUE = new AqlFieldImp<String>(ReiseaktivitaetComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "kategorieValue", String.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(ReiseaktivitaetComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(ReiseaktivitaetComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<UnbekannteReiseaktivitatEvaluation> UNBEKANNTE_REISEAKTIVITAT = new AqlFieldImp<UnbekannteReiseaktivitatEvaluation>(ReiseaktivitaetComposition.class, "/content[openEHR-EHR-EVALUATION.absence.v2 and name/value='Unbekannte Reiseaktivität']", "unbekannteReiseaktivitat", UnbekannteReiseaktivitatEvaluation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(ReiseaktivitaetComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(ReiseaktivitaetComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ReiseaktivitaetComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(ReiseaktivitaetComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<ReiseaktivitatObservation> REISEAKTIVITAT = new AqlFieldImp<ReiseaktivitatObservation>(ReiseaktivitaetComposition.class, "/content[openEHR-EHR-OBSERVATION.travel_event.v0 and name/value='Reiseaktivität']", "reiseaktivitat", ReiseaktivitatObservation.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(ReiseaktivitaetComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ReiseaktivitaetComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  private ReiseaktivitaetCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.registereintrag.v1");
  }

  public static ReiseaktivitaetCompositionContainment getInstance() {
    return new ReiseaktivitaetCompositionContainment();
  }
}
