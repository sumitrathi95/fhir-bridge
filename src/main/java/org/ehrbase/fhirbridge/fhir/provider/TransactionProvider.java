package org.ehrbase.fhirbridge.fhir.provider;


import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Transaction;
import ca.uhn.fhir.rest.annotation.TransactionParam;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// TODO: 27/10/2020 Do we need this provider?

@Component
public class TransactionProvider extends AbstractResourceProvider<Bundle> {

    private final Logger logger = LoggerFactory.getLogger(TransactionProvider.class);

    public TransactionProvider(FhirContext fhirContext, EhrbaseService ehrbaseService) {
        super(fhirContext, Bundle.class, null, ehrbaseService);
    }

    @Transaction
    public Bundle transaction(@TransactionParam Bundle tx) {
        logger.info("Bundle.id={}", tx.getId());
        //Bundle result = new Bundle();
        //return result;
        return tx;
    }
}
