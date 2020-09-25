package org.ehrbase.fhirbridge.opt.symptomcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.UnbekanntesSymptomDefiningcode;

public class UnbekanntesSymptomEvaluationContainment extends Containment {
  public SelectAqlField<UnbekanntesSymptomEvaluation> UNBEKANNTES_SYMPTOM_EVALUATION = new AqlFieldImp<UnbekanntesSymptomEvaluation>(UnbekanntesSymptomEvaluation.class, "", "UnbekanntesSymptomEvaluation", UnbekanntesSymptomEvaluation.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(UnbekanntesSymptomEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<UnbekanntesSymptomDefiningcode> UNBEKANNTES_SYMPTOM_DEFININGCODE = new AqlFieldImp<UnbekanntesSymptomDefiningcode>(UnbekanntesSymptomEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "unbekanntesSymptomDefiningcode", UnbekanntesSymptomDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(UnbekanntesSymptomEvaluation.class, "/protocol[at0003]/items[at0006]", "erweiterung", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(UnbekanntesSymptomEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<String> UNBEKANNTES_SYMPTOM_VALUE = new AqlFieldImp<String>(UnbekanntesSymptomEvaluation.class, "/data[at0001]/items[at0002]/name|value", "unbekanntesSymptomValue", String.class, this);

  public ListSelectAqlField<UnbekanntesSymptomAussageUberDieFehlendeInformationElement> AUSSAGE_UBER_DIE_FEHLENDE_INFORMATION = new ListAqlFieldImp<UnbekanntesSymptomAussageUberDieFehlendeInformationElement>(UnbekanntesSymptomEvaluation.class, "/data[at0001]/items[at0005]", "aussageUberDieFehlendeInformation", UnbekanntesSymptomAussageUberDieFehlendeInformationElement.class, this);

  private UnbekanntesSymptomEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.absence.v2");
  }

  public static UnbekanntesSymptomEvaluationContainment getInstance() {
    return new UnbekanntesSymptomEvaluationContainment();
  }
}
