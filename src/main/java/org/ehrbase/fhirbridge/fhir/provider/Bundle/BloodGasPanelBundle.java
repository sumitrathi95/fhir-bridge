package org.ehrbase.fhirbridge.fhir.provider.Bundle;

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.FHIRObservationBloodGasOpenehrBlutgasAnalyse;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;

public class BloodGasPanelBundle extends SupportedBundle {
    private final String bloodGasUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-gas-panel";
    private final String pHUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pH";
    private final String carbonDioxidePartialPressureUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/carbon-dioxide-partial-pressure";
    private final String oxygenPartialPressureUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-partial-pressure";
    private final String oxygenSaturationUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-saturation";

    private Observation bloodGasPanel;
    private Observation pH;
    private Observation carbonDioxidePartialPressure;
    private Observation oxygenPartialPressure;
    private Observation oxygenSaturation;

    public BloodGasPanelBundle(Bundle bundle) {
        super(bundle);
        setObservations();
    }

    @Override
    public MappedComposition processBundle() {
        SupportedBundle.logger.info(">>>>>>>>>>>>>>>>>> BLOOD GAS PANEL");
        String ehrUid = getEhrUID();
        return new MappedComposition(FHIRObservationBloodGasOpenehrBlutgasAnalyse.map(bloodGasPanel, oxygenPartialPressure, pH, carbonDioxidePartialPressure, oxygenSaturation), ehrUid);
    }

    @Override
    public void createdLog(VersionUid versionUid) {
        SupportedBundle.logger.info("Composition created with UID {} for FHIR profile {}", versionUid, Profile.BLOOD_GAS);
    }

    //TODO check if all profiles are necessary and which one can be left out
    private void setObservations() {
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            String profileUrl = entry.getResource().getMeta().getProfile().get(0).getValue();
            switch (profileUrl) {
                case bloodGasUrl:
                    this.bloodGasPanel = (Observation) entry.getResource();
                    break;
                case pHUrl:
                    this.pH = (Observation) entry.getResource();
                    break;
                case carbonDioxidePartialPressureUrl:
                    this.carbonDioxidePartialPressure = (Observation) entry.getResource();
                    break;
                case oxygenPartialPressureUrl:
                    this.oxygenPartialPressure = (Observation) entry.getResource();
                    break;
                case oxygenSaturationUrl:
                    this.oxygenSaturation = (Observation) entry.getResource();
                    break;
                default:
                    //TODO make this more generic one method for every Bundle
                    throw new UnprocessableEntityException("Blood gas panel bundle needs to contain only the profiles for the blood gas panel. Please delete profile " + profileUrl + " from the Bundle.");
            }
        }
        checkIfAllObservationsSet();
    }

    private void checkIfAllObservationsSet() {
        if (bloodGasPanel == null || oxygenPartialPressure == null || carbonDioxidePartialPressure == null || pH == null || oxygenSaturation == null)
            throw new NullPointerException("Bundle does not contain all necessary profiles for Blood Gas Panel, please check that all profiles are present");
    }
    //TODO refactor and move to Supported Bundle
    private String getEhrUID() {
        String ehrUID = "";
        for (Bundle.BundleEntryComponent bundle : bundle.getEntry()) {
            if (!bundle.getResource().getResourceType().toString().equals("Patient")) {
                Observation observation = (Observation) bundle.getResource();
                if (ehrUID.equals("") || ehrUID.equals(observation.getSubject().getReference().split("/")[1])) {
                    ehrUID = observation.getSubject().getReference().split("/")[1];
                } else {
                    throw new InternalErrorException("The subject Ids of the profile within the Bundle reference different Patient. A Blood Gas Panel must refer to one identical Patient!");
                }
            }
        }
        return ehrUID;
    }

}
