# Copyright (c) 2019 Wladislaw Wagner (Vitasystems GmbH), P. Wohlfarth (Appsfactory)
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



*** Variables ***
${DATA_SET_PATH}        ${EXECDIR}/robot/_resources/test_data/Condition



*** Keywords ***
create diagnose condition
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    Patient/${subject_id}

    &{resp}             POST    ${BASE_URL}/Condition    body=${payload}
                        Output Debug Info To Console


validate response - 201
    [Documentation]     Validates response of POST to /Condition endpoint
                        Integer    response status    201


get diagnose condition
    &{resp}             GET    ${BASE_URL}/Condition?identifier=${subject_id}
                        Integer    response status    200
                        Output Debug Info To Console