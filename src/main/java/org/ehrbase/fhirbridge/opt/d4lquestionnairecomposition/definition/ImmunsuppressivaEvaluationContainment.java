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

public class ImmunsuppressivaEvaluationContainment extends Containment {
  public SelectAqlField<ImmunsuppressivaEvaluation> IMMUNSUPPRESSIVA_EVALUATION = new AqlFieldImp<ImmunsuppressivaEvaluation>(ImmunsuppressivaEvaluation.class, "", "ImmunsuppressivaEvaluation", ImmunsuppressivaEvaluation.class, this);

  public SelectAqlField<Boolean> AKTUELLE_ANWENDUNG_VALUE = new AqlFieldImp<Boolean>(ImmunsuppressivaEvaluation.class, "/data[at0001]/items[at0004]/value|value", "aktuelleAnwendungValue", Boolean.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ImmunsuppressivaEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<String> NAME_DES_MEDIKAMENTS_VALUE = new AqlFieldImp<String>(ImmunsuppressivaEvaluation.class, "/data[at0001]/items[at0002]/value|value", "nameDesMedikamentsValue", String.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ImmunsuppressivaEvaluation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ImmunsuppressivaEvaluation.class, "/protocol[at0005]/items[at0019]", "erweiterung", Cluster.class, this);

  private ImmunsuppressivaEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.medication_summary.v0");
  }

  public static ImmunsuppressivaEvaluationContainment getInstance() {
    return new ImmunsuppressivaEvaluationContainment();
  }
}
