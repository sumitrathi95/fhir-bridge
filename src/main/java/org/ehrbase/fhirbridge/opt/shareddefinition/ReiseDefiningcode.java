package org.ehrbase.fhirbridge.opt.shareddefinition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ReiseDefiningcode implements EnumValueSet {
  N373067005("373067005", "373067005", "SNOMED Clinical Terms", "373067005"),

  N261665006("261665006", "261665006", "SNOMED Clinical Terms", "261665006"),

  N373066001("373066001", "373066001", "SNOMED Clinical Terms", "373066001");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ReiseDefiningcode(String value, String description, String terminologyId, String code) {
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
