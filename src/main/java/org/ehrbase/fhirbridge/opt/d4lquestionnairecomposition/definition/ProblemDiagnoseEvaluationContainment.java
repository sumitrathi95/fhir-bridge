package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

public class ProblemDiagnoseEvaluationContainment extends Containment {
  public SelectAqlField<ProblemDiagnoseEvaluation> PROBLEM_DIAGNOSE_EVALUATION = new AqlFieldImp<ProblemDiagnoseEvaluation>(ProblemDiagnoseEvaluation.class, "", "ProblemDiagnoseEvaluation", ProblemDiagnoseEvaluation.class, this);

  public SelectAqlField<SchnupfenInDenLetzten24StundenCluster> SCHNUPFEN_IN_DEN_LETZTEN24_STUNDEN = new AqlFieldImp<SchnupfenInDenLetzten24StundenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Schnupfen  in den letzten 24 Stunden']", "schnupfenInDenLetzten24Stunden", SchnupfenInDenLetzten24StundenCluster.class, this);

  public SelectAqlField<GliederschmerzenCluster> GLIEDERSCHMERZEN = new AqlFieldImp<GliederschmerzenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Gliederschmerzen']", "gliederschmerzen", GliederschmerzenCluster.class, this);

  public SelectAqlField<FieberInDenLetzten4TagenCluster> FIEBER_IN_DEN_LETZTEN4_TAGEN = new AqlFieldImp<FieberInDenLetzten4TagenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Fieber in den letzten 4 Tagen']", "fieberInDenLetzten4Tagen", FieberInDenLetzten4TagenCluster.class, this);

  public SelectAqlField<HalsschmerzenInDenLetzten24StundenCluster> HALSSCHMERZEN_IN_DEN_LETZTEN24_STUNDEN = new AqlFieldImp<HalsschmerzenInDenLetzten24StundenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Halsschmerzen in den letzten 24 Stunden']", "halsschmerzenInDenLetzten24Stunden", HalsschmerzenInDenLetzten24StundenCluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ProblemDiagnoseEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<SchlappheitAngeschlagenheitCluster> SCHLAPPHEIT_ANGESCHLAGENHEIT = new AqlFieldImp<SchlappheitAngeschlagenheitCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Schlappheit / Angeschlagenheit']", "schlappheitAngeschlagenheit", SchlappheitAngeschlagenheitCluster.class, this);

  public SelectAqlField<HustenInDenLetzten24StundenCluster> HUSTEN_IN_DEN_LETZTEN24_STUNDEN = new AqlFieldImp<HustenInDenLetzten24StundenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Husten in den letzten 24 Stunden']", "hustenInDenLetzten24Stunden", HustenInDenLetzten24StundenCluster.class, this);

  public SelectAqlField<KopfschmerzenCluster> KOPFSCHMERZEN = new AqlFieldImp<KopfschmerzenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Kopfschmerzen']", "kopfschmerzen", KopfschmerzenCluster.class, this);

  public ListSelectAqlField<Cluster> STATUS = new ListAqlFieldImp<Cluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0046]", "status", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_ZEITPUNKT_DES_AUFTRETENS_DER_ERSTDIAGNOSE_VALUE = new AqlFieldImp<TemporalAccessor>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0077]/value|value", "datumZeitpunktDesAuftretensDerErstdiagnoseValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ProblemDiagnoseEvaluation.class, "/protocol[at0032]/items[at0071]", "erweiterung", Cluster.class, this);

  public SelectAqlField<DurchfallCluster> DURCHFALL = new AqlFieldImp<DurchfallCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Durchfall']", "durchfall", DurchfallCluster.class, this);

  public SelectAqlField<GeschmacksUndOderGeruchsverlustCluster> GESCHMACKS_UND_ODER_GERUCHSVERLUST = new AqlFieldImp<GeschmacksUndOderGeruchsverlustCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Geschmacks- und/oder Geruchsverlust']", "geschmacksUndOderGeruchsverlust", GeschmacksUndOderGeruchsverlustCluster.class, this);

  public SelectAqlField<String> NAME_DES_PROBLEMS_DER_DIAGNOSE_VALUE = new AqlFieldImp<String>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0002]/value|value", "nameDesProblemsDerDiagnoseValue", String.class, this);

  public ListSelectAqlField<Cluster> ANATOMISCHE_STELLE_STRUKTURIERT = new ListAqlFieldImp<Cluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0039]", "anatomischeStelleStrukturiert", Cluster.class, this);

  public SelectAqlField<AtemproblemeCluster> ATEMPROBLEME = new AqlFieldImp<AtemproblemeCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Atemprobleme']", "atemprobleme", AtemproblemeCluster.class, this);

  public SelectAqlField<SchuttelfrostInDenLetzten24StundenCluster> SCHUTTELFROST_IN_DEN_LETZTEN24_STUNDEN = new AqlFieldImp<SchuttelfrostInDenLetzten24StundenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Schüttelfrost in den letzten 24 Stunden']", "schuttelfrostInDenLetzten24Stunden", SchuttelfrostInDenLetzten24StundenCluster.class, this);

  public SelectAqlField<FieberInDenLetzten24StundenCluster> FIEBER_IN_DEN_LETZTEN24_STUNDEN = new AqlFieldImp<FieberInDenLetzten24StundenCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Fieber in den letzten 24 Stunden']", "fieberInDenLetzten24Stunden", FieberInDenLetzten24StundenCluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ProblemDiagnoseEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  private ProblemDiagnoseEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.problem_diagnosis.v1");
  }

  public static ProblemDiagnoseEvaluationContainment getInstance() {
    return new ProblemDiagnoseEvaluationContainment();
  }
}
