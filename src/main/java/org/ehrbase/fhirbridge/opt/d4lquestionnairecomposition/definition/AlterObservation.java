package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.age.v0")
public class AlterObservation {
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value|defining_code")
  private AlterskategorieDefiningcode alterskategorieDefiningcode;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  @Path("/language")
  private Language language;

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setAlterskategorieDefiningcode(
      AlterskategorieDefiningcode alterskategorieDefiningcode) {
     this.alterskategorieDefiningcode = alterskategorieDefiningcode;
  }

  public AlterskategorieDefiningcode getAlterskategorieDefiningcode() {
     return this.alterskategorieDefiningcode ;
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

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }
}
