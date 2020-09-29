package org.ehrbase.fhirbridge.fhir.provider;


import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.Transaction;
import ca.uhn.fhir.rest.annotation.TransactionParam;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.fhir.provider.Bundle.BloodGasPanelBundle;
import org.ehrbase.fhirbridge.fhir.provider.Bundle.MappedComposition;
import org.ehrbase.fhirbridge.fhir.provider.Bundle.SupportedBundle;
import org.ehrbase.fhirbridge.fhir.provider.Bundle.UnspecificBundle;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.Composition;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class TransactionProvider extends AbstractResourceProvider {
    private final Logger logger = LoggerFactory.getLogger(TransactionProvider.class);

    @Autowired
    public TransactionProvider(FhirContext fhirContext, EhrbaseService service) {
        super(fhirContext, service);
    }

    @Transaction
    @SuppressWarnings("unused")
    public Bundle transaction(@TransactionParam Bundle bundle)
    {
        logger.info("Bundle.id={}", bundle.getId());
        createBundle(bundle);
        return bundle;
    }

    private void createBundle(Bundle bundle){
        SupportedBundle supportedBundle = getBundleType(bundle);
        //TODO for other Bundles its several Compositions !!!!
        ArrayList<MappedComposition> compositions = supportedBundle.processBundle();
        saveCompositions(compositions, supportedBundle);
    }

    protected  void saveCompositions(ArrayList<MappedComposition> compositions, SupportedBundle supportedBundle){
        for (MappedComposition mappedComposition: compositions) {
            UUID ehrUid = getEhrUidForSubjectId(mappedComposition.getSubjectId());
            VersionUid versionUid = service.save(ehrUid, mappedComposition.getComposition());
            supportedBundle.createdLog(versionUid);
        }
    }

    private SupportedBundle getBundleType(Bundle bundle){
        SupportedBundle supportedBundle = new UnspecificBundle(bundle);
        for (Bundle.BundleEntryComponent bundleEntryComponent:bundle.getEntry()) {
            String profileUrl = bundleEntryComponent.getResource().getMeta().getProfile().get(0).getValueAsString();
            supportedBundle = determineBundleType(profileUrl, bundle);
            if(!supportedBundle.getClass().equals(UnspecificBundle.class)){
                return supportedBundle;
            }
        }
        return supportedBundle;
    }

    private SupportedBundle determineBundleType(String profileUrl, Bundle bundle) {
        logger.info("Bundle.id={}", profileUrl);
        logger.info("Bundle.id={}", Profile.BLOOD_GAS.getUrl());

        if(profileUrl.equals(Profile.BLOOD_GAS.getUrl())){
            return new BloodGasPanelBundle(bundle);
        }else{
            return new UnspecificBundle(bundle);
        }
    }


    @Override
    public Class<Bundle> getResourceType() {
        return Bundle.class;
    }
}
