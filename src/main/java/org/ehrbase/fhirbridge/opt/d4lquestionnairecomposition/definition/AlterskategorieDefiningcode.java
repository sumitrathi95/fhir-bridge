package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum AlterskategorieDefiningcode implements EnumValueSet {
  N6170("61-70", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/age-group", "61-70"),

  UBER80("über 80", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/age-group", "above-80"),

  N7180("71-80", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/age-group", "71-80"),

  N4050("40-50", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/age-group", "40-50"),

  JUNGER_ALS40("jünger als 40", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/age-group", "below-40"),

  N5160("51-60", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/age-group", "51-60");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  AlterskategorieDefiningcode(String value, String description, String terminologyId, String code) {
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
