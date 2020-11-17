package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

public class GeschlechtEvaluationContainment extends Containment {
  public SelectAqlField<GeschlechtEvaluation> GESCHLECHT_EVALUATION = new AqlFieldImp<GeschlechtEvaluation>(GeschlechtEvaluation.class, "", "GeschlechtEvaluation", GeschlechtEvaluation.class, this);

  public SelectAqlField<AdministrativesGeschlechtDefiningcode> ADMINISTRATIVES_GESCHLECHT_DEFININGCODE = new AqlFieldImp<AdministrativesGeschlechtDefiningcode>(GeschlechtEvaluation.class, "/data[at0002]/items[at0022]/value|defining_code", "administrativesGeschlechtDefiningcode", AdministrativesGeschlechtDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ZUSATZLICHE_DETAILS = new ListAqlFieldImp<Cluster>(GeschlechtEvaluation.class, "/data[at0002]/items[at0023]", "zusatzlicheDetails", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(GeschlechtEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(GeschlechtEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<GeschlechtBeiDerGeburtDefiningcode> GESCHLECHT_BEI_DER_GEBURT_DEFININGCODE = new AqlFieldImp<GeschlechtBeiDerGeburtDefiningcode>(GeschlechtEvaluation.class, "/data[at0002]/items[at0019]/value|defining_code", "geschlechtBeiDerGeburtDefiningcode", GeschlechtBeiDerGeburtDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(GeschlechtEvaluation.class, "/protocol[at0003]/items[at0005]", "erweiterung", Cluster.class, this);

  private GeschlechtEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.gender.v1");
  }

  public static GeschlechtEvaluationContainment getInstance() {
    return new GeschlechtEvaluationContainment();
  }
}
