package org.ehrbase.fhirbridge.opt.korpergroecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.height.v2")
public class LangeObservation {
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
  private Double langeMagnitude;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
  private String langeUnits;

  @Path("/language")
  private Language language;

  @Path("/protocol[at0007]/items[at0011]")
  private Cluster gerat;

  @Path("/protocol[at0007]/items[at0022]")
  private List<Cluster> erweiterung;

  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  public void setLangeMagnitude(Double langeMagnitude) {
     this.langeMagnitude = langeMagnitude;
  }

  public Double getLangeMagnitude() {
     return this.langeMagnitude ;
  }

  public void setLangeUnits(String langeUnits) {
     this.langeUnits = langeUnits;
  }

  public String getLangeUnits() {
     return this.langeUnits ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setGerat(Cluster gerat) {
     this.gerat = gerat;
  }

  public Cluster getGerat() {
     return this.gerat ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }
}
