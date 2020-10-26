package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

public class KortisonEvaluationContainment extends Containment {
  public SelectAqlField<KortisonEvaluation> KORTISON_EVALUATION = new AqlFieldImp<KortisonEvaluation>(KortisonEvaluation.class, "", "KortisonEvaluation", KortisonEvaluation.class, this);

  public SelectAqlField<Boolean> AKTUELLE_ANWENDUNG_VALUE = new AqlFieldImp<Boolean>(KortisonEvaluation.class, "/data[at0001]/items[at0004]/value|value", "aktuelleAnwendungValue", Boolean.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(KortisonEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<String> NAME_DES_MEDIKAMENTS_VALUE = new AqlFieldImp<String>(KortisonEvaluation.class, "/data[at0001]/items[at0002]/value|value", "nameDesMedikamentsValue", String.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KortisonEvaluation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(KortisonEvaluation.class, "/protocol[at0005]/items[at0019]", "erweiterung", Cluster.class, this);

  private KortisonEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.medication_summary.v0");
  }

  public static KortisonEvaluationContainment getInstance() {
    return new KortisonEvaluationContainment();
  }
}
