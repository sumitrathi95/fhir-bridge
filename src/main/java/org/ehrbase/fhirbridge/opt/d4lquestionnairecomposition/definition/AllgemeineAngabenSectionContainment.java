package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AllgemeineAngabenSectionContainment extends Containment {
  public SelectAqlField<AllgemeineAngabenSection> ALLGEMEINE_ANGABEN_SECTION = new AqlFieldImp<AllgemeineAngabenSection>(AllgemeineAngabenSection.class, "", "AllgemeineAngabenSection", AllgemeineAngabenSection.class, this);

  public SelectAqlField<PflegetatigkeitEvaluation> PFLEGETATIGKEIT = new AqlFieldImp<PflegetatigkeitEvaluation>(AllgemeineAngabenSection.class, "/items[openEHR-EHR-EVALUATION.care_activity.v0]", "pflegetatigkeit", PflegetatigkeitEvaluation.class, this);

  public SelectAqlField<ZusammenfassungDerBeschaftigungEvaluation> ZUSAMMENFASSUNG_DER_BESCHAFTIGUNG = new AqlFieldImp<ZusammenfassungDerBeschaftigungEvaluation>(AllgemeineAngabenSection.class, "/items[openEHR-EHR-EVALUATION.occupation_summary.v1]", "zusammenfassungDerBeschaftigung", ZusammenfassungDerBeschaftigungEvaluation.class, this);

  public SelectAqlField<StatusSchwangerschaftStillzeitEvaluation> STATUS_SCHWANGERSCHAFT_STILLZEIT = new AqlFieldImp<StatusSchwangerschaftStillzeitEvaluation>(AllgemeineAngabenSection.class, "/items[openEHR-EHR-EVALUATION.pregnancy_bf_status.v0]", "statusSchwangerschaftStillzeit", StatusSchwangerschaftStillzeitEvaluation.class, this);

  public SelectAqlField<AlterObservation> ALTER = new AqlFieldImp<AlterObservation>(AllgemeineAngabenSection.class, "/items[openEHR-EHR-OBSERVATION.age.v0]", "alter", AlterObservation.class, this);

  public SelectAqlField<UmgCovid19KontaktObservation> UMG_COVID19_KONTAKT = new AqlFieldImp<UmgCovid19KontaktObservation>(AllgemeineAngabenSection.class, "/items[openEHR-EHR-OBSERVATION.covid_19_kontakt.v0]", "umgCovid19Kontakt", UmgCovid19KontaktObservation.class, this);

  public SelectAqlField<AusschlussRauchenEvaluation> AUSSCHLUSS_RAUCHEN = new AqlFieldImp<AusschlussRauchenEvaluation>(AllgemeineAngabenSection.class, "/items[openEHR-EHR-EVALUATION.exclusion_specific.v1 and name/value='Ausschluss - Rauchen']", "ausschlussRauchen", AusschlussRauchenEvaluation.class, this);

  public SelectAqlField<ZusammenfassungRauchverhaltenEvaluation> ZUSAMMENFASSUNG_RAUCHVERHALTEN = new AqlFieldImp<ZusammenfassungRauchverhaltenEvaluation>(AllgemeineAngabenSection.class, "/items[openEHR-EHR-EVALUATION.tobacco_smoking_summary.v1]", "zusammenfassungRauchverhalten", ZusammenfassungRauchverhaltenEvaluation.class, this);

  public SelectAqlField<WohnsituationEvaluation> WOHNSITUATION = new AqlFieldImp<WohnsituationEvaluation>(AllgemeineAngabenSection.class, "/items[openEHR-EHR-EVALUATION.living_arrangement.v0]", "wohnsituation", WohnsituationEvaluation.class, this);

  public SelectAqlField<AusschlussPflegetatigkeitEvaluation> AUSSCHLUSS_PFLEGETATIGKEIT = new AqlFieldImp<AusschlussPflegetatigkeitEvaluation>(AllgemeineAngabenSection.class, "/items[openEHR-EHR-EVALUATION.exclusion_specific.v1 and name/value='Ausschluss - Pflegetätigkeit']", "ausschlussPflegetatigkeit", AusschlussPflegetatigkeitEvaluation.class, this);

  private AllgemeineAngabenSectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static AllgemeineAngabenSectionContainment getInstance() {
    return new AllgemeineAngabenSectionContainment();
  }
}
