package org.ehrbase.fhirbridge;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.commons.io.IOUtils;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FhirBridgeApplicationIT {

    @LocalServerPort
    private int port;

    @Autowired
    private FhirContext fhirContext;

    @Autowired
    private ResourceLoader resourceLoader;

    private IGenericClient client;

    @Before
    public void setUp() {
        client = fhirContext.newRestfulGenericClient("http://localhost:" + port + "/fhir-bridge/fhir");
    }

    @Test
    public void createDiagnosticReportLab() throws IOException {
        MethodOutcome methodOutcome = client.create()
                .resource(getContent("classpath:/DiagnosticReport/diagnosticreport-example-diagnosticreportlab.json"))
                .execute();

        Assertions.assertEquals(true, methodOutcome.getCreated());
        Assertions.assertTrue(methodOutcome.getResource() instanceof DiagnosticReport);
        Assertions.assertNotNull(methodOutcome.getResource());
        Assertions.assertEquals("1", methodOutcome.getResource().getMeta().getVersionId());
    }

    @Test
    public void createDiagnosticReportLabFailed() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create()
                        .resource(getContent("classpath:/DiagnosticReport/diagnosticreport-example-bloodexam.json"))
                        .execute());

        OperationOutcome operationOutcome = (OperationOutcome) exception.getOperationOutcome();
        Assertions.assertEquals(8, operationOutcome.getIssue().size());
    }

    @Test
    public void createOperationLab() throws IOException {
        MethodOutcome methodOutcome = client.create()
                .resource(getContent("classpath:/Observation/observation-example-observationlab.json"))
                .execute();

        Assertions.assertEquals(true, methodOutcome.getCreated());
        Assertions.assertTrue(methodOutcome.getResource() instanceof Observation);
        Assertions.assertNotNull(methodOutcome.getResource());
        Assertions.assertEquals("1", methodOutcome.getResource().getMeta().getVersionId());

    }

    @Test
    public void createCoronavirusNachweis() throws IOException {
        MethodOutcome methodOutcome = client.create()
                .resource(getContent("classpath:/Observation/observation-example-coronavirusnachweistest.json"))
                .execute();

        Assertions.assertEquals(true, methodOutcome.getCreated());
        Assertions.assertTrue(methodOutcome.getResource() instanceof Observation);
        Assertions.assertNotNull(methodOutcome.getResource());
        Assertions.assertEquals("1", methodOutcome.getResource().getMeta().getVersionId());
    }

    @Test
    public void createCoronavirusNachweisFailed() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create()
                        .resource(getContent("classpath:/Observation/observation-example-bloodpresure.json"))
                        .execute());

        OperationOutcome operationOutcome = (OperationOutcome) exception.getOperationOutcome();
        Assertions.assertEquals(7, operationOutcome.getIssue().size());
    }

    private String getContent(String location) throws IOException {
        Resource resource = resourceLoader.getResource(location);
        try (InputStream input = resource.getInputStream()) {
            return IOUtils.toString(input, StandardCharsets.UTF_8);
        }
    }
}
