package org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

public class KeineReiseaktivitaetEvaluationContainment extends Containment {
  public SelectAqlField<KeineReiseaktivitaetEvaluation> KEINE_REISEAKTIVITAET_EVALUATION = new AqlFieldImp<KeineReiseaktivitaetEvaluation>(KeineReiseaktivitaetEvaluation.class, "", "KeineReiseaktivitaetEvaluation", KeineReiseaktivitaetEvaluation.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(KeineReiseaktivitaetEvaluation.class, "/protocol[at0009]/items[at0011]", "erweiterung", Cluster.class, this);

  public SelectAqlField<FehlendeInformationDefiningcode> PROBLEM_DIAGNOSE_DEFININGCODE = new AqlFieldImp<FehlendeInformationDefiningcode>(KeineReiseaktivitaetEvaluation.class, "/data[at0001]/items[at0003]/value|defining_code", "problemDiagnoseDefiningcode", FehlendeInformationDefiningcode.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(KeineReiseaktivitaetEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<AussageUberDenAusschlussDefiningcode> AUSSAGE_UBER_DEN_AUSSCHLUSS_DEFININGCODE = new AqlFieldImp<AussageUberDenAusschlussDefiningcode>(KeineReiseaktivitaetEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "aussageUberDenAusschlussDefiningcode", AussageUberDenAusschlussDefiningcode.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KeineReiseaktivitaetEvaluation.class, "/language", "language", Language.class, this);

  private KeineReiseaktivitaetEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.exclusion_specific.v1");
  }

  public static KeineReiseaktivitaetEvaluationContainment getInstance() {
    return new KeineReiseaktivitaetEvaluationContainment();
  }
}
