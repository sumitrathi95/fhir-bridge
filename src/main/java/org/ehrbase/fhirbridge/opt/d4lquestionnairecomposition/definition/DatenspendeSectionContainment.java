package org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class DatenspendeSectionContainment extends Containment {
  public SelectAqlField<DatenspendeSection> DATENSPENDE_SECTION = new AqlFieldImp<DatenspendeSection>(DatenspendeSection.class, "", "DatenspendeSection", DatenspendeSection.class, this);

  public SelectAqlField<EinwilligungserklarungAction> EINWILLIGUNGSERKLARUNG = new AqlFieldImp<EinwilligungserklarungAction>(DatenspendeSection.class, "/items[openEHR-EHR-ACTION.informed_consent.v0]", "einwilligungserklarung", EinwilligungserklarungAction.class, this);

  private DatenspendeSectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static DatenspendeSectionContainment getInstance() {
    return new DatenspendeSectionContainment();
  }
}
