package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum SchweregradDefiningcode implements EnumValueSet {
  MODERAT("Moderat", "Die Intensität des Symptoms/Krankheitsanzeichens führt zu einer Beeinträchtigung der normalen Aktivität.", "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "at0024"),

  LEICHT("Leicht", "Die Intensität des Symptoms/Krankheitsanzeichens führt zu keiner Beeinträchtigung der normalen Aktivität.", "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "at0023"),

  N38_C("< 38 °C", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "below-38C"),

  N40_C("40 °C", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "40C"),

  N39_C("39 °C", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "39C"),

  N41_C("41 °C", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "41C"),

  N42_C("> 42 °C", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "above-42C"),

  SCHWER("Schwer", "Die Intensität des Symptoms/Krankheitsanzeichens verhindert eine normale Aktivität.", "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "at0025"),

  ICH_WEISS_ES_NICHT("Ich weiß es nicht", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/fever-class", "LA12688-0");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  SchweregradDefiningcode(String value, String description, String terminologyId, String code) {
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
