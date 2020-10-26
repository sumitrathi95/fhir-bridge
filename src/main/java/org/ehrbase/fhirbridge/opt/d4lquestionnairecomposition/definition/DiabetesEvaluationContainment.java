package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

public class DiabetesEvaluationContainment extends Containment {
  public SelectAqlField<DiabetesEvaluation> DIABETES_EVALUATION = new AqlFieldImp<DiabetesEvaluation>(DiabetesEvaluation.class, "", "DiabetesEvaluation", DiabetesEvaluation.class, this);

  public ListSelectAqlField<Cluster> STATUS = new ListAqlFieldImp<Cluster>(DiabetesEvaluation.class, "/data[at0001]/items[at0046]", "status", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(DiabetesEvaluation.class, "/protocol[at0032]/items[at0071]", "erweiterung", Cluster.class, this);

  public SelectAqlField<String> NAME_DES_PROBLEMS_DER_DIAGNOSE_VALUE = new AqlFieldImp<String>(DiabetesEvaluation.class, "/data[at0001]/items[at0002]/value|value", "nameDesProblemsDerDiagnoseValue", String.class, this);

  public ListSelectAqlField<Cluster> ANATOMISCHE_STELLE_STRUKTURIERT = new ListAqlFieldImp<Cluster>(DiabetesEvaluation.class, "/data[at0001]/items[at0039]", "anatomischeStelleStrukturiert", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(DiabetesEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<VorhandenDefiningcode> VORHANDEN_DEFININGCODE = new AqlFieldImp<VorhandenDefiningcode>(DiabetesEvaluation.class, "/data[at0001]/items[at0009]/value|defining_code", "vorhandenDefiningcode", VorhandenDefiningcode.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(DiabetesEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_ANGABEN = new ListAqlFieldImp<Cluster>(DiabetesEvaluation.class, "/data[at0001]/items[at0043]", "spezifischeAngaben", Cluster.class, this);

  private DiabetesEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.problem_diagnosis.v1");
  }

  public static DiabetesEvaluationContainment getInstance() {
    return new DiabetesEvaluationContainment();
  }
}
