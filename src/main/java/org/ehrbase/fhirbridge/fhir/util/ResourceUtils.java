package org.ehrbase.fhirbridge.fhir.util;

import org.hl7.fhir.r4.model.Resource;

public class ResourceUtils {

    private ResourceUtils() {
    }

    public static String getInternalReference(Resource resource) {
        if (resource == null || resource.getId() == null) {
            return null;
        }
        return resource.getClass().getName() + "/" + resource.getId();
    }
}
