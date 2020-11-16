package org.ehrbase.fhirbridge.opt.symptomcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.ehrbase.fhirbridge.opt.symptomcomposition.definition.AusgeschlossenesSymptomEvaluation;
import org.ehrbase.fhirbridge.opt.symptomcomposition.definition.KategorieDefiningcode;
import org.ehrbase.fhirbridge.opt.symptomcomposition.definition.StatusDefiningcode;
import org.ehrbase.fhirbridge.opt.symptomcomposition.definition.UnbekanntesSymptomEvaluation;
import org.ehrbase.fhirbridge.opt.symptomcomposition.definition.VorliegendesSymptomObservation;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.registereintrag.v1")
@Template("Symptom")
public class SymptomComposition {
  @Id
  private VersionUid versionUid;

  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  @Path("/context/participations")
  private List<Participation> participations;

  @Path("/language")
  private Language language;

  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  @Path("/context/other_context[at0001]/items[at0004]/value|defining_code")
  private StatusDefiningcode statusDefiningcode;

  @Path("/context/other_context[at0001]/items[at0005]/value|defining_code")
  private KategorieDefiningcode kategorieDefiningcode;

  @Path("/territory")
  private Territory territory;

  @Path("/content[openEHR-EHR-EVALUATION.exclusion_specific.v1 and name/value='Ausgeschlossenes Symptom']")
  private AusgeschlossenesSymptomEvaluation ausgeschlossenesSymptom;

  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  @Path("/composer")
  private PartyProxy composer;

  @Path("/content[openEHR-EHR-EVALUATION.absence.v2 and name/value='Unbekanntes Symptom']")
  private UnbekanntesSymptomEvaluation unbekanntesSymptom;

  @Path("/context/setting|defining_code")
  private SettingDefiningcode settingDefiningcode;

  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  @Path("/context/location")
  private String location;

  @Path("/content[openEHR-EHR-OBSERVATION.symptom_sign.v0 and name/value='Vorliegendes Symptom']")
  private VorliegendesSymptomObservation vorliegendesSymptom;

  @Path("/category|defining_code")
  private CategoryDefiningcode categoryDefiningcode;

  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> erweiterung;

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
     this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
     return this.endTimeValue ;
  }

  public void setParticipations(List<Participation> participations) {
     this.participations = participations;
  }

  public List<Participation> getParticipations() {
     return this.participations ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
     this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
     return this.healthCareFacility ;
  }

  public void setStatusDefiningcode(StatusDefiningcode statusDefiningcode) {
     this.statusDefiningcode = statusDefiningcode;
  }

  public StatusDefiningcode getStatusDefiningcode() {
     return this.statusDefiningcode ;
  }

  public void setKategorieDefiningcode(KategorieDefiningcode kategorieDefiningcode) {
     this.kategorieDefiningcode = kategorieDefiningcode;
  }

  public KategorieDefiningcode getKategorieDefiningcode() {
     return this.kategorieDefiningcode ;
  }

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
  }

  public void setAusgeschlossenesSymptom(
      AusgeschlossenesSymptomEvaluation ausgeschlossenesSymptom) {
     this.ausgeschlossenesSymptom = ausgeschlossenesSymptom;
  }

  public AusgeschlossenesSymptomEvaluation getAusgeschlossenesSymptom() {
     return this.ausgeschlossenesSymptom ;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

  public void setComposer(PartyProxy composer) {
     this.composer = composer;
  }

  public PartyProxy getComposer() {
     return this.composer ;
  }

  public void setUnbekanntesSymptom(UnbekanntesSymptomEvaluation unbekanntesSymptom) {
     this.unbekanntesSymptom = unbekanntesSymptom;
  }

  public UnbekanntesSymptomEvaluation getUnbekanntesSymptom() {
     return this.unbekanntesSymptom ;
  }

  public void setSettingDefiningcode(SettingDefiningcode settingDefiningcode) {
     this.settingDefiningcode = settingDefiningcode;
  }

  public SettingDefiningcode getSettingDefiningcode() {
     return this.settingDefiningcode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setLocation(String location) {
     this.location = location;
  }

  public String getLocation() {
     return this.location ;
  }

  public void setVorliegendesSymptom(VorliegendesSymptomObservation vorliegendesSymptom) {
     this.vorliegendesSymptom = vorliegendesSymptom;
  }

  public VorliegendesSymptomObservation getVorliegendesSymptom() {
     return this.vorliegendesSymptom ;
  }

  public void setCategoryDefiningcode(CategoryDefiningcode categoryDefiningcode) {
     this.categoryDefiningcode = categoryDefiningcode;
  }

  public CategoryDefiningcode getCategoryDefiningcode() {
     return this.categoryDefiningcode ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }
}
