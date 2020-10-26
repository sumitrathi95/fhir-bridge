package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum VerzogerungDerRezeptabgabeDefiningcode implements EnumValueSet {
  VERZOGERUNG_DER_REZEPTABGABE("Verzögerung der Rezeptabgabe", "Das Rezept wurde aufgrund einer technischen oder pharmazeutischen Störung nicht ausgegeben.", "local", "at0008");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  VerzogerungDerRezeptabgabeDefiningcode(String value, String description, String terminologyId,
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
