package org.ehrbase.fhirbridge.opt.symptomcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.AussageUberDenAusschlussDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.exclusion_specific.v1")
public class AusgeschlossenesSymptomEvaluation {
  @Path("/protocol[at0009]/items[at0011]")
  private List<Cluster> erweiterung;

  @Path("/data[at0001]/items[at0003]/value|defining_code")
  private org.ehrbase.fhirbridge.opt.shareddefinition.DiagnoseDefiningcode diagnoseDefiningcode;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private AussageUberDenAusschlussDefiningcode aussageUberDenAusschlussDefiningcode;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[at0003]/name|defining_code")
  private DiagnoseDefiningcode diagnoseDefiningcodeProblem;

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setDiagnoseDefiningcode(
      org.ehrbase.fhirbridge.opt.shareddefinition.DiagnoseDefiningcode diagnoseDefiningcode) {
     this.diagnoseDefiningcode = diagnoseDefiningcode;
  }

  public org.ehrbase.fhirbridge.opt.shareddefinition.DiagnoseDefiningcode getDiagnoseDefiningcode(
      ) {
     return this.diagnoseDefiningcode ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setAussageUberDenAusschlussDefiningcode(
      AussageUberDenAusschlussDefiningcode aussageUberDenAusschlussDefiningcode) {
     this.aussageUberDenAusschlussDefiningcode = aussageUberDenAusschlussDefiningcode;
  }

  public AussageUberDenAusschlussDefiningcode getAussageUberDenAusschlussDefiningcode() {
     return this.aussageUberDenAusschlussDefiningcode ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setDiagnoseDefiningcodeProblem(DiagnoseDefiningcode diagnoseDefiningcodeProblem) {
     this.diagnoseDefiningcodeProblem = diagnoseDefiningcodeProblem;
  }

  public DiagnoseDefiningcode getDiagnoseDefiningcodeProblem() {
     return this.diagnoseDefiningcodeProblem ;
  }
}
