package org.ehrbase.fhirbridge.opt.symptomcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.AussageUberDenAusschlussDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

public class AusgeschlossenesSymptomEvaluationContainment extends Containment {
  public SelectAqlField<AusgeschlossenesSymptomEvaluation> AUSGESCHLOSSENES_SYMPTOM_EVALUATION = new AqlFieldImp<AusgeschlossenesSymptomEvaluation>(AusgeschlossenesSymptomEvaluation.class, "", "AusgeschlossenesSymptomEvaluation", AusgeschlossenesSymptomEvaluation.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(AusgeschlossenesSymptomEvaluation.class, "/protocol[at0009]/items[at0011]", "erweiterung", Cluster.class, this);

  public SelectAqlField<org.ehrbase.fhirbridge.opt.shareddefinition.DiagnoseDefiningcode> DIAGNOSE_DEFININGCODE = new AqlFieldImp<org.ehrbase.fhirbridge.opt.shareddefinition.DiagnoseDefiningcode>(AusgeschlossenesSymptomEvaluation.class, "/data[at0001]/items[at0003]/value|defining_code", "diagnoseDefiningcode", org.ehrbase.fhirbridge.opt.shareddefinition.DiagnoseDefiningcode.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AusgeschlossenesSymptomEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<AussageUberDenAusschlussDefiningcode> AUSSAGE_UBER_DEN_AUSSCHLUSS_DEFININGCODE = new AqlFieldImp<AussageUberDenAusschlussDefiningcode>(AusgeschlossenesSymptomEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "aussageUberDenAusschlussDefiningcode", AussageUberDenAusschlussDefiningcode.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AusgeschlossenesSymptomEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<DiagnoseDefiningcode> DIAGNOSE_DEFININGCODE_PROBLEM = new AqlFieldImp<DiagnoseDefiningcode>(AusgeschlossenesSymptomEvaluation.class, "/data[at0001]/items[at0003]/name|defining_code", "diagnoseDefiningcodeProblem", DiagnoseDefiningcode.class, this);

  private AusgeschlossenesSymptomEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.exclusion_specific.v1");
  }

  public static AusgeschlossenesSymptomEvaluationContainment getInstance() {
    return new AusgeschlossenesSymptomEvaluationContainment();
  }
}
