package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.TransitionDefiningcode;

@Entity
@Archetype("openEHR-EHR-ACTION.informed_consent.v0")
public class EinwilligungserklarungAction {
  @Path("/ism_transition[at0013]/careflow_step|defining_code")
  private GeplantDefiningcode geplantDefiningcode;

  @Path("/ism_transition[at0015]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcode;

  @Path("/ism_transition[at0013]/current_state|defining_code")
  private EmpfohleneArzneimittelDefiningcode2 geplantDefiningcodeCurrentState;

  @Path("/ism_transition[at0021]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode einwilligungNichtErhaltenDefiningcode;

  @Path("/ism_transition[at0013]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeGeplant;

  @Path("/ism_transition[at0017]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeEinwilligungWiderrufen;

  @Path("/ism_transition[at0018]/current_state|defining_code")
  private RezeptWurdeWiderrufenDefiningcode abgesagtDefiningcode;

  @Path("/ism_transition[at0018]/careflow_step|defining_code")
  private AbgesagtDefiningcode abgesagtDefiningcodeCareflowStep;

  @Path("/ism_transition[at0016]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeEinwilligungVerweigert;

  @Path("/ism_transition[at0027]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeTerminGeplant;

  @Path("/protocol[at0024]/items[at0028]")
  private List<Cluster> anfordererDerEinwilligungserklarung;

  @Path("/ism_transition[at0022]/current_state|defining_code")
  private RezeptWurdeAusgefuhrtDefiningcode abgeschlossenDefiningcode;

  @Path("/description[at0001]/items[at0011]/value|value")
  private String beschreibungDerEinwilligungValue;

  @Path("/ism_transition[at0014]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeEinwilligungErbeten;

  @Path("/ism_transition[at0022]/careflow_step|defining_code")
  private AbgeschlossenDefiningcode abgeschlossenDefiningcodeCareflowStep;

  @Path("/ism_transition[at0017]/current_state|defining_code")
  private GrossereAnderungDerVerordnungDefiningcode einwilligungWiderrufenDefiningcode;

  @Path("/description[at0001]/items[at0002]/value|value")
  private String prozedurStudieAktivitatValue;

  @Path("/ism_transition[at0017]/careflow_step|defining_code")
  private EinwilligungWiderrufenDefiningcode einwilligungWiderrufenDefiningcodeCareflowStep;

  @Path("/ism_transition[at0016]/current_state|defining_code")
  private GrossereAnderungDerVerordnungDefiningcode einwilligungVerweigertDefiningcode;

  @Path("/ism_transition[at0016]/careflow_step|defining_code")
  private EinwilligungVerweigertDefiningcode einwilligungVerweigertDefiningcodeCareflowStep;

  @Path("/ism_transition[at0022]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeAbgeschlossen;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/ism_transition[at0021]/careflow_step|defining_code")
  private EinwilligungNichtErhaltenDefiningcode einwilligungNichtErhaltenDefiningcodeCareflowStep;

  @Path("/ism_transition[at0021]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeEinwilligungNichtErhalten;

  @Path("/description[at0001]/items[openEHR-EHR-CLUSTER.address.v0]")
  private AdresseCluster adresse;

  @Path("/language")
  private Language language;

  @Path("/ism_transition[at0015]/careflow_step|defining_code")
  private EinwilligungErteiltDefiningcode einwilligungErteiltDefiningcode;

  @Path("/ism_transition[at0015]/current_state|defining_code")
  private VerabreichungEinerDosisWurdeAusgelassenDefiningcode einwilligungErteiltDefiningcodeCurrentState;

  @Path("/protocol[at0024]/items[at0029]")
  private List<Cluster> einwilligendePerson;

  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/ism_transition[at0019]/current_state|defining_code")
  private MedikamentenbehanlungWurdeVerschobenDefiningcode verschobenDefiningcode;

  @Path("/ism_transition[at0019]/careflow_step|defining_code")
  private VerschobenDefiningcode verschobenDefiningcodeCareflowStep;

  @Path("/ism_transition[at0014]/current_state|defining_code")
  private RezeptWartetAufGenehmigungDefiningcode2 einwilligungErbetenDefiningcode;

  @Path("/ism_transition[at0014]/careflow_step|defining_code")
  private EinwilligungErbetenDefiningcode einwilligungErbetenDefiningcodeCareflowStep;

  @Path("/ism_transition[at0019]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeVerschoben;

  @Path("/ism_transition[at0027]/careflow_step|defining_code")
  private TerminGeplantDefiningcode terminGeplantDefiningcode;

  @Path("/ism_transition[at0018]/transition|defining_code")
  private TransitionDefiningcode transitionDefiningcodeAbgesagt;

  @Path("/ism_transition[at0027]/current_state|defining_code")
  private ArzneimittelStartdatumVoraussetzungDefiningcode terminGeplantDefiningcodeCurrentState;

  public void setGeplantDefiningcode(GeplantDefiningcode geplantDefiningcode) {
     this.geplantDefiningcode = geplantDefiningcode;
  }

  public GeplantDefiningcode getGeplantDefiningcode() {
     return this.geplantDefiningcode ;
  }

  public void setTransitionDefiningcode(TransitionDefiningcode transitionDefiningcode) {
     this.transitionDefiningcode = transitionDefiningcode;
  }

  public TransitionDefiningcode getTransitionDefiningcode() {
     return this.transitionDefiningcode ;
  }

  public void setGeplantDefiningcodeCurrentState(
      EmpfohleneArzneimittelDefiningcode2 geplantDefiningcodeCurrentState) {
     this.geplantDefiningcodeCurrentState = geplantDefiningcodeCurrentState;
  }

  public EmpfohleneArzneimittelDefiningcode2 getGeplantDefiningcodeCurrentState() {
     return this.geplantDefiningcodeCurrentState ;
  }

  public void setEinwilligungNichtErhaltenDefiningcode(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode einwilligungNichtErhaltenDefiningcode) {
     this.einwilligungNichtErhaltenDefiningcode = einwilligungNichtErhaltenDefiningcode;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getEinwilligungNichtErhaltenDefiningcode(
      ) {
     return this.einwilligungNichtErhaltenDefiningcode ;
  }

  public void setTransitionDefiningcodeGeplant(
      TransitionDefiningcode transitionDefiningcodeGeplant) {
     this.transitionDefiningcodeGeplant = transitionDefiningcodeGeplant;
  }

  public TransitionDefiningcode getTransitionDefiningcodeGeplant() {
     return this.transitionDefiningcodeGeplant ;
  }

  public void setTransitionDefiningcodeEinwilligungWiderrufen(
      TransitionDefiningcode transitionDefiningcodeEinwilligungWiderrufen) {
     this.transitionDefiningcodeEinwilligungWiderrufen = transitionDefiningcodeEinwilligungWiderrufen;
  }

  public TransitionDefiningcode getTransitionDefiningcodeEinwilligungWiderrufen() {
     return this.transitionDefiningcodeEinwilligungWiderrufen ;
  }

  public void setAbgesagtDefiningcode(RezeptWurdeWiderrufenDefiningcode abgesagtDefiningcode) {
     this.abgesagtDefiningcode = abgesagtDefiningcode;
  }

  public RezeptWurdeWiderrufenDefiningcode getAbgesagtDefiningcode() {
     return this.abgesagtDefiningcode ;
  }

  public void setAbgesagtDefiningcodeCareflowStep(
      AbgesagtDefiningcode abgesagtDefiningcodeCareflowStep) {
     this.abgesagtDefiningcodeCareflowStep = abgesagtDefiningcodeCareflowStep;
  }

  public AbgesagtDefiningcode getAbgesagtDefiningcodeCareflowStep() {
     return this.abgesagtDefiningcodeCareflowStep ;
  }

  public void setTransitionDefiningcodeEinwilligungVerweigert(
      TransitionDefiningcode transitionDefiningcodeEinwilligungVerweigert) {
     this.transitionDefiningcodeEinwilligungVerweigert = transitionDefiningcodeEinwilligungVerweigert;
  }

  public TransitionDefiningcode getTransitionDefiningcodeEinwilligungVerweigert() {
     return this.transitionDefiningcodeEinwilligungVerweigert ;
  }

  public void setTransitionDefiningcodeTerminGeplant(
      TransitionDefiningcode transitionDefiningcodeTerminGeplant) {
     this.transitionDefiningcodeTerminGeplant = transitionDefiningcodeTerminGeplant;
  }

  public TransitionDefiningcode getTransitionDefiningcodeTerminGeplant() {
     return this.transitionDefiningcodeTerminGeplant ;
  }

  public void setAnfordererDerEinwilligungserklarung(
      List<Cluster> anfordererDerEinwilligungserklarung) {
     this.anfordererDerEinwilligungserklarung = anfordererDerEinwilligungserklarung;
  }

  public List<Cluster> getAnfordererDerEinwilligungserklarung() {
     return this.anfordererDerEinwilligungserklarung ;
  }

  public void setAbgeschlossenDefiningcode(
      RezeptWurdeAusgefuhrtDefiningcode abgeschlossenDefiningcode) {
     this.abgeschlossenDefiningcode = abgeschlossenDefiningcode;
  }

  public RezeptWurdeAusgefuhrtDefiningcode getAbgeschlossenDefiningcode() {
     return this.abgeschlossenDefiningcode ;
  }

  public void setBeschreibungDerEinwilligungValue(String beschreibungDerEinwilligungValue) {
     this.beschreibungDerEinwilligungValue = beschreibungDerEinwilligungValue;
  }

  public String getBeschreibungDerEinwilligungValue() {
     return this.beschreibungDerEinwilligungValue ;
  }

  public void setTransitionDefiningcodeEinwilligungErbeten(
      TransitionDefiningcode transitionDefiningcodeEinwilligungErbeten) {
     this.transitionDefiningcodeEinwilligungErbeten = transitionDefiningcodeEinwilligungErbeten;
  }

  public TransitionDefiningcode getTransitionDefiningcodeEinwilligungErbeten() {
     return this.transitionDefiningcodeEinwilligungErbeten ;
  }

  public void setAbgeschlossenDefiningcodeCareflowStep(
      AbgeschlossenDefiningcode abgeschlossenDefiningcodeCareflowStep) {
     this.abgeschlossenDefiningcodeCareflowStep = abgeschlossenDefiningcodeCareflowStep;
  }

  public AbgeschlossenDefiningcode getAbgeschlossenDefiningcodeCareflowStep() {
     return this.abgeschlossenDefiningcodeCareflowStep ;
  }

  public void setEinwilligungWiderrufenDefiningcode(
      GrossereAnderungDerVerordnungDefiningcode einwilligungWiderrufenDefiningcode) {
     this.einwilligungWiderrufenDefiningcode = einwilligungWiderrufenDefiningcode;
  }

  public GrossereAnderungDerVerordnungDefiningcode getEinwilligungWiderrufenDefiningcode() {
     return this.einwilligungWiderrufenDefiningcode ;
  }

  public void setProzedurStudieAktivitatValue(String prozedurStudieAktivitatValue) {
     this.prozedurStudieAktivitatValue = prozedurStudieAktivitatValue;
  }

  public String getProzedurStudieAktivitatValue() {
     return this.prozedurStudieAktivitatValue ;
  }

  public void setEinwilligungWiderrufenDefiningcodeCareflowStep(
      EinwilligungWiderrufenDefiningcode einwilligungWiderrufenDefiningcodeCareflowStep) {
     this.einwilligungWiderrufenDefiningcodeCareflowStep = einwilligungWiderrufenDefiningcodeCareflowStep;
  }

  public EinwilligungWiderrufenDefiningcode getEinwilligungWiderrufenDefiningcodeCareflowStep() {
     return this.einwilligungWiderrufenDefiningcodeCareflowStep ;
  }

  public void setEinwilligungVerweigertDefiningcode(
      GrossereAnderungDerVerordnungDefiningcode einwilligungVerweigertDefiningcode) {
     this.einwilligungVerweigertDefiningcode = einwilligungVerweigertDefiningcode;
  }

  public GrossereAnderungDerVerordnungDefiningcode getEinwilligungVerweigertDefiningcode() {
     return this.einwilligungVerweigertDefiningcode ;
  }

  public void setEinwilligungVerweigertDefiningcodeCareflowStep(
      EinwilligungVerweigertDefiningcode einwilligungVerweigertDefiningcodeCareflowStep) {
     this.einwilligungVerweigertDefiningcodeCareflowStep = einwilligungVerweigertDefiningcodeCareflowStep;
  }

  public EinwilligungVerweigertDefiningcode getEinwilligungVerweigertDefiningcodeCareflowStep() {
     return this.einwilligungVerweigertDefiningcodeCareflowStep ;
  }

  public void setTransitionDefiningcodeAbgeschlossen(
      TransitionDefiningcode transitionDefiningcodeAbgeschlossen) {
     this.transitionDefiningcodeAbgeschlossen = transitionDefiningcodeAbgeschlossen;
  }

  public TransitionDefiningcode getTransitionDefiningcodeAbgeschlossen() {
     return this.transitionDefiningcodeAbgeschlossen ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setEinwilligungNichtErhaltenDefiningcodeCareflowStep(
      EinwilligungNichtErhaltenDefiningcode einwilligungNichtErhaltenDefiningcodeCareflowStep) {
     this.einwilligungNichtErhaltenDefiningcodeCareflowStep = einwilligungNichtErhaltenDefiningcodeCareflowStep;
  }

  public EinwilligungNichtErhaltenDefiningcode getEinwilligungNichtErhaltenDefiningcodeCareflowStep(
      ) {
     return this.einwilligungNichtErhaltenDefiningcodeCareflowStep ;
  }

  public void setTransitionDefiningcodeEinwilligungNichtErhalten(
      TransitionDefiningcode transitionDefiningcodeEinwilligungNichtErhalten) {
     this.transitionDefiningcodeEinwilligungNichtErhalten = transitionDefiningcodeEinwilligungNichtErhalten;
  }

  public TransitionDefiningcode getTransitionDefiningcodeEinwilligungNichtErhalten() {
     return this.transitionDefiningcodeEinwilligungNichtErhalten ;
  }

  public void setAdresse(AdresseCluster adresse) {
     this.adresse = adresse;
  }

  public AdresseCluster getAdresse() {
     return this.adresse ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setEinwilligungErteiltDefiningcode(
      EinwilligungErteiltDefiningcode einwilligungErteiltDefiningcode) {
     this.einwilligungErteiltDefiningcode = einwilligungErteiltDefiningcode;
  }

  public EinwilligungErteiltDefiningcode getEinwilligungErteiltDefiningcode() {
     return this.einwilligungErteiltDefiningcode ;
  }

  public void setEinwilligungErteiltDefiningcodeCurrentState(
      VerabreichungEinerDosisWurdeAusgelassenDefiningcode einwilligungErteiltDefiningcodeCurrentState) {
     this.einwilligungErteiltDefiningcodeCurrentState = einwilligungErteiltDefiningcodeCurrentState;
  }

  public VerabreichungEinerDosisWurdeAusgelassenDefiningcode getEinwilligungErteiltDefiningcodeCurrentState(
      ) {
     return this.einwilligungErteiltDefiningcodeCurrentState ;
  }

  public void setEinwilligendePerson(List<Cluster> einwilligendePerson) {
     this.einwilligendePerson = einwilligendePerson;
  }

  public List<Cluster> getEinwilligendePerson() {
     return this.einwilligendePerson ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setVerschobenDefiningcode(
      MedikamentenbehanlungWurdeVerschobenDefiningcode verschobenDefiningcode) {
     this.verschobenDefiningcode = verschobenDefiningcode;
  }

  public MedikamentenbehanlungWurdeVerschobenDefiningcode getVerschobenDefiningcode() {
     return this.verschobenDefiningcode ;
  }

  public void setVerschobenDefiningcodeCareflowStep(
      VerschobenDefiningcode verschobenDefiningcodeCareflowStep) {
     this.verschobenDefiningcodeCareflowStep = verschobenDefiningcodeCareflowStep;
  }

  public VerschobenDefiningcode getVerschobenDefiningcodeCareflowStep() {
     return this.verschobenDefiningcodeCareflowStep ;
  }

  public void setEinwilligungErbetenDefiningcode(
      RezeptWartetAufGenehmigungDefiningcode2 einwilligungErbetenDefiningcode) {
     this.einwilligungErbetenDefiningcode = einwilligungErbetenDefiningcode;
  }

  public RezeptWartetAufGenehmigungDefiningcode2 getEinwilligungErbetenDefiningcode() {
     return this.einwilligungErbetenDefiningcode ;
  }

  public void setEinwilligungErbetenDefiningcodeCareflowStep(
      EinwilligungErbetenDefiningcode einwilligungErbetenDefiningcodeCareflowStep) {
     this.einwilligungErbetenDefiningcodeCareflowStep = einwilligungErbetenDefiningcodeCareflowStep;
  }

  public EinwilligungErbetenDefiningcode getEinwilligungErbetenDefiningcodeCareflowStep() {
     return this.einwilligungErbetenDefiningcodeCareflowStep ;
  }

  public void setTransitionDefiningcodeVerschoben(
      TransitionDefiningcode transitionDefiningcodeVerschoben) {
     this.transitionDefiningcodeVerschoben = transitionDefiningcodeVerschoben;
  }

  public TransitionDefiningcode getTransitionDefiningcodeVerschoben() {
     return this.transitionDefiningcodeVerschoben ;
  }

  public void setTerminGeplantDefiningcode(TerminGeplantDefiningcode terminGeplantDefiningcode) {
     this.terminGeplantDefiningcode = terminGeplantDefiningcode;
  }

  public TerminGeplantDefiningcode getTerminGeplantDefiningcode() {
     return this.terminGeplantDefiningcode ;
  }

  public void setTransitionDefiningcodeAbgesagt(
      TransitionDefiningcode transitionDefiningcodeAbgesagt) {
     this.transitionDefiningcodeAbgesagt = transitionDefiningcodeAbgesagt;
  }

  public TransitionDefiningcode getTransitionDefiningcodeAbgesagt() {
     return this.transitionDefiningcodeAbgesagt ;
  }

  public void setTerminGeplantDefiningcodeCurrentState(
      ArzneimittelStartdatumVoraussetzungDefiningcode terminGeplantDefiningcodeCurrentState) {
     this.terminGeplantDefiningcodeCurrentState = terminGeplantDefiningcodeCurrentState;
  }

  public ArzneimittelStartdatumVoraussetzungDefiningcode getTerminGeplantDefiningcodeCurrentState(
      ) {
     return this.terminGeplantDefiningcodeCurrentState ;
  }
}
