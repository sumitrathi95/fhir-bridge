package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class SymptomeSectionContainment extends Containment {
  public SelectAqlField<SymptomeSection> SYMPTOME_SECTION = new AqlFieldImp<SymptomeSection>(SymptomeSection.class, "", "SymptomeSection", SymptomeSection.class, this);

  public ListSelectAqlField<ProblemDiagnoseEvaluation> PROBLEM_DIAGNOSE = new ListAqlFieldImp<ProblemDiagnoseEvaluation>(SymptomeSection.class, "/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]", "problemDiagnose", ProblemDiagnoseEvaluation.class, this);

  private SymptomeSectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static SymptomeSectionContainment getInstance() {
    return new SymptomeSectionContainment();
  }
}
