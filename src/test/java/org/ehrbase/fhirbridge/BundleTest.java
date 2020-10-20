package org.ehrbase.fhirbridge;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import ca.uhn.fhir.util.OperationOutcomeUtil;
import org.hl7.fhir.r4.model.Bundle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class BundleTest extends FhirBridgeApplicationTestFactory {
    @Test
    public void createBloodGasPanel() throws IOException {

        String resource = getContent("classpath:/Bundle/BloodGas.json");
        resource = resource.replaceAll(
                "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                "Patient/" + subjectIdValue);

        MethodOutcome outcome = client.create().resource(resource).execute();

        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertTrue(outcome.getResource() instanceof Bundle);
        Assertions.assertNotNull(outcome.getResource());
        Assertions.assertEquals("1", outcome.getResource().getMeta().getVersionId());
    }


    @Test
    public void createBloodGasPanelOnlyPH() throws IOException {

        String resource = getContent("classpath:/Bundle/OnlyWithPH.json");
        resource = resource.replaceAll(
                "Patient/([0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12})",
                "Patient/" + this.subjectIdValue);

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
        UnprocessableEntityException exception = Assertions.assertThrows(UnprocessableEntityException.class,
                () -> client.create().resource(getContent(
                        "classpath:/Bundle/ToManyProfiles.json"))
                        .execute());

        Assertions.assertEquals(OperationOutcomeUtil.getFirstIssueDetails(context, exception.getOperationOutcome()), "Make sure only the for Blood Gas Panel supported Profiles are contained in the Bundle these are: blood gas panel, oxygen saturation, carbon dioxide saturation, ph, oxygen partaial pressure");
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



}
