package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.TransitionDefiningcode;

@Entity
@Archetype("openEHR-EHR-ACTION.medication.v1")
public class ArzneimittelverwaltungAction {
  @Path("/ism_transition[at0015]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcode;

  @Path("/ism_transition[at0013]/current_state|defining_code")
  private MedikamentenbehanlungWurdeVerschobenDefiningcode medikamentenbehanlungWurdeVerschobenDefiningcode;

  @Path("/ism_transition[at0039]/current_state|defining_code")
  private GrossereAnderungDerVerordnungDefiningcode grossereAnderungDerVerordnungDefiningcode;

  @Path("/ism_transition[at0150]/current_state|defining_code")
  private RezeptWurdeWiderrufenDefiningcode rezeptWurdeWiderrufenDefiningcode;

  @Path("/ism_transition[at0005]/careflow_step|defining_code")
  private ArzneimittelWurdeNeuBewertetDefiningcode arzneimittelWurdeNeuBewertetDefiningcode;

  @Path("/ism_transition[at0013]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeMedikamentenbehanlungWurdeVerschoben;

  @Path("/ism_transition[at0018]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode verabreichungEinerDosisWurdeAusgelassenDefiningcode;

  @Path("/ism_transition[at0012]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeArzneimittelbehanlungWurdeAbgesagt;

  @Path("/ism_transition[at0016]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeArzneimittelStartdatumVoraussetzung;

  @Path("/ism_transition[at0009]/current_state|defining_code")
  private VerabreichungWurdeAusgesetztDefiningcode verabreichungWurdeAusgesetztDefiningcode;

  @Path("/ism_transition[at0150]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeRezeptWurdeWiderrufen;

  @Path("/ism_transition[at0151]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeRezeptIstUngultigOderAbgelaufen;

  @Path("/description[at0017]/items[at0104]")
  private Cluster arzneimitteldetails;

  @Path("/ism_transition[at0012]/careflow_step|defining_code")
  private ArzneimittelbehanlungWurdeAbgesagtDefiningcode arzneimittelbehanlungWurdeAbgesagtDefiningcode;

  @Path("/protocol[at0030]/items[at0085]")
  private List<Cluster> erweiterung;

  @Path("/ism_transition[at0145]/careflow_step|defining_code")
  private RezeptWartetAufGenehmigungDefiningcode rezeptWartetAufGenehmigungDefiningcode;

  @Path("/ism_transition[at0004]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode arzneimittelbehandlungHatBegonnenDefiningcode;

  @Path("/ism_transition[at0016]/current_state|defining_code")
  private ArzneimittelStartdatumVoraussetzungDefiningcode arzneimittelStartdatumVoraussetzungDefiningcode;

  @Path("/ism_transition[at0011]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeReAutorisierugDesRezeptsAusstehend;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/ism_transition[at0109]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeEmpfohleneArzneimittel;

  @Path("/ism_transition[at0008]/careflow_step|defining_code")
  private VerzogerungDerRezeptabgabeDefiningcode verzogerungDerRezeptabgabeDefiningcode;

  @Path("/ism_transition[at0010]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeRezeptNeuAusgestellt;

  @Path("/ism_transition[at0106]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode rezeptIstReAutorisiertDefiningcode;

  @Path("/ism_transition[at0007]/careflow_step|defining_code")
  private ArzneimittelbehandlungIstAbgeschlossenDefiningcode arzneimittelbehandlungIstAbgeschlossenDefiningcode;

  @Path("/ism_transition[at0002]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode rezeptAusgestelltDefiningcode;

  @Path("/ism_transition[at0011]/careflow_step|defining_code")
  private ReAutorisierugDesRezeptsAusstehendDefiningcode reAutorisierugDesRezeptsAusstehendDefiningcode;

  @Path("/ism_transition[at0041]/careflow_step|defining_code")
  private GeringfugigeAnderungDerVerordnungDefiningcode geringfugigeAnderungDerVerordnungDefiningcode;

  @Path("/ism_transition[at0015]/current_state|defining_code")
  private GrossereAnderungDerVerordnungDefiningcode arzneimittelbehandlungGestopptDefiningcode;

  @Path("/ism_transition[at0153]/current_state|defining_code")
  private ArzneimittelStartdatumVoraussetzungDefiningcode arzneimittelWurdeGenehmigtDefiningcode;

  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/ism_transition[at0151]/current_state|defining_code")
  private GrossereAnderungDerVerordnungDefiningcode rezeptIstUngultigOderAbgelaufenDefiningcode;

  @Path("/ism_transition[at0006]/careflow_step|defining_code")
  private DosisWurdeVerabreichtDefiningcode dosisWurdeVerabreichtDefiningcode;

  @Path("/ism_transition[at0044]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode verabreichungEinerDosisWurdeVerschobenDefiningcode;

  @Path("/description[at0017]/items[at0020]/value|value")
  private String arzneimittelValue;

  @Path("/ism_transition[at0148]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode arzneimittelWurdeVorbereitetDefiningcode;

  @Path("/description[at0017]/items[at0131]")
  private Cluster dosis;

  @Path("/ism_transition[at0010]/careflow_step|defining_code")
  private RezeptNeuAusgestelltDefiningcode rezeptNeuAusgestelltDefiningcode;

  @Path("/ism_transition[at0152]/current_state|defining_code")
  private RezeptWurdeAusgefuhrtDefiningcode rezeptWurdeAusgefuhrtDefiningcode;

  @Path("/ism_transition[at0109]/careflow_step|defining_code")
  private EmpfohleneArzneimittelDefiningcode empfohleneArzneimittelDefiningcode;

  @Path("/ism_transition[at0013]/careflow_step|defining_code")
  private MedikamentenbehanlungWurdeVerschobenDefiningcode2 medikamentenbehanlungWurdeVerschobenDefiningcodeCareflowStep;

  @Path("/ism_transition[at0004]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeArzneimittelbehandlungHatBegonnen;

  @Path("/ism_transition[at0005]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeArzneimittelWurdeNeuBewertet;

  @Path("/ism_transition[at0005]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode arzneimittelWurdeNeuBewertetDefiningcodeCurrentState;

  @Path("/ism_transition[at0002]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeRezeptAusgestellt;

  @Path("/ism_transition[at0006]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeDosisWurdeVerabreicht;

  @Path("/ism_transition[at0018]/careflow_step|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode2 verabreichungEinerDosisWurdeAusgelassenDefiningcodeCareflowStep;

  @Path("/ism_transition[at0009]/careflow_step|defining_code")
  private VerabreichungWurdeAusgesetztDefiningcode2 verabreichungWurdeAusgesetztDefiningcodeCareflowStep;

  @Path("/ism_transition[at0003]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeRezeptWurdeAusgegebenEingelost;

  @Path("/ism_transition[at0039]/careflow_step|defining_code")
  private GrossereAnderungDerVerordnungDefiningcode2 grossereAnderungDerVerordnungDefiningcodeCareflowStep;

  @Path("/ism_transition[at0145]/current_state|defining_code")
  private RezeptWartetAufGenehmigungDefiningcode2 rezeptWartetAufGenehmigungDefiningcodeCurrentState;

  @Path("/ism_transition[at0041]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeGeringfugigeAnderungDerVerordnung;

  @Path("/ism_transition[at0003]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode rezeptWurdeAusgegebenEingelostDefiningcode;

  @Path("/ism_transition[at0016]/careflow_step|defining_code")
  private ArzneimittelStartdatumVoraussetzungDefiningcode2 arzneimittelStartdatumVoraussetzungDefiningcodeCareflowStep;

  @Path("/ism_transition[at0004]/careflow_step|defining_code")
  private ArzneimittelbehandlungHatBegonnenDefiningcode arzneimittelbehandlungHatBegonnenDefiningcodeCareflowStep;

  @Path("/ism_transition[at0044]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeVerabreichungEinerDosisWurdeVerschoben;

  @Path("/ism_transition[at0008]/current_state|defining_code")
  private VerabreichungWurdeAusgesetztDefiningcode verzogerungDerRezeptabgabeDefiningcodeCurrentState;

  @Path("/ism_transition[at0012]/current_state|defining_code")
  private RezeptWurdeWiderrufenDefiningcode arzneimittelbehanlungWurdeAbgesagtDefiningcodeCurrentState;

  @Path("/ism_transition[at0150]/careflow_step|defining_code")
  private RezeptWurdeWiderrufenDefiningcode2 rezeptWurdeWiderrufenDefiningcodeCareflowStep;

  @Path("/ism_transition[at0106]/careflow_step|defining_code")
  private RezeptIstReAutorisiertDefiningcode rezeptIstReAutorisiertDefiningcodeCareflowStep;

  @Path("/ism_transition[at0007]/current_state|defining_code")
  private RezeptWurdeAusgefuhrtDefiningcode arzneimittelbehandlungIstAbgeschlossenDefiningcodeCurrentState;

  @Path("/ism_transition[at0002]/careflow_step|defining_code")
  private RezeptAusgestelltDefiningcode rezeptAusgestelltDefiningcodeCareflowStep;

  @Path("/language")
  private Language language;

  @Path("/ism_transition[at0106]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeRezeptIstReAutorisiert;

  @Path("/ism_transition[at0015]/careflow_step|defining_code")
  private ArzneimittelbehandlungGestopptDefiningcode arzneimittelbehandlungGestopptDefiningcodeCareflowStep;

  @Path("/description[at0017]/items[at0053]")
  private List<Cluster> zusatzlicheDetails;

  @Path("/ism_transition[at0003]/careflow_step|defining_code")
  private RezeptWurdeAusgegebenEingelostDefiningcode rezeptWurdeAusgegebenEingelostDefiningcodeCareflowStep;

  @Path("/ism_transition[at0041]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode geringfugigeAnderungDerVerordnungDefiningcodeCurrentState;

  @Path("/ism_transition[at0148]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeArzneimittelWurdeVorbereitet;

  @Path("/ism_transition[at0153]/careflow_step|defining_code")
  private ArzneimittelWurdeGenehmigtDefiningcode arzneimittelWurdeGenehmigtDefiningcodeCareflowStep;

  @Path("/ism_transition[at0011]/current_state|defining_code")
  private VerabreichungWurdeAusgesetztDefiningcode reAutorisierugDesRezeptsAusstehendDefiningcodeCurrentState;

  @Path("/ism_transition[at0151]/careflow_step|defining_code")
  private RezeptIstUngultigOderAbgelaufenDefiningcode rezeptIstUngultigOderAbgelaufenDefiningcodeCareflowStep;

  @Path("/ism_transition[at0153]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeArzneimittelWurdeGenehmigt;

  @Path("/ism_transition[at0006]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode dosisWurdeVerabreichtDefiningcodeCurrentState;

  @Path("/ism_transition[at0152]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeRezeptWurdeAusgefuhrt;

  @Path("/ism_transition[at0148]/careflow_step|defining_code")
  private ArzneimittelWurdeVorbereitetDefiningcode arzneimittelWurdeVorbereitetDefiningcodeCareflowStep;

  @Path("/ism_transition[at0044]/careflow_step|defining_code")
  private VerabreichungEinerDosisWurdeVerschobenDefiningcode verabreichungEinerDosisWurdeVerschobenDefiningcodeCareflowStep;

  @Path("/description[at0017]/items[at0149]/value|value")
  private Boolean vergangeneImpfungSeitOktober2019Value;

  @Path("/ism_transition[at0008]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeVerzogerungDerRezeptabgabe;

  @Path("/ism_transition[at0009]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeVerabreichungWurdeAusgesetzt;

  @Path("/ism_transition[at0109]/current_state|defining_code")
  private EmpfohleneArzneimittelDefiningcode2 empfohleneArzneimittelDefiningcodeCurrentState;

  @Path("/ism_transition[at0145]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeRezeptWartetAufGenehmigung;

  @Path("/ism_transition[at0039]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeGrossereAnderungDerVerordnung;

  @Path("/ism_transition[at0007]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeArzneimittelbehandlungIstAbgeschlossen;

  @Path("/ism_transition[at0018]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeVerabreichungEinerDosisWurdeAusgelassen;

  @Path("/ism_transition[at0010]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode rezeptNeuAusgestelltDefiningcodeCurrentState;

  @Path("/ism_transition[at0152]/careflow_step|defining_code")
  private RezeptWurdeAusgefuhrtDefiningcode2 rezeptWurdeAusgefuhrtDefiningcodeCareflowStep;

  public void setTransitionDefiningcode(TransitionDefiningcode transitionDefiningcode) {
     this.transitionDefiningcode = transitionDefiningcode;
  }

  public TransitionDefiningcode getTransitionDefiningcode() {
     return this.transitionDefiningcode ;
  }

  public void setMedikamentenbehanlungWurdeVerschobenDefiningcode(
      MedikamentenbehanlungWurdeVerschobenDefiningcode medikamentenbehanlungWurdeVerschobenDefiningcode) {
     this.medikamentenbehanlungWurdeVerschobenDefiningcode = medikamentenbehanlungWurdeVerschobenDefiningcode;
  }

  public MedikamentenbehanlungWurdeVerschobenDefiningcode getMedikamentenbehanlungWurdeVerschobenDefiningcode(
      ) {
     return this.medikamentenbehanlungWurdeVerschobenDefiningcode ;
  }

  public void setGrossereAnderungDerVerordnungDefiningcode(
      GrossereAnderungDerVerordnungDefiningcode grossereAnderungDerVerordnungDefiningcode) {
     this.grossereAnderungDerVerordnungDefiningcode = grossereAnderungDerVerordnungDefiningcode;
  }

  public GrossereAnderungDerVerordnungDefiningcode getGrossereAnderungDerVerordnungDefiningcode() {
     return this.grossereAnderungDerVerordnungDefiningcode ;
  }

  public void setRezeptWurdeWiderrufenDefiningcode(
      RezeptWurdeWiderrufenDefiningcode rezeptWurdeWiderrufenDefiningcode) {
     this.rezeptWurdeWiderrufenDefiningcode = rezeptWurdeWiderrufenDefiningcode;
  }

  public RezeptWurdeWiderrufenDefiningcode getRezeptWurdeWiderrufenDefiningcode() {
     return this.rezeptWurdeWiderrufenDefiningcode ;
  }

  public void setArzneimittelWurdeNeuBewertetDefiningcode(
      ArzneimittelWurdeNeuBewertetDefiningcode arzneimittelWurdeNeuBewertetDefiningcode) {
     this.arzneimittelWurdeNeuBewertetDefiningcode = arzneimittelWurdeNeuBewertetDefiningcode;
  }

  public ArzneimittelWurdeNeuBewertetDefiningcode getArzneimittelWurdeNeuBewertetDefiningcode() {
     return this.arzneimittelWurdeNeuBewertetDefiningcode ;
  }

  public void setTransitionDefiningcodeMedikamentenbehanlungWurdeVerschoben(
      TransitionDefiningcode transitionDefiningcodeMedikamentenbehanlungWurdeVerschoben) {
     this.transitionDefiningcodeMedikamentenbehanlungWurdeVerschoben = transitionDefiningcodeMedikamentenbehanlungWurdeVerschoben;
  }

  public TransitionDefiningcode getTransitionDefiningcodeMedikamentenbehanlungWurdeVerschoben() {
     return this.transitionDefiningcodeMedikamentenbehanlungWurdeVerschoben ;
  }

  public void setVerabreichungEinerDosisWurdeAusgelassenDefiningcode(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode verabreichungEinerDosisWurdeAusgelassenDefiningcode) {
     this.verabreichungEinerDosisWurdeAusgelassenDefiningcode = verabreichungEinerDosisWurdeAusgelassenDefiningcode;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getVerabreichungEinerDosisWurdeAusgelassenDefiningcode(
      ) {
     return this.verabreichungEinerDosisWurdeAusgelassenDefiningcode ;
  }

  public void setTransitionDefiningcodeArzneimittelbehanlungWurdeAbgesagt(
      TransitionDefiningcode transitionDefiningcodeArzneimittelbehanlungWurdeAbgesagt) {
     this.transitionDefiningcodeArzneimittelbehanlungWurdeAbgesagt = transitionDefiningcodeArzneimittelbehanlungWurdeAbgesagt;
  }

  public TransitionDefiningcode getTransitionDefiningcodeArzneimittelbehanlungWurdeAbgesagt() {
     return this.transitionDefiningcodeArzneimittelbehanlungWurdeAbgesagt ;
  }

  public void setTransitionDefiningcodeArzneimittelStartdatumVoraussetzung(
      TransitionDefiningcode transitionDefiningcodeArzneimittelStartdatumVoraussetzung) {
     this.transitionDefiningcodeArzneimittelStartdatumVoraussetzung = transitionDefiningcodeArzneimittelStartdatumVoraussetzung;
  }

  public TransitionDefiningcode getTransitionDefiningcodeArzneimittelStartdatumVoraussetzung() {
     return this.transitionDefiningcodeArzneimittelStartdatumVoraussetzung ;
  }

  public void setVerabreichungWurdeAusgesetztDefiningcode(
      VerabreichungWurdeAusgesetztDefiningcode verabreichungWurdeAusgesetztDefiningcode) {
     this.verabreichungWurdeAusgesetztDefiningcode = verabreichungWurdeAusgesetztDefiningcode;
  }

  public VerabreichungWurdeAusgesetztDefiningcode getVerabreichungWurdeAusgesetztDefiningcode() {
     return this.verabreichungWurdeAusgesetztDefiningcode ;
  }

  public void setTransitionDefiningcodeRezeptWurdeWiderrufen(
      TransitionDefiningcode transitionDefiningcodeRezeptWurdeWiderrufen) {
     this.transitionDefiningcodeRezeptWurdeWiderrufen = transitionDefiningcodeRezeptWurdeWiderrufen;
  }

  public TransitionDefiningcode getTransitionDefiningcodeRezeptWurdeWiderrufen() {
     return this.transitionDefiningcodeRezeptWurdeWiderrufen ;
  }

  public void setTransitionDefiningcodeRezeptIstUngultigOderAbgelaufen(
      TransitionDefiningcode transitionDefiningcodeRezeptIstUngultigOderAbgelaufen) {
     this.transitionDefiningcodeRezeptIstUngultigOderAbgelaufen = transitionDefiningcodeRezeptIstUngultigOderAbgelaufen;
  }

  public TransitionDefiningcode getTransitionDefiningcodeRezeptIstUngultigOderAbgelaufen() {
     return this.transitionDefiningcodeRezeptIstUngultigOderAbgelaufen ;
  }

  public void setArzneimitteldetails(Cluster arzneimitteldetails) {
     this.arzneimitteldetails = arzneimitteldetails;
  }

  public Cluster getArzneimitteldetails() {
     return this.arzneimitteldetails ;
  }

  public void setArzneimittelbehanlungWurdeAbgesagtDefiningcode(
      ArzneimittelbehanlungWurdeAbgesagtDefiningcode arzneimittelbehanlungWurdeAbgesagtDefiningcode) {
     this.arzneimittelbehanlungWurdeAbgesagtDefiningcode = arzneimittelbehanlungWurdeAbgesagtDefiningcode;
  }

  public ArzneimittelbehanlungWurdeAbgesagtDefiningcode getArzneimittelbehanlungWurdeAbgesagtDefiningcode(
      ) {
     return this.arzneimittelbehanlungWurdeAbgesagtDefiningcode ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setRezeptWartetAufGenehmigungDefiningcode(
      RezeptWartetAufGenehmigungDefiningcode rezeptWartetAufGenehmigungDefiningcode) {
     this.rezeptWartetAufGenehmigungDefiningcode = rezeptWartetAufGenehmigungDefiningcode;
  }

  public RezeptWartetAufGenehmigungDefiningcode getRezeptWartetAufGenehmigungDefiningcode() {
     return this.rezeptWartetAufGenehmigungDefiningcode ;
  }

  public void setArzneimittelbehandlungHatBegonnenDefiningcode(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode arzneimittelbehandlungHatBegonnenDefiningcode) {
     this.arzneimittelbehandlungHatBegonnenDefiningcode = arzneimittelbehandlungHatBegonnenDefiningcode;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getArzneimittelbehandlungHatBegonnenDefiningcode(
      ) {
     return this.arzneimittelbehandlungHatBegonnenDefiningcode ;
  }

  public void setArzneimittelStartdatumVoraussetzungDefiningcode(
      ArzneimittelStartdatumVoraussetzungDefiningcode arzneimittelStartdatumVoraussetzungDefiningcode) {
     this.arzneimittelStartdatumVoraussetzungDefiningcode = arzneimittelStartdatumVoraussetzungDefiningcode;
  }

  public ArzneimittelStartdatumVoraussetzungDefiningcode getArzneimittelStartdatumVoraussetzungDefiningcode(
      ) {
     return this.arzneimittelStartdatumVoraussetzungDefiningcode ;
  }

  public void setTransitionDefiningcodeReAutorisierugDesRezeptsAusstehend(
      TransitionDefiningcode transitionDefiningcodeReAutorisierugDesRezeptsAusstehend) {
     this.transitionDefiningcodeReAutorisierugDesRezeptsAusstehend = transitionDefiningcodeReAutorisierugDesRezeptsAusstehend;
  }

  public TransitionDefiningcode getTransitionDefiningcodeReAutorisierugDesRezeptsAusstehend() {
     return this.transitionDefiningcodeReAutorisierugDesRezeptsAusstehend ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setTransitionDefiningcodeEmpfohleneArzneimittel(
      TransitionDefiningcode transitionDefiningcodeEmpfohleneArzneimittel) {
     this.transitionDefiningcodeEmpfohleneArzneimittel = transitionDefiningcodeEmpfohleneArzneimittel;
  }

  public TransitionDefiningcode getTransitionDefiningcodeEmpfohleneArzneimittel() {
     return this.transitionDefiningcodeEmpfohleneArzneimittel ;
  }

  public void setVerzogerungDerRezeptabgabeDefiningcode(
      VerzogerungDerRezeptabgabeDefiningcode verzogerungDerRezeptabgabeDefiningcode) {
     this.verzogerungDerRezeptabgabeDefiningcode = verzogerungDerRezeptabgabeDefiningcode;
  }

  public VerzogerungDerRezeptabgabeDefiningcode getVerzogerungDerRezeptabgabeDefiningcode() {
     return this.verzogerungDerRezeptabgabeDefiningcode ;
  }

  public void setTransitionDefiningcodeRezeptNeuAusgestellt(
      TransitionDefiningcode transitionDefiningcodeRezeptNeuAusgestellt) {
     this.transitionDefiningcodeRezeptNeuAusgestellt = transitionDefiningcodeRezeptNeuAusgestellt;
  }

  public TransitionDefiningcode getTransitionDefiningcodeRezeptNeuAusgestellt() {
     return this.transitionDefiningcodeRezeptNeuAusgestellt ;
  }

  public void setRezeptIstReAutorisiertDefiningcode(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode rezeptIstReAutorisiertDefiningcode) {
     this.rezeptIstReAutorisiertDefiningcode = rezeptIstReAutorisiertDefiningcode;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getRezeptIstReAutorisiertDefiningcode(
      ) {
     return this.rezeptIstReAutorisiertDefiningcode ;
  }

  public void setArzneimittelbehandlungIstAbgeschlossenDefiningcode(
      ArzneimittelbehandlungIstAbgeschlossenDefiningcode arzneimittelbehandlungIstAbgeschlossenDefiningcode) {
     this.arzneimittelbehandlungIstAbgeschlossenDefiningcode = arzneimittelbehandlungIstAbgeschlossenDefiningcode;
  }

  public ArzneimittelbehandlungIstAbgeschlossenDefiningcode getArzneimittelbehandlungIstAbgeschlossenDefiningcode(
      ) {
     return this.arzneimittelbehandlungIstAbgeschlossenDefiningcode ;
  }

  public void setRezeptAusgestelltDefiningcode(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode rezeptAusgestelltDefiningcode) {
     this.rezeptAusgestelltDefiningcode = rezeptAusgestelltDefiningcode;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getRezeptAusgestelltDefiningcode() {
     return this.rezeptAusgestelltDefiningcode ;
  }

  public void setReAutorisierugDesRezeptsAusstehendDefiningcode(
      ReAutorisierugDesRezeptsAusstehendDefiningcode reAutorisierugDesRezeptsAusstehendDefiningcode) {
     this.reAutorisierugDesRezeptsAusstehendDefiningcode = reAutorisierugDesRezeptsAusstehendDefiningcode;
  }

  public ReAutorisierugDesRezeptsAusstehendDefiningcode getReAutorisierugDesRezeptsAusstehendDefiningcode(
      ) {
     return this.reAutorisierugDesRezeptsAusstehendDefiningcode ;
  }

  public void setGeringfugigeAnderungDerVerordnungDefiningcode(
      GeringfugigeAnderungDerVerordnungDefiningcode geringfugigeAnderungDerVerordnungDefiningcode) {
     this.geringfugigeAnderungDerVerordnungDefiningcode = geringfugigeAnderungDerVerordnungDefiningcode;
  }

  public GeringfugigeAnderungDerVerordnungDefiningcode getGeringfugigeAnderungDerVerordnungDefiningcode(
      ) {
     return this.geringfugigeAnderungDerVerordnungDefiningcode ;
  }

  public void setArzneimittelbehandlungGestopptDefiningcode(
      GrossereAnderungDerVerordnungDefiningcode arzneimittelbehandlungGestopptDefiningcode) {
     this.arzneimittelbehandlungGestopptDefiningcode = arzneimittelbehandlungGestopptDefiningcode;
  }

  public GrossereAnderungDerVerordnungDefiningcode getArzneimittelbehandlungGestopptDefiningcode() {
     return this.arzneimittelbehandlungGestopptDefiningcode ;
  }

  public void setArzneimittelWurdeGenehmigtDefiningcode(
      ArzneimittelStartdatumVoraussetzungDefiningcode arzneimittelWurdeGenehmigtDefiningcode) {
     this.arzneimittelWurdeGenehmigtDefiningcode = arzneimittelWurdeGenehmigtDefiningcode;
  }

  public ArzneimittelStartdatumVoraussetzungDefiningcode getArzneimittelWurdeGenehmigtDefiningcode(
      ) {
     return this.arzneimittelWurdeGenehmigtDefiningcode ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setRezeptIstUngultigOderAbgelaufenDefiningcode(
      GrossereAnderungDerVerordnungDefiningcode rezeptIstUngultigOderAbgelaufenDefiningcode) {
     this.rezeptIstUngultigOderAbgelaufenDefiningcode = rezeptIstUngultigOderAbgelaufenDefiningcode;
  }

  public GrossereAnderungDerVerordnungDefiningcode getRezeptIstUngultigOderAbgelaufenDefiningcode(
      ) {
     return this.rezeptIstUngultigOderAbgelaufenDefiningcode ;
  }

  public void setDosisWurdeVerabreichtDefiningcode(
      DosisWurdeVerabreichtDefiningcode dosisWurdeVerabreichtDefiningcode) {
     this.dosisWurdeVerabreichtDefiningcode = dosisWurdeVerabreichtDefiningcode;
  }

  public DosisWurdeVerabreichtDefiningcode getDosisWurdeVerabreichtDefiningcode() {
     return this.dosisWurdeVerabreichtDefiningcode ;
  }

  public void setVerabreichungEinerDosisWurdeVerschobenDefiningcode(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode verabreichungEinerDosisWurdeVerschobenDefiningcode) {
     this.verabreichungEinerDosisWurdeVerschobenDefiningcode = verabreichungEinerDosisWurdeVerschobenDefiningcode;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getVerabreichungEinerDosisWurdeVerschobenDefiningcode(
      ) {
     return this.verabreichungEinerDosisWurdeVerschobenDefiningcode ;
  }

  public void setArzneimittelValue(String arzneimittelValue) {
     this.arzneimittelValue = arzneimittelValue;
  }

  public String getArzneimittelValue() {
     return this.arzneimittelValue ;
  }

  public void setArzneimittelWurdeVorbereitetDefiningcode(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode arzneimittelWurdeVorbereitetDefiningcode) {
     this.arzneimittelWurdeVorbereitetDefiningcode = arzneimittelWurdeVorbereitetDefiningcode;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getArzneimittelWurdeVorbereitetDefiningcode(
      ) {
     return this.arzneimittelWurdeVorbereitetDefiningcode ;
  }

  public void setDosis(Cluster dosis) {
     this.dosis = dosis;
  }

  public Cluster getDosis() {
     return this.dosis ;
  }

  public void setRezeptNeuAusgestelltDefiningcode(
      RezeptNeuAusgestelltDefiningcode rezeptNeuAusgestelltDefiningcode) {
     this.rezeptNeuAusgestelltDefiningcode = rezeptNeuAusgestelltDefiningcode;
  }

  public RezeptNeuAusgestelltDefiningcode getRezeptNeuAusgestelltDefiningcode() {
     return this.rezeptNeuAusgestelltDefiningcode ;
  }

  public void setRezeptWurdeAusgefuhrtDefiningcode(
      RezeptWurdeAusgefuhrtDefiningcode rezeptWurdeAusgefuhrtDefiningcode) {
     this.rezeptWurdeAusgefuhrtDefiningcode = rezeptWurdeAusgefuhrtDefiningcode;
  }

  public RezeptWurdeAusgefuhrtDefiningcode getRezeptWurdeAusgefuhrtDefiningcode() {
     return this.rezeptWurdeAusgefuhrtDefiningcode ;
  }

  public void setEmpfohleneArzneimittelDefiningcode(
      EmpfohleneArzneimittelDefiningcode empfohleneArzneimittelDefiningcode) {
     this.empfohleneArzneimittelDefiningcode = empfohleneArzneimittelDefiningcode;
  }

  public EmpfohleneArzneimittelDefiningcode getEmpfohleneArzneimittelDefiningcode() {
     return this.empfohleneArzneimittelDefiningcode ;
  }

  public void setMedikamentenbehanlungWurdeVerschobenDefiningcodeCareflowStep(
      MedikamentenbehanlungWurdeVerschobenDefiningcode2 medikamentenbehanlungWurdeVerschobenDefiningcodeCareflowStep) {
     this.medikamentenbehanlungWurdeVerschobenDefiningcodeCareflowStep = medikamentenbehanlungWurdeVerschobenDefiningcodeCareflowStep;
  }

  public MedikamentenbehanlungWurdeVerschobenDefiningcode2 getMedikamentenbehanlungWurdeVerschobenDefiningcodeCareflowStep(
      ) {
     return this.medikamentenbehanlungWurdeVerschobenDefiningcodeCareflowStep ;
  }

  public void setTransitionDefiningcodeArzneimittelbehandlungHatBegonnen(
      TransitionDefiningcode transitionDefiningcodeArzneimittelbehandlungHatBegonnen) {
     this.transitionDefiningcodeArzneimittelbehandlungHatBegonnen = transitionDefiningcodeArzneimittelbehandlungHatBegonnen;
  }

  public TransitionDefiningcode getTransitionDefiningcodeArzneimittelbehandlungHatBegonnen() {
     return this.transitionDefiningcodeArzneimittelbehandlungHatBegonnen ;
  }

  public void setTransitionDefiningcodeArzneimittelWurdeNeuBewertet(
      TransitionDefiningcode transitionDefiningcodeArzneimittelWurdeNeuBewertet) {
     this.transitionDefiningcodeArzneimittelWurdeNeuBewertet = transitionDefiningcodeArzneimittelWurdeNeuBewertet;
  }

  public TransitionDefiningcode getTransitionDefiningcodeArzneimittelWurdeNeuBewertet() {
     return this.transitionDefiningcodeArzneimittelWurdeNeuBewertet ;
  }

  public void setArzneimittelWurdeNeuBewertetDefiningcodeCurrentState(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode arzneimittelWurdeNeuBewertetDefiningcodeCurrentState) {
     this.arzneimittelWurdeNeuBewertetDefiningcodeCurrentState = arzneimittelWurdeNeuBewertetDefiningcodeCurrentState;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getArzneimittelWurdeNeuBewertetDefiningcodeCurrentState(
      ) {
     return this.arzneimittelWurdeNeuBewertetDefiningcodeCurrentState ;
  }

  public void setTransitionDefiningcodeRezeptAusgestellt(
      TransitionDefiningcode transitionDefiningcodeRezeptAusgestellt) {
     this.transitionDefiningcodeRezeptAusgestellt = transitionDefiningcodeRezeptAusgestellt;
  }

  public TransitionDefiningcode getTransitionDefiningcodeRezeptAusgestellt() {
     return this.transitionDefiningcodeRezeptAusgestellt ;
  }

  public void setTransitionDefiningcodeDosisWurdeVerabreicht(
      TransitionDefiningcode transitionDefiningcodeDosisWurdeVerabreicht) {
     this.transitionDefiningcodeDosisWurdeVerabreicht = transitionDefiningcodeDosisWurdeVerabreicht;
  }

  public TransitionDefiningcode getTransitionDefiningcodeDosisWurdeVerabreicht() {
     return this.transitionDefiningcodeDosisWurdeVerabreicht ;
  }

  public void setVerabreichungEinerDosisWurdeAusgelassenDefiningcodeCareflowStep(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode2 verabreichungEinerDosisWurdeAusgelassenDefiningcodeCareflowStep) {
     this.verabreichungEinerDosisWurdeAusgelassenDefiningcodeCareflowStep = verabreichungEinerDosisWurdeAusgelassenDefiningcodeCareflowStep;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode2 getVerabreichungEinerDosisWurdeAusgelassenDefiningcodeCareflowStep(
      ) {
     return this.verabreichungEinerDosisWurdeAusgelassenDefiningcodeCareflowStep ;
  }

  public void setVerabreichungWurdeAusgesetztDefiningcodeCareflowStep(
      VerabreichungWurdeAusgesetztDefiningcode2 verabreichungWurdeAusgesetztDefiningcodeCareflowStep) {
     this.verabreichungWurdeAusgesetztDefiningcodeCareflowStep = verabreichungWurdeAusgesetztDefiningcodeCareflowStep;
  }

  public VerabreichungWurdeAusgesetztDefiningcode2 getVerabreichungWurdeAusgesetztDefiningcodeCareflowStep(
      ) {
     return this.verabreichungWurdeAusgesetztDefiningcodeCareflowStep ;
  }

  public void setTransitionDefiningcodeRezeptWurdeAusgegebenEingelost(
      TransitionDefiningcode transitionDefiningcodeRezeptWurdeAusgegebenEingelost) {
     this.transitionDefiningcodeRezeptWurdeAusgegebenEingelost = transitionDefiningcodeRezeptWurdeAusgegebenEingelost;
  }

  public TransitionDefiningcode getTransitionDefiningcodeRezeptWurdeAusgegebenEingelost() {
     return this.transitionDefiningcodeRezeptWurdeAusgegebenEingelost ;
  }

  public void setGrossereAnderungDerVerordnungDefiningcodeCareflowStep(
      GrossereAnderungDerVerordnungDefiningcode2 grossereAnderungDerVerordnungDefiningcodeCareflowStep) {
     this.grossereAnderungDerVerordnungDefiningcodeCareflowStep = grossereAnderungDerVerordnungDefiningcodeCareflowStep;
  }

  public GrossereAnderungDerVerordnungDefiningcode2 getGrossereAnderungDerVerordnungDefiningcodeCareflowStep(
      ) {
     return this.grossereAnderungDerVerordnungDefiningcodeCareflowStep ;
  }

  public void setRezeptWartetAufGenehmigungDefiningcodeCurrentState(
      RezeptWartetAufGenehmigungDefiningcode2 rezeptWartetAufGenehmigungDefiningcodeCurrentState) {
     this.rezeptWartetAufGenehmigungDefiningcodeCurrentState = rezeptWartetAufGenehmigungDefiningcodeCurrentState;
  }

  public RezeptWartetAufGenehmigungDefiningcode2 getRezeptWartetAufGenehmigungDefiningcodeCurrentState(
      ) {
     return this.rezeptWartetAufGenehmigungDefiningcodeCurrentState ;
  }

  public void setTransitionDefiningcodeGeringfugigeAnderungDerVerordnung(
      TransitionDefiningcode transitionDefiningcodeGeringfugigeAnderungDerVerordnung) {
     this.transitionDefiningcodeGeringfugigeAnderungDerVerordnung = transitionDefiningcodeGeringfugigeAnderungDerVerordnung;
  }

  public TransitionDefiningcode getTransitionDefiningcodeGeringfugigeAnderungDerVerordnung() {
     return this.transitionDefiningcodeGeringfugigeAnderungDerVerordnung ;
  }

  public void setRezeptWurdeAusgegebenEingelostDefiningcode(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode rezeptWurdeAusgegebenEingelostDefiningcode) {
     this.rezeptWurdeAusgegebenEingelostDefiningcode = rezeptWurdeAusgegebenEingelostDefiningcode;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getRezeptWurdeAusgegebenEingelostDefiningcode(
      ) {
     return this.rezeptWurdeAusgegebenEingelostDefiningcode ;
  }

  public void setArzneimittelStartdatumVoraussetzungDefiningcodeCareflowStep(
      ArzneimittelStartdatumVoraussetzungDefiningcode2 arzneimittelStartdatumVoraussetzungDefiningcodeCareflowStep) {
     this.arzneimittelStartdatumVoraussetzungDefiningcodeCareflowStep = arzneimittelStartdatumVoraussetzungDefiningcodeCareflowStep;
  }

  public ArzneimittelStartdatumVoraussetzungDefiningcode2 getArzneimittelStartdatumVoraussetzungDefiningcodeCareflowStep(
      ) {
     return this.arzneimittelStartdatumVoraussetzungDefiningcodeCareflowStep ;
  }

  public void setArzneimittelbehandlungHatBegonnenDefiningcodeCareflowStep(
      ArzneimittelbehandlungHatBegonnenDefiningcode arzneimittelbehandlungHatBegonnenDefiningcodeCareflowStep) {
     this.arzneimittelbehandlungHatBegonnenDefiningcodeCareflowStep = arzneimittelbehandlungHatBegonnenDefiningcodeCareflowStep;
  }

  public ArzneimittelbehandlungHatBegonnenDefiningcode getArzneimittelbehandlungHatBegonnenDefiningcodeCareflowStep(
      ) {
     return this.arzneimittelbehandlungHatBegonnenDefiningcodeCareflowStep ;
  }

  public void setTransitionDefiningcodeVerabreichungEinerDosisWurdeVerschoben(
      TransitionDefiningcode transitionDefiningcodeVerabreichungEinerDosisWurdeVerschoben) {
     this.transitionDefiningcodeVerabreichungEinerDosisWurdeVerschoben = transitionDefiningcodeVerabreichungEinerDosisWurdeVerschoben;
  }

  public TransitionDefiningcode getTransitionDefiningcodeVerabreichungEinerDosisWurdeVerschoben() {
     return this.transitionDefiningcodeVerabreichungEinerDosisWurdeVerschoben ;
  }

  public void setVerzogerungDerRezeptabgabeDefiningcodeCurrentState(
      VerabreichungWurdeAusgesetztDefiningcode verzogerungDerRezeptabgabeDefiningcodeCurrentState) {
     this.verzogerungDerRezeptabgabeDefiningcodeCurrentState = verzogerungDerRezeptabgabeDefiningcodeCurrentState;
  }

  public VerabreichungWurdeAusgesetztDefiningcode getVerzogerungDerRezeptabgabeDefiningcodeCurrentState(
      ) {
     return this.verzogerungDerRezeptabgabeDefiningcodeCurrentState ;
  }

  public void setArzneimittelbehanlungWurdeAbgesagtDefiningcodeCurrentState(
      RezeptWurdeWiderrufenDefiningcode arzneimittelbehanlungWurdeAbgesagtDefiningcodeCurrentState) {
     this.arzneimittelbehanlungWurdeAbgesagtDefiningcodeCurrentState = arzneimittelbehanlungWurdeAbgesagtDefiningcodeCurrentState;
  }

  public RezeptWurdeWiderrufenDefiningcode getArzneimittelbehanlungWurdeAbgesagtDefiningcodeCurrentState(
      ) {
     return this.arzneimittelbehanlungWurdeAbgesagtDefiningcodeCurrentState ;
  }

  public void setRezeptWurdeWiderrufenDefiningcodeCareflowStep(
      RezeptWurdeWiderrufenDefiningcode2 rezeptWurdeWiderrufenDefiningcodeCareflowStep) {
     this.rezeptWurdeWiderrufenDefiningcodeCareflowStep = rezeptWurdeWiderrufenDefiningcodeCareflowStep;
  }

  public RezeptWurdeWiderrufenDefiningcode2 getRezeptWurdeWiderrufenDefiningcodeCareflowStep() {
     return this.rezeptWurdeWiderrufenDefiningcodeCareflowStep ;
  }

  public void setRezeptIstReAutorisiertDefiningcodeCareflowStep(
      RezeptIstReAutorisiertDefiningcode rezeptIstReAutorisiertDefiningcodeCareflowStep) {
     this.rezeptIstReAutorisiertDefiningcodeCareflowStep = rezeptIstReAutorisiertDefiningcodeCareflowStep;
  }

  public RezeptIstReAutorisiertDefiningcode getRezeptIstReAutorisiertDefiningcodeCareflowStep() {
     return this.rezeptIstReAutorisiertDefiningcodeCareflowStep ;
  }

  public void setArzneimittelbehandlungIstAbgeschlossenDefiningcodeCurrentState(
      RezeptWurdeAusgefuhrtDefiningcode arzneimittelbehandlungIstAbgeschlossenDefiningcodeCurrentState) {
     this.arzneimittelbehandlungIstAbgeschlossenDefiningcodeCurrentState = arzneimittelbehandlungIstAbgeschlossenDefiningcodeCurrentState;
  }

  public RezeptWurdeAusgefuhrtDefiningcode getArzneimittelbehandlungIstAbgeschlossenDefiningcodeCurrentState(
      ) {
     return this.arzneimittelbehandlungIstAbgeschlossenDefiningcodeCurrentState ;
  }

  public void setRezeptAusgestelltDefiningcodeCareflowStep(
      RezeptAusgestelltDefiningcode rezeptAusgestelltDefiningcodeCareflowStep) {
     this.rezeptAusgestelltDefiningcodeCareflowStep = rezeptAusgestelltDefiningcodeCareflowStep;
  }

  public RezeptAusgestelltDefiningcode getRezeptAusgestelltDefiningcodeCareflowStep() {
     return this.rezeptAusgestelltDefiningcodeCareflowStep ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setTransitionDefiningcodeRezeptIstReAutorisiert(
      TransitionDefiningcode transitionDefiningcodeRezeptIstReAutorisiert) {
     this.transitionDefiningcodeRezeptIstReAutorisiert = transitionDefiningcodeRezeptIstReAutorisiert;
  }

  public TransitionDefiningcode getTransitionDefiningcodeRezeptIstReAutorisiert() {
     return this.transitionDefiningcodeRezeptIstReAutorisiert ;
  }

  public void setArzneimittelbehandlungGestopptDefiningcodeCareflowStep(
      ArzneimittelbehandlungGestopptDefiningcode arzneimittelbehandlungGestopptDefiningcodeCareflowStep) {
     this.arzneimittelbehandlungGestopptDefiningcodeCareflowStep = arzneimittelbehandlungGestopptDefiningcodeCareflowStep;
  }

  public ArzneimittelbehandlungGestopptDefiningcode getArzneimittelbehandlungGestopptDefiningcodeCareflowStep(
      ) {
     return this.arzneimittelbehandlungGestopptDefiningcodeCareflowStep ;
  }

  public void setZusatzlicheDetails(List<Cluster> zusatzlicheDetails) {
     this.zusatzlicheDetails = zusatzlicheDetails;
  }

  public List<Cluster> getZusatzlicheDetails() {
     return this.zusatzlicheDetails ;
  }

  public void setRezeptWurdeAusgegebenEingelostDefiningcodeCareflowStep(
      RezeptWurdeAusgegebenEingelostDefiningcode rezeptWurdeAusgegebenEingelostDefiningcodeCareflowStep) {
     this.rezeptWurdeAusgegebenEingelostDefiningcodeCareflowStep = rezeptWurdeAusgegebenEingelostDefiningcodeCareflowStep;
  }

  public RezeptWurdeAusgegebenEingelostDefiningcode getRezeptWurdeAusgegebenEingelostDefiningcodeCareflowStep(
      ) {
     return this.rezeptWurdeAusgegebenEingelostDefiningcodeCareflowStep ;
  }

  public void setGeringfugigeAnderungDerVerordnungDefiningcodeCurrentState(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode geringfugigeAnderungDerVerordnungDefiningcodeCurrentState) {
     this.geringfugigeAnderungDerVerordnungDefiningcodeCurrentState = geringfugigeAnderungDerVerordnungDefiningcodeCurrentState;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getGeringfugigeAnderungDerVerordnungDefiningcodeCurrentState(
      ) {
     return this.geringfugigeAnderungDerVerordnungDefiningcodeCurrentState ;
  }

  public void setTransitionDefiningcodeArzneimittelWurdeVorbereitet(
      TransitionDefiningcode transitionDefiningcodeArzneimittelWurdeVorbereitet) {
     this.transitionDefiningcodeArzneimittelWurdeVorbereitet = transitionDefiningcodeArzneimittelWurdeVorbereitet;
  }

  public TransitionDefiningcode getTransitionDefiningcodeArzneimittelWurdeVorbereitet() {
     return this.transitionDefiningcodeArzneimittelWurdeVorbereitet ;
  }

  public void setArzneimittelWurdeGenehmigtDefiningcodeCareflowStep(
      ArzneimittelWurdeGenehmigtDefiningcode arzneimittelWurdeGenehmigtDefiningcodeCareflowStep) {
     this.arzneimittelWurdeGenehmigtDefiningcodeCareflowStep = arzneimittelWurdeGenehmigtDefiningcodeCareflowStep;
  }

  public ArzneimittelWurdeGenehmigtDefiningcode getArzneimittelWurdeGenehmigtDefiningcodeCareflowStep(
      ) {
     return this.arzneimittelWurdeGenehmigtDefiningcodeCareflowStep ;
  }

  public void setReAutorisierugDesRezeptsAusstehendDefiningcodeCurrentState(
      VerabreichungWurdeAusgesetztDefiningcode reAutorisierugDesRezeptsAusstehendDefiningcodeCurrentState) {
     this.reAutorisierugDesRezeptsAusstehendDefiningcodeCurrentState = reAutorisierugDesRezeptsAusstehendDefiningcodeCurrentState;
  }

  public VerabreichungWurdeAusgesetztDefiningcode getReAutorisierugDesRezeptsAusstehendDefiningcodeCurrentState(
      ) {
     return this.reAutorisierugDesRezeptsAusstehendDefiningcodeCurrentState ;
  }

  public void setRezeptIstUngultigOderAbgelaufenDefiningcodeCareflowStep(
      RezeptIstUngultigOderAbgelaufenDefiningcode rezeptIstUngultigOderAbgelaufenDefiningcodeCareflowStep) {
     this.rezeptIstUngultigOderAbgelaufenDefiningcodeCareflowStep = rezeptIstUngultigOderAbgelaufenDefiningcodeCareflowStep;
  }

  public RezeptIstUngultigOderAbgelaufenDefiningcode getRezeptIstUngultigOderAbgelaufenDefiningcodeCareflowStep(
      ) {
     return this.rezeptIstUngultigOderAbgelaufenDefiningcodeCareflowStep ;
  }

  public void setTransitionDefiningcodeArzneimittelWurdeGenehmigt(
      TransitionDefiningcode transitionDefiningcodeArzneimittelWurdeGenehmigt) {
     this.transitionDefiningcodeArzneimittelWurdeGenehmigt = transitionDefiningcodeArzneimittelWurdeGenehmigt;
  }

  public TransitionDefiningcode getTransitionDefiningcodeArzneimittelWurdeGenehmigt() {
     return this.transitionDefiningcodeArzneimittelWurdeGenehmigt ;
  }

  public void setDosisWurdeVerabreichtDefiningcodeCurrentState(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode dosisWurdeVerabreichtDefiningcodeCurrentState) {
     this.dosisWurdeVerabreichtDefiningcodeCurrentState = dosisWurdeVerabreichtDefiningcodeCurrentState;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getDosisWurdeVerabreichtDefiningcodeCurrentState(
      ) {
     return this.dosisWurdeVerabreichtDefiningcodeCurrentState ;
  }

  public void setTransitionDefiningcodeRezeptWurdeAusgefuhrt(
      TransitionDefiningcode transitionDefiningcodeRezeptWurdeAusgefuhrt) {
     this.transitionDefiningcodeRezeptWurdeAusgefuhrt = transitionDefiningcodeRezeptWurdeAusgefuhrt;
  }

  public TransitionDefiningcode getTransitionDefiningcodeRezeptWurdeAusgefuhrt() {
     return this.transitionDefiningcodeRezeptWurdeAusgefuhrt ;
  }

  public void setArzneimittelWurdeVorbereitetDefiningcodeCareflowStep(
      ArzneimittelWurdeVorbereitetDefiningcode arzneimittelWurdeVorbereitetDefiningcodeCareflowStep) {
     this.arzneimittelWurdeVorbereitetDefiningcodeCareflowStep = arzneimittelWurdeVorbereitetDefiningcodeCareflowStep;
  }

  public ArzneimittelWurdeVorbereitetDefiningcode getArzneimittelWurdeVorbereitetDefiningcodeCareflowStep(
      ) {
     return this.arzneimittelWurdeVorbereitetDefiningcodeCareflowStep ;
  }

  public void setVerabreichungEinerDosisWurdeVerschobenDefiningcodeCareflowStep(
      VerabreichungEinerDosisWurdeVerschobenDefiningcode verabreichungEinerDosisWurdeVerschobenDefiningcodeCareflowStep) {
     this.verabreichungEinerDosisWurdeVerschobenDefiningcodeCareflowStep = verabreichungEinerDosisWurdeVerschobenDefiningcodeCareflowStep;
  }

  public VerabreichungEinerDosisWurdeVerschobenDefiningcode getVerabreichungEinerDosisWurdeVerschobenDefiningcodeCareflowStep(
      ) {
     return this.verabreichungEinerDosisWurdeVerschobenDefiningcodeCareflowStep ;
  }

  public void setVergangeneImpfungSeitOktober2019Value(
      Boolean vergangeneImpfungSeitOktober2019Value) {
     this.vergangeneImpfungSeitOktober2019Value = vergangeneImpfungSeitOktober2019Value;
  }

  public Boolean isVergangeneImpfungSeitOktober2019Value() {
     return this.vergangeneImpfungSeitOktober2019Value ;
  }

  public void setTransitionDefiningcodeVerzogerungDerRezeptabgabe(
      TransitionDefiningcode transitionDefiningcodeVerzogerungDerRezeptabgabe) {
     this.transitionDefiningcodeVerzogerungDerRezeptabgabe = transitionDefiningcodeVerzogerungDerRezeptabgabe;
  }

  public TransitionDefiningcode getTransitionDefiningcodeVerzogerungDerRezeptabgabe() {
     return this.transitionDefiningcodeVerzogerungDerRezeptabgabe ;
  }

  public void setTransitionDefiningcodeVerabreichungWurdeAusgesetzt(
      TransitionDefiningcode transitionDefiningcodeVerabreichungWurdeAusgesetzt) {
     this.transitionDefiningcodeVerabreichungWurdeAusgesetzt = transitionDefiningcodeVerabreichungWurdeAusgesetzt;
  }

  public TransitionDefiningcode getTransitionDefiningcodeVerabreichungWurdeAusgesetzt() {
     return this.transitionDefiningcodeVerabreichungWurdeAusgesetzt ;
  }

  public void setEmpfohleneArzneimittelDefiningcodeCurrentState(
      EmpfohleneArzneimittelDefiningcode2 empfohleneArzneimittelDefiningcodeCurrentState) {
     this.empfohleneArzneimittelDefiningcodeCurrentState = empfohleneArzneimittelDefiningcodeCurrentState;
  }

  public EmpfohleneArzneimittelDefiningcode2 getEmpfohleneArzneimittelDefiningcodeCurrentState() {
     return this.empfohleneArzneimittelDefiningcodeCurrentState ;
  }

  public void setTransitionDefiningcodeRezeptWartetAufGenehmigung(
      TransitionDefiningcode transitionDefiningcodeRezeptWartetAufGenehmigung) {
     this.transitionDefiningcodeRezeptWartetAufGenehmigung = transitionDefiningcodeRezeptWartetAufGenehmigung;
  }

  public TransitionDefiningcode getTransitionDefiningcodeRezeptWartetAufGenehmigung() {
     return this.transitionDefiningcodeRezeptWartetAufGenehmigung ;
  }

  public void setTransitionDefiningcodeGrossereAnderungDerVerordnung(
      TransitionDefiningcode transitionDefiningcodeGrossereAnderungDerVerordnung) {
     this.transitionDefiningcodeGrossereAnderungDerVerordnung = transitionDefiningcodeGrossereAnderungDerVerordnung;
  }

  public TransitionDefiningcode getTransitionDefiningcodeGrossereAnderungDerVerordnung() {
     return this.transitionDefiningcodeGrossereAnderungDerVerordnung ;
  }

  public void setTransitionDefiningcodeArzneimittelbehandlungIstAbgeschlossen(
      TransitionDefiningcode transitionDefiningcodeArzneimittelbehandlungIstAbgeschlossen) {
     this.transitionDefiningcodeArzneimittelbehandlungIstAbgeschlossen = transitionDefiningcodeArzneimittelbehandlungIstAbgeschlossen;
  }

  public TransitionDefiningcode getTransitionDefiningcodeArzneimittelbehandlungIstAbgeschlossen() {
     return this.transitionDefiningcodeArzneimittelbehandlungIstAbgeschlossen ;
  }

  public void setTransitionDefiningcodeVerabreichungEinerDosisWurdeAusgelassen(
      TransitionDefiningcode transitionDefiningcodeVerabreichungEinerDosisWurdeAusgelassen) {
     this.transitionDefiningcodeVerabreichungEinerDosisWurdeAusgelassen = transitionDefiningcodeVerabreichungEinerDosisWurdeAusgelassen;
  }

  public TransitionDefiningcode getTransitionDefiningcodeVerabreichungEinerDosisWurdeAusgelassen() {
     return this.transitionDefiningcodeVerabreichungEinerDosisWurdeAusgelassen ;
  }

  public void setRezeptNeuAusgestelltDefiningcodeCurrentState(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode rezeptNeuAusgestelltDefiningcodeCurrentState) {
     this.rezeptNeuAusgestelltDefiningcodeCurrentState = rezeptNeuAusgestelltDefiningcodeCurrentState;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getRezeptNeuAusgestelltDefiningcodeCurrentState(
      ) {
     return this.rezeptNeuAusgestelltDefiningcodeCurrentState ;
  }

  public void setRezeptWurdeAusgefuhrtDefiningcodeCareflowStep(
      RezeptWurdeAusgefuhrtDefiningcode2 rezeptWurdeAusgefuhrtDefiningcodeCareflowStep) {
     this.rezeptWurdeAusgefuhrtDefiningcodeCareflowStep = rezeptWurdeAusgefuhrtDefiningcodeCareflowStep;
  }

  public RezeptWurdeAusgefuhrtDefiningcode2 getRezeptWurdeAusgefuhrtDefiningcodeCareflowStep() {
     return this.rezeptWurdeAusgefuhrtDefiningcodeCareflowStep ;
  }
}
