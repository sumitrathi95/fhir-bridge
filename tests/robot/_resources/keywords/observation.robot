# Copyright (c) 2019 Wladislaw Wagner (Vitasystems GmbH), Peter Wohlfarth (Appsfactory GmbH), Dave Petzold (Appsfactory GmbH)
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



*** Keywords ***

# .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. 
#| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
#| | ____   ____  | || |      __      | || |   _____      | || |     _____    | || |  ________    | || |      __      | || |  _________   | || |  _________   | |
#| ||_  _| |_  _| | || |     /  \     | || |  |_   _|     | || |    |_   _|   | || | |_   ___ `.  | || |     /  \     | || | |  _   _  |  | || | |_   ___  |  | |
#| |  \ \   / /   | || |    / /\ \    | || |    | |       | || |      | |     | || |   | |   `. \ | || |    / /\ \    | || | |_/ | | \_|  | || |   | |_  \_|  | |
#| |   \ \ / /    | || |   / ____ \   | || |    | |   _   | || |      | |     | || |   | |    | | | || |   / ____ \   | || |     | |      | || |   |  _|  _   | |
#| |    \ ' /     | || | _/ /    \ \_ | || |   _| |__/ |  | || |     _| |_    | || |  _| |___.' / | || | _/ /    \ \_ | || |    _| |_     | || |  _| |___/ |  | |
#| |     \_/      | || ||____|  |____|| || |  |________|  | || |    |_____|   | || | |________.'  | || ||____|  |____|| || |   |_____|    | || | |_________|  | |
#| |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | |
#| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
# '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' 


validate response - 201
    Integer    response status    201

    String     response body resourceType    Observation
    String     response body id
    String     response body meta versionId    1


validate response - 422 (default profile not supported)
    Integer    response status    422

    String     response body resourceType    OperationOutcome
    String     response body issue 0 diagnostics
    ...        pattern=Default profile is not supported for Observation. One of the following profiles is expected:


validate response - 422 (profile not supported)
    Integer    response status    422

    String     response body resourceType    OperationOutcome
    String     response body issue 0 diagnostics
    ...        pattern=Profile http://hl7.org/fhir/StructureDefinition/vitalsigns is not supported for Observation. One of the following profiles is expected:


# .----------------.  .----------------.  .----------------. 
#| .--------------. || .--------------. || .--------------. |
#| |    ______    | || |  _________   | || |  _________   | |
#| |  .' ___  |   | || | |_   ___  |  | || | |  _   _  |  | |
#| | / .'   \_|   | || |   | |_  \_|  | || | |_/ | | \_|  | |
#| | | |    ____  | || |   |  _|  _   | || |     | |      | |
#| | \ `.___]  _| | || |  _| |___/ |  | || |    _| |_     | |
#| |  `._____.'   | || | |_________|  | || |   |_____|    | |
#| |              | || |              | || |              | |
#| '--------------' || '--------------' || '--------------' |
# '----------------'  '----------------'  '----------------' 


get body temperature
    &{resp}             GET    ${BASE_URL}/Observation?identifier=${subject_id}&_profile=http://hl7.org/fhir/StructureDefinition/bodytemp
                        Integer    response status    200
                        String     request method    GET
                        String     response body id
                        String     response body resourceType    Bundle
                        # Eventuell mit Array noch resourcetype Observation im entry abfragen
                        Output Debug Info To Console


get observation lab
    &{resp}             GET    ${BASE_URL}/Observation?identifier=${subject_id}&_profile=https://www.medizininformatik-initiative.de/fhir/core/StructureDefinition/ObservationLab
                        Integer    response status    200
                        String     request method    GET
                        String     response body id
                        String     response body resourceType    Bundle
                        Output Debug Info To Console


get coronavirus lab results
    &{resp}             GET    ${BASE_URL}/Observation?identifier=${subject_id}&_profile=https://charite.infectioncontrol.de/fhir/core/StructureDefinition/CoronavirusNachweisTest
                        Integer    response status    200
                        String     request method    GET
                        String     response body id
                        String     response body resourceType    Bundle
                        Output Debug Info To Console


# .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. 
#| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |
#| |     ______   | || |  _______     | || |  _________   | || |      __      | || |  _________   | || |  _________   | |
#| |   .' ___  |  | || | |_   __ \    | || | |_   ___  |  | || |     /  \     | || | |  _   _  |  | || | |_   ___  |  | |
#| |  / .'   \_|  | || |   | |__) |   | || |   | |_  \_|  | || |    / /\ \    | || | |_/ | | \_|  | || |   | |_  \_|  | |
#| |  | |         | || |   |  __ /    | || |   |  _|  _   | || |   / ____ \   | || |     | |      | || |   |  _|  _   | |
#| |  \ `.___.'\  | || |  _| |  \ \_  | || |  _| |___/ |  | || | _/ /    \ \_ | || |    _| |_     | || |  _| |___/ |  | |
#| |   `._____.'  | || | |____| |___| | || | |_________|  | || ||____|  |____|| || |   |_____|    | || | |_________|  | |
#| |              | || |              | || |              | || |              | || |              | || |              | |
#| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |
# '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' 


create blood pressure
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create body temperature
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create FIO2
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create heart rate
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create observation lab
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create sofa score
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create observation
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create coronavirus lab result
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console
