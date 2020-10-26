package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class AllgemeineAngabenSection {
  @Path("/items[openEHR-EHR-EVALUATION.care_activity.v0]")
  private PflegetatigkeitEvaluation pflegetatigkeit;

  @Path("/items[openEHR-EHR-EVALUATION.occupation_summary.v1]")
  private ZusammenfassungDerBeschaftigungEvaluation zusammenfassungDerBeschaftigung;

  @Path("/items[openEHR-EHR-EVALUATION.pregnancy_bf_status.v0]")
  private StatusSchwangerschaftStillzeitEvaluation statusSchwangerschaftStillzeit;

  @Path("/items[openEHR-EHR-OBSERVATION.age.v0]")
  private AlterObservation alter;

  @Path("/items[openEHR-EHR-OBSERVATION.covid_19_kontakt.v0]")
  private UmgCovid19KontaktObservation umgCovid19Kontakt;

  @Path("/items[openEHR-EHR-EVALUATION.exclusion_specific.v1 and name/value='Ausschluss - Rauchen']")
  private AusschlussRauchenEvaluation ausschlussRauchen;

  @Path("/items[openEHR-EHR-EVALUATION.tobacco_smoking_summary.v1]")
  private ZusammenfassungRauchverhaltenEvaluation zusammenfassungRauchverhalten;

  @Path("/items[openEHR-EHR-EVALUATION.living_arrangement.v0]")
  private WohnsituationEvaluation wohnsituation;

  @Path("/items[openEHR-EHR-EVALUATION.exclusion_specific.v1 and name/value='Ausschluss - Pflegetätigkeit']")
  private AusschlussPflegetatigkeitEvaluation ausschlussPflegetatigkeit;

  public void setPflegetatigkeit(PflegetatigkeitEvaluation pflegetatigkeit) {
     this.pflegetatigkeit = pflegetatigkeit;
  }

  public PflegetatigkeitEvaluation getPflegetatigkeit() {
     return this.pflegetatigkeit ;
  }

  public void setZusammenfassungDerBeschaftigung(
      ZusammenfassungDerBeschaftigungEvaluation zusammenfassungDerBeschaftigung) {
     this.zusammenfassungDerBeschaftigung = zusammenfassungDerBeschaftigung;
  }

  public ZusammenfassungDerBeschaftigungEvaluation getZusammenfassungDerBeschaftigung() {
     return this.zusammenfassungDerBeschaftigung ;
  }

  public void setStatusSchwangerschaftStillzeit(
      StatusSchwangerschaftStillzeitEvaluation statusSchwangerschaftStillzeit) {
     this.statusSchwangerschaftStillzeit = statusSchwangerschaftStillzeit;
  }

  public StatusSchwangerschaftStillzeitEvaluation getStatusSchwangerschaftStillzeit() {
     return this.statusSchwangerschaftStillzeit ;
  }

  public void setAlter(AlterObservation alter) {
     this.alter = alter;
  }

  public AlterObservation getAlter() {
     return this.alter ;
  }

  public void setUmgCovid19Kontakt(UmgCovid19KontaktObservation umgCovid19Kontakt) {
     this.umgCovid19Kontakt = umgCovid19Kontakt;
  }

  public UmgCovid19KontaktObservation getUmgCovid19Kontakt() {
     return this.umgCovid19Kontakt ;
  }

  public void setAusschlussRauchen(AusschlussRauchenEvaluation ausschlussRauchen) {
     this.ausschlussRauchen = ausschlussRauchen;
  }

  public AusschlussRauchenEvaluation getAusschlussRauchen() {
     return this.ausschlussRauchen ;
  }

  public void setZusammenfassungRauchverhalten(
      ZusammenfassungRauchverhaltenEvaluation zusammenfassungRauchverhalten) {
     this.zusammenfassungRauchverhalten = zusammenfassungRauchverhalten;
  }

  public ZusammenfassungRauchverhaltenEvaluation getZusammenfassungRauchverhalten() {
     return this.zusammenfassungRauchverhalten ;
  }

  public void setWohnsituation(WohnsituationEvaluation wohnsituation) {
     this.wohnsituation = wohnsituation;
  }

  public WohnsituationEvaluation getWohnsituation() {
     return this.wohnsituation ;
  }

  public void setAusschlussPflegetatigkeit(
      AusschlussPflegetatigkeitEvaluation ausschlussPflegetatigkeit) {
     this.ausschlussPflegetatigkeit = ausschlussPflegetatigkeit;
  }

  public AusschlussPflegetatigkeitEvaluation getAusschlussPflegetatigkeit() {
     return this.ausschlussPflegetatigkeit ;
  }
}
