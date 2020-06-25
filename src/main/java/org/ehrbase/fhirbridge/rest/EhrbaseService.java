package org.ehrbase.fhirbridge.rest;

import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.client.aql.record.Record2;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient;
import org.ehrbase.fhirbridge.opt.diagnosecomposition.DiagnoseComposition;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.IntensivmedizinischesMonitoringKorpertemperaturComposition;
import org.ehrbase.fhirbridge.opt.kennzeichnungerregernachweissarscov2composition.KennzeichnungErregernachweisSARSCoV2Composition;
import org.ehrbase.fhirbridge.opt.laborbefundcomposition.LaborbefundComposition;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public boolean ehrExistsBySubjectId(String subject_id)
    {
        Query<Record1<String>> query = Query.buildNativeQuery("SELECT e/ehr_id/value FROM EHR e WHERE e/ehr_status/subject/external_ref/id/value = '"+ subject_id +"'", String.class);
        List<Record1<String>> results = new ArrayList<Record1<String>>();
        try {
            results = client.aqlEndpoint().execute(query);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        /*
        Query<Record2<String, OffsetDateTime>> query = Query.buildNativeQuery("SELECT e/ehr_id/value, e/time_created/value FROM EHR e WHERE e/ehr_status/subject/external_ref/id/value = '"+ subject_id +"'", String.class, OffsetDateTime.class);
        List<Record2<String, OffsetDateTime>> results = new ArrayList<Record2<String, OffsetDateTime>>();

        try {
            System.out.println(client.aqlEndpoint().getClass().toString());
            results = client.aqlEndpoint().execute(query);
            System.out.println("what");
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getCause().getMessage());
            e.printStackTrace();
        }
        */

        return results.size() > 0;
    }

    public DefaultRestClient getClient() {
        return client;
    }

    public String ehrIdBySubjectId(String subject_id)
    {
        Query<Record1<String>> query = Query.buildNativeQuery("SELECT e/ehr_id/value FROM EHR e WHERE e/ehr_status/subject/external_ref/id/value = '"+ subject_id +"'", String.class);
        List<Record1<String>> results = new ArrayList<Record1<String>>();
        try {
            results = client.aqlEndpoint().execute(query);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        if (results.size() > 0)
            return results.get(0).value1();
        else
            return null;
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
