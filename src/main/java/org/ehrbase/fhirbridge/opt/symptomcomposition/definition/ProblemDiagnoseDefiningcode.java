package org.ehrbase.fhirbridge.opt.symptomcomposition.definition;

import java.lang.String;
import org.ehrbase.client.classgenerator.EnumValueSet;

public enum ProblemDiagnoseDefiningcode implements EnumValueSet {
  ASTHENIA_FINDING("Asthenia (finding)", null, "SNOMED Clinical Terms", "13791008"),

  DIARRHEA_FINDING("Diarrhea (finding)", null, "SNOMED Clinical Terms", "62315008"),

  CLOUDED_CONSCIOUSNESS_FINDING("Clouded consciousness (finding)", null, "SNOMED Clinical Terms", "40917007"),

  ASYMPTOMATIC_FINDING("Asymptomatic (finding)", null, "SNOMED Clinical Terms", "84387000"),

  DRY_COUGH_FINDING("Dry cough (finding)", null, "SNOMED Clinical Terms", "11833005"),

  CHEST_PAIN_FINDING("Chest pain (finding)", null, "SNOMED Clinical Terms", "29857009"),

  MUSCLE_PAIN_FINDING("Muscle pain (finding)", null, "SNOMED Clinical Terms", "68962001"),

  SEIZURE_FINDING("Seizure (finding)", null, "SNOMED Clinical Terms", "91175000"),

  PRODUCTIVE_COUGH_FINDING("Productive cough (finding)", null, "SNOMED Clinical Terms", "28743005"),

  VOMITING_DISORDER("Vomiting (disorder)", null, "SNOMED Clinical Terms", "422400008"),

  PAIN_FINDING("Pain (finding)", null, "SNOMED Clinical Terms", "22253000"),

  NAUSEA_FINDING("Nausea (finding)", null, "SNOMED Clinical Terms", "422587007"),

  LOSS_OF_SENSE_OF_SMELL_FINDING("Loss of sense of smell (finding)", null, "SNOMED Clinical Terms", "44169009"),

  DISTURBANCE_OF_CONSCIOUSNESS_FINDING("Disturbance of consciousness (finding)", null, "SNOMED Clinical Terms", "3006004"),

  PAIN_IN_THROAT_FINDING("Pain in throat (finding)", null, "SNOMED Clinical Terms", "162397003"),

  ERUPTION_OF_SKIN_DISORDER("Eruption of skin (disorder)", null, "SNOMED Clinical Terms", "271807003"),

  HEADACHE_FINDING("Headache (finding)", null, "SNOMED Clinical Terms", "25064002"),

  JOINT_PAIN_FINDING("Joint pain (finding)", null, "SNOMED Clinical Terms", "57676002"),

  FATIGUE_FINDING("Fatigue (finding)", null, "SNOMED Clinical Terms", "84229001"),

  WHEEZING_FINDING("Wheezing (finding)", null, "SNOMED Clinical Terms", "56018004"),

  LOSS_OF_TASTE_FINDING("Loss of taste (finding)", null, "SNOMED Clinical Terms", "36955009"),

  INDRAWING_OF_RIBS_DURING_RESPIRATION_FINDING("Indrawing of ribs during respiration (finding)", null, "SNOMED Clinical Terms", "248567008"),

  BLEEDING_FINDING("Bleeding (finding)", null, "SNOMED Clinical Terms", "131148009"),

  UNABLE_TO_WALK_FINDING("Unable to walk (finding)", null, "SNOMED Clinical Terms", "282145008"),

  SKIN_ULCER_DISORDER("Skin ulcer (disorder)", null, "SNOMED Clinical Terms", "46742003"),

  NASAL_DISCHARGE_FINDING("Nasal discharge (finding)", null, "SNOMED Clinical Terms", "64531003"),

  COUGH_FINDING("Cough (finding)", null, "SNOMED Clinical Terms", "49727002"),

  ABDOMINAL_PAIN_FINDING("Abdominal pain (finding)", null, "SNOMED Clinical Terms", "21522001"),

  MALAISE_FINDING("Malaise (finding)", null, "SNOMED Clinical Terms", "367391008"),

  HEMOPTYSIS_FINDING("Hemoptysis (finding)", null, "SNOMED Clinical Terms", "66857006"),

  N38_CELSIUS_FINDING("Fever greater than 100.4 Fahrenheit / 38° Celsius (finding)", null, "SNOMED Clinical Terms", "426000000"),

  CHILL_FINDING("Chill (finding)", null, "SNOMED Clinical Terms", "43724002"),

  RIGOR_FINDING("Rigor (finding)", null, "SNOMED Clinical Terms", "38880002"),

  LYMPHADENOPATHY_DISORDER("Lymphadenopathy (disorder)", null, "SNOMED Clinical Terms", "30746006"),

  FEVER_FINDING("Fever (finding)", null, "SNOMED Clinical Terms", "386661006"),

  FEELING_FEVERISH_FINDING("Feeling feverish (finding)", null, "SNOMED Clinical Terms", "103001002"),

  NASAL_CONGESTION_FINDING("Nasal congestion (finding)", null, "SNOMED Clinical Terms", "68235000"),

  LOSS_OF_APPETITE_FINDING("Loss of appetite (finding)", null, "SNOMED Clinical Terms", "79890006"),

  CONJUNCTIVITIS_DISORDER("Conjunctivitis (disorder)", null, "SNOMED Clinical Terms", "9826008"),

  DYSPNEA_FINDING("Dyspnea (finding)", null, "SNOMED Clinical Terms", "267036007");

  private String value;

  private String description;

  private String terminologyId;

  private String code;

  ProblemDiagnoseDefiningcode(String value, String description, String terminologyId, String code) {
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
