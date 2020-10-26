package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class MedikamenteImpfungenSectionContainment extends Containment {
  public SelectAqlField<MedikamenteImpfungenSection> MEDIKAMENTE_IMPFUNGEN_SECTION = new AqlFieldImp<MedikamenteImpfungenSection>(MedikamenteImpfungenSection.class, "", "MedikamenteImpfungenSection", MedikamenteImpfungenSection.class, this);

  public ListSelectAqlField<ArzneimittelverwaltungAction> ARZNEIMITTELVERWALTUNG = new ListAqlFieldImp<ArzneimittelverwaltungAction>(MedikamenteImpfungenSection.class, "/items[openEHR-EHR-ACTION.medication.v1]", "arzneimittelverwaltung", ArzneimittelverwaltungAction.class, this);

  public SelectAqlField<ImmunsuppressivaEvaluation> IMMUNSUPPRESSIVA = new AqlFieldImp<ImmunsuppressivaEvaluation>(MedikamenteImpfungenSection.class, "/items[openEHR-EHR-EVALUATION.medication_summary.v0 and name/value='Immunsuppressiva']", "immunsuppressiva", ImmunsuppressivaEvaluation.class, this);

  public SelectAqlField<KortisonEvaluation> KORTISON = new AqlFieldImp<KortisonEvaluation>(MedikamenteImpfungenSection.class, "/items[openEHR-EHR-EVALUATION.medication_summary.v0 and name/value='Kortison']", "kortison", KortisonEvaluation.class, this);

  private MedikamenteImpfungenSectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static MedikamenteImpfungenSectionContainment getInstance() {
    return new MedikamenteImpfungenSectionContainment();
  }
}
