# Copyright (c) 2020 P. Wohlfarth (Appsfactory), Wladislaw Wagner (Vitasystems GmbH)
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

Force Tags              FHIR



*** Variables ***
${BASE_URL}             http://localhost:8888/fhir-bridge/fhir
${EHRBASE_URL}          http://localhost:8080/ehrbase/rest/openehr/v1
${DATA_SET_PATH_CONDITION}        ${CURDIR}/_resources/test_data/Condition
${DATA_SET_PATH_OBSERVATION}        ${CURDIR}/_resources/test_data/Observation
${VALID EHR DATA SETS}       ${CURDIR}/_resources/test_data/ehr/valid



*** Test Cases ***
001 Create Diagnose Condition
    [Documentation]     1. create EHR
    ...                 2. trigger condition endpoint

    create new ehr               000_ehr_status.json
    create diagnose condition    condition-example.json


002 Search Diagnose Condition
    [Documentation]     Search Diagnose Condition

    get diagnose condition


003 Create Body Temperature 
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	create new ehr               000_ehr_status.json
	create body temperature    observation-bodytemp-example.json

004 Search Body Temperature
    [Documentation]    Search Body Temperature

	get body temperature



*** Keywords ***
create new ehr
    [Arguments]         ${ehr_status_object}
    [Documentation]     Creates new EHR record with a server-generated ehr_id.
    ...                 DEPENDENCY: `prepare new request session`
    ...                 :ehr_status_object: ehr_status_as_json_file

    ${ehr_status_json}  Load JSON From File    ${VALID EHR DATA SETS}/${ehr_status_object}
                        Update Value To Json    ${ehr_status_json}    $.subject.external_ref.id.value
                        ...    ${{str(uuid.uuid4())}}
                        Update Value To Json    ${ehr_status_json}    $.subject.external_ref.namespace
                        ...    namespace_${{''.join(random.choices(string.digits, k=7))}}

    &{resp}=            POST    ${EHRBASE_URL}/ehr    ${ehr_status_json}
                        Integer      response status    201
                        Output Debug Info To Console

                        Set Suite Variable    ${response}    ${resp}
                        extract subject_id from response


create ehr
    [Documentation]     Creates new EHR record with a server-generated ehr_id.
    ...                 DEPENDENCY: `prepare new request session`

    &{resp}=            REST.POST    ${EHRBASE_URL}/ehr
                        Set Test Variable    ${response}    ${resp}
                        Output Debug Info To Console
                        extract ehr_id from response


*** Keywords ***
create diagnose condition
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_CONDITION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    Patient/${subject_id}

    &{resp}             POST    ${BASE_URL}/Condition    body=${payload}
                        Integer    response status    201
                        Output Debug Info To Console

create body temperature
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    Patient/${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Integer    response status    201
                        Output Debug Info To Console

get diagnose condition
    &{resp}             GET    ${BASE_URL}/Condition?identifier=${subject_id}
                        Integer    response status    200
                        Output Debug Info To Console

get body temperature
    &{resp}             GET    ${BASE_URL}/Observation?identifier=${subject_id}&_profile=http://hl7.org/fhir/StructureDefinition/bodytemp
                        Integer    response status    200
                        Output Debug Info To Console


