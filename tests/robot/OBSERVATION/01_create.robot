# Copyright (c) 2020 Peter Wohlfarth (Appsfactory GmbH), Wladislaw Wagner (Vitasystems GmbH), Dave Petzold (Appsfactory GmbH)
#
# This file is part of Project EHRbase
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.



*** Settings ***
# Library                 REST
# Library                 Collections
# Library                 JSONLibrary
Resource                ${EXECDIR}/robot/_resources/suite_settings.robot

Test Setup              generic.prepare new request session    Prefer=return=representation

Force Tags              create



*** Variables ***




*** Test Cases ***
001 Create Body Temperature 
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create body temperature    observation-bodytemp-example.json
    observation.validate response - 201


002 Create Blood Pressure 
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create blood pressure    observation-bloodpressure-example.json
    observation.validate response - 201


003 Create FIO2 
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create FIO2    observation-example-fiO2.json
    observation.validate response - 201


004 Create Heart Rate 
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create heart rate    observation-example-heart-rate.json
    observation.validate response - 201


005 Create Sofa Score
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create sofa score    observation-sofa-score-example.json
    observation.validate response - 201


006 Create Observation Lab
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create observation lab    observation-observationlab-example.json
    observation.validate response - 201


007 Create Observation Using Default Profile
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create observation    observation-example.json
    observation.validate response - 422 (default profile not supported)


008 Create Observation Using Unsupported Profile
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create observation    observation-vitalsigns-example.json
    observation.validate response - 422 (profile not supported)


009 Create Coronavirus Lab Result
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create observation    observation-coronavirusnachweistest-example.json
    observation.validate response - 201


010 Create Body Height
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint
    
	ehr.create new ehr    000_ehr_status.json
	observation.create observation  observation-example-body-height.json
	observation.validate response - 201


011 Create Pregnancy Status
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create pregnancy status    observation-pregnancy-status-example.json
  	observation.validate response - 201


012 Create Frailty Scale Score
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint
	[Tags]             not-ready

	ehr.create new ehr    000_ehr_status.json
	observation.create frailty scale score    observation-frailty-scale-score-example.json
  	observation.validate response - 201


013 Create Heart Rate (valid variants)
    [Documentation]    1. create EHR
    ...                2. fill json with table values
    ...                3. trigger observation endpoint
    ...                4. validate outcome
    [Tags]             todo    valid    test
    [Template]         create Observation Heart Rate JSON
#|  ressourceType  |     ID     |  profile  |  status  |                             Identifier                            |                      category                             |                                        code                                      |         subject                        |      DateTime  |                      valueQuantity                     |  dataabsentreason  |  ArryNumber  |  R.-Code  |  diagnostics  |
#|                 |            |           |          |  avalable  |  coding.system  |  coding.code  |  system  |  value  |  available  |  codingavailable  |  system  |     code     |  available  |  coding available  |  0.system  |  0.code  |  1.system  |  1.code  |  available  |          reference       |                |  available  |  value  |  unit  |  system      |  code  |                    |              |           |               |
    Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       true			true			  valid		  8867-4		valid         abcd    true    		         valid      		2020-02-25		true		 ${80.7}	test	    valid	   /min				201    	${EMPTY}
    Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       true			true			  valid		  8867-4		valid         abcd    true    		         valid      		2020-02-25		false		 ${80.7}	test	    valid	   /min				422    	${EMPTY}
#   Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       true			true			  valid		  8867-4		valid         abcd    true    		         valid      		2020-02-25		true		 missing    missing	    missing	   missing			422    	${EMPTY}
#   Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       true			true			  valid		  8867-4		valid         abcd    true    		         valid      		2020-02-25		true		 ${EMPTY}	${EMPTY}    ${EMPTY}    ${EMPTY}		422    	${EMPTY}
#   Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       true			true			  valid		  8867-4		valid         abcd    true    		         valid      		missing			422    	${EMPTY} 
#   Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       true			true			  valid		  8867-4		valid         abcd    true    		${{str(uuid.uuid4())}}      400    	Does not contain resource type 
#   Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       true			true			  valid		  8867-4		valid         abcd    false    		valid      					422    	Element 'Observation.subject': mindestens erforderlich = 1, aber nur gefunden 0
#   Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       true			true			  valid		  8867-4		valid         abcd    true    		${12345}     				422    	Fehler beim Parsen von JSON: Der primitive Wert muss ein String sein
#   Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       true			true			  valid		  8867-4		valid         abcd    true    		missing      				422    	Objekt muss einen Inhalt haben
#	Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       true			true			  valid		  8867-4		valid         abcd    true    		${EMPTY}     				422    	Dieses Element stimmt mit keinem bekannten Slice defined in the profile
#	Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       true			true			  ${EMPTY}    ${EMPTY}		${EMPTY}      ${EMPTY}      422    	value kann nicht leer sein
#	Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       true			false			  valid		  8867-4		valid         abcd      	422    	mindestens erforderlich = 1, aber nur gefunden 0
#	Observation    	 heart-rate    valid		final		true          valid             test         valid      abcd       true             true            valid      vital-signs       false			true			  valid		  8867-4		valid         abcd      	422    	mindestens erforderlich = 1, aber nur gefunden 0
#   Observation    	 heart-rate    valid		final		true          valid             abcd         valid      abcd       false            true            valid      vital-signs       	422    	mindestens erforderlich = 1, aber nur gefunden 0
#	Observation    	 heart-rate    valid		final		true          valid             abcd         valid      abcd       true             false           valid      vital-signs       	422    	Objekt muss einen Inhalt haben
#	Observation    	 heart-rate    valid		final		true          valid             abcd         valid      abcd       true             true            test       vital-signs       	422    	Dieses Element stimmt mit keinem bekannten Slice defined in the profile
#	Observation    	 heart-rate    valid		final		true          valid             abcd         valid      abcd       true             true            valid      test       			422    	Dieses Element stimmt mit keinem bekannten Slice defined in the profile
#	Observation    	 heart-rate    valid		final		false         valid             abcd          valid      abcd       201    	${EMPTY}



014 Create Heart Rate (invalid variants)
    [Documentation]    1. create EHR
    ...                2. fill json with table values
    ...                3. trigger observation endpoint
    ...                4. validate outcome
    [Tags]             todo    invalid
    [Template]         create Observation Heart Rate JSON
    #|  ressourceType  |     ID     |  profile  |  status  |                             Identifier                           |              category           |                             code                            |  reference  |  DateTime  |                      valueQuantity                 |  dataabsentreason  |  R.-Code  |  diagnostics  |
    #|                 |            |           |          |  avalable  |  coding.system  |  coding.code  |  system  |  code  |  available  |  code  |  system  |  available  |  0.code  |  0.system  |  1.code  |  1.system  |             |            |  available  |  value  |  unit  |  system  |  code  |                    |           |               |

