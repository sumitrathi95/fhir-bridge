package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum BerufsbereichDefiningcode implements EnumValueSet {
  MEDIZINISCHEN_BEREICH_PFLEGE_ARZTPRAXIS_ODER_KRANKENHAUS("Medizinischen Bereich (Pflege, Arztpraxis oder Krankenhaus)", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/occupation-class", "medical"),

  GEMEINSCHAFTSEINRICHTUNG_SCHULE_KITA_UNIVERSITAT_HEIM_ETC("Gemeinschaftseinrichtung (Schule, Kita, Universität, Heim etc.)", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/occupation-class", "community"),

  SONSTIGES("Sonstiges", null, "http://fhir.data4life.care/covid-19/r4/CodeSystem/occupation-class", "LA46-8");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  BerufsbereichDefiningcode(String value, String description, String terminologyId, String code) {
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
