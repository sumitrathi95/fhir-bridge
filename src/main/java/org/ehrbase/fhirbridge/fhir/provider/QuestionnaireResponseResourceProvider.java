package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.springframework.stereotype.Component;

@Component
public class QuestionnaireResponseResourceProvider extends AbstractResourceProvider<QuestionnaireResponse> {

    public QuestionnaireResponseResourceProvider(FhirContext fhirContext,
                                                 IFhirResourceDao<QuestionnaireResponse> questionnaireResponseDao,
                                                 EhrbaseService ehrbaseService) {
        super(fhirContext, QuestionnaireResponse.class, questionnaireResponseDao, ehrbaseService);
    }

    @Create
    public MethodOutcome createQuestionnaireResponse(@ResourceParam QuestionnaireResponse questionnaireResponse) {
        fhirResourceDao.create(questionnaireResponse);
        return new MethodOutcome()
                .setCreated(true)
                .setResource(questionnaireResponse);
    }
}
