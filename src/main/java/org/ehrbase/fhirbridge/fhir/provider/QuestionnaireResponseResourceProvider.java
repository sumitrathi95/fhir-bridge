package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.fhir.audit.AuditService;
import org.ehrbase.fhirbridge.mapping.FhirSarsTestResultOpenehrPathogenDetection;
import org.ehrbase.fhirbridge.mapping.Questionnaire.FhirObservationToOpenehrQuestionnaire;
import org.ehrbase.fhirbridge.opt.d4lquestionnairecomposition.D4LQuestionnaireComposition;
import org.ehrbase.fhirbridge.opt.kennzeichnungerregernachweissarscov2composition.KennzeichnungErregernachweisSARSCoV2Composition;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QuestionnaireResponseResourceProvider extends AbstractResourceProvider {

    private final IFhirResourceDao<QuestionnaireResponse> questionnaireResponseDao;
    private final Logger logger = LoggerFactory.getLogger(ObservationResourceProvider.class);

    public QuestionnaireResponseResourceProvider(FhirContext context, EhrbaseService ehrbaseService, AuditService auditService,
                                                 IFhirResourceDao<QuestionnaireResponse> questionnaireResponseDao) {
        super(context, ehrbaseService, auditService);
        this.questionnaireResponseDao = questionnaireResponseDao;
    }

    @Create
    public MethodOutcome createQuestionnaireResponse(@ResourceParam QuestionnaireResponse questionnaireResponse) {
        questionnaireResponseDao.create(questionnaireResponse);

        mapD4LQuestionnaire(questionnaireResponse);

        auditService.registerCreateResourceSuccessEvent(questionnaireResponse);

        return new MethodOutcome()
                .setCreated(true)
                .setResource(questionnaireResponse);
    }

    private void mapD4LQuestionnaire(QuestionnaireResponse questionnaireResponse) {
        UUID ehrUid = getEhrUidForSubjectId(questionnaireResponse.getSubject().getReference().split(":")[2]); //TODO no subject id in questionnaire
        D4LQuestionnaireComposition d4LQuestionnaireComposition = new FhirObservationToOpenehrQuestionnaire().map(questionnaireResponse);
        VersionUid versionUid = ehrbaseService.saveQuestionnaire(ehrUid, d4LQuestionnaireComposition);
        logger.info("Composition created with UID {} for FHIR profile {}", versionUid, Profile.D4L_Questionnaire);
        auditService.registerMapResourceEvent(AuditEvent.AuditEventOutcome._0, "Success", questionnaireResponse);


    }

    @Override
    public Class<? extends IBaseResource> getResourceType() {
        return QuestionnaireResponse.class;
    }
}
