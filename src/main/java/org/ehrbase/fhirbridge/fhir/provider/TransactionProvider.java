package org.ehrbase.fhirbridge.fhir.provider;


import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Transaction;
import ca.uhn.fhir.rest.annotation.TransactionParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.fhir.provider.Bundle.BloodGasPanelBundle;
import org.ehrbase.fhirbridge.fhir.provider.Bundle.MappedComposition;
import org.ehrbase.fhirbridge.fhir.provider.Bundle.SupportedBundle;
import org.ehrbase.fhirbridge.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
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
      //      createBundle(bundle);

        return bundle;
    }

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome create(@ResourceParam Bundle bundle)
    {
        logger.info("Bundle.id={}", bundle.getId());
        //       try {
//          createBundle(bundle);
//        }catch (Exception e)
//        {
//            throw new UnprocessableEntityException("There was a problem saving the composition: " + e.getMessage(), e);
//        }
        createBundle(bundle);

        bundle.setId(new IdType(1L));
        bundle.getMeta().setVersionId("1");
        bundle.getMeta().setLastUpdatedElement(InstantType.withCurrentTime());

        return new MethodOutcome()
                .setCreated(true)
                .setResource(bundle);

    }

    private void createBundle( Bundle bundle){
        SupportedBundle supportedBundle = getBundleType(bundle);
        MappedComposition composition = supportedBundle.processBundle();
        saveCompositions(composition, supportedBundle);
    }

    protected void saveCompositions(MappedComposition composition, SupportedBundle supportedBundle){
        UUID ehrUid = getEhrUidForSubjectId(composition.getSubjectId());
       // VersionUid versionUid = service.saveBlutGas(ehrUid, new BefundDerBlutgasanalyseComposition());
       // logger.info(composition.toString());
        VersionUid versionUid = service.saveBlutGas(ehrUid, composition.getComposition());
        supportedBundle.createdLog(versionUid);

//        for (MappedComposition mappedComposition: compositions) {
//            UUID ehrUid = getEhrUidForSubjectId(mappedComposition.getSubjectId());
//            VersionUid versionUid = service.saveBlutGas(ehrUid, (BefundDerBlutgasanalyseComposition) mappedComposition.getComposition());
//            supportedBundle.createdLog(versionUid);
//        }

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
