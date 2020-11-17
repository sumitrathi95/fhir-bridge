# Copyright (c) 2019 Wladislaw Wagner (Vitasystems GmbH), Peter Wohlfarth (Appsfactory GmbH),
# Dave Petzold (Appsfactory GmbH)
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

#                       oooo   o8o        .o8                .             
#                       `888   `"'       "888              .o8             
# oooo    ooo  .oooo.    888  oooo   .oooo888   .oooo.   .o888oo  .ooooo.  
#  `88.  .8'  `P  )88b   888  `888  d88' `888  `P  )88b    888   d88' `88b 
#   `88..8'    .oP"888   888   888  888   888   .oP"888    888   888ooo888 
#    `888'    d8(  888   888   888  888   888  d8(  888    888 . 888    .o 
#     `8'     `Y888""8o o888o o888o `Y8bod88P" `Y888""8o   "888" `Y8bod8P' 
#
# [ VALIDATION KEYWORDS ] 

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



#                                                 oooo                     
#                                                 `888                     
#  .oooo.o  .ooooo.   .oooo.   oooo d8b  .ooooo.   888 .oo.                
# d88(  "8 d88' `88b `P  )88b  `888""8P d88' `"Y8  888P"Y88b               
# `"Y88b.  888ooo888  .oP"888   888     888        888   888               
# o.  )88b 888    .o d8(  888   888     888   .o8  888   888               
# 8""888P' `Y8bod8P' `Y888""8o d888b    `Y8bod8P' o888o o888o   
#
# [ SEARCH/RETRIEVE ]


get body temperature
    &{resp}             GET    ${BASE_URL}/Observation?identifier=${subject_id}&_profile=http://hl7.org/fhir/StructureDefinition/bodytemp
                        Integer    response status    200
                        String     request method    GET
                        String     response body id
                        String     response body resourceType    Bundle
                        String     response body entry 0 resource resourceType    Observation
                        Output Debug Info To Console


get observation lab
    &{resp}             GET    ${BASE_URL}/Observation?identifier=${subject_id}&_profile=https://www.medizininformatik-initiative.de/fhir/core/modul-labor/StructureDefinition/ObservationLab
                        Integer    response status    200
                        String     request method    GET
                        String     response body id
                        String     response body resourceType    Bundle
                        String     response body entry 0 resource resourceType    Observation
                        Output Debug Info To Console


get coronavirus lab results
    &{resp}             GET    ${BASE_URL}/Observation?identifier=${subject_id}&_profile=https://charite.infectioncontrol.de/fhir/core/StructureDefinition/CoronavirusNachweisTest
                        Integer    response status    200
                        String     request method    GET
                        String     response body id
                        String     response body resourceType    Bundle
                        String     response body entry 0 resource resourceType    Observation
                        Output Debug Info To Console



#                                            .
#                                          .o8
#  .ooooo.  oooo d8b  .ooooo.   .oooo.   .o888oo  .ooooo.
# d88' `"Y8 `888""8P d88' `88b `P  )88b    888   d88' `88b
# 888        888     888ooo888  .oP"888    888   888ooo888
# 888   .o8  888     888    .o d8(  888    888 . 888    .o
# `Y8bod8P' d888b    `Y8bod8P' `Y888""8o   "888" `Y8bod8P'
#
# [ SUCEED CREATING ]


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


create body height
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create pregnancy status
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console


create frailty scale score
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console



create Observation Heart Rate JSON
    #[Arguments]         ${resourceType}    ${ID}    ${profile}    ${status}    ${Identifier.available}    ${Identifiercodingsystem}    ${Identifiercodingcode}    ${Identifiersystem}    ${Identifiervalue}    ${categoryavailable}    ${categorycodingavailable}    ${categorysystem}    ${categorycode}    ${code.available}    ${code.0.system}    ${code.0.code}    ${code.1.system}    ${code.1.code}    ${reference}    ${datetime}    ${vQ.available}    ${vQ.value}    ${vQ.unit}    ${vQ.system}    ${vQ.code}    ${dateabsentreason}    ${responsecode}    ${diagnostics}
    [Arguments]         ${resourceType}    ${ID}    ${profile}    ${status}     ${Identifieravailable}    ${Identifiercodingsystem}    ${Identifiercodingcode}    ${Identifiersystem}    ${Identifiervalue}    ${categoryavailable}    ${categorycodingavailable}    ${categorysystem}    ${categorycode}    ${responsecode}    ${diagnostics}

                        prepare new request session  Prefer=return=representation

    #${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/observation-example-heart-rate.json

    &{resp}             Run Keywords
                        ...    ehr.create new ehr    000_ehr_status.json                AND
                        ...    load JSON     observation-example-heart-rate.json        AND
                        ...    update Resource Type    ${resourceType}                  AND
                        ...    update ID               ${ID}                            AND
                        ...    update Profile          ${profile}                       AND
                        ...    update Status           ${status}                        AND
                        ...    update Identifier       ${Identifieravailable}    ${Identifiercodingsystem}    ${Identifiercodingcode}    ${Identifiersystem}    ${Identifiervalue}    AND
                        ...    update Category         ${categoryavailable}    ${categorycodingavailable}    ${categorysystem}    ${categorycode}    AND
                        ...    POST    ${BASE_URL}/Observation    body=${payload}       AND
                        ...    Output Debug Info To Console                             AND
                        ...    validation JSON    ${responsecode}    ${diagnostics}

create Observation Body Temperature JSON
    #[Arguments]         ${resourceType}    ${ID}    ${profile}    ${status}    ${Identifier.available}    ${Identifiercodingsystem}    ${Identifiercodingcode}    ${Identifiersystem}    ${Identifiervalue}    ${categoryavailable}    ${categorycodingavailable}    ${categorysystem}    ${categorycode}    ${code.available}    ${code.0.system}    ${code.0.code}    ${code.1.system}    ${code.1.code}    ${reference}    ${datetime}    ${vQ.available}    ${vQ.value}    ${vQ.unit}    ${vQ.system}    ${vQ.code}    ${dateabsentreason}    ${responsecode}    ${diagnostics}
    [Arguments]         ${resourceType}    ${ID}    ${profile}    ${status}     ${Identifieravailable}    ${Identifiercodingsystem}    ${Identifiercodingcode}    ${Identifiersystem}    ${Identifiervalue}    ${categoryavailable}    ${categorycodingavailable}    ${categorysystem}    ${categorycode}    ${codeavailable}    ${codecodingavailable}    ${codesystem}    ${codecode}   ${codedisplay}    ${responsecode}    ${diagnostics}

                        prepare new request session  Prefer=return=representation

    #${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/observation-bodytemp-example.json

    &{resp}             Run Keywords
                        ...    ehr.create new ehr    000_ehr_status.json                AND
                        ...    load JSON     observation-bodytemp-example.json          AND
                        ...    update Resource Type    ${resourceType}                  AND
                        ...    update ID               ${ID}                            AND
                        ...    update Profile          ${profile}                       AND
                        ...    update Status           ${status}                        AND
                        ...    update Identifier       ${Identifieravailable}    ${Identifiercodingsystem}    ${Identifiercodingcode}    ${Identifiersystem}    ${Identifiervalue}    ${Identifierreference}    AND
                        ...    update Category         ${categoryavailable}    ${categorycodingavailable}    ${categorysystem}    ${categorycode}    AND
                        ...    update Code             ${codeavailable}    ${codecodingavailable}    ${codetextavailable}     ${codetext}    ${codesystem}    ${codecode}   ${codedisplay}    AND
						...    POST    ${BASE_URL}/Observation    body=${payload}       AND
                        ...    Output Debug Info To Console                             AND
                        ...    validation JSON    ${responsecode}    ${diagnostics}


#   oooo  .oooooo..o   .oooooo.   ooooo      ooo 
#   `888 d8P'    `Y8  d8P'  `Y8b  `888b.     `8' 
#    888 Y88bo.      888      888  8 `88b.    8  
#    888  `"Y8888o.  888      888  8   `88b.  8  
#    888      `"Y88b 888      888  8     `88b.8  
#    888 oo     .d8P `88b    d88'  8       `888  
#.o. 88P 8""88888P'   `Y8bood8P'  o8o        `8  
#`Y888P                                        
#
# [ UPDATE JSON VALUES ]

load JSON
    [Arguments]         ${fhir_resource}

    ${payload}         Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                       Update Value To Json    ${payload}    $.subject.reference    urn:uuid:${subject_id}
                       Set Test Variable  ${payload}  ${payload}


validation JSON
    [Arguments]         ${responsecode}    ${diagnostics}

                        Integer    response status    ${responsecode}

                        Run Keyword And Return If  $responsecode!="201"
                        ...     String     response body issue 0 diagnostics    pattern=${diagnostics}


update Resource Type
    [Arguments]         ${resourceType}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $resourceType=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.resourceType    ${resourceType}

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $resourceType=="missing"
                        ...    Delete Object From Json  ${payload}  $.resourceType

                        # Else
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.resourceType    ${resourceType}

update ID
    [Arguments]         ${ID}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $ID=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.id   ${ID}

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $ID=="missing"
                        ...    Delete Object From Json  ${payload}  $.id

                        # Else
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.id   ${ID}

update Profile
    [Arguments]         ${profile}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $profile=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.meta.profile[0]    ${profile}

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $profile=="missing"
                        ...    Delete Object From Json  ${payload}  $.meta.profile[0]

                        # Run Keyword only when resourcetype is valid
                        Run Keyword And Return If    $profile=="valid"
                        ...    Update Value To Json    ${payload}    $.meta.profile[0]    http://hl7.org/fhir/StructureDefinition/heartrate

                        # Else
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.meta.profile[0]    ${profile}

update Status
    [Arguments]         ${status}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $status=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.status   ${status}

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $status=="missing"
                        ...    Delete Object From Json  ${payload}  $.status

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.status   ${status}

update Identifier
    [Arguments]         ${Identifieravailable}    ${Identifiercodingsystem}    ${Identifiercodingcode}    ${Identifiersystem}    ${Identifiervalue}

                        # Run Keywords only if identifier is available
                        Run Keyword And Return If    $Identifieravailable=="true"
                        ...    Run Keywords
                        ...    update Identifier coding system    ${Identifiercodingsystem}    AND
                        ...    update Identifier coding code      ${Identifiercodingcode}      AND
                        ...    update Identifier system           ${Identifiersystem}          AND
                        ...    update Identifier value            ${Identifiervalue}           AND
						...    update Identifier reference        ${Identifierreference}

                        # Run Keyword only if Identifier is not available
                        Run Keyword And Return If    $Identifieravailable=="false"
                        ...    Delete Object From Json  ${payload}  $.identifier

update Identifier coding system
    [Arguments]         ${Identifiercodingsystem}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $Identifiercodingsystem=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.identifier[0].type.coding[0].system   ${Identifiercodingsystem}

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $Identifiercodingsystem=="missing"
                        ...    Delete Object From Json    ${payload}    $.identifier[0].type.coding[0].system

                        # Run Keyword only when resourcetype is valid
                        Run Keyword And Return If    $Identifiercodingsystem=="valid"
                        ...    Update Value To Json    ${payload}    $.identifier[0].type.coding[0].system   http://terminology.hl7.org/CodeSystem/v2-0203

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.identifier[0].type.coding[0].system   ${Identifiercodingsystem}

update Identifier coding code
    [Arguments]         ${Identifiercodingcode}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $Identifiercodingcode=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.identifier[0].type.coding[0].code   ${Identifiercodingcode}

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $Identifiercodingcode=="missing"
                        ...    Delete Object From Json    ${payload}    $.identifier[0].type.coding[0].code

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.identifier[0].type.coding[0].code   ${Identifiercodingcode}

update Identifier system
    [Arguments]         ${Identifiersystem}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $Identifiersystem=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.identifier[0].system   ${Identifiersystem}

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $Identifiersystem=="missing"
                        ...    Delete Object From Json    ${payload}    $.identifier[0].system

                        # Run Keyword only when resourcetype is valid
                        Run Keyword And Return If    $Identifiersystem=="valid"
                        ...    Update Value To Json    ${payload}    $.identifier[0].system   https://www.charite.de/fhir/CodeSystem/observation-identifiers

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.identifier[0].system   ${Identifiersystem}

update Identifier value
    [Arguments]         ${Identifiervalue}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $Identifiervalue=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.identifier[0].value   ${Identifiervalue}

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $Identifiervalue=="missing"
                        ...    Delete Object From Json    ${payload}    $.identifier[0].value

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.identifier[0].value   ${Identifiervalue}

update Identifier reference
    [Arguments]         ${Identifierreference}

                        # Run Keyword only when Identifier.assigner.reference is empty
                        Run Keyword And Return If    $Identifierreference=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.identifier[0].assigner.reference   ${Identifiervalue}

                        # Run Keyword only when Identifier.assigner.reference is missing
                        Run Keyword And Return If    $Identifierreference=="missing"
                        ...    Delete Object From Json    ${payload}    $.identifier[0].assigner.reference

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.identifier[0].assigner.reference   ${Identifierreference}

update Category
    [Arguments]         ${categoryavailable}    ${categorycodingavailable}    ${categorysystem}    ${categorycode}

                        # Run Keyword only if category is available
                        Run Keyword And Return If    $categoryavailable=="true"
                        ...    update Category Coding    ${categorycodingavailable}    ${categorysystem}    ${categorycode}

                        # Run Keyword only if category is not available
                        Run Keyword And Return If    $categoryavailable=="false"
                        ...    Delete Object From Json  ${payload}  $.category

update Category Coding
    [Arguments]         ${categorycodingavailable}    ${categorysystem}    ${categorycode}

                        # Run Keyword only if Category-Coding is available
                        Run Keyword And Return If    $categorycodingavailable=="true"
                        ...    Run Keywords
                        ...    update Category System           ${categorysystem}          AND
                        ...    update Category Code             ${categorycode}

                        # Run Keyword only if Category-Coding is not available
                        Run Keyword And Return If    $categorycodingavailable=="false"
                        ...    Delete Object From Json  ${payload}  $.category[0].coding

update Category System
    [Arguments]         ${categorysystem}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $categorysystem=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.category[0].coding[0].system    ${categorysystem}

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $categorysystem=="missing"
                        ...    Delete Object From Json    ${payload}    $.category[0].coding[0].system

                        # Run Keyword only when resourcetype is valid
                        Run Keyword And Return If    $categorysystem=="valid"
                        ...    Update Value To Json    ${payload}    $.category[0].coding[0].system    http://terminology.hl7.org/CodeSystem/observation-category

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.category[0].coding[0].system    ${categorysystem}

update Category Code
    [Arguments]         ${categorycode}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $categorycode=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.category[0].coding[0].code    ${categorycode}

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $categorycode=="missing"
                        ...    Delete Object From Json    ${payload}    $.category[0].coding[0].code

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.category[0].coding[0].code    ${categorycode}

update Code
    [Arguments]         ${codeavailable}    ${codecodingavailable}    ${codetextavailable}     ${codetext}    ${codesystem}    ${codecode}    ${codedisplay}

                        # Run Keyword only if code is available
                        Run Keyword And Return If    $codeavailable=="true"
                        ...    update Code Coding    ${codecodingavailable}    ${codesystem}    ${codecode}    ${codedisplay}    AND
						...    update Code Text    ${codetextavailable}    ${codetext}

                        # Run Keyword only if code is not available
                        Run Keyword And Return If    $codeavailable=="false"
                        ...    Delete Object From Json  ${payload}  $.code

Update Code Coding
    [Arguments]         ${codecodingavailable}    ${codesystem}    ${codecode}    ${codedisplay}

                        # Run Keyword only if Code-Coding is available
                        Run Keyword And Return If    $codecodingavailable=="true"
                        ...    Run Keywords
                        ...    update Code System           ${codesystem}          AND
                        ...    update Code Code             ${codecode}            AND
                        ...    update Code Display          ${codedisplay}

                        # Run Keyword only if Code-Coding is not available
                        Run Keyword And Return If    $codecodingavailable=="false"
                        ...    Delete Object From Json  ${payload}  $.code[0].coding

Update Code System
	[Arguments]        ${codesystem}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $codesystem=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.code[0].coding[0].system    ${codesystem}    AND
						...    Update Value To Json    ${payload}    $.code[0].coding[1].system    ${codesystem}

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $codesystem=="missing"
                        ...    Delete Object From Json    ${payload}    $.code[0].coding[0].system    AND
                        ...    Delete Object From Json    ${payload}    $.code[0].coding[1].system

                        # Run Keyword only when resourcetype is valid
                        Run Keyword And Return If    $codesystem=="valid"
                        ...    Update Value To Json    ${payload}    $.code[0].coding[0].system    http://loinc.org    AND
                        ...    Update Value To Json    ${payload}    $.code[0].coding[1].system    http://snomed.info/sct

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.code[0].coding[0].system    ${codesystem}   AND
						...    Update Value To Json    ${payload}    $.code[0].coding[1].system    ${codesystem}

Update Code Code
	[Arguments]        ${codecode}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $codecode=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.code[0].coding[0].code   ${codecode}    AND
                        ...    Update Value To Json    ${payload}    $.code[0].coding[1].code   ${codecode} 

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $codecode=="missing"
                        ...    Delete Object From Json    ${payload}    $.code[0].coding[0].code    AND
                        ...    Delete Object From Json    ${payload}    $.code[0].coding[1].code

                        # Run Keyword only when resourcetype is valid
                        Run Keyword And Return If    $codecode=="valid"
                        ...    Update Value To Json    ${payload}    $.code[0].coding[0].code    “8310-5”    AND
                        ...    Update Value To Json    ${payload}    $.code[0].coding[1].code    “386725007”

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.code[0].coding[0].code    ${codecode} AND
						...    Update Value To Json    ${payload}    $.code[0].coding[1].code    ${codecode}

Update Code Display
	[Arguments]        ${codedisplay}

                        # Run Keyword only when resourcetype is empty
                        Run Keyword And Return If    $codedisplay=="${EMPTY}"
                        ...    Update Value To Json    ${payload}    $.code[0].coding[0].display   ${codedisplay}    AND
						...    Update Value To Json    ${payload}    $.code[0].coding[1].display   ${codedisplay} 

                        # Run Keyword only when resourcetype is missing
                        Run Keyword And Return If    $codedisplay=="missing"
                        ...    Delete Object From Json    ${payload}    $.code[0].coding[0].display    AND
                        ...    Delete Object From Json    ${payload}    $.code[0].coding[1].display

						# Run Keyword only when resourcetype is valid
                        Run Keyword And Return If    $codecode=="valid"
                        ...    Update Value To Json    ${payload}    $.code[0].coding[0].code    "Body temperature"    AND
                        ...    Update Value To Json    ${payload}    $.code[0].coding[1].code    "Body temperature (observable entity)"

                        # Else 
                        Run Keyword  
                        ...    Update Value To Json    ${payload}    $.code[0].coding[0].display     ${codedisplay}    AND
						...    Update Value To Json    ${payload}    $.code[0].coding[1].display     ${codedisplay}    AND