package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.InstantType;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.springframework.stereotype.Component;

@Component
public class QuestionnaireResponseResourceProvider extends AbstractResourceProvider{

    public QuestionnaireResponseResourceProvider(FhirContext context, EhrbaseService service) {
        super(context, service);
    }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return QuestionnaireResponse.class;
    }

    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createQuestionnaireResponse(@ResourceParam QuestionnaireResponse questionnaireResponse) {

        questionnaireResponse.setId(new IdType(1L));
        questionnaireResponse.getMeta().setVersionId("1");
        questionnaireResponse.getMeta().setLastUpdatedElement(InstantType.withCurrentTime());

        return new MethodOutcome()
                .setCreated(true)
                .setResource(questionnaireResponse);
    }
}
