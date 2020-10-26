package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
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
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.AllgemeineAngabenSection;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.DatenspendeSection;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.MedikamenteImpfungenSection;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.SymptomeSection;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.VorGrunderkrankungenSection;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;

public class D4LQuestionnaireCompositionContainment extends Containment {
  public SelectAqlField<D4LQuestionnaireComposition> D4_L_QUESTIONNAIRE_COMPOSITION = new AqlFieldImp<D4LQuestionnaireComposition>(D4LQuestionnaireComposition.class, "", "D4LQuestionnaireComposition", D4LQuestionnaireComposition.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(D4LQuestionnaireComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(D4LQuestionnaireComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(D4LQuestionnaireComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(D4LQuestionnaireComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<AllgemeineAngabenSection> ALLGEMEINE_ANGABEN = new AqlFieldImp<AllgemeineAngabenSection>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']", "allgemeineAngaben", AllgemeineAngabenSection.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(D4LQuestionnaireComposition.class, "/territory", "territory", Territory.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(D4LQuestionnaireComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<MedikamenteImpfungenSection> MEDIKAMENTE_IMPFUNGEN = new AqlFieldImp<MedikamenteImpfungenSection>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Medikamente / Impfungen']", "medikamenteImpfungen", MedikamenteImpfungenSection.class, this);

  public SelectAqlField<SymptomeSection> SYMPTOME = new AqlFieldImp<SymptomeSection>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']", "symptome", SymptomeSection.class, this);

  public SelectAqlField<VorGrunderkrankungenSection> VOR_GRUNDERKRANKUNGEN = new AqlFieldImp<VorGrunderkrankungenSection>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Vor-/Grunderkrankungen']", "vorGrunderkrankungen", VorGrunderkrankungenSection.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(D4LQuestionnaireComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<SettingDefiningcode> SETTING_DEFININGCODE = new AqlFieldImp<SettingDefiningcode>(D4LQuestionnaireComposition.class, "/context/setting|defining_code", "settingDefiningcode", SettingDefiningcode.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(D4LQuestionnaireComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(D4LQuestionnaireComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<DatenspendeSection> DATENSPENDE = new AqlFieldImp<DatenspendeSection>(D4LQuestionnaireComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Datenspende']", "datenspende", DatenspendeSection.class, this);

  public SelectAqlField<CategoryDefiningcode> CATEGORY_DEFININGCODE = new AqlFieldImp<CategoryDefiningcode>(D4LQuestionnaireComposition.class, "/category|defining_code", "categoryDefiningcode", CategoryDefiningcode.class, this);

  private D4LQuestionnaireCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.self_monitoring.v0");
  }

  public static D4LQuestionnaireCompositionContainment getInstance() {
    return new D4LQuestionnaireCompositionContainment();
  }
}
