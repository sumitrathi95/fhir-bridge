package org.ehrbase.fhirbridge.opt.shareddefinition;

import java.lang.String;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum BundeslandRegionDefiningcode implements EnumValueSet {
  DE_BB("DE-BB", "DE-BB", "ISO3166-2-de", "DE-BB"),

  DE_HE("DE-HE", "DE-HE", "ISO3166-2-de", "DE-HE"),

  DE_BE("DE-BE", "DE-BE", "ISO3166-2-de", "DE-BE"),

  DE_HH("DE-HH", "DE-HH", "ISO3166-2-de", "DE-HH"),

  DE_BW("DE-BW", "DE-BW", "ISO3166-2-de", "DE-BW"),

  DE_HB("DE-HB", "DE-HB", "ISO3166-2-de", "DE-HB"),

  DE_NI("DE-NI", "DE-NI", "ISO3166-2-de", "DE-NI"),

  DE_BY("DE-BY", "DE-BY", "ISO3166-2-de", "DE-BY"),

  DE_TH("DE-TH", "DE-TH", "ISO3166-2-de", "DE-TH"),

  DE_SH("DE-SH", "DE-SH", "ISO3166-2-de", "DE-SH"),

  DE_SL("DE-SL", "DE-SL", "ISO3166-2-de", "DE-SL"),

  DE_SN("DE-SN", "DE-SN", "ISO3166-2-de", "DE-SN"),

  DE_RP("DE-RP", "DE-RP", "ISO3166-2-de", "DE-RP"),

  DE_MV("DE-MV", "DE-MV", "ISO3166-2-de", "DE-MV"),

  DE_NW("DE-NW", "DE-NW", "ISO3166-2-de", "DE-NW"),

  DE_ST("DE-ST", "DE-ST", "ISO3166-2-de", "DE-ST");

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


  public static BundeslandRegionDefiningcode createByCode(String input_code) {
    BundeslandRegionDefiningcode ret = null;
    int numCoresspondingItems = 0;

    //identify
    for (BundeslandRegionDefiningcode c : BundeslandRegionDefiningcode.values()) {
      if (input_code.equals(c.code)) {
        numCoresspondingItems = numCoresspondingItems + 1;
        ret = c;
      }
    }

    if(numCoresspondingItems != 1)
    {
      throw new UnprocessableEntityException("There is no code for '"+input_code+"' defined in BundeslandRegionDefiningcode.java");
    }
    return (ret);
  }
}
