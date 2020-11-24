package org.ehrbase.fhirbridge;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import ca.uhn.fhir.util.OperationOutcomeUtil;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.zip.DataFormatException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class D4LQuestionnaireIT extends FhirBridgeApplicationTestAbstract {

    @Test
    public void createQuestionnaire() throws IOException {
        String resource = getContent("classpath:/QuestionnaireResponse/covapp-response_old.json");
        resource = resource.replaceAll(PATIENT_REFERENCE_REGEXP, this.patientReference);
        MethodOutcome outcome = client.create().resource(resource).execute();
        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertTrue(outcome.getResource() instanceof QuestionnaireResponse);
        Assertions.assertNotNull(outcome.getResource());
        Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
    }


    @Test
    public void createWithPregnancy() throws IOException {
        String resource = getContent("classpath:/QuestionnaireResponse/with-pregnant.json");
        resource = resource.replaceAll(PATIENT_REFERENCE_REGEXP, this.patientReference);
        MethodOutcome outcome = client.create().resource(resource).execute();
        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertTrue(outcome.getResource() instanceof QuestionnaireResponse);
        Assertions.assertNotNull(outcome.getResource());
        Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
    }

    @Test
    public void createWithLivingSituation() throws IOException {
        String resource = getContent("classpath:/QuestionnaireResponse/with-living-situation.json");
        resource = resource.replaceAll(PATIENT_REFERENCE_REGEXP, this.patientReference);
        MethodOutcome outcome = client.create().resource(resource).execute();
        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertTrue(outcome.getResource() instanceof QuestionnaireResponse);
        Assertions.assertNotNull(outcome.getResource());
        Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
    }


    @Test
    public void createWithImmunosuppresant() throws IOException {
        String resource = getContent("classpath:/QuestionnaireResponse/with-immunosuppresant.json");
        resource = resource.replaceAll(PATIENT_REFERENCE_REGEXP, this.patientReference);
        MethodOutcome outcome = client.create().resource(resource).execute();
        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertTrue(outcome.getResource() instanceof QuestionnaireResponse);
        Assertions.assertNotNull(outcome.getResource());
        Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
    }


    @Test
    public void createWithWrongQuestionLinkId() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-linkID.json"))
                        .execute());
        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "LinkId P123 undefined");
    }

    @Test
    public void createWithWrongAgeCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-age.json"))
                        .execute());
        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "The code for age:123-50 cannot be mapped, plese enter a valid code e.g. 61-70");

    }


    @Test
    public void createWithWrongWohnungssituationCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-living-situation.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "The code for Wohnungsituation:12213213 cannot be mapped, please enter a valid code e.g. Wohnt mit anderen zusammen (LOINC: LA9996-5)");
    }

    @Test
    public void createWithWrongPregnancyCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-pregnant.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "The code for Pregnancy:asas cannot be mapped, please enter a valid code e.g. pregnant (LA15173-0), not pregnant (LA26683-5) or unknown(LA4489-6) )");
    }


    @Test
    public void createWithWrongChronicLungDiseaseCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-chronic-lung-disease.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "The code LA126ddd88-0 for Question: ChronischeLungenkrankheit cannot be mapped, please enter a valid code valid codes are: Yes (LA33-6), No (LA32-8), dont know (LA12688-0)");
    }


    @Test
    public void createWithWrongObesity() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-obesity.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "The code LAassa32-8 for Question: Adipositas cannot be mapped, please enter a valid code valid codes are: Yes (LA33-6), No (LA32-8), dont know (LA12688-0)");
    }

    @Test
    public void createWithWrongHeartDiseaseCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-heart-disease.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "The code LAassa32-8 for Question: Herzerkrankung cannot be mapped, please enter a valid code valid codes are: Yes (LA33-6), No (LA32-8), dont know (LA12688-0)");
    }

    @Test
    public void createWithWrongDiabetesCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-diabetes.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "The code asaasd-0 for Question: Diabetes cannot be mapped, please enter a valid code valid codes are: Yes (LA33-6), No (LA32-8), dont know (LA12688-0)");
    }

    @Test
    public void createWithWrongFluencaCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-fluenca.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LA3asd2-8\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }

    @Test
    public void createWithWrongImmunosuppressantCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-immunosuppresant.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"asd\" cannot be mapped to boolean, has to be either LA33-6, LA33-8 or LA12688-0");
    }

    @Test
    public void createWithWrongSteroidsCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-steroids.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LA12688asd-0\" cannot be mapped to boolean, has to be either LA33-6, LA33-8 or LA12688-0");
    }

    @Test
    public void createWithWrongFever24hCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-fever24h.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LA32-asdf\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }

    @Test
    public void createWithWrongFever4DaysCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-fever-4days.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LA33-ad6\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }

    @Test
    public void createWithWrongChillsCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-chills.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LA3sad3-6\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }

    @Test
    public void createWithWrongTiredCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-tired.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LA3ads3-6\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }


    @Test
    public void createWithWrongBodyAches() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-bodyaches.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LA3asd2-8\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }


    @Test
    public void createWithWrongPresistentCoughing() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-persistent-coughing.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LAasd33-6\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }

    @Test
    public void createWithWrongRhinitis() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-rhinitis.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LA32-asd8\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }

    @Test
    public void createWithWrongDiarrhea() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-diarrhea.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LA3asd2-8\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }

    @Test
    public void createWithWrongSoreThroat() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-sore-throat.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LAasd33-6\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }

    @Test
    public void createWithWrongHeadacheCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-headache.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LAasd32-8\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }

    @Test
    public void createWithWrongProblemsBreathingCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-problems-breathing.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LA3211-8\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }

    @Test
    public void createWithWrongTasteSmellLoseCode() {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/QuestionnaireResponse/wrong-taste-smell-lose.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "\"LA3asd2-8\" cannot be mapped to boolean, has to be either LA33-6 or LA33-8");
    }



}
