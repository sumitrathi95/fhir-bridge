package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class VorGrunderkrankungenSection {
  @Path("/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Chronische Lungenkrankheit']")
  private ChronischeLungenkrankheitEvaluation chronischeLungenkrankheit;

  @Path("/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Diabetes']")
  private DiabetesEvaluation diabetes;

  @Path("/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Herzerkrankung']")
  private HerzerkrankungEvaluation herzerkrankung;

  @Path("/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Adipositas']")
  private AdipositasEvaluation adipositas;

  public void setChronischeLungenkrankheit(
      ChronischeLungenkrankheitEvaluation chronischeLungenkrankheit) {
     this.chronischeLungenkrankheit = chronischeLungenkrankheit;
  }

  public ChronischeLungenkrankheitEvaluation getChronischeLungenkrankheit() {
     return this.chronischeLungenkrankheit ;
  }

  public void setDiabetes(DiabetesEvaluation diabetes) {
     this.diabetes = diabetes;
  }

  public DiabetesEvaluation getDiabetes() {
     return this.diabetes ;
  }

  public void setHerzerkrankung(HerzerkrankungEvaluation herzerkrankung) {
     this.herzerkrankung = herzerkrankung;
  }

  public HerzerkrankungEvaluation getHerzerkrankung() {
     return this.herzerkrankung ;
  }

  public void setAdipositas(AdipositasEvaluation adipositas) {
     this.adipositas = adipositas;
  }

  public AdipositasEvaluation getAdipositas() {
     return this.adipositas ;
  }
}
