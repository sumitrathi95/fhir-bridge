package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AdministrativesGeschlechtDefiningcode implements EnumValueSet {
  UNKNOWN("Unknown", null, "http", "unknown"),

  MALE("Male", null, "http", "male"),

  FEMALE("Female", null, "http", "female"),

  OTHER("Other", null, "http", "other");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AdministrativesGeschlechtDefiningcode(String value, String description, String terminologyId,
      String code) {
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
