package org.ehrbase.fhirbridge;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import ca.uhn.fhir.util.OperationOutcomeUtil;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.generic.PartySelf;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.PartyRef;
import org.apache.commons.io.IOUtils;
import org.ehrbase.fhirbridge.config.FhirConfiguration;
import org.ehrbase.fhirbridge.config.TerminologyMode;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import java.util.Date;
import java.util.UUID;

/**
 * Integration Tests
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FhirBridgeApplicationIT {

        private final Logger logger = LoggerFactory.getLogger(FhirBridgeApplicationIT.class);

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

        private UUID ehrId;
        private String subjectIdValue;

        @BeforeEach
        public void setUp() {
                context.getRestfulClientFactory().setSocketTimeout(30 * 1000);
                client = context.newRestfulGenericClient("http://localhost:" + port + "/fhir-bridge/fhir");

                // Create EHR for the rests of the tests to run on this
                EhrStatus ehrStatus = new EhrStatus();

                this.subjectIdValue = UUID.randomUUID().toString();
                HierObjectId subjectId = new HierObjectId(subjectIdValue);
                ehrStatus.setSubject(new PartySelf(new PartyRef(subjectId, "demographic", "PERSON")));

                ehrStatus.setArchetypeNodeId("openEHR-EHR-EHR_STATUS.generic.v1");
                ehrStatus.setName(new DvText("test status"));

                this.ehrId = service.createEhr(ehrStatus);

                logger.info("EHR UID: {}", this.ehrId);
                logger.info("Subjed ID: {}", this.subjectIdValue);
        }

        @Test
        public void createDiagnoseCondition() throws IOException {

                Date now = new Date();

                String resource = getContent("classpath:/Condition/condition-example.json");
                resource = resource.replaceAll(
                        "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                        "Patient/" + this.subjectIdValue);

                MethodOutcome outcome = client.create().resource(resource).execute();

                Assertions.assertEquals(1L, outcome.getId().getIdPartAsLong());
                Assertions.assertEquals(true, outcome.getCreated());
                Assertions.assertNotNull(outcome.getResource());
                Assertions.assertTrue(outcome.getResource().getMeta().getLastUpdated().after(now));
                Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
        }

        @Test
        public void createConditionUsingInvalidProfile() {
                UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                        () -> client.create().resource(getContent(
                                "classpath:/Condition/condition-invalid-profile-example.json"))
                                .execute());

                Assertions.assertEquals("Specified profile type was \"Observation\", but found type \"Condition\"",
                        OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()));
        }

        @Test
        public void createDiagnosticReportLab() throws IOException {
                Date now = new Date();

                String resource = getContent(
                        "classpath:/DiagnosticReport/diagnosticreport-diagnosticreportlab-example.json");
                resource = resource.replaceAll(
                        "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                        "Patient/" + this.subjectIdValue);

                MethodOutcome outcome = client.create().resource(resource).execute();

                Assertions.assertEquals(1L, outcome.getId().getIdPartAsLong());
                Assertions.assertEquals(true, outcome.getCreated());
                Assertions.assertNotNull(outcome.getResource());
                Assertions.assertTrue(outcome.getResource().getMeta().getLastUpdated().after(now));
                Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
        }

        @Test
        public void createDiagnosticReportLabContainedObservation() throws IOException {
                Date now = new Date();

                String resource = getContent(
                        "classpath:/DiagnosticReport/diagnosticreport-diagnosticreportlab-example-contained_obs.json");
                resource = resource.replaceAll(
                        "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                        "Patient/" + this.subjectIdValue);

                MethodOutcome outcome = client.create().resource(resource).execute();

                Assertions.assertEquals(1L, outcome.getId().getIdPartAsLong());
                Assertions.assertEquals(true, outcome.getCreated());
                Assertions.assertNotNull(outcome.getResource());
                Assertions.assertTrue(outcome.getResource().getMeta().getLastUpdated().after(now));
                Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
        }

        @Test
        public void createDiagnosticReportUsingDefaultProfile() throws IOException {

                String resource = getContent("classpath:/DiagnosticReport/diagnosticreport-example.json");
                resource = resource.replaceAll(
                        "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                        "Patient/" + this.subjectIdValue);
                String finalResource = resource;

                UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                        () -> client.create().resource(finalResource).execute());

                OperationOutcome outcome = (OperationOutcome) exception.getOperationOutcome();
                Assertions.assertEquals(1, outcome.getIssue().size());
                Assertions.assertEquals(
                        "Default profile is not supported for DiagnosticReport. One of the following profiles is expected: "
                                + "[https://www.medizininformatik-initiative.de/fhir/core/StructureDefinition/DiagnosticReportLab]",
                        OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()));
        }

        @Test
        public void createDiagnosticReportUsingUnsupportedProfile() {
                UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                        () -> client.create().resource(getContent(
                                "classpath:/DiagnosticReport/diagnosticreport-hla-genetics-results-example.json"))
                                .execute());

                OperationOutcome outcome = (OperationOutcome) exception.getOperationOutcome();
                Assertions.assertEquals(1, outcome.getIssue().size());
                Assertions.assertEquals(
                        "Profile http://hl7.org/fhir/StructureDefinition/hlaresult is not supported for DiagnosticReport. "
                                + "One of the following profiles is expected: [https://www.medizininformatik-initiative.de/fhir/core/StructureDefinition/DiagnosticReportLab]",
                        OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()));
        }

        @Test
        public void createBodyTemp() throws IOException {

                String resource = getContent("classpath:/Observation/observation-bodytemp-example.json");
                resource = resource.replaceAll(
                        "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                        "Patient/" + this.subjectIdValue);

                MethodOutcome outcome = client.create().resource(resource).execute();

                Assertions.assertEquals(true, outcome.getCreated());
                Assertions.assertTrue(outcome.getResource() instanceof Observation);
                Assertions.assertNotNull(outcome.getResource());
                Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
        }

        @Test
        public void createCoronavirusLabResults() throws IOException {

                logger.info("--------------------------- createCoronavirusNAchweisTest");

                // Remote terminology validation will make this resource fail because the LOINC
                // codes are not yet there
                if (config.getFhirProperties().getValidation().getTerminology().getMode() == TerminologyMode.EMBEDDED) {

                        UnprocessableEntityException exception = Assertions.assertThrows(
                                UnprocessableEntityException.class,
                                () -> client.create().resource(getContent(
                                        "classpath:/Observation/observation-coronavirusnachweistest-example.json"))
                                        .execute());

                        OperationOutcome operationOutcome = (OperationOutcome) exception.getOperationOutcome();

                        logger.info("------------------------------- "
                                + operationOutcome.getIssue().get(0).getDiagnostics());

                        Assertions.assertEquals(4, operationOutcome.getIssue().size());
                        OperationOutcome.OperationOutcomeIssueComponent issue = operationOutcome.getIssue().get(3);
                        Assertions.assertEquals(OperationOutcome.IssueSeverity.ERROR, issue.getSeverity());
                        Assertions.assertEquals("Observation.code.coding[0]", issue.getLocation().get(0).toString());
                } else // Remote terminology validation is OFF, example wont fail
                {
                        String resource = getContent(
                                "classpath:/Observation/observation-coronavirusnachweistest-example.json");
                        resource = resource.replaceAll(
                                "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                                "Patient/" + this.subjectIdValue);

                        MethodOutcome outcome = client.create().resource(resource).execute();

                        Assertions.assertEquals(true, outcome.getCreated());
                        Assertions.assertTrue(outcome.getResource() instanceof Observation);
                        Assertions.assertNotNull(outcome.getResource());
                        Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
                }
        }

        @Test
        public void createObservationLab() throws IOException {

                String resource = getContent("classpath:/Observation/observation-observationlab-example.json");
                resource = resource.replaceAll(
                        "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                        "Patient/" + this.subjectIdValue);

                MethodOutcome outcome = client.create().resource(resource).execute();

                Assertions.assertEquals(true, outcome.getCreated());
                Assertions.assertTrue(outcome.getResource() instanceof Observation);
                Assertions.assertNotNull(outcome.getResource());
                Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
        }

        @Test
        public void createObservationUsingDefaultProfile() {
                UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                        () -> client.create()
                                .resource(getContent("classpath:/Observation/observation-example.json"))
                                .execute());

                OperationOutcome operationOutcome = (OperationOutcome) exception.getOperationOutcome();
                Assertions.assertEquals(1, operationOutcome.getIssue().size());
                Assertions.assertEquals(
                        "Default profile is not supported for Observation. One of the following profiles is expected: "
                                + "[http://hl7.org/fhir/StructureDefinition/bodytemp, https://charite.infectioncontrol.de/fhir/core/StructureDefinition/CoronavirusNachweisTest, "
                                + "https://www.medizininformatik-initiative.de/fhir/core/StructureDefinition/ObservationLab]",
                        OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()));
        }

        @Test
        public void createObservationUsingUnsupportedProfile() {
                UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                        () -> client.create().resource(getContent(
                                "classpath:/Observation/observation-vitalsigns-example.json"))
                                .execute());

                OperationOutcome operationOutcome = (OperationOutcome) exception.getOperationOutcome();
                Assertions.assertEquals(1, operationOutcome.getIssue().size());
                Assertions.assertEquals(
                        "Profile http://hl7.org/fhir/StructureDefinition/vitalsigns is not supported for Observation. One of the following profiles is expected: "
                                + "[http://hl7.org/fhir/StructureDefinition/bodytemp, https://charite.infectioncontrol.de/fhir/core/StructureDefinition/CoronavirusNachweisTest, "
                                + "https://www.medizininformatik-initiative.de/fhir/core/StructureDefinition/ObservationLab]",
                        OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()));
        }

        @Test
        public void createQuestionnaireResponse() throws IOException {

                String resource = getContent("classpath:/QuestionnaireResponse/covapp-response.json");
                resource = resource.replaceAll(
                        "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                        "Patient/" + this.subjectIdValue);

                MethodOutcome outcome = client.create().resource(resource).execute();

                Assertions.assertEquals(true, outcome.getCreated());
                Assertions.assertTrue(outcome.getResource() instanceof QuestionnaireResponse);
                Assertions.assertNotNull(outcome.getResource());
                Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
        }

        // FIXME: we need to use the status in the create ehr service, we are using null
        // in the client library because the current create has an issue.
        // for now the workaround is to use the Insomnia request to create the EHR for
        // this patient before running the tests
        @Test
        public void testEhrExistsDoesExist() {
                // Create EHR and test the EHR exists for the given patient ID
                EhrStatus ehrStatus = new EhrStatus();

                String subjectIdValue = UUID.randomUUID().toString();
                HierObjectId subjectId = new HierObjectId(subjectIdValue);
                ehrStatus.setSubject(new PartySelf(new PartyRef(subjectId, "demographic", "PERSON")));

                ehrStatus.setArchetypeNodeId("openEHR-EHR-EHR_STATUS.generic.v1");
                ehrStatus.setName(new DvText("test status"));

                UUID ehrId = service.createEhr(ehrStatus);

                Assertions.assertNotNull(ehrId);

                Assertions.assertTrue(service.ehrExistsBySubjectId(subjectIdValue));
        }

        @Test
        public void testEhrExistsDoesNotExist() {
                Assertions.assertFalse(service.ehrExistsBySubjectId("xxxxx"));
        }

        @Test
        public void searchBodyTemp() throws IOException {

                // Needs at least one temp, can't rely on the tess execution order to create a
                // body temp in the server
                String resource = getContent("classpath:/Observation/observation-bodytemp-example.json");
                resource = resource.replaceAll(
                        "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                        "Patient/" + this.subjectIdValue);

                MethodOutcome outcome = client.create().resource(resource).execute();

                Bundle bundle = client.search().forResource(Observation.class).withProfile(Profile.BODY_TEMP.getUrl())
                        .where(Patient.IDENTIFIER.exactly().identifier(this.subjectIdValue))
                        .returnBundle(Bundle.class).execute();

                Assertions.assertTrue(bundle.getTotal() > 0);
        }

        @Test
        public void searchCoronavirusLabResults() throws IOException {

                // Needs at least one lab result, can't rely on the tess execution order
                // WARNING: this will fail if terminology validation is turned on
                String resource = getContent("classpath:/Observation/observation-coronavirusnachweistest-example.json");
                resource = resource.replaceAll(
                        "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                        "Patient/" + this.subjectIdValue);

                MethodOutcome outcome = client.create().resource(resource).execute();

                Bundle bundle = client.search().forResource(Observation.class)
                        .withProfile(Profile.CORONARIRUS_NACHWEIS_TEST.getUrl())
                        .where(Patient.IDENTIFIER.exactly().identifier(this.subjectIdValue))
                        .returnBundle(Bundle.class).execute();

                Assertions.assertTrue(bundle.getTotal() > 0);
        }

        @Test
        public void searchObservationLab() throws IOException {

                // Needs at least one observation lab, can't rely on the tess execution order
                String resource = getContent("classpath:/Observation/observation-observationlab-example.json");
                resource = resource.replaceAll(
                        "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                        "Patient/" + this.subjectIdValue);

                MethodOutcome outcome = client.create().resource(resource).execute();

                Bundle bundle = client.search().forResource(Observation.class)
                        .withProfile(Profile.OBSERVATION_LAB.getUrl())
                        .where(Patient.IDENTIFIER.exactly().identifier(this.subjectIdValue))
                        .returnBundle(Bundle.class).execute();

                Assertions.assertTrue(bundle.getTotal() > 0);
        }

        @Test
        public void searchDiagnoseCondition() throws IOException {

                // Needs at least one condition, can't rely on the tess execution order
                String resource = getContent("classpath:/Condition/condition-example.json");
                resource = resource.replaceAll(
                        "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                        "Patient/" + this.subjectIdValue);

                MethodOutcome outcome = client.create().resource(resource).execute();

                Bundle bundle = client.search().forResource(Condition.class)
                        .where(Patient.IDENTIFIER.exactly().identifier(this.subjectIdValue))
                        .returnBundle(Bundle.class).execute();

                logger.info("CONDITIONS: " + bundle.getTotal());

                Assertions.assertTrue(bundle.getTotal() > 0);
        }

        private String getContent(String location) throws IOException {
                Resource resource = resourceLoader.getResource(location);
                try (InputStream input = resource.getInputStream()) {
                        return IOUtils.toString(input, StandardCharsets.UTF_8);
                }
        }

        @Test
        public void createObservationBodyHeight() throws IOException {
                String resource = getContent("classpath:/Observation/observation-bodyheight-example.json");
                resource = resource.replaceAll("Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})", "Patient/" + this.subjectIdValue);
                MethodOutcome outcome = client.create()
                        .resource(resource)
                        .execute(); //Where does this get executed?
                Assertions.assertEquals(true, outcome.getCreated());
                Assertions.assertTrue(outcome.getResource() instanceof Observation);
                Assertions.assertNotNull(outcome.getResource());
                Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
        }

}


