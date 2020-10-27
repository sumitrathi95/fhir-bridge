package org.ehrbase.fhirbridge;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import ca.uhn.fhir.util.OperationOutcomeUtil;
import org.hl7.fhir.r4.model.Bundle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BundleTest extends FhirBridgeApplicationTestFactory {
    @Test
    public void createBloodGasPanel() throws IOException {

        String resource = getContent("classpath:/Bundle/BloodGas.json");

        resource = resource.replaceAll(PATIENT_REFERENCE_REGEXP, this.patientReference);

        MethodOutcome outcome = client.create().resource(resource).execute();

        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertTrue(outcome.getResource() instanceof Bundle);
        Assertions.assertNotNull(outcome.getResource());
        Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
    }


    @Test
    public void createBloodGasPanelOnlyPH() throws IOException {

        String resource = getContent("classpath:/Bundle/OnlyWithPH.json");
        resource = resource.replaceAll(PATIENT_REFERENCE_REGEXP, this.patientReference);


        MethodOutcome outcome = client.create().resource(resource).execute();

        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertTrue(outcome.getResource() instanceof Bundle);
        Assertions.assertNotNull(outcome.getResource());
        Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
    }


    @Test
    public void createBloodGasPanelWrongSubjectId() throws IOException {
        InternalErrorException exception = Assertions.assertThrows(InternalErrorException.class,
                () -> client.create().resource(getContent(
                        "classpath:/Bundle/WrongSubjectIds.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "The subject Ids of the profile within the Bundle reference different Patient. A Blood Gas Panel must refer to one identical Patient!");
    }

    @Test
    public void createBloodGasPanelIncludingInvalidProfile() throws IOException {
        InternalErrorException exception = Assertions.assertThrows(InternalErrorException.class,
                () -> client.create().resource(getContent(
                        "classpath:/Bundle/AdditionalInvalidProfile.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "Blood gas panel bundle needs to contain only the profiles for the blood gas panel. Please delete profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/inhaled-oxygen-concentration from the Bundle.");
    }

    @Test
    public void createBloodGasWithoutBloodGasPanelProfile() throws IOException {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/Bundle/NoBloodGasPanelProfile.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "The Bundle provided is not supported. Supported is currently only a Bundle containing the profiles for Blood Gas Panel");
    }

    @Test
    public void createBloodGasWithMissingProfiles() throws IOException {
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/Bundle/MissingProfiles.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "Bundle Blood gas panel needs to contain at least one of the following profiles: oxygen partial pressure, carbon dioxide partial pressure, ph or oxygen saturation");
    }

    @Test
    public void createBloodGasWithDuplicatedProfile() throws IOException {
        InternalErrorException exception = Assertions.assertThrows(InternalErrorException.class,
                () -> client.create().resource(getContent(
                        "classpath:/Bundle/DuplicateProfile.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "Oxygen partial pressure profile is duplicated within the bundle, please delete one of them");
    }


    @Test
    public void createBloodGasWithDuplicatedProfile2() throws IOException {
        InternalErrorException exception = Assertions.assertThrows(InternalErrorException.class,
                () -> client.create().resource(getContent(
                        "classpath:/Bundle/DuplicateProfile2.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "Oxygen saturation profile is duplicated within the bundle, please delete one of them");
    }

}
