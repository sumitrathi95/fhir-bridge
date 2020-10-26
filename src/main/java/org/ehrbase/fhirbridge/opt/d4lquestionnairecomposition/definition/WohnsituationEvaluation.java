package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.living_arrangement.v0")
public class WohnsituationEvaluation {
  @Path("/data[at0001]/items[at0004]/value|defining_code")
  private WohnsituationDefiningcode wohnsituationDefiningcode;

  @Path("/data[at0001]/items[at0008]")
  private List<Cluster> erganzendeDetails;

  @Path("/protocol[at0002]/items[at0011]")
  private List<Cluster> erweiterung;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  public void setWohnsituationDefiningcode(WohnsituationDefiningcode wohnsituationDefiningcode) {
     this.wohnsituationDefiningcode = wohnsituationDefiningcode;
  }

  public WohnsituationDefiningcode getWohnsituationDefiningcode() {
     return this.wohnsituationDefiningcode ;
  }

  public void setErganzendeDetails(List<Cluster> erganzendeDetails) {
     this.erganzendeDetails = erganzendeDetails;
  }

  public List<Cluster> getErganzendeDetails() {
     return this.erganzendeDetails ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
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
}
