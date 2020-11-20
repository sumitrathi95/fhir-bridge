package org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

public class UnbekannteReiseaktivitaetEvaluationContainment extends Containment {
  public SelectAqlField<UnbekannteReiseaktivitaetEvaluation> UNBEKANNTE_REISEAKTIVITAET_EVALUATION = new AqlFieldImp<UnbekannteReiseaktivitaetEvaluation>(UnbekannteReiseaktivitaetEvaluation.class, "", "UnbekannteReiseaktivitaetEvaluation", UnbekannteReiseaktivitaetEvaluation.class, this);

  public SelectAqlField<AussageUberDieFehlendeInformationDefiningcode> AUSSAGE_UBER_DIE_FEHLENDE_INFORMATION_DEFININGCODE = new AqlFieldImp<AussageUberDieFehlendeInformationDefiningcode>(UnbekannteReiseaktivitaetEvaluation.class, "/data[at0001]/items[at0005]/value|defining_code", "aussageUberDieFehlendeInformationDefiningcode", AussageUberDieFehlendeInformationDefiningcode.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(UnbekannteReiseaktivitaetEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<FehlendeInformationDefiningcode> FEHLENDE_INFORMATION_DEFININGCODE = new AqlFieldImp<FehlendeInformationDefiningcode>(UnbekannteReiseaktivitaetEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "fehlendeInformationDefiningcode", FehlendeInformationDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(UnbekannteReiseaktivitaetEvaluation.class, "/protocol[at0003]/items[at0006]", "erweiterung", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(UnbekannteReiseaktivitaetEvaluation.class, "/language", "language", Language.class, this);

  private UnbekannteReiseaktivitaetEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.absence.v2");
  }

  public static UnbekannteReiseaktivitaetEvaluationContainment getInstance() {
    return new UnbekannteReiseaktivitaetEvaluationContainment();
  }
}
