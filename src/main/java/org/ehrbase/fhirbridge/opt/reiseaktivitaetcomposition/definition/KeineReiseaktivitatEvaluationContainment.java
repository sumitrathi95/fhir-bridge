package org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

public class KeineReiseaktivitatEvaluationContainment extends Containment {
  public SelectAqlField<KeineReiseaktivitatEvaluation> KEINE_REISEAKTIVITAT_EVALUATION = new AqlFieldImp<KeineReiseaktivitatEvaluation>(KeineReiseaktivitatEvaluation.class, "", "KeineReiseaktivitatEvaluation", KeineReiseaktivitatEvaluation.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(KeineReiseaktivitatEvaluation.class, "/protocol[at0009]/items[at0011]", "erweiterung", Cluster.class, this);

  public SelectAqlField<ProblemDiagnoseDefiningcode> PROBLEM_DIAGNOSE_DEFININGCODE = new AqlFieldImp<ProblemDiagnoseDefiningcode>(KeineReiseaktivitatEvaluation.class, "/data[at0001]/items[at0003]/value|defining_code", "problemDiagnoseDefiningcode", ProblemDiagnoseDefiningcode.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(KeineReiseaktivitatEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<AussageUberDenAusschlussDefiningcode> AUSSAGE_UBER_DEN_AUSSCHLUSS_DEFININGCODE = new AqlFieldImp<AussageUberDenAusschlussDefiningcode>(KeineReiseaktivitatEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "aussageUberDenAusschlussDefiningcode", AussageUberDenAusschlussDefiningcode.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KeineReiseaktivitatEvaluation.class, "/language", "language", Language.class, this);

  private KeineReiseaktivitatEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.exclusion_specific.v1");
  }

  public static KeineReiseaktivitatEvaluationContainment getInstance() {
    return new KeineReiseaktivitatEvaluationContainment();
  }
}
