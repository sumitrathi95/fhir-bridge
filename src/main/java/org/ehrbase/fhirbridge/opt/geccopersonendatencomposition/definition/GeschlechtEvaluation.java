package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.gender.v1")
public class GeschlechtEvaluation {
  @Path("/data[at0002]/items[at0022]/value|defining_code")
  private AdministrativesGeschlechtDefiningcode administrativesGeschlechtDefiningcode;

  @Path("/data[at0002]/items[at0023]")
  private List<Cluster> zusatzlicheDetails;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  @Path("/data[at0002]/items[at0019]/value|defining_code")
  private GeschlechtBeiDerGeburtDefiningcode geschlechtBeiDerGeburtDefiningcode;

  @Path("/protocol[at0003]/items[at0005]")
  private List<Cluster> erweiterung;

  public void setAdministrativesGeschlechtDefiningcode(
      AdministrativesGeschlechtDefiningcode administrativesGeschlechtDefiningcode) {
     this.administrativesGeschlechtDefiningcode = administrativesGeschlechtDefiningcode;
  }

  public AdministrativesGeschlechtDefiningcode getAdministrativesGeschlechtDefiningcode() {
     return this.administrativesGeschlechtDefiningcode ;
  }

  public void setZusatzlicheDetails(List<Cluster> zusatzlicheDetails) {
     this.zusatzlicheDetails = zusatzlicheDetails;
  }

  public List<Cluster> getZusatzlicheDetails() {
     return this.zusatzlicheDetails ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setGeschlechtBeiDerGeburtDefiningcode(
      GeschlechtBeiDerGeburtDefiningcode geschlechtBeiDerGeburtDefiningcode) {
     this.geschlechtBeiDerGeburtDefiningcode = geschlechtBeiDerGeburtDefiningcode;
  }

  public GeschlechtBeiDerGeburtDefiningcode getGeschlechtBeiDerGeburtDefiningcode() {
     return this.geschlechtBeiDerGeburtDefiningcode ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }
}
