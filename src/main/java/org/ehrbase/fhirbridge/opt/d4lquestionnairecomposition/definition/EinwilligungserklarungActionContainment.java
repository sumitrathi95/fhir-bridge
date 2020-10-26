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
import org.ehrbase.fhirbridge.opt.shareddefinition.TransitionDefiningcode;

public class EinwilligungserklarungActionContainment extends Containment {
  public SelectAqlField<EinwilligungserklarungAction> EINWILLIGUNGSERKLARUNG_ACTION = new AqlFieldImp<EinwilligungserklarungAction>(EinwilligungserklarungAction.class, "", "EinwilligungserklarungAction", EinwilligungserklarungAction.class, this);

  public SelectAqlField<GeplantDefiningcode> GEPLANT_DEFININGCODE = new AqlFieldImp<GeplantDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0013]/careflow_step|defining_code", "geplantDefiningcode", GeplantDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE = new AqlFieldImp<TransitionDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0015]/transition|defining_code", "transitionDefiningcode", TransitionDefiningcode.class, this);

  public SelectAqlField<EmpfohleneArzneimittelDefiningcode2> GEPLANT_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<EmpfohleneArzneimittelDefiningcode2>(EinwilligungserklarungAction.class, "/ism_transition[at0013]/current_state|defining_code", "geplantDefiningcodeCurrentState", EmpfohleneArzneimittelDefiningcode2.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> EINWILLIGUNG_NICHT_ERHALTEN_DEFININGCODE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0021]/current_state|defining_code", "einwilligungNichtErhaltenDefiningcode", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_GEPLANT = new AqlFieldImp<TransitionDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0013]/transition|defining_code", "transitionDefiningcodeGeplant", TransitionDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_EINWILLIGUNG_WIDERRUFEN = new AqlFieldImp<TransitionDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0017]/transition|defining_code", "transitionDefiningcodeEinwilligungWiderrufen", TransitionDefiningcode.class, this);

  public SelectAqlField<RezeptWurdeWiderrufenDefiningcode> ABGESAGT_DEFININGCODE = new AqlFieldImp<RezeptWurdeWiderrufenDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0018]/current_state|defining_code", "abgesagtDefiningcode", RezeptWurdeWiderrufenDefiningcode.class, this);

  public SelectAqlField<AbgesagtDefiningcode> ABGESAGT_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<AbgesagtDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0018]/careflow_step|defining_code", "abgesagtDefiningcodeCareflowStep", AbgesagtDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_EINWILLIGUNG_VERWEIGERT = new AqlFieldImp<TransitionDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0016]/transition|defining_code", "transitionDefiningcodeEinwilligungVerweigert", TransitionDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_TERMIN_GEPLANT = new AqlFieldImp<TransitionDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0027]/transition|defining_code", "transitionDefiningcodeTerminGeplant", TransitionDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ANFORDERER_DER_EINWILLIGUNGSERKLARUNG = new ListAqlFieldImp<Cluster>(EinwilligungserklarungAction.class, "/protocol[at0024]/items[at0028]", "anfordererDerEinwilligungserklarung", Cluster.class, this);

  public SelectAqlField<RezeptWurdeAusgefuhrtDefiningcode> ABGESCHLOSSEN_DEFININGCODE = new AqlFieldImp<RezeptWurdeAusgefuhrtDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0022]/current_state|defining_code", "abgeschlossenDefiningcode", RezeptWurdeAusgefuhrtDefiningcode.class, this);

  public SelectAqlField<String> BESCHREIBUNG_DER_EINWILLIGUNG_VALUE = new AqlFieldImp<String>(EinwilligungserklarungAction.class, "/description[at0001]/items[at0011]/value|value", "beschreibungDerEinwilligungValue", String.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_EINWILLIGUNG_ERBETEN = new AqlFieldImp<TransitionDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0014]/transition|defining_code", "transitionDefiningcodeEinwilligungErbeten", TransitionDefiningcode.class, this);

  public SelectAqlField<AbgeschlossenDefiningcode> ABGESCHLOSSEN_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<AbgeschlossenDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0022]/careflow_step|defining_code", "abgeschlossenDefiningcodeCareflowStep", AbgeschlossenDefiningcode.class, this);

  public SelectAqlField<GrossereAnderungDerVerordnungDefiningcode> EINWILLIGUNG_WIDERRUFEN_DEFININGCODE = new AqlFieldImp<GrossereAnderungDerVerordnungDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0017]/current_state|defining_code", "einwilligungWiderrufenDefiningcode", GrossereAnderungDerVerordnungDefiningcode.class, this);

  public SelectAqlField<String> PROZEDUR_STUDIE_AKTIVITAT_VALUE = new AqlFieldImp<String>(EinwilligungserklarungAction.class, "/description[at0001]/items[at0002]/value|value", "prozedurStudieAktivitatValue", String.class, this);

  public SelectAqlField<EinwilligungWiderrufenDefiningcode> EINWILLIGUNG_WIDERRUFEN_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<EinwilligungWiderrufenDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0017]/careflow_step|defining_code", "einwilligungWiderrufenDefiningcodeCareflowStep", EinwilligungWiderrufenDefiningcode.class, this);

  public SelectAqlField<GrossereAnderungDerVerordnungDefiningcode> EINWILLIGUNG_VERWEIGERT_DEFININGCODE = new AqlFieldImp<GrossereAnderungDerVerordnungDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0016]/current_state|defining_code", "einwilligungVerweigertDefiningcode", GrossereAnderungDerVerordnungDefiningcode.class, this);

  public SelectAqlField<EinwilligungVerweigertDefiningcode> EINWILLIGUNG_VERWEIGERT_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<EinwilligungVerweigertDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0016]/careflow_step|defining_code", "einwilligungVerweigertDefiningcodeCareflowStep", EinwilligungVerweigertDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_ABGESCHLOSSEN = new AqlFieldImp<TransitionDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0022]/transition|defining_code", "transitionDefiningcodeAbgeschlossen", TransitionDefiningcode.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(EinwilligungserklarungAction.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<EinwilligungNichtErhaltenDefiningcode> EINWILLIGUNG_NICHT_ERHALTEN_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<EinwilligungNichtErhaltenDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0021]/careflow_step|defining_code", "einwilligungNichtErhaltenDefiningcodeCareflowStep", EinwilligungNichtErhaltenDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_EINWILLIGUNG_NICHT_ERHALTEN = new AqlFieldImp<TransitionDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0021]/transition|defining_code", "transitionDefiningcodeEinwilligungNichtErhalten", TransitionDefiningcode.class, this);

  public SelectAqlField<AdresseCluster> ADRESSE = new AqlFieldImp<AdresseCluster>(EinwilligungserklarungAction.class, "/description[at0001]/items[openEHR-EHR-CLUSTER.address.v0]", "adresse", AdresseCluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(EinwilligungserklarungAction.class, "/language", "language", Language.class, this);

  public SelectAqlField<EinwilligungErteiltDefiningcode> EINWILLIGUNG_ERTEILT_DEFININGCODE = new AqlFieldImp<EinwilligungErteiltDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0015]/careflow_step|defining_code", "einwilligungErteiltDefiningcode", EinwilligungErteiltDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> EINWILLIGUNG_ERTEILT_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0015]/current_state|defining_code", "einwilligungErteiltDefiningcodeCurrentState", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public ListSelectAqlField<Cluster> EINWILLIGENDE_PERSON = new ListAqlFieldImp<Cluster>(EinwilligungserklarungAction.class, "/protocol[at0024]/items[at0029]", "einwilligendePerson", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(EinwilligungserklarungAction.class, "/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<MedikamentenbehanlungWurdeVerschobenDefiningcode> VERSCHOBEN_DEFININGCODE = new AqlFieldImp<MedikamentenbehanlungWurdeVerschobenDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0019]/current_state|defining_code", "verschobenDefiningcode", MedikamentenbehanlungWurdeVerschobenDefiningcode.class, this);

  public SelectAqlField<VerschobenDefiningcode> VERSCHOBEN_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<VerschobenDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0019]/careflow_step|defining_code", "verschobenDefiningcodeCareflowStep", VerschobenDefiningcode.class, this);

  public SelectAqlField<RezeptWartetAufGenehmigungDefiningcode2> EINWILLIGUNG_ERBETEN_DEFININGCODE = new AqlFieldImp<RezeptWartetAufGenehmigungDefiningcode2>(EinwilligungserklarungAction.class, "/ism_transition[at0014]/current_state|defining_code", "einwilligungErbetenDefiningcode", RezeptWartetAufGenehmigungDefiningcode2.class, this);

  public SelectAqlField<EinwilligungErbetenDefiningcode> EINWILLIGUNG_ERBETEN_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<EinwilligungErbetenDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0014]/careflow_step|defining_code", "einwilligungErbetenDefiningcodeCareflowStep", EinwilligungErbetenDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_VERSCHOBEN = new AqlFieldImp<TransitionDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0019]/transition|defining_code", "transitionDefiningcodeVerschoben", TransitionDefiningcode.class, this);

  public SelectAqlField<TerminGeplantDefiningcode> TERMIN_GEPLANT_DEFININGCODE = new AqlFieldImp<TerminGeplantDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0027]/careflow_step|defining_code", "terminGeplantDefiningcode", TerminGeplantDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_ABGESAGT = new AqlFieldImp<TransitionDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0018]/transition|defining_code", "transitionDefiningcodeAbgesagt", TransitionDefiningcode.class, this);

  public SelectAqlField<ArzneimittelStartdatumVoraussetzungDefiningcode> TERMIN_GEPLANT_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<ArzneimittelStartdatumVoraussetzungDefiningcode>(EinwilligungserklarungAction.class, "/ism_transition[at0027]/current_state|defining_code", "terminGeplantDefiningcodeCurrentState", ArzneimittelStartdatumVoraussetzungDefiningcode.class, this);

  private EinwilligungserklarungActionContainment() {
    super("openEHR-EHR-ACTION.informed_consent.v0");
  }

  public static EinwilligungserklarungActionContainment getInstance() {
    return new EinwilligungserklarungActionContainment();
  }
}
