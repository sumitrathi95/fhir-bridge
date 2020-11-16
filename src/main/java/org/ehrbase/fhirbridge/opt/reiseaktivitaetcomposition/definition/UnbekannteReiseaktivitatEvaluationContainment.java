package org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

public class UnbekannteReiseaktivitatEvaluationContainment extends Containment {
  public SelectAqlField<UnbekannteReiseaktivitatEvaluation> UNBEKANNTE_REISEAKTIVITAT_EVALUATION = new AqlFieldImp<UnbekannteReiseaktivitatEvaluation>(UnbekannteReiseaktivitatEvaluation.class, "", "UnbekannteReiseaktivitatEvaluation", UnbekannteReiseaktivitatEvaluation.class, this);

  public SelectAqlField<AussageUberDieFehlendeInformationDefiningcode> AUSSAGE_UBER_DIE_FEHLENDE_INFORMATION_DEFININGCODE = new AqlFieldImp<AussageUberDieFehlendeInformationDefiningcode>(UnbekannteReiseaktivitatEvaluation.class, "/data[at0001]/items[at0005]/value|defining_code", "aussageUberDieFehlendeInformationDefiningcode", AussageUberDieFehlendeInformationDefiningcode.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(UnbekannteReiseaktivitatEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<ProblemDiagnoseDefiningcode> FEHLENDE_INFORMATION_DEFININGCODE = new AqlFieldImp<ProblemDiagnoseDefiningcode>(UnbekannteReiseaktivitatEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "fehlendeInformationDefiningcode", ProblemDiagnoseDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(UnbekannteReiseaktivitatEvaluation.class, "/protocol[at0003]/items[at0006]", "erweiterung", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(UnbekannteReiseaktivitatEvaluation.class, "/language", "language", Language.class, this);

  private UnbekannteReiseaktivitatEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.absence.v2");
  }

  public static UnbekannteReiseaktivitatEvaluationContainment getInstance() {
    return new UnbekannteReiseaktivitatEvaluationContainment();
  }
}
