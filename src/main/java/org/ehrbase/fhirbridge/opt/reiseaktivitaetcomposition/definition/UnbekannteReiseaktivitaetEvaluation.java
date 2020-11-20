package org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.absence.v2")
public class UnbekannteReiseaktivitaetEvaluation {
  @Path("/data[at0001]/items[at0005]/value|defining_code")
  private AussageUberDieFehlendeInformationDefiningcode aussageUberDieFehlendeInformationDefiningcode;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private FehlendeInformationDefiningcode fehlendeInformationDefiningcode;

  @Path("/protocol[at0003]/items[at0006]")
  private List<Cluster> erweiterung;

  @Path("/language")
  private Language language;

  public void setAussageUberDieFehlendeInformationDefiningcode(
      AussageUberDieFehlendeInformationDefiningcode aussageUberDieFehlendeInformationDefiningcode) {
     this.aussageUberDieFehlendeInformationDefiningcode = aussageUberDieFehlendeInformationDefiningcode;
  }

  public AussageUberDieFehlendeInformationDefiningcode getAussageUberDieFehlendeInformationDefiningcode(
      ) {
     return this.aussageUberDieFehlendeInformationDefiningcode ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setFehlendeInformationDefiningcode(
      FehlendeInformationDefiningcode fehlendeInformationDefiningcode) {
     this.fehlendeInformationDefiningcode = fehlendeInformationDefiningcode;
  }

  public FehlendeInformationDefiningcode getFehlendeInformationDefiningcode() {
     return this.fehlendeInformationDefiningcode ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }
}
