package org.ehrbase.fhirbridge.fhir.provider;


import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.fhir.audit.AuditService;
import org.ehrbase.fhirbridge.fhir.resource.bundle.BloodGasPanelBundle;
import org.ehrbase.fhirbridge.fhir.resource.bundle.MappedBundleComposition;
import org.ehrbase.fhirbridge.fhir.resource.bundle.SupportedBundle;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class TransactionProvider extends AbstractResourceProvider {

    private final Logger logger = LoggerFactory.getLogger(TransactionProvider.class);

    @Autowired
    public TransactionProvider(FhirContext fhirContext, EhrbaseService ehrbaseService, AuditService auditService) {
        super(fhirContext, ehrbaseService, auditService);
    }

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome create(@ResourceParam Bundle bundle)
    {
        logger.info("Bundle.id={}", bundle.getId());

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
        saveCompositions(supportedBundle.processBundle(), supportedBundle);
    }

    protected void saveCompositions(MappedBundleComposition composition, SupportedBundle supportedBundle){
        UUID ehrUid = getEhrUidForSubjectId(composition.getSubjectId());
        VersionUid versionUid = ehrbaseService.saveComposition(ehrUid, composition.getComposition());
        supportedBundle.createdLog(versionUid);
    }

    private SupportedBundle getBundleType(Bundle bundle){
        Optional<SupportedBundle> supportedBundle;
        for (Bundle.BundleEntryComponent bundleEntryComponent:bundle.getEntry()) {
            supportedBundle = determineBundleType(bundleEntryComponent.getResource().getMeta().getProfile().get(0).getValueAsString(), bundle);
            if(supportedBundle.isPresent()){
                return supportedBundle.get();
            }
        }
        throw new UnprocessableEntityException("The Bundle provided is not supported. Supported is currently only a Bundle containing the profiles for Blood Gas Panel");
    }

    private Optional<SupportedBundle> determineBundleType(String profileUrl, Bundle bundle) {
        if(profileUrl.equals(Profile.BLOOD_GAS.getUrl())) {
            return Optional.of(new BloodGasPanelBundle(bundle));
        }
            return Optional.empty();
    }


    @Override
    public Class<Bundle> getResourceType() {
        return Bundle.class;
    }
}
