package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum VorhandenDefiningcode implements EnumValueSet {
  NEIN("Nein", null, "LOINC", "LA32-8"),

  JA("Ja", null, "LOINC", "LA33-6"),

  ICH_WEISS_ES_NICHT("Ich weiß es nicht", null, "LOINC", "LA12688-0");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  VorhandenDefiningcode(String value, String description, String terminologyId, String code) {
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
