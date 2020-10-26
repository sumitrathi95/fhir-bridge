package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.pregnancy_bf_status.v0")
public class StatusSchwangerschaftStillzeitEvaluation {
  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/items[at0002]/value|value")
  private Boolean schwangerValue;

  @Path("/language")
  private Language language;

  @Path("/protocol[at0004]/items[at0006]")
  private List<Cluster> erweiterung;

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setSchwangerValue(Boolean schwangerValue) {
     this.schwangerValue = schwangerValue;
  }

  public Boolean isSchwangerValue() {
     return this.schwangerValue ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }
}
