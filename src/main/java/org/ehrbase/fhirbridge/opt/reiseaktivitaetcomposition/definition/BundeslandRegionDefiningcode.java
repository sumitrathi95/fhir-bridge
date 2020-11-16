package org.ehrbase.fhirbridge.opt.reiseaktivitaetcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum BundeslandRegionDefiningcode implements EnumValueSet {
  HAMBURG("Hamburg", null, "ISO3166-2-de", "DE-HH"),

  BERLIN("Berlin", null, "ISO3166-2-de", "DE-BE"),

  BAYERN("Bayern", null, "ISO3166-2-de", "DE-BY"),

  THEURINGEN("Theuringen", null, "ISO3166-2-de", "DE-TH"),

  HESSEN("Hessen", null, "ISO3166-2-de", "DE-HE"),

  BRANDENBURG("Brandenburg", null, "ISO3166-2-de", "DE-BB"),

  BADEN_WUERTTEMBERG("Baden-Wuerttemberg", null, "ISO3166-2-de", "DE-BW"),

  BREMEN("Bremen", null, "ISO3166-2-de", "DE-HB"),

  MECKLENBURG_VORPOMMERN("Mecklenburg-Vorpommern", null, "ISO3166-2-de", "DE-MV"),

  SCHLESWIG_HOLSTEIN("Schleswig-Holstein", null, "ISO3166-2-de", "DE-SH"),

  SAARLAND("Saarland", null, "ISO3166-2-de", "DE-SL"),

  RHEINLAND_PFALZ("Rheinland-Pfalz", null, "ISO3166-2-de", "DE-RP"),

  SACHSEN_ANHALT("Sachsen-Anhalt", null, "ISO3166-2-de", "DE-ST"),

  SACHSEN("Sachsen", null, "ISO3166-2-de", "DE-SN"),

  NORDRHEIN_WESTFALEN("Nordrhein-Westfalen", null, "ISO3166-2-de", "DE-NW"),

  NIEDERSACHSEN("Niedersachsen", null, "ISO3166-2-de", "DE-NI");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  BundeslandRegionDefiningcode(String value, String description, String terminologyId,
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
