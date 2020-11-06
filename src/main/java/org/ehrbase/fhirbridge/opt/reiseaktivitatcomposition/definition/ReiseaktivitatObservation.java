package org.ehrbase.fhirbridge.opt.reiseaktivitatcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.MathFunctionDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.ReiseDefiningcode;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.travel_event.v0")
public class ReiseaktivitatObservation {
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code")
  private ReiseDefiningcode reiseDefiningcode;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0010]")
  private List<ReiseaktivitatBestimmtesReisezielCluster> bestimmtesReiseziel;

  @Path("/protocol[at0007]/items[at0021]")
  private List<Cluster> erweiterung;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0009]/value|value")
  private TemporalAccessor abreisedatumValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0025]")
  private List<Cluster> zusatzlicheReisedetails;

  @Path("/data[at0001]/events[at0002]/width|value")
  private TemporalAmount widthValue;

  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/items[at0019]/value|value")
  private TemporalAccessor ruckreisedatumValue;

  @Path("/data[at0001]/events[at0002]/math_function|defining_code")
  private MathFunctionDefiningcode mathFunctionDefiningcode;

  public void setReiseDefiningcode(ReiseDefiningcode reiseDefiningcode) {
     this.reiseDefiningcode = reiseDefiningcode;
  }

  public ReiseDefiningcode getReiseDefiningcode() {
     return this.reiseDefiningcode ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setBestimmtesReiseziel(
      List<ReiseaktivitatBestimmtesReisezielCluster> bestimmtesReiseziel) {
     this.bestimmtesReiseziel = bestimmtesReiseziel;
  }

  public List<ReiseaktivitatBestimmtesReisezielCluster> getBestimmtesReiseziel() {
     return this.bestimmtesReiseziel ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setAbreisedatumValue(TemporalAccessor abreisedatumValue) {
     this.abreisedatumValue = abreisedatumValue;
  }

  public TemporalAccessor getAbreisedatumValue() {
     return this.abreisedatumValue ;
  }

  public void setZusatzlicheReisedetails(List<Cluster> zusatzlicheReisedetails) {
     this.zusatzlicheReisedetails = zusatzlicheReisedetails;
  }

  public List<Cluster> getZusatzlicheReisedetails() {
     return this.zusatzlicheReisedetails ;
  }

  public void setWidthValue(TemporalAmount widthValue) {
     this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
     return this.widthValue ;
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

  public void setRuckreisedatumValue(TemporalAccessor ruckreisedatumValue) {
     this.ruckreisedatumValue = ruckreisedatumValue;
  }

  public TemporalAccessor getRuckreisedatumValue() {
     return this.ruckreisedatumValue ;
  }

  public void setMathFunctionDefiningcode(MathFunctionDefiningcode mathFunctionDefiningcode) {
     this.mathFunctionDefiningcode = mathFunctionDefiningcode;
  }

  public MathFunctionDefiningcode getMathFunctionDefiningcode() {
     return this.mathFunctionDefiningcode ;
  }
}
