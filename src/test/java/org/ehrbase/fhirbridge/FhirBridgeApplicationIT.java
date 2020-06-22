package org.ehrbase.fhirbridge;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import ca.uhn.fhir.util.OperationOutcomeUtil;
import org.apache.commons.io.IOUtils;
import org.ehrbase.fhirbridge.config.FhirConfiguration;
import org.ehrbase.fhirbridge.config.TerminologyMode;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.utils.EOperationOutcome;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Integration Tests
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FhirBridgeApplicationIT {

    @LocalServerPort
    private int port;

    @Autowired
    private FhirContext context;

    @Autowired
    private FhirConfiguration config;

    @Autowired
    private ResourceLoader resourceLoader;

    private IGenericClient client;

    @Autowired
    private EhrbaseService service;

    @BeforeEach
    public void setUp() {
        context.getRestfulClientFactory().setSocketTimeout(60 * 1000);
        client = context.newRestfulGenericClient("http://localhost:" + port + "/fhir-bridge/fhir");
    }

    @Test
    public void createCondition() throws IOException {
        Date now = new Date();
        MethodOutcome outcome = client.create()
                .resource(getContent("classpath:/Condition/condition-example.json"))
                .execute();

        Assertions.assertEquals(1L, outcome.getId().getIdPartAsLong());
        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertNotNull(outcome.getResource());
        Assertions.assertTrue(outcome.getResource().getMeta().getLastUpdated().after(now));
        Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
    }

    @Test
    public void createConditionUsingInvalidProfile() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create()
                        .resource(getContent("classpath:/Condition/condition-invalid-profile-example.json"))
                        .execute());

        Assertions.assertEquals("Specified profile type was \"Observation\", but found type \"Condition\"", OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()));
    }

    @Test
    public void createDiagnosticReportLab() throws IOException {
        Date now = new Date();
        MethodOutcome outcome = client.create()
                .resource(getContent("classpath:/DiagnosticReport/diagnosticreport-diagnosticreportlab-example.json"))
                .execute();

        Assertions.assertEquals(1L, outcome.getId().getIdPartAsLong());
        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertNotNull(outcome.getResource());
        Assertions.assertTrue(outcome.getResource().getMeta().getLastUpdated().after(now));
        Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
    }

    @Test
    public void createDiagnosticReportLabContainedObservation() throws IOException {
        Date now = new Date();
        MethodOutcome outcome = client.create()
                .resource(getContent("classpath:/DiagnosticReport/diagnosticreport-diagnosticreportlab-example-contained_obs.json"))
                .execute();

        Assertions.assertEquals(1L, outcome.getId().getIdPartAsLong());
        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertNotNull(outcome.getResource());
        Assertions.assertTrue(outcome.getResource().getMeta().getLastUpdated().after(now));
        Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
    }

    @Test
    public void createDiagnosticReportUsingDefaultProfile() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create()
                        .resource(getContent("classpath:/DiagnosticReport/diagnosticreport-example.json"))
                        .execute());

        OperationOutcome outcome = (OperationOutcome) exception.getOperationOutcome();
        Assertions.assertEquals(1, outcome.getIssue().size());
        Assertions.assertEquals("Default profile is not supported for DiagnosticReport. One of the following profiles is expected: " +
                        "[https://www.medizininformatik-initiative.de/fhir/core/StructureDefinition/DiagnosticReportLab]",
                OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()));
    }

    @Test
    public void createDiagnosticReportUsingUnsupportedProfile() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create()
                        .resource(getContent("classpath:/DiagnosticReport/diagnosticreport-hla-genetics-results-example.json"))
                        .execute());

        OperationOutcome outcome = (OperationOutcome) exception.getOperationOutcome();
        Assertions.assertEquals(1, outcome.getIssue().size());
        Assertions.assertEquals("Profile http://hl7.org/fhir/StructureDefinition/hlaresult is not supported for DiagnosticReport. " +
                        "One of the following profiles is expected: [https://www.medizininformatik-initiative.de/fhir/core/StructureDefinition/DiagnosticReportLab]",
                OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()));
    }

    @Test
    public void createBodyTemp() throws IOException {
        MethodOutcome methodOutcome = client.create()
                .resource(getContent("classpath:/Observation/observation-bodytemp-example.json"))
                .execute();

        Assertions.assertEquals(true, methodOutcome.getCreated());
        Assertions.assertTrue(methodOutcome.getResource() instanceof Observation);
        Assertions.assertNotNull(methodOutcome.getResource());
        Assertions.assertEquals("1", methodOutcome.getResource().getMeta().getVersionId());
    }

    @Test
    public void createCoronavirusNachweisTest() throws IOException {

        System.out.println("--------------------------- createCoronavirusNAchweisTest");

        // Remote terminology validation will make this resource fail because the LOINC codes are not yet there
        if (config.getFhirProperties().getValidation().getTerminology().getMode() == TerminologyMode.EMBEDDED) {

            UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                    () -> client.create()
                            .resource(getContent("classpath:/Observation/observation-coronavirusnachweistest-example.json"))
                            .execute());

            OperationOutcome operationOutcome = (OperationOutcome) exception.getOperationOutcome();

            System.out.println("------------------------------- " + operationOutcome.getIssue().get(0).getDiagnostics());

            Assertions.assertEquals(4, operationOutcome.getIssue().size());
            OperationOutcome.OperationOutcomeIssueComponent issue = operationOutcome.getIssue().get(3);
            Assertions.assertEquals(OperationOutcome.IssueSeverity.ERROR, issue.getSeverity());
            Assertions.assertEquals("Observation.code.coding[0]", issue.getLocation().get(0).toString());
        }
        else // Remote terminology validation is OFF, example wont fail
        {
            MethodOutcome methodOutcome = client.create()
                    .resource(getContent("classpath:/Observation/observation-coronavirusnachweistest-example.json"))
                    .execute();

            Assertions.assertEquals(true, methodOutcome.getCreated());
            Assertions.assertTrue(methodOutcome.getResource() instanceof Observation);
            Assertions.assertNotNull(methodOutcome.getResource());
            Assertions.assertEquals("1", methodOutcome.getResource().getMeta().getVersionId());
        }
    }

    @Test
    public void createObservationLab() throws IOException {
        MethodOutcome methodOutcome = client.create()
                .resource(getContent("classpath:/Observation/observation-observationlab-example.json"))
                .execute();

        Assertions.assertEquals(true, methodOutcome.getCreated());
        Assertions.assertTrue(methodOutcome.getResource() instanceof Observation);
        Assertions.assertNotNull(methodOutcome.getResource());
        Assertions.assertEquals("1", methodOutcome.getResource().getMeta().getVersionId());

    }

    @Test
    public void createObservationUsingDefaultProfile() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create()
                        .resource(getContent("classpath:/Observation/observation-example.json"))
                        .execute());

        OperationOutcome operationOutcome = (OperationOutcome) exception.getOperationOutcome();
        Assertions.assertEquals(1, operationOutcome.getIssue().size());
        Assertions.assertEquals("Default profile is not supported for Observation. One of the following profiles is expected: " +
                        "[http://hl7.org/fhir/StructureDefinition/bodytemp, https://charite.infectioncontrol.de/fhir/core/StructureDefinition/CoronavirusNachweisTest, " +
                        "https://www.medizininformatik-initiative.de/fhir/core/StructureDefinition/ObservationLab]",
                OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()));
    }

    @Test
    public void createObservationUsingUnsupportedProfile() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create()
                        .resource(getContent("classpath:/Observation/observation-vitalsigns-example.json"))
                        .execute());

        OperationOutcome operationOutcome = (OperationOutcome) exception.getOperationOutcome();
        Assertions.assertEquals(1, operationOutcome.getIssue().size());
        Assertions.assertEquals("Profile http://hl7.org/fhir/StructureDefinition/vitalsigns is not supported for Observation. One of the following profiles is expected: " +
                        "[http://hl7.org/fhir/StructureDefinition/bodytemp, https://charite.infectioncontrol.de/fhir/core/StructureDefinition/CoronavirusNachweisTest, " +
                        "https://www.medizininformatik-initiative.de/fhir/core/StructureDefinition/ObservationLab]",
                OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()));
    }

    @Test
    public void createQuestionnaireResponse() throws IOException {
        MethodOutcome methodOutcome = client.create()
                .resource(getContent("classpath:/QuestionnaireResponse/covapp-response.json"))
                .execute();

        Assertions.assertEquals(true, methodOutcome.getCreated());
        Assertions.assertTrue(methodOutcome.getResource() instanceof QuestionnaireResponse);
        Assertions.assertNotNull(methodOutcome.getResource());
        Assertions.assertEquals("1", methodOutcome.getResource().getMeta().getVersionId());
    }

    /* FIXME: we need to use the status in the create ehr service, we are using null in the client library because the current create has an issue.
    @Test
    public void testEhrExistsDoesExist()
    {
        // FIXME: this will only work if the ehr is created previously with a specific ehr_status, we currently removed
        // the ehr_status from the service.createEhr()
        Assertions.assertTrue(service.ehrExistsBySubjectId("88a2d7db-6c78-4cd5-9610-30eb548e2e82"));
    }
    */

    @Test
    public void testEhrExistsDoesNotExist()
    {
        Assertions.assertFalse(service.ehrExistsBySubjectId("xxxxx"));
    }


    private String getContent(String location) throws IOException {
        Resource resource = resourceLoader.getResource(location);
        try (InputStream input = resource.getInputStream()) {
            return IOUtils.toString(input, StandardCharsets.UTF_8);
        }
    }
}
