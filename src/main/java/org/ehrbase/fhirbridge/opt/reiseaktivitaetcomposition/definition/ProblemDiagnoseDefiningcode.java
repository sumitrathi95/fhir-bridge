package org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ProblemDiagnoseDefiningcode implements EnumValueSet {
  HISTORY_OF_TRAVEL("History of Travel", null, "LOINC", "8691-8");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ProblemDiagnoseDefiningcode(String value, String description, String terminologyId, String code) {
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
