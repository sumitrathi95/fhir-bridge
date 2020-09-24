package org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum UntersuchterAnalytDefiningcode implements EnumValueSet {

  OXYGEN_SATURATION_IN_BLOOD("Oxygen saturation in Blood", null, "LOINC", "20564-1"),

  OXYGEN_SATURATION_IN_ARTERIAL_BLOOD("Oxygen saturation in Arterial blood", null, "LOINC", "2708-6"),

  //FIXME these ENUMS where added manually since OPT script failed

  CARBON_DIOXIDE_IN_BLOOD("Carbon dioxide [Partial pressure] in Blood", null, "LOINC", "11557-6"),

  CARBON_DIOXIDE_IN_ARTERIAL_BLOOD("Carbon dioxide [Partial pressure] in Arterial blood", null, "LOINC", "2019-8"),

  CARBON_DIOXIDE_IN_CAPILLARY_BLOOD("Carbon dioxide [Partial pressure] in Capillary blood", null, "LOINC", "2020-6"),


  OXYGEN_IN_BLOOD("Oxygen [Partial pressure] in blood", null, "LOINC", "11556-8"),

  OXYGEN_IN_ARTERIAL_BLOOD("Oxygen [Partial pressure] in Arterial blood", null, "LOINC", "2703-7"),

  OXYGEN_IN_CAPILLARY_BLOOD("Oxygen [Partial pressure] in Capillary Blood", null, "LOINC", "2704-5"),


  PH_SERUM_OR_PLASMA("pH of Serum or Plasma", null, "LOINC", "2753-2"),

  PH_VENOUS_BLOOD("pH of Venous blood", null, "LOINC", "2746-6"),

  PH_CAPILLARY_BLOOD("pH of Capillary blood", null, "LOINC", "2745-8"),

  PH_ARTERIAL_BLOOD("pH of Arterial blood", null, "LOINC", "2744-1"),

  PH_MIXED_VENOUS_BLOOD("pH of Mixed venous blood", null, "LOINC", "19213-8"),

  PH_BLOOD("pH of Blood", null, "LOINC", "11558-4");


  private final String value;

  private final String description;

  private final String terminologyId;

  private final String code;

  UntersuchterAnalytDefiningcode(String value, String description, String terminologyId,
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
