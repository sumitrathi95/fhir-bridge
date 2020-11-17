package org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import java.time.temporal.Temporal;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.person_birth_data_iso.v0")
public class DatenZurGeburtCluster {
  @Path("/items[at0003]/value|defining_code")
  private CodePhrase kodierungFurMehrlingsgeburtenDefiningcode;

  @Path("/items[at0001]/value|value")
  private Temporal geburtsdatumValue;

  @Path("/items[at0006]")
  private Cluster landerspezifischeDaten;

  public void setKodierungFurMehrlingsgeburtenDefiningcode(
      CodePhrase kodierungFurMehrlingsgeburtenDefiningcode) {
     this.kodierungFurMehrlingsgeburtenDefiningcode = kodierungFurMehrlingsgeburtenDefiningcode;
  }

  public CodePhrase getKodierungFurMehrlingsgeburtenDefiningcode() {
     return this.kodierungFurMehrlingsgeburtenDefiningcode ;
  }

  public void setGeburtsdatumValue(Temporal geburtsdatumValue) {
     this.geburtsdatumValue = geburtsdatumValue;
  }

  public Temporal getGeburtsdatumValue() {
     return this.geburtsdatumValue ;
  }

  public void setLanderspezifischeDaten(Cluster landerspezifischeDaten) {
     this.landerspezifischeDaten = landerspezifischeDaten;
  }

  public Cluster getLanderspezifischeDaten() {
     return this.landerspezifischeDaten ;
  }
}
