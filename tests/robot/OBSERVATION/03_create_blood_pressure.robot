# Copyright (c) 2020 Wladislaw Wagner (Vitasystems GmbH)
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
Resource                ${EXECDIR}/robot/_resources/suite_settings.robot

Test Setup              generic.prepare new request session    Prefer=return=representation

Force Tags              create



*** Variables ***




*** Test Cases ***
001 Create Blood Pressure Fails Due To Invalid/Missing 'resourceType'
	[Documentation]     TODO
	...					NOTE: use Regular Expressions to replace braces () as described here:
	...          		https://json-schema.org/understanding-json-schema/reference/regular_expressions.html#example
	[Template]			fail to create blood pressure
    [Tags]              xxx

	# FIELD/PATH		VALUE			ERROR MESSAGE
	$.resourceType		missing			Unable to find resourceType property
	$.resourceType		pusemukkel		This does not appear to be a FHIR resource .unknown name 'pusemukkel'.
	$.resourceType		${EMPTY}		This does not appear to be a FHIR resource .unknown name ''.
    $.resourceType		${123}			This does not appear to be a FHIR resource .unknown name '123'.


002 Create Blood Pressure Fails Due To Invalid/Missing 'meta'
	[Documentation]     TODO
	[Template]			fail to create blood pressure
    [Tags]              xxx

	# FIELD/PATH		VALUE			ERROR MESSAGE
	$.meta				missing			Default profile is not supported for Observation. One of the following profiles is expected: .http://...
	$.meta.profile		missing			Object must have some content
	$.meta.profile      ${{["invalid_url"]}}    Canonical URLs must be absolute URLs if they are not fragment references .invalid_url.
	$.meta.profile      ${{["http://wrong.url"]}}    Profile reference 'http://wrong.url' could not be resolved, so has not been checked
	$.meta.profile		${EMPTY}		This property must be an Array, not a a primitive property

	# comment: the next one sets the value to an empty list/array []
	$.meta.profile		${{[]}}			Default profile is not supported for Observation. One of the following profiles is expected: .http://...
	
	# comment: the next one sets value to an empty object {}
	$.meta.profile		${{{}}}			This property must be an Array, not a an object



002 Create Blood Pressure Fails Due To Invalid/Missing 'category'
	[Documentation]     TODO
	[Template]			fail to create blood pressure
    [Tags]              zzz

	# FIELD/PATH		VALUE			ISSUE	ERROR MESSAGE
	# 									INDEX
	$.category			missing			0		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.category': minimum required = 1, but only found 0
	$.category			${{[]}}			0		Profile https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/blood-pressure, Element 'Observation.category': minimum required = 1, but only found 0
	$.category			${{{}}}			0		This property must be an Array, not an Object
	$.category			${{[{}]}}		0		Object must have some content

	# FIELD/PATH			VALUE		INDEX	ERROR MESSAGE
	$.category[0].coding    missing		0		Object must have some content
	$.category[0].coding    ${EMPTY}	0		This property must be an Array, not a primitive property

	# FIELD/PATH						VALUE		INDEX	ERROR MESSAGE
	$.category[0].coding[0].system    	missing    	2    	A code with no system has no defined meaning. A system should be provided





*** Keywords ***
fail to create blood pressure
    [Arguments]         ${json_path}  ${value}  ${error_message}

	ehr.create new ehr    000_ehr_status.json
	generate payload from example json    ${json_path}  ${value}
	create blood pressure    ${payload}
	observation.validate response - 422 (with error message)    ${error_message}


generate payload from example json
	[Arguments]			${json_path}  ${value}

	${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/observation-bloodpressure-example.json
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}
						Delete Object From Json    ${payload}    $.text


	# comment: delete field/object
	Run Keyword And Return If   $value=="missing"
	...    Run Keywords    Delete Object From Json    ${payload}    ${json_path}    AND
	...	   Set Test Variable    ${payload}    ${payload}
		   Output    ${payload}


	# comment: set value from table
	Update Value To Json    ${payload}    ${json_path}    ${value}
	Set Test Variable    ${payload}    ${payload}


create blood pressure
    [Arguments]         ${fhir_resource}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console




# BACKUP


# fail to create blood pressure
#     [Arguments]         ${json_path}  ${value}  ${issue_index}  ${error_message}

# 	ehr.create new ehr    000_ehr_status.json
# 	generate payload from example json    ${json_path}  ${value}
# 	create blood pressure    ${payload}
# 	observation.validate response - 422 (with error message)    ${issue_index}    ${error_message}
