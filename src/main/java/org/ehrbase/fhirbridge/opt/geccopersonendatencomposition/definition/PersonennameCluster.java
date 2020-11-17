package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import java.lang.Boolean;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.person_name.v0")
public class PersonennameCluster {
  @Path("/items[at0014]/value")
  private DvInterval gultigkeitszeitraum;

  @Path("/items[at0006]/value|defining_code")
  private NamensartDefiningcode namensartDefiningcode;

  @Path("/items[at0002]/items[at0003]/value|value")
  private String vornameValue;

  @Path("/items[at0022]/value|value")
  private Boolean bevorzugterNameValue;

  @Path("/items[at0002]/items[at0005]/value|value")
  private String nachnameValue;

  @Path("/items[at0001]/value|value")
  private String nameUnstrukturiertValue;

  @Path("/items[at0002]/items[at0004]")
  private List<PersonennameWeitererVornameElement> weitererVorname;

  @Path("/items[at0002]/items[at0017]/value|value")
  private String titelValue;

  @Path("/items[at0002]/items[at0018]/value|value")
  private String suffixValue;

  public void setGultigkeitszeitraum(DvInterval gultigkeitszeitraum) {
     this.gultigkeitszeitraum = gultigkeitszeitraum;
  }

  public DvInterval getGultigkeitszeitraum() {
     return this.gultigkeitszeitraum ;
  }

  public void setNamensartDefiningcode(NamensartDefiningcode namensartDefiningcode) {
     this.namensartDefiningcode = namensartDefiningcode;
  }

  public NamensartDefiningcode getNamensartDefiningcode() {
     return this.namensartDefiningcode ;
  }

  public void setVornameValue(String vornameValue) {
     this.vornameValue = vornameValue;
  }

  public String getVornameValue() {
     return this.vornameValue ;
  }

  public void setBevorzugterNameValue(Boolean bevorzugterNameValue) {
     this.bevorzugterNameValue = bevorzugterNameValue;
  }

  public Boolean isBevorzugterNameValue() {
     return this.bevorzugterNameValue ;
  }

  public void setNachnameValue(String nachnameValue) {
     this.nachnameValue = nachnameValue;
  }

  public String getNachnameValue() {
     return this.nachnameValue ;
  }

  public void setNameUnstrukturiertValue(String nameUnstrukturiertValue) {
     this.nameUnstrukturiertValue = nameUnstrukturiertValue;
  }

  public String getNameUnstrukturiertValue() {
     return this.nameUnstrukturiertValue ;
  }

  public void setWeitererVorname(List<PersonennameWeitererVornameElement> weitererVorname) {
     this.weitererVorname = weitererVorname;
  }

  public List<PersonennameWeitererVornameElement> getWeitererVorname() {
     return this.weitererVorname ;
  }

  public void setTitelValue(String titelValue) {
     this.titelValue = titelValue;
  }

  public String getTitelValue() {
     return this.titelValue ;
  }

  public void setSuffixValue(String suffixValue) {
     this.suffixValue = suffixValue;
  }

  public String getSuffixValue() {
     return this.suffixValue ;
  }
}
