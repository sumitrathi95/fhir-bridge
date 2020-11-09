package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.problem_diagnosis.v1")
public class ProblemDiagnoseEvaluation {
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Schnupfen  in den letzten 24 Stunden']")
  private SchnupfenInDenLetzten24StundenCluster schnupfenInDenLetzten24Stunden;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Gliederschmerzen']")
  private GliederschmerzenCluster gliederschmerzen;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Fieber in den letzten 4 Tagen']")
  private FieberInDenLetzten4TagenCluster fieberInDenLetzten4Tagen;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Halsschmerzen in den letzten 24 Stunden']")
  private HalsschmerzenInDenLetzten24StundenCluster halsschmerzenInDenLetzten24Stunden;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Schlappheit / Angeschlagenheit']")
  private SchlappheitAngeschlagenheitCluster schlappheitAngeschlagenheit;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Husten in den letzten 24 Stunden']")
  private HustenInDenLetzten24StundenCluster hustenInDenLetzten24Stunden;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Kopfschmerzen']")
  private KopfschmerzenCluster kopfschmerzen;

  @Path("/data[at0001]/items[at0046]")
  private List<Cluster> status;

  @Path("/data[at0001]/items[at0077]/value|value")
  private TemporalAccessor datumZeitpunktDesAuftretensDerErstdiagnoseValue;

  @Path("/protocol[at0032]/items[at0071]")
  private List<Cluster> erweiterung;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Durchfall']")
  private DurchfallCluster durchfall;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Geschmacks- und/oder Geruchsverlust']")
  private GeschmacksUndOderGeruchsverlustCluster geschmacksUndOderGeruchsverlust;

  @Path("/data[at0001]/items[at0002]/value|value")
  private String nameDesProblemsDerDiagnoseValue;

  @Path("/data[at0001]/items[at0039]")
  private List<Cluster> anatomischeStelleStrukturiert;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Atemprobleme']")
  private AtemproblemeCluster atemprobleme;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Schüttelfrost in den letzten 24 Stunden']")
  private SchuttelfrostInDenLetzten24StundenCluster schuttelfrostInDenLetzten24Stunden;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Fieber in den letzten 24 Stunden']")
  private FieberInDenLetzten24StundenCluster fieberInDenLetzten24Stunden;

  @Path("/subject")
  private PartyProxy subject;

  public void setSchnupfenInDenLetzten24Stunden(
      SchnupfenInDenLetzten24StundenCluster schnupfenInDenLetzten24Stunden) {
     this.schnupfenInDenLetzten24Stunden = schnupfenInDenLetzten24Stunden;
  }

  public SchnupfenInDenLetzten24StundenCluster getSchnupfenInDenLetzten24Stunden() {
     return this.schnupfenInDenLetzten24Stunden ;
  }

  public void setGliederschmerzen(GliederschmerzenCluster gliederschmerzen) {
     this.gliederschmerzen = gliederschmerzen;
  }

  public GliederschmerzenCluster getGliederschmerzen() {
     return this.gliederschmerzen ;
  }

  public void setFieberInDenLetzten4Tagen(
      FieberInDenLetzten4TagenCluster fieberInDenLetzten4Tagen) {
     this.fieberInDenLetzten4Tagen = fieberInDenLetzten4Tagen;
  }

  public FieberInDenLetzten4TagenCluster getFieberInDenLetzten4Tagen() {
     return this.fieberInDenLetzten4Tagen ;
  }

  public void setHalsschmerzenInDenLetzten24Stunden(
      HalsschmerzenInDenLetzten24StundenCluster halsschmerzenInDenLetzten24Stunden) {
     this.halsschmerzenInDenLetzten24Stunden = halsschmerzenInDenLetzten24Stunden;
  }

  public HalsschmerzenInDenLetzten24StundenCluster getHalsschmerzenInDenLetzten24Stunden() {
     return this.halsschmerzenInDenLetzten24Stunden ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setSchlappheitAngeschlagenheit(
      SchlappheitAngeschlagenheitCluster schlappheitAngeschlagenheit) {
     this.schlappheitAngeschlagenheit = schlappheitAngeschlagenheit;
  }

  public SchlappheitAngeschlagenheitCluster getSchlappheitAngeschlagenheit() {
     return this.schlappheitAngeschlagenheit ;
  }

  public void setHustenInDenLetzten24Stunden(
      HustenInDenLetzten24StundenCluster hustenInDenLetzten24Stunden) {
     this.hustenInDenLetzten24Stunden = hustenInDenLetzten24Stunden;
  }

  public HustenInDenLetzten24StundenCluster getHustenInDenLetzten24Stunden() {
     return this.hustenInDenLetzten24Stunden ;
  }

  public void setKopfschmerzen(KopfschmerzenCluster kopfschmerzen) {
     this.kopfschmerzen = kopfschmerzen;
  }

  public KopfschmerzenCluster getKopfschmerzen() {
     return this.kopfschmerzen ;
  }

  public void setStatus(List<Cluster> status) {
     this.status = status;
  }

  public List<Cluster> getStatus() {
     return this.status ;
  }

  public void setDatumZeitpunktDesAuftretensDerErstdiagnoseValue(
      TemporalAccessor datumZeitpunktDesAuftretensDerErstdiagnoseValue) {
      this.datumZeitpunktDesAuftretensDerErstdiagnoseValue = datumZeitpunktDesAuftretensDerErstdiagnoseValue;
  }

  public TemporalAccessor getDatumZeitpunktDesAuftretensDerErstdiagnoseValue() {
     return this.datumZeitpunktDesAuftretensDerErstdiagnoseValue ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setDurchfall(DurchfallCluster durchfall) {
     this.durchfall = durchfall;
  }

  public DurchfallCluster getDurchfall() {
     return this.durchfall ;
  }

  public void setGeschmacksUndOderGeruchsverlust(
      GeschmacksUndOderGeruchsverlustCluster geschmacksUndOderGeruchsverlust) {
     this.geschmacksUndOderGeruchsverlust = geschmacksUndOderGeruchsverlust;
  }

  public GeschmacksUndOderGeruchsverlustCluster getGeschmacksUndOderGeruchsverlust() {
     return this.geschmacksUndOderGeruchsverlust ;
  }

  public void setNameDesProblemsDerDiagnoseValue(String nameDesProblemsDerDiagnoseValue) {
     this.nameDesProblemsDerDiagnoseValue = nameDesProblemsDerDiagnoseValue;
  }

  public String getNameDesProblemsDerDiagnoseValue() {
     return this.nameDesProblemsDerDiagnoseValue ;
  }

  public void setAnatomischeStelleStrukturiert(List<Cluster> anatomischeStelleStrukturiert) {
     this.anatomischeStelleStrukturiert = anatomischeStelleStrukturiert;
  }

  public List<Cluster> getAnatomischeStelleStrukturiert() {
     return this.anatomischeStelleStrukturiert ;
  }

  public void setAtemprobleme(AtemproblemeCluster atemprobleme) {
     this.atemprobleme = atemprobleme;
  }

  public AtemproblemeCluster getAtemprobleme() {
     return this.atemprobleme ;
  }

  public void setSchuttelfrostInDenLetzten24Stunden(
      SchuttelfrostInDenLetzten24StundenCluster schuttelfrostInDenLetzten24Stunden) {
     this.schuttelfrostInDenLetzten24Stunden = schuttelfrostInDenLetzten24Stunden;
  }

  public SchuttelfrostInDenLetzten24StundenCluster getSchuttelfrostInDenLetzten24Stunden() {
     return this.schuttelfrostInDenLetzten24Stunden ;
  }

  public void setFieberInDenLetzten24Stunden(
      FieberInDenLetzten24StundenCluster fieberInDenLetzten24Stunden) {
     this.fieberInDenLetzten24Stunden = fieberInDenLetzten24Stunden;
  }

  public FieberInDenLetzten24StundenCluster getFieberInDenLetzten24Stunden() {
     return this.fieberInDenLetzten24Stunden ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }
}
