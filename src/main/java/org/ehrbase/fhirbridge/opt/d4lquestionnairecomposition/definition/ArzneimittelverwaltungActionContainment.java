package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.TransitionDefiningcode;

public class ArzneimittelverwaltungActionContainment extends Containment {
  public SelectAqlField<ArzneimittelverwaltungAction> ARZNEIMITTELVERWALTUNG_ACTION = new AqlFieldImp<ArzneimittelverwaltungAction>(ArzneimittelverwaltungAction.class, "", "ArzneimittelverwaltungAction", ArzneimittelverwaltungAction.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0015]/transition|defining_code", "transitionDefiningcode", TransitionDefiningcode.class, this);

  public SelectAqlField<MedikamentenbehanlungWurdeVerschobenDefiningcode> MEDIKAMENTENBEHANLUNG_WURDE_VERSCHOBEN_DEFININGCODE = new AqlFieldImp<MedikamentenbehanlungWurdeVerschobenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0013]/current_state|defining_code", "medikamentenbehanlungWurdeVerschobenDefiningcode", MedikamentenbehanlungWurdeVerschobenDefiningcode.class, this);

  public SelectAqlField<GrossereAnderungDerVerordnungDefiningcode> GROSSERE_ANDERUNG_DER_VERORDNUNG_DEFININGCODE = new AqlFieldImp<GrossereAnderungDerVerordnungDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0039]/current_state|defining_code", "grossereAnderungDerVerordnungDefiningcode", GrossereAnderungDerVerordnungDefiningcode.class, this);

  public SelectAqlField<RezeptWurdeWiderrufenDefiningcode> REZEPT_WURDE_WIDERRUFEN_DEFININGCODE = new AqlFieldImp<RezeptWurdeWiderrufenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0150]/current_state|defining_code", "rezeptWurdeWiderrufenDefiningcode", RezeptWurdeWiderrufenDefiningcode.class, this);

  public SelectAqlField<ArzneimittelWurdeNeuBewertetDefiningcode> ARZNEIMITTEL_WURDE_NEU_BEWERTET_DEFININGCODE = new AqlFieldImp<ArzneimittelWurdeNeuBewertetDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0005]/careflow_step|defining_code", "arzneimittelWurdeNeuBewertetDefiningcode", ArzneimittelWurdeNeuBewertetDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_MEDIKAMENTENBEHANLUNG_WURDE_VERSCHOBEN = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0013]/transition|defining_code", "transitionDefiningcodeMedikamentenbehanlungWurdeVerschoben", TransitionDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> VERABREICHUNG_EINER_DOSIS_WURDE_AUSGELASSEN_DEFININGCODE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0018]/current_state|defining_code", "verabreichungEinerDosisWurdeAusgelassenDefiningcode", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_ARZNEIMITTELBEHANLUNG_WURDE_ABGESAGT = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0012]/transition|defining_code", "transitionDefiningcodeArzneimittelbehanlungWurdeAbgesagt", TransitionDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_ARZNEIMITTEL_STARTDATUM_VORAUSSETZUNG = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0016]/transition|defining_code", "transitionDefiningcodeArzneimittelStartdatumVoraussetzung", TransitionDefiningcode.class, this);

  public SelectAqlField<VerabreichungWurdeAusgesetztDefiningcode> VERABREICHUNG_WURDE_AUSGESETZT_DEFININGCODE = new AqlFieldImp<VerabreichungWurdeAusgesetztDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0009]/current_state|defining_code", "verabreichungWurdeAusgesetztDefiningcode", VerabreichungWurdeAusgesetztDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_REZEPT_WURDE_WIDERRUFEN = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0150]/transition|defining_code", "transitionDefiningcodeRezeptWurdeWiderrufen", TransitionDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_REZEPT_IST_UNGULTIG_ODER_ABGELAUFEN = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0151]/transition|defining_code", "transitionDefiningcodeRezeptIstUngultigOderAbgelaufen", TransitionDefiningcode.class, this);

  public SelectAqlField<Cluster> ARZNEIMITTELDETAILS = new AqlFieldImp<Cluster>(ArzneimittelverwaltungAction.class, "/description[at0017]/items[at0104]", "arzneimitteldetails", Cluster.class, this);

  public SelectAqlField<ArzneimittelbehanlungWurdeAbgesagtDefiningcode> ARZNEIMITTELBEHANLUNG_WURDE_ABGESAGT_DEFININGCODE = new AqlFieldImp<ArzneimittelbehanlungWurdeAbgesagtDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0012]/careflow_step|defining_code", "arzneimittelbehanlungWurdeAbgesagtDefiningcode", ArzneimittelbehanlungWurdeAbgesagtDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ArzneimittelverwaltungAction.class, "/protocol[at0030]/items[at0085]", "erweiterung", Cluster.class, this);

  public SelectAqlField<RezeptWartetAufGenehmigungDefiningcode> REZEPT_WARTET_AUF_GENEHMIGUNG_DEFININGCODE = new AqlFieldImp<RezeptWartetAufGenehmigungDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0145]/careflow_step|defining_code", "rezeptWartetAufGenehmigungDefiningcode", RezeptWartetAufGenehmigungDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> ARZNEIMITTELBEHANDLUNG_HAT_BEGONNEN_DEFININGCODE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0004]/current_state|defining_code", "arzneimittelbehandlungHatBegonnenDefiningcode", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public SelectAqlField<ArzneimittelStartdatumVoraussetzungDefiningcode> ARZNEIMITTEL_STARTDATUM_VORAUSSETZUNG_DEFININGCODE = new AqlFieldImp<ArzneimittelStartdatumVoraussetzungDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0016]/current_state|defining_code", "arzneimittelStartdatumVoraussetzungDefiningcode", ArzneimittelStartdatumVoraussetzungDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_RE_AUTORISIERUG_DES_REZEPTS_AUSSTEHEND = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0011]/transition|defining_code", "transitionDefiningcodeReAutorisierugDesRezeptsAusstehend", TransitionDefiningcode.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ArzneimittelverwaltungAction.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_EMPFOHLENE_ARZNEIMITTEL = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0109]/transition|defining_code", "transitionDefiningcodeEmpfohleneArzneimittel", TransitionDefiningcode.class, this);

  public SelectAqlField<VerzogerungDerRezeptabgabeDefiningcode> VERZOGERUNG_DER_REZEPTABGABE_DEFININGCODE = new AqlFieldImp<VerzogerungDerRezeptabgabeDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0008]/careflow_step|defining_code", "verzogerungDerRezeptabgabeDefiningcode", VerzogerungDerRezeptabgabeDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_REZEPT_NEU_AUSGESTELLT = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0010]/transition|defining_code", "transitionDefiningcodeRezeptNeuAusgestellt", TransitionDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> REZEPT_IST_RE_AUTORISIERT_DEFININGCODE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0106]/current_state|defining_code", "rezeptIstReAutorisiertDefiningcode", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public SelectAqlField<ArzneimittelbehandlungIstAbgeschlossenDefiningcode> ARZNEIMITTELBEHANDLUNG_IST_ABGESCHLOSSEN_DEFININGCODE = new AqlFieldImp<ArzneimittelbehandlungIstAbgeschlossenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0007]/careflow_step|defining_code", "arzneimittelbehandlungIstAbgeschlossenDefiningcode", ArzneimittelbehandlungIstAbgeschlossenDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> REZEPT_AUSGESTELLT_DEFININGCODE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0002]/current_state|defining_code", "rezeptAusgestelltDefiningcode", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public SelectAqlField<ReAutorisierugDesRezeptsAusstehendDefiningcode> RE_AUTORISIERUG_DES_REZEPTS_AUSSTEHEND_DEFININGCODE = new AqlFieldImp<ReAutorisierugDesRezeptsAusstehendDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0011]/careflow_step|defining_code", "reAutorisierugDesRezeptsAusstehendDefiningcode", ReAutorisierugDesRezeptsAusstehendDefiningcode.class, this);

  public SelectAqlField<GeringfugigeAnderungDerVerordnungDefiningcode> GERINGFUGIGE_ANDERUNG_DER_VERORDNUNG_DEFININGCODE = new AqlFieldImp<GeringfugigeAnderungDerVerordnungDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0041]/careflow_step|defining_code", "geringfugigeAnderungDerVerordnungDefiningcode", GeringfugigeAnderungDerVerordnungDefiningcode.class, this);

  public SelectAqlField<GrossereAnderungDerVerordnungDefiningcode> ARZNEIMITTELBEHANDLUNG_GESTOPPT_DEFININGCODE = new AqlFieldImp<GrossereAnderungDerVerordnungDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0015]/current_state|defining_code", "arzneimittelbehandlungGestopptDefiningcode", GrossereAnderungDerVerordnungDefiningcode.class, this);

  public SelectAqlField<ArzneimittelStartdatumVoraussetzungDefiningcode> ARZNEIMITTEL_WURDE_GENEHMIGT_DEFININGCODE = new AqlFieldImp<ArzneimittelStartdatumVoraussetzungDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0153]/current_state|defining_code", "arzneimittelWurdeGenehmigtDefiningcode", ArzneimittelStartdatumVoraussetzungDefiningcode.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(ArzneimittelverwaltungAction.class, "/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<GrossereAnderungDerVerordnungDefiningcode> REZEPT_IST_UNGULTIG_ODER_ABGELAUFEN_DEFININGCODE = new AqlFieldImp<GrossereAnderungDerVerordnungDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0151]/current_state|defining_code", "rezeptIstUngultigOderAbgelaufenDefiningcode", GrossereAnderungDerVerordnungDefiningcode.class, this);

  public SelectAqlField<DosisWurdeVerabreichtDefiningcode> DOSIS_WURDE_VERABREICHT_DEFININGCODE = new AqlFieldImp<DosisWurdeVerabreichtDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0006]/careflow_step|defining_code", "dosisWurdeVerabreichtDefiningcode", DosisWurdeVerabreichtDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> VERABREICHUNG_EINER_DOSIS_WURDE_VERSCHOBEN_DEFININGCODE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0044]/current_state|defining_code", "verabreichungEinerDosisWurdeVerschobenDefiningcode", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public SelectAqlField<String> ARZNEIMITTEL_VALUE = new AqlFieldImp<String>(ArzneimittelverwaltungAction.class, "/description[at0017]/items[at0020]/value|value", "arzneimittelValue", String.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> ARZNEIMITTEL_WURDE_VORBEREITET_DEFININGCODE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0148]/current_state|defining_code", "arzneimittelWurdeVorbereitetDefiningcode", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public SelectAqlField<Cluster> DOSIS = new AqlFieldImp<Cluster>(ArzneimittelverwaltungAction.class, "/description[at0017]/items[at0131]", "dosis", Cluster.class, this);

  public SelectAqlField<RezeptNeuAusgestelltDefiningcode> REZEPT_NEU_AUSGESTELLT_DEFININGCODE = new AqlFieldImp<RezeptNeuAusgestelltDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0010]/careflow_step|defining_code", "rezeptNeuAusgestelltDefiningcode", RezeptNeuAusgestelltDefiningcode.class, this);

  public SelectAqlField<RezeptWurdeAusgefuhrtDefiningcode> REZEPT_WURDE_AUSGEFUHRT_DEFININGCODE = new AqlFieldImp<RezeptWurdeAusgefuhrtDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0152]/current_state|defining_code", "rezeptWurdeAusgefuhrtDefiningcode", RezeptWurdeAusgefuhrtDefiningcode.class, this);

  public SelectAqlField<EmpfohleneArzneimittelDefiningcode> EMPFOHLENE_ARZNEIMITTEL_DEFININGCODE = new AqlFieldImp<EmpfohleneArzneimittelDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0109]/careflow_step|defining_code", "empfohleneArzneimittelDefiningcode", EmpfohleneArzneimittelDefiningcode.class, this);

  public SelectAqlField<MedikamentenbehanlungWurdeVerschobenDefiningcode2> MEDIKAMENTENBEHANLUNG_WURDE_VERSCHOBEN_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<MedikamentenbehanlungWurdeVerschobenDefiningcode2>(ArzneimittelverwaltungAction.class, "/ism_transition[at0013]/careflow_step|defining_code", "medikamentenbehanlungWurdeVerschobenDefiningcodeCareflowStep", MedikamentenbehanlungWurdeVerschobenDefiningcode2.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_ARZNEIMITTELBEHANDLUNG_HAT_BEGONNEN = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0004]/transition|defining_code", "transitionDefiningcodeArzneimittelbehandlungHatBegonnen", TransitionDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_ARZNEIMITTEL_WURDE_NEU_BEWERTET = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0005]/transition|defining_code", "transitionDefiningcodeArzneimittelWurdeNeuBewertet", TransitionDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> ARZNEIMITTEL_WURDE_NEU_BEWERTET_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0005]/current_state|defining_code", "arzneimittelWurdeNeuBewertetDefiningcodeCurrentState", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_REZEPT_AUSGESTELLT = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0002]/transition|defining_code", "transitionDefiningcodeRezeptAusgestellt", TransitionDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_DOSIS_WURDE_VERABREICHT = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0006]/transition|defining_code", "transitionDefiningcodeDosisWurdeVerabreicht", TransitionDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode2> VERABREICHUNG_EINER_DOSIS_WURDE_AUSGELASSEN_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode2>(ArzneimittelverwaltungAction.class, "/ism_transition[at0018]/careflow_step|defining_code", "verabreichungEinerDosisWurdeAusgelassenDefiningcodeCareflowStep", VerabreichungEinerDosisWurdeAusgelassenDefiningcode2.class, this);

  public SelectAqlField<VerabreichungWurdeAusgesetztDefiningcode2> VERABREICHUNG_WURDE_AUSGESETZT_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<VerabreichungWurdeAusgesetztDefiningcode2>(ArzneimittelverwaltungAction.class, "/ism_transition[at0009]/careflow_step|defining_code", "verabreichungWurdeAusgesetztDefiningcodeCareflowStep", VerabreichungWurdeAusgesetztDefiningcode2.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_REZEPT_WURDE_AUSGEGEBEN_EINGELOST = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0003]/transition|defining_code", "transitionDefiningcodeRezeptWurdeAusgegebenEingelost", TransitionDefiningcode.class, this);

  public SelectAqlField<GrossereAnderungDerVerordnungDefiningcode2> GROSSERE_ANDERUNG_DER_VERORDNUNG_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<GrossereAnderungDerVerordnungDefiningcode2>(ArzneimittelverwaltungAction.class, "/ism_transition[at0039]/careflow_step|defining_code", "grossereAnderungDerVerordnungDefiningcodeCareflowStep", GrossereAnderungDerVerordnungDefiningcode2.class, this);

  public SelectAqlField<RezeptWartetAufGenehmigungDefiningcode2> REZEPT_WARTET_AUF_GENEHMIGUNG_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<RezeptWartetAufGenehmigungDefiningcode2>(ArzneimittelverwaltungAction.class, "/ism_transition[at0145]/current_state|defining_code", "rezeptWartetAufGenehmigungDefiningcodeCurrentState", RezeptWartetAufGenehmigungDefiningcode2.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_GERINGFUGIGE_ANDERUNG_DER_VERORDNUNG = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0041]/transition|defining_code", "transitionDefiningcodeGeringfugigeAnderungDerVerordnung", TransitionDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> REZEPT_WURDE_AUSGEGEBEN_EINGELOST_DEFININGCODE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0003]/current_state|defining_code", "rezeptWurdeAusgegebenEingelostDefiningcode", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public SelectAqlField<ArzneimittelStartdatumVoraussetzungDefiningcode2> ARZNEIMITTEL_STARTDATUM_VORAUSSETZUNG_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<ArzneimittelStartdatumVoraussetzungDefiningcode2>(ArzneimittelverwaltungAction.class, "/ism_transition[at0016]/careflow_step|defining_code", "arzneimittelStartdatumVoraussetzungDefiningcodeCareflowStep", ArzneimittelStartdatumVoraussetzungDefiningcode2.class, this);

  public SelectAqlField<ArzneimittelbehandlungHatBegonnenDefiningcode> ARZNEIMITTELBEHANDLUNG_HAT_BEGONNEN_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<ArzneimittelbehandlungHatBegonnenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0004]/careflow_step|defining_code", "arzneimittelbehandlungHatBegonnenDefiningcodeCareflowStep", ArzneimittelbehandlungHatBegonnenDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_VERABREICHUNG_EINER_DOSIS_WURDE_VERSCHOBEN = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0044]/transition|defining_code", "transitionDefiningcodeVerabreichungEinerDosisWurdeVerschoben", TransitionDefiningcode.class, this);

  public SelectAqlField<VerabreichungWurdeAusgesetztDefiningcode> VERZOGERUNG_DER_REZEPTABGABE_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<VerabreichungWurdeAusgesetztDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0008]/current_state|defining_code", "verzogerungDerRezeptabgabeDefiningcodeCurrentState", VerabreichungWurdeAusgesetztDefiningcode.class, this);

  public SelectAqlField<RezeptWurdeWiderrufenDefiningcode> ARZNEIMITTELBEHANLUNG_WURDE_ABGESAGT_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<RezeptWurdeWiderrufenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0012]/current_state|defining_code", "arzneimittelbehanlungWurdeAbgesagtDefiningcodeCurrentState", RezeptWurdeWiderrufenDefiningcode.class, this);

  public SelectAqlField<RezeptWurdeWiderrufenDefiningcode2> REZEPT_WURDE_WIDERRUFEN_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<RezeptWurdeWiderrufenDefiningcode2>(ArzneimittelverwaltungAction.class, "/ism_transition[at0150]/careflow_step|defining_code", "rezeptWurdeWiderrufenDefiningcodeCareflowStep", RezeptWurdeWiderrufenDefiningcode2.class, this);

  public SelectAqlField<RezeptIstReAutorisiertDefiningcode> REZEPT_IST_RE_AUTORISIERT_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<RezeptIstReAutorisiertDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0106]/careflow_step|defining_code", "rezeptIstReAutorisiertDefiningcodeCareflowStep", RezeptIstReAutorisiertDefiningcode.class, this);

  public SelectAqlField<RezeptWurdeAusgefuhrtDefiningcode> ARZNEIMITTELBEHANDLUNG_IST_ABGESCHLOSSEN_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<RezeptWurdeAusgefuhrtDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0007]/current_state|defining_code", "arzneimittelbehandlungIstAbgeschlossenDefiningcodeCurrentState", RezeptWurdeAusgefuhrtDefiningcode.class, this);

  public SelectAqlField<RezeptAusgestelltDefiningcode> REZEPT_AUSGESTELLT_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<RezeptAusgestelltDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0002]/careflow_step|defining_code", "rezeptAusgestelltDefiningcodeCareflowStep", RezeptAusgestelltDefiningcode.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ArzneimittelverwaltungAction.class, "/language", "language", Language.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_REZEPT_IST_RE_AUTORISIERT = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0106]/transition|defining_code", "transitionDefiningcodeRezeptIstReAutorisiert", TransitionDefiningcode.class, this);

  public SelectAqlField<ArzneimittelbehandlungGestopptDefiningcode> ARZNEIMITTELBEHANDLUNG_GESTOPPT_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<ArzneimittelbehandlungGestopptDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0015]/careflow_step|defining_code", "arzneimittelbehandlungGestopptDefiningcodeCareflowStep", ArzneimittelbehandlungGestopptDefiningcode.class, this);

  public ListSelectAqlField<Cluster> ZUSATZLICHE_DETAILS = new ListAqlFieldImp<Cluster>(ArzneimittelverwaltungAction.class, "/description[at0017]/items[at0053]", "zusatzlicheDetails", Cluster.class, this);

  public SelectAqlField<RezeptWurdeAusgegebenEingelostDefiningcode> REZEPT_WURDE_AUSGEGEBEN_EINGELOST_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<RezeptWurdeAusgegebenEingelostDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0003]/careflow_step|defining_code", "rezeptWurdeAusgegebenEingelostDefiningcodeCareflowStep", RezeptWurdeAusgegebenEingelostDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> GERINGFUGIGE_ANDERUNG_DER_VERORDNUNG_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0041]/current_state|defining_code", "geringfugigeAnderungDerVerordnungDefiningcodeCurrentState", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_ARZNEIMITTEL_WURDE_VORBEREITET = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0148]/transition|defining_code", "transitionDefiningcodeArzneimittelWurdeVorbereitet", TransitionDefiningcode.class, this);

  public SelectAqlField<ArzneimittelWurdeGenehmigtDefiningcode> ARZNEIMITTEL_WURDE_GENEHMIGT_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<ArzneimittelWurdeGenehmigtDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0153]/careflow_step|defining_code", "arzneimittelWurdeGenehmigtDefiningcodeCareflowStep", ArzneimittelWurdeGenehmigtDefiningcode.class, this);

  public SelectAqlField<VerabreichungWurdeAusgesetztDefiningcode> RE_AUTORISIERUG_DES_REZEPTS_AUSSTEHEND_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<VerabreichungWurdeAusgesetztDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0011]/current_state|defining_code", "reAutorisierugDesRezeptsAusstehendDefiningcodeCurrentState", VerabreichungWurdeAusgesetztDefiningcode.class, this);

  public SelectAqlField<RezeptIstUngultigOderAbgelaufenDefiningcode> REZEPT_IST_UNGULTIG_ODER_ABGELAUFEN_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<RezeptIstUngultigOderAbgelaufenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0151]/careflow_step|defining_code", "rezeptIstUngultigOderAbgelaufenDefiningcodeCareflowStep", RezeptIstUngultigOderAbgelaufenDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_ARZNEIMITTEL_WURDE_GENEHMIGT = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0153]/transition|defining_code", "transitionDefiningcodeArzneimittelWurdeGenehmigt", TransitionDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> DOSIS_WURDE_VERABREICHT_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0006]/current_state|defining_code", "dosisWurdeVerabreichtDefiningcodeCurrentState", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_REZEPT_WURDE_AUSGEFUHRT = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0152]/transition|defining_code", "transitionDefiningcodeRezeptWurdeAusgefuhrt", TransitionDefiningcode.class, this);

  public SelectAqlField<ArzneimittelWurdeVorbereitetDefiningcode> ARZNEIMITTEL_WURDE_VORBEREITET_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<ArzneimittelWurdeVorbereitetDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0148]/careflow_step|defining_code", "arzneimittelWurdeVorbereitetDefiningcodeCareflowStep", ArzneimittelWurdeVorbereitetDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeVerschobenDefiningcode> VERABREICHUNG_EINER_DOSIS_WURDE_VERSCHOBEN_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<VerabreichungEinerDosisWurdeVerschobenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0044]/careflow_step|defining_code", "verabreichungEinerDosisWurdeVerschobenDefiningcodeCareflowStep", VerabreichungEinerDosisWurdeVerschobenDefiningcode.class, this);

  public SelectAqlField<Boolean> VERGANGENE_IMPFUNG_SEIT_OKTOBER2019_VALUE = new AqlFieldImp<Boolean>(ArzneimittelverwaltungAction.class, "/description[at0017]/items[at0149]/value|value", "vergangeneImpfungSeitOktober2019Value", Boolean.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_VERZOGERUNG_DER_REZEPTABGABE = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0008]/transition|defining_code", "transitionDefiningcodeVerzogerungDerRezeptabgabe", TransitionDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_VERABREICHUNG_WURDE_AUSGESETZT = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0009]/transition|defining_code", "transitionDefiningcodeVerabreichungWurdeAusgesetzt", TransitionDefiningcode.class, this);

  public SelectAqlField<EmpfohleneArzneimittelDefiningcode2> EMPFOHLENE_ARZNEIMITTEL_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<EmpfohleneArzneimittelDefiningcode2>(ArzneimittelverwaltungAction.class, "/ism_transition[at0109]/current_state|defining_code", "empfohleneArzneimittelDefiningcodeCurrentState", EmpfohleneArzneimittelDefiningcode2.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_REZEPT_WARTET_AUF_GENEHMIGUNG = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0145]/transition|defining_code", "transitionDefiningcodeRezeptWartetAufGenehmigung", TransitionDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_GROSSERE_ANDERUNG_DER_VERORDNUNG = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0039]/transition|defining_code", "transitionDefiningcodeGrossereAnderungDerVerordnung", TransitionDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_ARZNEIMITTELBEHANDLUNG_IST_ABGESCHLOSSEN = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0007]/transition|defining_code", "transitionDefiningcodeArzneimittelbehandlungIstAbgeschlossen", TransitionDefiningcode.class, this);

  public SelectAqlField<TransitionDefiningcode> TRANSITION_DEFININGCODE_VERABREICHUNG_EINER_DOSIS_WURDE_AUSGELASSEN = new AqlFieldImp<TransitionDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0018]/transition|defining_code", "transitionDefiningcodeVerabreichungEinerDosisWurdeAusgelassen", TransitionDefiningcode.class, this);

  public SelectAqlField<VerabreichungEinerDosisWurdeAusgelassenDefiningcode> REZEPT_NEU_AUSGESTELLT_DEFININGCODE_CURRENT_STATE = new AqlFieldImp<VerabreichungEinerDosisWurdeAusgelassenDefiningcode>(ArzneimittelverwaltungAction.class, "/ism_transition[at0010]/current_state|defining_code", "rezeptNeuAusgestelltDefiningcodeCurrentState", VerabreichungEinerDosisWurdeAusgelassenDefiningcode.class, this);

  public SelectAqlField<RezeptWurdeAusgefuhrtDefiningcode2> REZEPT_WURDE_AUSGEFUHRT_DEFININGCODE_CAREFLOW_STEP = new AqlFieldImp<RezeptWurdeAusgefuhrtDefiningcode2>(ArzneimittelverwaltungAction.class, "/ism_transition[at0152]/careflow_step|defining_code", "rezeptWurdeAusgefuhrtDefiningcodeCareflowStep", RezeptWurdeAusgefuhrtDefiningcode2.class, this);

  private ArzneimittelverwaltungActionContainment() {
    super("openEHR-EHR-ACTION.medication.v1");
  }

  public static ArzneimittelverwaltungActionContainment getInstance() {
    return new ArzneimittelverwaltungActionContainment();
  }
}
