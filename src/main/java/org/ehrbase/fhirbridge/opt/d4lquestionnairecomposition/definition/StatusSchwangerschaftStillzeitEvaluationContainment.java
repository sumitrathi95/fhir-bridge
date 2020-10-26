package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

public class StatusSchwangerschaftStillzeitEvaluationContainment extends Containment {
  public SelectAqlField<StatusSchwangerschaftStillzeitEvaluation> STATUS_SCHWANGERSCHAFT_STILLZEIT_EVALUATION = new AqlFieldImp<StatusSchwangerschaftStillzeitEvaluation>(StatusSchwangerschaftStillzeitEvaluation.class, "", "StatusSchwangerschaftStillzeitEvaluation", StatusSchwangerschaftStillzeitEvaluation.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(StatusSchwangerschaftStillzeitEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Boolean> SCHWANGER_VALUE = new AqlFieldImp<Boolean>(StatusSchwangerschaftStillzeitEvaluation.class, "/data[at0001]/items[at0002]/value|value", "schwangerValue", Boolean.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(StatusSchwangerschaftStillzeitEvaluation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(StatusSchwangerschaftStillzeitEvaluation.class, "/protocol[at0004]/items[at0006]", "erweiterung", Cluster.class, this);

  private StatusSchwangerschaftStillzeitEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.pregnancy_bf_status.v0");
  }

  public static StatusSchwangerschaftStillzeitEvaluationContainment getInstance() {
    return new StatusSchwangerschaftStillzeitEvaluationContainment();
  }
}
