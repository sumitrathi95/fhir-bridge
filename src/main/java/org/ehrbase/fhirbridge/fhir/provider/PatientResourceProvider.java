package org.ehrbase.fhirbridge.fhir.provider;

import ca.uhn.fhir.context.FhirContext;

import java.util.UUID;

import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.fhir.ProfileUtils;
import org.ehrbase.fhirbridge.fhir.audit.AuditService;
import org.ehrbase.fhirbridge.mapping.FhirPatientOpenehrGECCO_Personendaten;
import org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.GECCOPersonendatenComposition;
import org.ehrbase.fhirbridge.rest.EhrbaseService;
import org.hl7.fhir.r4.model.AuditEvent;
import org.hl7.fhir.r4.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;

@Component
public class PatientResourceProvider extends AbstractResourceProvider {

	private final Logger logger = LoggerFactory.getLogger(PatientResourceProvider.class);

    private final IFhirResourceDao<Patient> patientDao;
   
    public PatientResourceProvider(FhirContext fhirContext, EhrbaseService ehrbaseService, AuditService auditService,
            IFhirResourceDao<Patient> patientDao) {
    	super(fhirContext, ehrbaseService, auditService);
    	this.patientDao = patientDao;
	}
    
    public boolean isDefaultProfileSupported() {
        return false;
    }
    
    @Create
    @SuppressWarnings("unused")
    public MethodOutcome createPatient(@ResourceParam Patient patient) {
    	
    	checkProfiles(patient);

        patientDao.create(patient);
        //TODO fix patient.id - IFhirResourceDao seems to derive a wrong patientId for this resource type
    	//patient.setId("061c0898-17f8-4777-aa59-e7e7438af567"); //this is not enough as fix...

        auditService.registerCreateResourceSuccessEvent(patient);

        logger.info(">>>>>>>>>>>>>>>>>> Trying to create Patient/Personendaten");
        logger.info(patient.getId());//TODO patient.getId() is not retrieving the ID...
        // will throw exceptions and block the request if the patient doesn't have an EHR
        UUID ehrUid = getEhrUidForSubjectId(patient.getId()); //TODO check if Id is the correct identifier for ehr

        try {
            if (ProfileUtils.hasProfile(patient, Profile.PATIENT)) {

                logger.info(">>>>>>>>>>>>>>>>>> Patient/Personendaten");

                GECCOPersonendatenComposition composition = FhirPatientOpenehrGECCO_Personendaten.map(patient);

                //UUID ehrId = service.createEhr(); // <<< reflections error!
                VersionUid versionUid = ehrbaseService.savePatient(ehrUid, composition);
                logger.info("Composition created with UID {} for FHIR profile {}", versionUid, Profile.PATIENT);
            }
            auditService.registerMapResourceEvent(AuditEvent.AuditEventOutcome._0, "Success", patient);
        } catch (Exception e) {
            auditService.registerMapResourceEvent(AuditEvent.AuditEventOutcome._8, e.getMessage(), patient);
            throw new UnprocessableEntityException("There was a problem saving the composition" + e.getMessage(), e);
        }

        return new MethodOutcome()
                .setCreated(true)
                .setResource(patient);
    }

    public UUID getEhrUidForSubjectId(String subjectId) {
        try {
            String ehrId = ehrbaseService.ehrIdBySubjectId(subjectId);
            if (ehrId != null) {
                return UUID.fromString(ehrId); // validates the format
            } else {
                throw new ResourceNotFoundException("EHR for patient " + subjectId + " doesn't exists");
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UnprocessableEntityException("Couldn't get the EHR ID", e);
        }
    }
    
	@Override
	public Class<Patient> getResourceType() {
		return Patient.class;
	}
	
}
