package org.ehrbase.fhirbridge.fhir.provider;


import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Transaction;
import ca.uhn.fhir.rest.annotation.TransactionParam;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.fhir.provider.Bundle.BloodGasPanelBundle;
import org.ehrbase.fhirbridge.fhir.provider.Bundle.MappedComposition;
import org.ehrbase.fhirbridge.fhir.provider.Bundle.SupportedBundle;
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

    protected void saveCompositions(ArrayList<MappedComposition> compositions, SupportedBundle supportedBundle){
        for (MappedComposition mappedComposition: compositions) {
            UUID ehrUid = getEhrUidForSubjectId(mappedComposition.getSubjectId());
            VersionUid versionUid = service.save(ehrUid, mappedComposition.getComposition());
            supportedBundle.createdLog(versionUid);
        }
    }

    private SupportedBundle getBundleType(Bundle bundle){
        //FIXME try to avoid using null
        SupportedBundle supportedBundle;
        for (Bundle.BundleEntryComponent bundleEntryComponent:bundle.getEntry()) {
            String profileUrl = bundleEntryComponent.getResource().getMeta().getProfile().get(0).getValueAsString();
            supportedBundle = determineBundleType(profileUrl, bundle);
            if(supportedBundle != null){
                return supportedBundle;
            }
        }
        throw new UnprocessableEntityException("The Bundle provided is not supported. Supported is currently only a Bundle containing the profiles for Blood Gas Panel"); //TODO define this in a more generic way!
    }

    private SupportedBundle determineBundleType(String profileUrl, Bundle bundle) {
        if(profileUrl.equals(Profile.BLOOD_GAS.getUrl())){
            return new BloodGasPanelBundle(bundle);
        }else{
            return null;
        }
    }


    @Override
    public Class<Bundle> getResourceType() {
        return Bundle.class;
    }
}
