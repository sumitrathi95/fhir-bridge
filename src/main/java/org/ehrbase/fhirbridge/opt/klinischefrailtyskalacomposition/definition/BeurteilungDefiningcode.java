package org.ehrbase.fhirbridge.opt.klinischefrailtyskalacomposition.definition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum BeurteilungDefiningcode implements EnumValueSet {
  SEHR_FIT               ("sehr fit", "*", "local", "at005"),
  DURCHSCHNITTLICH_AKTIV ("Durchschnittlich aktiv", "*", "local", "at006"),
  GUT_ZURECHTKOMMEND     ("Gut zurechtkommend", "*", "local", "at007"),
  VULNERABEL             ("Vulnerabel", "*", "local", "at008"),
  GERINGGRADIG_FRAIL     ("Geringgradig frail", "*", "local", "at009"),
  MITTELGRADIG_FRAIL     ("Mittelgradig frail", "*", "local", "at010"),
  AUSGEPRAGT_FRAIL       ("Ausgeprägt frail", "*", "local", "at011"),
  EXTREM_FRAIL           ("Extrem frail", "*", "local", "at012"),
  TERMINAL_ERKRANKT      ("Terminal erkrankt", "*", "local", "at012");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  BeurteilungDefiningcode(String value, String description, String terminologyId, String code) {
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
