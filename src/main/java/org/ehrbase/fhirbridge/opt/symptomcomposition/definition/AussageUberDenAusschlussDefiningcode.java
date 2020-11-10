package org.ehrbase.fhirbridge.opt.symptomcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AussageUberDenAusschlussDefiningcode implements EnumValueSet {
  KNOWN_ABSENT_QUALIFIER_VALUE("Known absent (qualifier value)", null, "SNOMED Clinical Terms", "410594000");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AussageUberDenAusschlussDefiningcode(String value, String description, String terminologyId,
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
