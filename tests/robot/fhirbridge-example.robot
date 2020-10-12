*** Settings ***
Library                 REST
Library                 Collections
Library                 JSONLibrary

Test Setup              prepare new request session    Prefer=return=representation

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


create diagnose condition
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_CONDITION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    Patien/${subject_id}

    &{resp}             POST    ${BASE_URL}/Condition    body=${payload}
                        Output Debug Info To Console

create body temperature
    [Arguments]         ${fhir_resource}

    ${payload}          Load JSON From File    ${DATA_SET_PATH_OBSERVATION}/${fhir_resource}
                        # Output    ${payload}
                        Update Value To Json    ${payload}    $.subject.reference    Patien/${subject_id}

    &{resp}             POST    ${BASE_URL}/Observation    body=${payload}
                        Output Debug Info To Console

get diagnose condition
    &{resp}             GET    ${BASE_URL}/Condition?identifier=${subject_id}
                        Integer    response status    200
                        Output Debug Info To Console

get body temperature
    &{resp}             GET    ${BASE_URL}/Observation?identifier=${subject_id}
                        Integer    response status    200
                        Output Debug Info To Console


extract subject_id from response
    [Documentation]     Extracts subject_id from response of preceding request.
    ...                 This KW executes only in EHR_SERVICE test suite, it is ignored
    ...                 in all over test suites.

    ${subjectid}=       String      response body ehr_status subject external_ref id value
                        Log To Console    \n\tDEBUG OUTPUT - EHR_STATUS SUBJECT_ID: \n\t${subjectid}[0]
                        Set Suite Variable    ${subject_id}    ${subjectid}[0]
    

Output Debug Info To Console
    [Documentation]     Prints all details of a request to console in JSON style.
    ...                 - request headers
    ...                 - request body
    ...                 - response headers
    ...                 - response body
    Output


prepare new request session
    [Arguments]         ${headers}=JSON    &{extra_headers}
    [Documentation]     Prepares request settings for RESTistance AND RequestsLibrary
    ...                 :headers: valid argument values:
    ...                     - JSON (default)
    ...                     - XML
    ...                     - no accept header
    ...                     - no content header
    ...                     - no accept header xml
    ...                     - no accept/content headers
    ...                     - no headers
    ...
    ...                 :extra_headers: optional, valid value examples: 
    ...                     - Prefer=return=representation
    ...                     - If-Match={ehrstatus_uid}
    
                        Log Many            ${headers}  ${extra_headers}

                        # case: JSON (DEFAULT)
                        Run Keyword If      $headers=='JSON'    set request headers
                        ...                 content=application/json
                        ...                 accept=application/json
                        ...                 &{extra_headers}

                        # case: no Accept header, Content-Type=JSON
                        Run Keyword If      $headers=='no accept header'    set request headers
                        ...                 content=application/json
                        ...                 &{extra_headers}

                        # case: no Content-Type header
                        Run Keyword If      $headers=='no content header'    set request headers
                        ...                 accept=application/json
                        ...                 &{extra_headers}

                        # case: no Accept & no Content-Type header
                        Run Keyword If      $headers=='no accept/content headers'    set request headers
                        ...                 &{extra_headers}

                        # case: no headers
                        Run Keyword If      $headers=='no headers'    set request headers  


set request headers
    [Arguments]         ${content}=${NONE}  ${accept}=${NONE}  &{extra_headers}
    [Documentation]     Sets the headers of a request
    ...                 :content: None (default) / application/json / application/xml
    ...                 :accept: None (default) / application/json / application/xml
    ...                 :extra_headers: optional - e.g. Prefer=return=representation
    ...                                            e.g. If-Match={ehrstatus_uid}
    ...
    ...                 ATTENTIION: RESTInstance lib sets {"Content-Type": "applicable/json"}
    ...                             and {"Accept": "application/json, */*"} by default!
    ...                             As a workaround set them to None, null or empty - i.e.:
    ...                             - "Content-Type=    "
    ...                             - "Accept=    "

                        Log Many            ${content}  ${accept}  ${extra_headers}
                        Run Keyword If    "${content}"=="${NONE}" and "${accept}"=="${NONE}"
                        ...    Log To Console   \nWARNING: NO REQUEST HEADERS SET!

    &{headers}=         Create Dictionary     &{EMPTY}
    
                        Set To Dictionary    ${headers}
                        ...                  Content-Type=${content}
                        ...                  Accept=${accept}
                        ...                  &{extra_headers}

    # comment: headers for RESTinstance Library
    &{headers}=         Set Headers         ${headers}
                        # Set Headers         ${authorization}
