package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class VorGrunderkrankungenSectionContainment extends Containment {
  public SelectAqlField<VorGrunderkrankungenSection> VOR_GRUNDERKRANKUNGEN_SECTION = new AqlFieldImp<VorGrunderkrankungenSection>(VorGrunderkrankungenSection.class, "", "VorGrunderkrankungenSection", VorGrunderkrankungenSection.class, this);

  public SelectAqlField<ChronischeLungenkrankheitEvaluation> CHRONISCHE_LUNGENKRANKHEIT = new AqlFieldImp<ChronischeLungenkrankheitEvaluation>(VorGrunderkrankungenSection.class, "/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Chronische Lungenkrankheit']", "chronischeLungenkrankheit", ChronischeLungenkrankheitEvaluation.class, this);

  public SelectAqlField<DiabetesEvaluation> DIABETES = new AqlFieldImp<DiabetesEvaluation>(VorGrunderkrankungenSection.class, "/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Diabetes']", "diabetes", DiabetesEvaluation.class, this);

  public SelectAqlField<HerzerkrankungEvaluation> HERZERKRANKUNG = new AqlFieldImp<HerzerkrankungEvaluation>(VorGrunderkrankungenSection.class, "/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Herzerkrankung']", "herzerkrankung", HerzerkrankungEvaluation.class, this);

  public SelectAqlField<AdipositasEvaluation> ADIPOSITAS = new AqlFieldImp<AdipositasEvaluation>(VorGrunderkrankungenSection.class, "/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Adipositas']", "adipositas", AdipositasEvaluation.class, this);

  private VorGrunderkrankungenSectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static VorGrunderkrankungenSectionContainment getInstance() {
    return new VorGrunderkrankungenSectionContainment();
  }
}
