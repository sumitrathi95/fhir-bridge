package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class SymptomeSection {
  @Path("/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]")
  private List<ProblemDiagnoseEvaluation> problemDiagnose;

  public void setProblemDiagnose(List<ProblemDiagnoseEvaluation> problemDiagnose) {
     this.problemDiagnose = problemDiagnose;
  }

  public List<ProblemDiagnoseEvaluation> getProblemDiagnose() {
     return this.problemDiagnose ;
  }
}
