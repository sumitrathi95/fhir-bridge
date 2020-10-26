package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum MedikamentenbehanlungWurdeVerschobenDefiningcode2 implements EnumValueSet {
  MEDIKAMENTENBEHANLUNG_WURDE_VERSCHOBEN("Medikamentenbehanlung wurde verschoben", "Die geplante Medikamentenbehanlung wurde vor der Verabreichung verschoben.", "local", "at0013");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  MedikamentenbehanlungWurdeVerschobenDefiningcode2(String value, String description,
      String terminologyId, String code) {
    this.value = value;
    this.description = description;
    this.terminologyId = terminologyId;
    this.code = code;
  }

  public String getValue() {
     return this.value ;
  }

  public String getDescription() {
     return this.description ;
  }

  public String getTerminologyId() {
     return this.terminologyId ;
  }

  public String getCode() {
     return this.code ;
  }
}
