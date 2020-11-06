package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum WohnsituationDefiningcode implements EnumValueSet {
  WOHNT_MIT_ANDEREN_ZUSAMMEN("Wohnt mit anderen zusammen", null, "LOINC", "LA9996-5"),

  ALLEIN_WOHNEND("Allein wohnend", null, "LOINC", "LA6255-9");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  WohnsituationDefiningcode(String value, String description, String terminologyId, String code) {
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