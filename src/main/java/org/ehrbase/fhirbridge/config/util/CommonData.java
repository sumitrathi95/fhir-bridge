package org.ehrbase.fhirbridge.config.util;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import org.hl7.fhir.r4.model.DomainResource;

public class CommonData {

    public static FeederAudit constructFeederAudit(DomainResource fhirResource)
    {
        FeederAudit fa = new FeederAudit();


        // source system
        String sourceSystem = fhirResource.getMeta().getSource();

        if (sourceSystem == null) sourceSystem = "FHIR-bridge";

        FeederAuditDetails fad = new FeederAuditDetails();
        fad.setSystemId(sourceSystem);

        fa.setOriginatingSystemAudit(fad);


        // original content
        FhirContext ctx = FhirContext.forR4();
        IParser parser = ctx.newJsonParser();
        String originalContent = parser.encodeResourceToString(fhirResource);

        DvParsable originalContentEnc = new DvParsable();
        originalContentEnc.setValue(originalContent);
        originalContentEnc.setFormalism("application/json");

        fa.setOriginalContent(originalContentEnc);


        return fa;
    }
}
