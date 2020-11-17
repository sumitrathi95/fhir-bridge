package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum EthnischerHintergrundDefiningcode implements EnumValueSet {
  OTHER_ETHNIC_MIXED_ORIGIN("Other ethnic, mixed origin", null, "SNOMED Clinical Terms", "186019001"),

  ARABS("Arabs", null, "SNOMED Clinical Terms", "90027003"),

  CAUCASIAN("Caucasian", null, "SNOMED Clinical Terms", "14045001"),

  BLACK_AFRICAN("Black African", null, "SNOMED Clinical Terms", "18167009"),

  ASIAN("Asian", null, "SNOMED Clinical Terms", "315280000"),

  HISPANIC_OR_LATINO("Hispanic or Latino", null, "SNOMED Clinical Terms", "2135-2");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  EthnischerHintergrundDefiningcode(String value, String description, String terminologyId,
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
