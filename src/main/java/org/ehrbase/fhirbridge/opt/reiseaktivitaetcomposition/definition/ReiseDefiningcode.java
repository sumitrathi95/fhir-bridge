package org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ReiseDefiningcode implements EnumValueSet {
  NEIN("Nein", "Die Person ist nicht innerhalb des angegebenen Zeitraums gereist.", "SNOMED Clinical Terms", "at0005"),

  UNBEKANNT("Unbekannt", "Es ist nicht bekannt, ob die Person innerhalb des angegebenen Zeitinraumss gereist ist.", "SNOMED Clinical Terms", "at0027"),

  YES_QUALIFIER_VALUE("Yes (qualifier value)", null, "SNOMED Clinical Terms", "373066001"),

  JA_NATIONAL("Ja - national", "Die Person ist innerhalb des angegebenen Zeitraums national gereist.", "SNOMED Clinical Terms", "at0006");

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
