package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition;

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
import org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition.GeschlechtEvaluation;
import org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition.PersonendatenAdminEntry;
import org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition.StatusDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;

public class GECCOPersonendatenCompositionContainment extends Containment {
  public SelectAqlField<GECCOPersonendatenComposition> G_E_C_C_O_PERSONENDATEN_COMPOSITION = new AqlFieldImp<GECCOPersonendatenComposition>(GECCOPersonendatenComposition.class, "", "GECCOPersonendatenComposition", GECCOPersonendatenComposition.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GECCOPersonendatenComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(GECCOPersonendatenComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(GECCOPersonendatenComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(GECCOPersonendatenComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<StatusDefiningcode> STATUS_DEFININGCODE = new AqlFieldImp<StatusDefiningcode>(GECCOPersonendatenComposition.class, "/context/other_context[at0001]/items[at0004]/value|defining_code", "statusDefiningcode", StatusDefiningcode.class, this);

  public SelectAqlField<String> KATEGORIE_VALUE = new AqlFieldImp<String>(GECCOPersonendatenComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "kategorieValue", String.class, this);

  public SelectAqlField<GeschlechtEvaluation> GESCHLECHT = new AqlFieldImp<GeschlechtEvaluation>(GECCOPersonendatenComposition.class, "/content[openEHR-EHR-EVALUATION.gender.v1]", "geschlecht", GeschlechtEvaluation.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(GECCOPersonendatenComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<PersonendatenAdminEntry> PERSONENDATEN = new AqlFieldImp<PersonendatenAdminEntry>(GECCOPersonendatenComposition.class, "/content[openEHR-EHR-ADMIN_ENTRY.person_data.v0]", "personendaten", PersonendatenAdminEntry.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GECCOPersonendatenComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(GECCOPersonendatenComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(GECCOPersonendatenComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(GECCOPersonendatenComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(GECCOPersonendatenComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(GECCOPersonendatenComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(GECCOPersonendatenComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  private GECCOPersonendatenCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.registereintrag.v1");
  }

  public static GECCOPersonendatenCompositionContainment getInstance() {
    return new GECCOPersonendatenCompositionContainment();
  }
}
