package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
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
import org.ehrbase.fhirbridge.opt.CompositionAbstract;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.AllgemeineAngabenSection;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.DatenspendeSection;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.MedikamenteImpfungenSection;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.SymptomeSection;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition.VorGrunderkrankungenSection;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.self_monitoring.v0")
@Template("D4L_questionnaire")
public class D4LQuestionnaireComposition extends CompositionAbstract {
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

  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']")
  private AllgemeineAngabenSection allgemeineAngaben;

  @Path("/territory")
  private Territory territory;

  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Medikamente / Impfungen']")
  private MedikamenteImpfungenSection medikamenteImpfungen;

  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']")
  private SymptomeSection symptome;

  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Vor-/Grunderkrankungen']")
  private VorGrunderkrankungenSection vorGrunderkrankungen;

  @Path("/composer")
  private PartyProxy composer;

  @Path("/context/setting|defining_code")
  private SettingDefiningcode settingDefiningcode;

  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  @Path("/context/location")
  private String location;

  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Datenspende']")
  private DatenspendeSection datenspende;

  @Path("/category|defining_code")
  private CategoryDefiningcode categoryDefiningcode;

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

  public void setAllgemeineAngaben(AllgemeineAngabenSection allgemeineAngaben) {
     this.allgemeineAngaben = allgemeineAngaben;
  }

  public AllgemeineAngabenSection getAllgemeineAngaben() {
     return this.allgemeineAngaben ;
  }

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

  public void setMedikamenteImpfungen(MedikamenteImpfungenSection medikamenteImpfungen) {
     this.medikamenteImpfungen = medikamenteImpfungen;
  }

  public MedikamenteImpfungenSection getMedikamenteImpfungen() {
     return this.medikamenteImpfungen ;
  }

  public void setSymptome(SymptomeSection symptome) {
     this.symptome = symptome;
  }

  public SymptomeSection getSymptome() {
     return this.symptome ;
  }

  public void setVorGrunderkrankungen(VorGrunderkrankungenSection vorGrunderkrankungen) {
     this.vorGrunderkrankungen = vorGrunderkrankungen;
  }

  public VorGrunderkrankungenSection getVorGrunderkrankungen() {
     return this.vorGrunderkrankungen ;
  }

  public void setComposer(PartyProxy composer) {
     this.composer = composer;
  }

  public PartyProxy getComposer() {
     return this.composer ;
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

  public void setDatenspende(DatenspendeSection datenspende) {
     this.datenspende = datenspende;
  }

  public DatenspendeSection getDatenspende() {
     return this.datenspende ;
  }

  public void setCategoryDefiningcode(CategoryDefiningcode categoryDefiningcode) {
     this.categoryDefiningcode = categoryDefiningcode;
  }

  public CategoryDefiningcode getCategoryDefiningcode() {
     return this.categoryDefiningcode ;
  }
}
