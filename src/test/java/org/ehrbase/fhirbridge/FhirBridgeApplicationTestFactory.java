package org.ehrbase.fhirbridge;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.apache.commons.io.IOUtils;
import org.ehrbase.fhirbridge.config.FhirConfiguration;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class FhirBridgeApplicationTestFactory {

    final Logger logger = LoggerFactory.getLogger(FhirBridgeApplicationIT.class);

    static final String PATIENT_REFERENCE_REGEXP = "urn:uuid:([0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12})";

    @LocalServerPort
    int port;

    @Autowired
    FhirContext context;

    @Autowired
    private FhirConfiguration config;

    @Autowired
    ResourceLoader resourceLoader;

    IGenericClient client;

    @Autowired
    EhrbaseService service;

    UUID ehrId;
    String subjectIdValue;

    String patientReference;

    @BeforeEach
    void setUp() {
        context.getRestfulClientFactory().setSocketTimeout(30 * 1000);
        client = context.newRestfulGenericClient("http://localhost:" + port + "/fhir-bridge/fhir");

        // Create EHR for the rests of the tests to run on this
        EhrStatus ehrStatus = new EhrStatus();

        this.subjectIdValue = UUID.randomUUID().toString();
        HierObjectId subjectId = new HierObjectId(subjectIdValue);
        ehrStatus.setSubject(new PartySelf(new PartyRef(subjectId, "demographic", "PERSON")));

        ehrStatus.setArchetypeNodeId("openEHR-EHR-EHR_STATUS.generic.v1");
        ehrStatus.setName(new DvText("test status"));

        UUID ehrId = service.createEhr(ehrStatus);

        logger.info("EHR UID: {}", ehrId);
        logger.info("Subjed ID: {}", this.subjectIdValue);

        this.patientReference = "urn:uuid:" + subjectIdValue;
    }

    String getContent(String location) throws IOException {
        Resource resource = resourceLoader.getResource(location);
        try (InputStream input = resource.getInputStream()) {
            return IOUtils.toString(input, StandardCharsets.UTF_8);
        }
    }
}
