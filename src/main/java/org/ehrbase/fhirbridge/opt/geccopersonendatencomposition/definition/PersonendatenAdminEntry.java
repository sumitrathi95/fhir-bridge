package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-ADMIN_ENTRY.person_data.v0")
public class PersonendatenAdminEntry {
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.address_cc.v0]")
  private List<AdresseCluster> adresse;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.ethnischer_hintergrund.v0]")
  private List<EthnischerHintergrundCluster> ethnischerHintergrund;

  @Path("/data[at0001]/items[at0024]/items[at0025]/value|value")
  private Boolean verstorbenValue;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.person_name.v0]")
  private List<PersonennameCluster> personenname;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.telecom_details.v0]")
  private List<EinzelheitenDerKommunikationCluster> einzelheitenDerKommunikation;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/items[at0024]/items[openEHR-EHR-CLUSTER.death_details.v1]")
  private AngabenZumTodCluster angabenZumTod;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.person_birth_data_iso.v0]")
  private DatenZurGeburtCluster datenZurGeburt;

  public void setAdresse(List<AdresseCluster> adresse) {
     this.adresse = adresse;
  }

  public List<AdresseCluster> getAdresse() {
     return this.adresse ;
  }

  public void setEthnischerHintergrund(List<EthnischerHintergrundCluster> ethnischerHintergrund) {
     this.ethnischerHintergrund = ethnischerHintergrund;
  }

  public List<EthnischerHintergrundCluster> getEthnischerHintergrund() {
     return this.ethnischerHintergrund ;
  }

  public void setVerstorbenValue(Boolean verstorbenValue) {
     this.verstorbenValue = verstorbenValue;
  }

  public Boolean isVerstorbenValue() {
     return this.verstorbenValue ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setPersonenname(List<PersonennameCluster> personenname) {
     this.personenname = personenname;
  }

  public List<PersonennameCluster> getPersonenname() {
     return this.personenname ;
  }

  public void setEinzelheitenDerKommunikation(
      List<EinzelheitenDerKommunikationCluster> einzelheitenDerKommunikation) {
     this.einzelheitenDerKommunikation = einzelheitenDerKommunikation;
  }

  public List<EinzelheitenDerKommunikationCluster> getEinzelheitenDerKommunikation() {
     return this.einzelheitenDerKommunikation ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setAngabenZumTod(AngabenZumTodCluster angabenZumTod) {
     this.angabenZumTod = angabenZumTod;
  }

  public AngabenZumTodCluster getAngabenZumTod() {
     return this.angabenZumTod ;
  }

  public void setDatenZurGeburt(DatenZurGeburtCluster datenZurGeburt) {
     this.datenZurGeburt = datenZurGeburt;
  }

  public DatenZurGeburtCluster getDatenZurGeburt() {
     return this.datenZurGeburt ;
  }
}
