package org.ehrbase.fhirbridge.fhir.provider.Bundle;

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.FHIRObservationBloodGasOpenehrBlutgasAnalyse;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;

public class BloodGasPanelBundle extends SupportedBundle {
    private final String bloodGasUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-gas-panel";
    private final String pHUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pH";
    private final String pCO2Url = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/carbon-dioxide-partial-pressure";
    private final String pO2Url = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-partial-pressure";
    private final String oxygenSaturationUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-saturation";

    private Observation bloodGasPanel;
    private Observation pH;
    private Observation pCO2;
    private Observation pO2;
    private Observation oxygenSaturation;

    public BloodGasPanelBundle(Bundle bundle) {
        super(bundle);
        setObservations();
    }

    @Override
    public ArrayList<MappedComposition> processBundle() {
        SupportedBundle.logger.info(">>>>>>>>>>>>>>>>>> BLOOD GAS PANEL {}", bundle.getIdentifier().getValue());
        String ehrUid = getEhrUID();
        return new ArrayList<>() {{
            add(new MappedComposition(FHIRObservationBloodGasOpenehrBlutgasAnalyse.map(bloodGasPanel, pO2, pH, pCO2, oxygenSaturation), ehrUid));
        }};

    }

    @Override
    public void createdLog(VersionUid versionUid) {
        SupportedBundle.logger.info("Composition created with UID {} for FHIR profile {}", versionUid, Profile.BLOOD_GAS);
    }

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
                case pCO2Url:
                    this.pCO2 = (Observation) entry.getResource();
                    break;
                case pO2Url:
                    this.pO2 = (Observation) entry.getResource();
                    break;
                case oxygenSaturationUrl:
                    this.oxygenSaturation = (Observation) entry.getResource();
                    break;
            }
        }
        checkIfAllObservationsSet();
    }

    private void checkIfAllObservationsSet() {
        if (bloodGasPanel == null || pO2 == null || pCO2 == null || pH == null || oxygenSaturation == null)
            throw new NullPointerException("Bundle does not contain all necessary profiles for Blood Gas Panel, please check that all profiles are present");
    }

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
