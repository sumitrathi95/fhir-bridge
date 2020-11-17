package org.ehrbase.fhirbridge.mapping;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.GECCOPersonendatenComposition;
import org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition.AdministrativesGeschlechtDefiningcode;
import org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition.DatenZurGeburtCluster;
import org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition.EthnischerHintergrundCluster;
import org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition.EthnischerHintergrundDefiningcode;
import org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition.GeschlechtEvaluation;
import org.ehrbase.fhirbridge.opt.geccopersonendatencomposition.definition.PersonendatenAdminEntry;
import org.ehrbase.fhirbridge.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturBeliebigesEreignisChoice;
import org.ehrbase.fhirbridge.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.opt.shareddefinition.Territory;
import org.hl7.fhir.instance.model.api.IPrimitiveType;
import org.hl7.fhir.r4.model.*;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;

import com.nedap.archie.rm.generic.PartySelf;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;

public class FhirPatientOpenehrGECCO_Personendaten {
    //private FHIRObservationHeartRateOpenehrHeartRate() {}

    public static GECCOPersonendatenComposition map(Patient fhirPatient) {
        //create composition and contained archetype objects
    	GECCOPersonendatenComposition composition = new GECCOPersonendatenComposition();
    	PersonendatenAdminEntry person_data = new PersonendatenAdminEntry();
    	GeschlechtEvaluation gender = new GeschlechtEvaluation();

        //map values of interest from FHIR observation
    	ZonedDateTime effectiveDateTime = null;
        try {
        	//NOTE only birth-date and ethnic-group occur in example - age occurs in example but is not part of template
            //administrative gender, deceased, date of deceased could be part of FHIR resource and have paths in template
        	
        	//get clinical values of interest from FHIR profile
        	//NOTE not sure if this is the best value for composition startTime since it refers to the documentation of the age, but its the only info on time of documentation...
        	//TODO try out whats the best way to extract infos from extensions (i see two approaches: this one and the one for ethnic_group)
        	Extension extension = fhirPatient.getExtensionByUrl("dateTimeOfDocumentation");
        	IPrimitiveType<String> date_time_of_documentation = (IPrimitiveType<String>) extension.getValueAsPrimitive();
        	effectiveDateTime = new SimpleDateFormat("dd/MM/yyyy").parse(date_time_of_documentation.getValue()).toInstant().atZone(ZoneId.systemDefault());
            Date birth_date = fhirPatient.getBirthDate();
            AdministrativeGender administrative_gender = fhirPatient.getGender();//NOTE (12.11.2020) they removed this from the examples again...
            BooleanType deceased_boolean = fhirPatient.getDeceasedBooleanType();//NOTE no example available for that (12.11.2020)
            DateTimeType deceased_dt = fhirPatient.getDeceasedDateTimeType();//NOTE no example available for that (12.11.2020)
            //ethnic-group
            Extension extension2 = fhirPatient.getExtensionByUrl("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/ethnic-group");
            Property temp_prop = extension.getNamedProperty("display");
            String ethnic_group = (String) extension.getNamedProperty("display").toString();
            
            //set stuff for composition
            //date of birth
            DatenZurGeburtCluster datenZurGeburtCluster = new DatenZurGeburtCluster();
            datenZurGeburtCluster.setGeburtsdatumValue((Temporal) birth_date);
            person_data.setDatenZurGeburt(datenZurGeburtCluster);
            //ethnic-group
            EthnischerHintergrundCluster ec = new EthnischerHintergrundCluster();
            ec.setEthnischerHintergrundDefiningcode(EthnischerHintergrundDefiningcode.valueOf(ethnic_group));
            List<EthnischerHintergrundCluster> items = new ArrayList<>();
            items.add(ec);
            person_data.setEthnischerHintergrund(items);
            //gender
            gender.setAdministrativesGeschlechtDefiningcode(AdministrativesGeschlechtDefiningcode.valueOf(administrative_gender.name())); //TODO check which property to use maybe its administrative_gender.toCode()
            
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
        //assemble composition
        composition.setPersonendaten(person_data);
        composition.setGeschlecht(gender);
        

        // Required fields by API
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test"); //FIXME: sensible value
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(effectiveDateTime);
        composition.setComposer(new PartySelf()); //FIXME: sensible value

        return composition;
    }
}
