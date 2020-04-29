package org.ehrbase.fhirbridge.rest;

import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.DiagnoseComposition;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.IntensivmedizinischesMonitoringKorpertemperaturComposition;
import org.ehrbase.fhirbridge.opt.kennzeichnungerregernachweissarscov2composition.KennzeichnungErregernachweisSARSCoV2Composition;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.LaborbefundComposition;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EhrbaseService {

    private final DefaultRestClient client;

    public EhrbaseService(DefaultRestClient client) {
        this.client = client;
    }

    public UUID createEhr() {
        return client.ehrEndpoint().createEhr();
    }

    public VersionUid saveLab(UUID ehrId, LaborbefundComposition composition) {
        // TODO invoke post processing

        client.compositionEndpoint(ehrId).mergeCompositionEntity(composition);

        return composition.getVersionUid();
    }

    public VersionUid saveDiagnosis(UUID ehrId, DiagnoseComposition composition) {
        // TODO invoke post processing

        client.compositionEndpoint(ehrId).mergeCompositionEntity(composition);

        return composition.getVersionUid();
    }

    public VersionUid saveTemp(UUID ehrId, IntensivmedizinischesMonitoringKorpertemperaturComposition composition) {
        // TODO invoke post processing

        client.compositionEndpoint(ehrId).mergeCompositionEntity(composition);

        return composition.getVersionUid();
    }

    public VersionUid saveTest(UUID ehrId, KennzeichnungErregernachweisSARSCoV2Composition composition) {
        // TODO invoke post processing

        client.compositionEndpoint(ehrId).mergeCompositionEntity(composition);

        return composition.getVersionUid();
    }
}
