package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Boolean;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.symptom_sign.v1")
public class GeschmacksUndOderGeruchsverlustCluster {
  @Path("/items[at0063]")
  private List<Cluster> krankheitsanzeichen;

  @Path("/items[at0001]/value|value")
  private String nameDesSymptomsKrankheitsanzeichensValue;

  @Path("/items[at0153]")
  private List<Cluster> spezifischeDetails;

  @Path("/items[at0035]/value|value")
  private Boolean vorhandenValue;

  @Path("/items[at0146]")
  private List<Cluster> vorangegangeneEpisoden;

  @Path("/items[at0147]")
  private List<Cluster> spezifischeAnatomischeLokalisation;

  public void setKrankheitsanzeichen(List<Cluster> krankheitsanzeichen) {
     this.krankheitsanzeichen = krankheitsanzeichen;
  }

  public List<Cluster> getKrankheitsanzeichen() {
     return this.krankheitsanzeichen ;
  }

  public void setNameDesSymptomsKrankheitsanzeichensValue(
      String nameDesSymptomsKrankheitsanzeichensValue) {
     this.nameDesSymptomsKrankheitsanzeichensValue = nameDesSymptomsKrankheitsanzeichensValue;
  }

  public String getNameDesSymptomsKrankheitsanzeichensValue() {
     return this.nameDesSymptomsKrankheitsanzeichensValue ;
  }

  public void setSpezifischeDetails(List<Cluster> spezifischeDetails) {
     this.spezifischeDetails = spezifischeDetails;
  }

  public List<Cluster> getSpezifischeDetails() {
     return this.spezifischeDetails ;
  }

  public void setVorhandenValue(Boolean vorhandenValue) {
     this.vorhandenValue = vorhandenValue;
  }

  public Boolean isVorhandenValue() {
     return this.vorhandenValue ;
  }

  public void setVorangegangeneEpisoden(List<Cluster> vorangegangeneEpisoden) {
     this.vorangegangeneEpisoden = vorangegangeneEpisoden;
  }

  public List<Cluster> getVorangegangeneEpisoden() {
     return this.vorangegangeneEpisoden ;
  }

  public void setSpezifischeAnatomischeLokalisation(
      List<Cluster> spezifischeAnatomischeLokalisation) {
     this.spezifischeAnatomischeLokalisation = spezifischeAnatomischeLokalisation;
  }

  public List<Cluster> getSpezifischeAnatomischeLokalisation() {
     return this.spezifischeAnatomischeLokalisation ;
  }
}
