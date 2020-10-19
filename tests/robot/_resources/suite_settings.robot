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

Library     REST
Library     String
Library     Collections
Library     OperatingSystem
Library     Process
Library     JSONLibrary

Resource    ${EXECDIR}/robot/_resources/keywords/generic.robot
Resource    ${EXECDIR}/robot/_resources/keywords/ehr.robot
Resource    ${EXECDIR}/robot/_resources/keywords/condition.robot
Resource    ${EXECDIR}/robot/_resources/keywords/observation.robot



*** Variables ***

${BASE_URL}                     http://localhost:8888/fhir-bridge/fhir
${EHRBASE_URL}                  http://localhost:8080/ehrbase/rest/openehr/v1
${DATA_SET_PATH_CONDITION}      ${EXECDIR}/robot/_resources/test_data/Condition
#${DATA_SET_PATH_OBSERVATION}    ${EXECDIR}/robot/_resources/test_data/Observation
${DATA_SET_PATH_OBSERVATION}    ./../../fhir-bridge/src/test/resources/Observation
${VALID EHR DATA SETS}          ${EXECDIR}/robot/_resources/test_data/ehr/valid
