package org.ehrbase.fhirbridge.fhir.provider.Bundle;

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.mapping.BlutGasAnalyse.FHIRObservationBloodGasOpenehrBlutgasAnalyse;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

//TODO refactor this class since its also used as a POJO which is not very clean since providing functionality access at wrong places
public class BloodGasPanelBundle extends SupportedBundle {
    private final String bloodGasUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-gas-panel";
    private final String pHUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/pH";
    private final String carbonDioxidePartialPressureUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/carbon-dioxide-partial-pressure";
    private final String oxygenPartialPressureUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-partial-pressure";
    private final String oxygenSaturationUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/oxygen-saturation";

    //TODO find a better solution as this
    private Optional<Observation>  bloodGasPanel = Optional.empty();
    private Optional<Observation>  pH = Optional.empty();
    private Optional<Observation>  carbonDioxidePartialPressure = Optional.empty();
    private Optional<Observation>  oxygenPartialPressure = Optional.empty();
    private Optional<Observation>  oxygenSaturation = Optional.empty();

    public BloodGasPanelBundle(Bundle bundle) {
        super(bundle);
        setObservations();
    }

    @Override
    public MappedBundleComposition processBundle() {
        SupportedBundle.logger.info(">>>>>>>>>>>>>>>>>> BLOOD GAS PANEL");
        String ehrUid = getEhrUID();
        return new MappedBundleComposition(FHIRObservationBloodGasOpenehrBlutgasAnalyse.map(this), ehrUid);
    }

    @Override
    public void createdLog(VersionUid versionUid) {
        SupportedBundle.logger.info("Composition created with UID {} for FHIR profile {}", versionUid, Profile.BLOOD_GAS);
    }

    //TODO check if all profiles are necessary and which one can be left out
    private void setObservations() {
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            setProfiles(entry);
        }
        checkIfObservationsComplete();
    }

    private void setProfiles(BundleEntryComponent entry){
        try {
            String profileUrl = entry.getResource().getMeta().getProfile().get(0).getValue();
        switch (profileUrl) {
            case bloodGasUrl:
                this.bloodGasPanel= Optional.of((Observation) entry.getResource());
                break;
            case pHUrl:
                this.pH = Optional.of((Observation) entry.getResource());
                break;
            case carbonDioxidePartialPressureUrl:
                this.carbonDioxidePartialPressure = Optional.of((Observation) entry.getResource());
                break;
            case oxygenPartialPressureUrl:
                this.oxygenPartialPressure = Optional.of((Observation) entry.getResource());
                break;
            case oxygenSaturationUrl:
                this.oxygenSaturation = Optional.of((Observation) entry.getResource());
                break;
            default:
                throw new InternalErrorException("Blood gas panel bundle needs to contain only the profiles for the blood gas panel. Please delete profile " + profileUrl + " from the Bundle.");
        }
        }catch (IndexOutOfBoundsException e){
            throw new UnprocessableEntityException("Make sure only the for Blood Gas Panel supported Profiles are contained in the Bundle these are: blood gas panel, oxygen saturation, carbon dioxide saturation, ph, oxygen partaial pressure");
        }
    }

    private void checkIfObservationsComplete() {
        if(!checkIfOneProfileIsPresent()){
            throw new UnprocessableEntityException("Bundle Blood gas panel needs to contain at least one of the following profiles: oxygen partial pressure, carbon dioxide partial pressure" +
                                ", ph or oxygen saturation");
        }
    }

    private boolean checkIfOneProfileIsPresent(){
        return oxygenPartialPressure.isPresent() || carbonDioxidePartialPressure.isPresent() || pH.isPresent() || oxygenSaturation.isPresent();
    }


    public Observation getBloodGasPanel() {
        return bloodGasPanel.get();
    }

    public Optional<Observation> getpH() {
        return pH;
    }

    public Optional<Observation> getCarbonDioxidePartialPressure() {
        return carbonDioxidePartialPressure;
    }

    public Optional<Observation> getOxygenPartialPressure() {
        return oxygenPartialPressure;
    }

    public Optional<Observation> getOxygenSaturation() {
        return oxygenSaturation;
    }

}
