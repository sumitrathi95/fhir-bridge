package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class MedikamenteImpfungenSection {
  @Path("/items[openEHR-EHR-ACTION.medication.v1]")
  private List<ArzneimittelverwaltungAction> arzneimittelverwaltung;

  @Path("/items[openEHR-EHR-EVALUATION.medication_summary.v0 and name/value='Immunsuppressiva']")
  private ImmunsuppressivaEvaluation immunsuppressiva;

  @Path("/items[openEHR-EHR-EVALUATION.medication_summary.v0 and name/value='Kortison']")
  private KortisonEvaluation kortison;

  public void setArzneimittelverwaltung(List<ArzneimittelverwaltungAction> arzneimittelverwaltung) {
     this.arzneimittelverwaltung = arzneimittelverwaltung;
  }

  public List<ArzneimittelverwaltungAction> getArzneimittelverwaltung() {
     return this.arzneimittelverwaltung ;
  }

  public void setImmunsuppressiva(ImmunsuppressivaEvaluation immunsuppressiva) {
     this.immunsuppressiva = immunsuppressiva;
  }

  public ImmunsuppressivaEvaluation getImmunsuppressiva() {
     return this.immunsuppressiva ;
  }

  public void setKortison(KortisonEvaluation kortison) {
     this.kortison = kortison;
  }

  public KortisonEvaluation getKortison() {
     return this.kortison ;
  }
}
