package org.ehrbase.fhirbridge.rest;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.DiagnoseComposition;
import org.ehrbase.fhirbridge.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
import org.ehrbase.fhirbridge.opt.geccolaborbefundcomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.opt.geccolaborbefundcomposition.definition.ProLaboranalytAnalytResultatDvquantity;
import org.ehrbase.fhirbridge.opt.geccolaborbefundcomposition.definition.ProLaboranalytCluster;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.IntensivmedizinischesMonitoringKorpertemperaturComposition;
import org.ehrbase.fhirbridge.opt.kennzeichnungerregernachweissarscov2composition.KennzeichnungErregernachweisSARSCoV2Composition;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/rest")
public class OpenEhrController {

    private final EhrbaseService service;

    @Autowired
    public OpenEhrController(EhrbaseService service) {
        this.service = service;
    }

    // probably not to include in end-product, but tester and helper endpoint for now
    @PostMapping(path = "/ehr")
    public ResponseEntity<UUID> postEhr() {
        UUID ehrId = service.createEhr();
        return ResponseEntity.ok(ehrId);
    }

    @PostMapping(path = "/{ehr_id}/labor")
    public ResponseEntity<VersionUid> postLabor(
            @PathVariable(value = "ehr_id") UUID ehrId,
            @RequestBody GECCOLaborbefundComposition body) {

        // TESTING      TODO: remove
        body = new GECCOLaborbefundComposition();
        body.setComposer(new PartySelf());
        body.setLanguage(Language.DE);
        body.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        body.setStartTimeValue(OffsetDateTime.now());
        body.setSettingDefiningcode(SettingDefiningcode.NURSING_HOME_CARE);
        body.setTerritory(Territory.DE);

        ProLaboranalytAnalytResultatDvquantity result_value = new ProLaboranalytAnalytResultatDvquantity();
        result_value.setAnalytResultatMagnitude(11.00);
        result_value.setAnalytResultatUnits("mg");

        ProLaboranalytCluster result_cluster = new ProLaboranalytCluster();
        result_cluster.setAnalytResultat(result_value);


        LaborergebnisObservation result_obs = new LaborergebnisObservation();

        result_obs.setProLaboranalyt(result_cluster);

        body.setLaborergebnis(result_obs);
        // END

        VersionUid versionUid = service.saveLab(ehrId, body);
        return ResponseEntity.ok(versionUid);
    }


    @PostMapping(path = "/{ehr_id}/diagnosis")
    public ResponseEntity<VersionUid> postDiagnosis(
            @PathVariable(value = "ehr_id") UUID ehrId,
            @RequestBody DiagnoseComposition body) {
        VersionUid versionUid = service.saveDiagnosis(ehrId, body);
        return ResponseEntity.ok(versionUid);
    }

    @PostMapping(path = "/{ehr_id}/temp")
    public ResponseEntity<VersionUid> postTemp(
            @PathVariable(value = "ehr_id") UUID ehrId,
            @RequestBody IntensivmedizinischesMonitoringKorpertemperaturComposition body) {
        VersionUid versionUid = service.saveTemp(ehrId, body);
        return ResponseEntity.ok(versionUid);
    }

    @PostMapping(path = "/{ehr_id}/test")
    public ResponseEntity<VersionUid> postTest(
            @PathVariable(value = "ehr_id") UUID ehrId,
            @RequestBody KennzeichnungErregernachweisSARSCoV2Composition body) {
        VersionUid versionUid = service.saveTest(ehrId, body);
        return ResponseEntity.ok(versionUid);
    }

}
