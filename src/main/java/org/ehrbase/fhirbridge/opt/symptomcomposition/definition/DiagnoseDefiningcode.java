package org.ehrbase.fhirbridge.opt.symptomcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum DiagnoseDefiningcode implements EnumValueSet {
  DIAGNOSE("Problem/Diagnose", "Das Problem oder die Diagnose, auf die sich die Ausschlussaussage bezieht. Zum Beispiel: \"Diabetes\", \"COPD\" oder \"Asthma\".", "local", "at0004");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  DiagnoseDefiningcode(String value, String description, String terminologyId, String code) {
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
