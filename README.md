# FHIR Bridge

## Project setup

### Build
```
mvn clean install
```

### Build without running tests
```
mvn clean install -DskipTests
```

### Run the application

Option 1:
```
mvn spring-boot:run
```

Option 2: (update the version number accordingly)
```
java -jar target/fhir-bridge-1.0.0-SNAPSHOT.jar 
```

### Run integration tests
```
mvn verify
```


## FHIR to openEHR Mappings

| FHIR Resource    | FHIR Profile                 | openEHR OPT                                                 |
| ---------------- | ---------------------------- | ----------------------------------------------------------- |
| DiagnosticReport | [DiagnosticReportLab][1]     | [Laborbefund][ckm1]                                         |
| Observation      | [bodytemp][2]                | [Intensivmedizinisches Monitoring Korpertemperatur][ckm2] * |
| Observation      | [CoronavirusNachweisTest][3] | [Kennzeichnung Erregernachweis SARS-CoV-2][ckm3]            |
| Condition        | -                            | [Diagnose][ckm4]                                            |

[1]: https://github.com/ehrbase/fhir-bridge/blob/master/src/main/resources/profiles/DiagnosticReportLab.xml
[2]: https://github.com/ehrbase/fhir-bridge/blob/master/src/main/resources/profiles/bodytemp.xml
[3]: https://github.com/ehrbase/fhir-bridge/blob/master/src/main/resources/profiles/CoronavirusNachweisTest.xml
[ckm1]: https://ckm.highmed.org/ckm/templates/1246.169.220
[ckm2]: https://ckm.highmed.org/ckm/templates/1246.169.671
[ckm3]: https://ckm.highmed.org/ckm/templates/1246.169.697
[ckm4]: https://ckm.highmed.org/ckm/templates/1246.169.714

* With the Korpertemperatur OPT we needed to change the template_id because it has a "ö", that makes EHRBASE fail on
  GET /template/adl1.4/$template_id. Raised an [issue][issue1] to check it.

[issue1]: https://github.com/ehrbase/project_management/issues/273

* For the Condition we don't have a FHIR profile, this case uses the native Condition resource.


### Mapping Resources

To define the data mappings, we used spreadsheets that can be found in the [mappings][map1]
folder. Those were generated from the archetypes referenced by the OPTs using the [ADL2CSV tool][map2]

[map1]: https://github.com/ehrbase/fhir-bridge/tree/master/mappings/archetypes
[map2]: https://www.youtube.com/watch?v=hMsRkIhuUsU


## Docker deployment

1. git clone git@github.com:ehrbase/fhir-bridge.git
2. cd fhir-bridge
3. mvn compile jib:dockerBuild
4. docker run -p 8888:8888 -e EHRBASE_ADDRESS=172.17.0.1 fhir-bridge

Note 1: 8888 is the default port of the API offered by the fhir-bridge app and the IP is where EHRBASE is running.

Note 2: it uses 8080 as default port to communicate to EHRBASE, if another port is needed, please add -e EHRBASE_PORT=NNNN

Note 3: if you need to run fhir-bridge on another port please add -e SERVER_PORT=MMMM and change -p MMMM:MMMM


## Testing FHIR operations

There is an [Insomnia REST Client][insomnia] configuration in the "src/test/resources/Insomnia_YYYY-MM-DD.json" file project,
that contains requests to test the FHIR interface.

From Insomnia, import the JSON file, and you will see some requests are created for you.

When you click on a request in Insomnia, you will see some parameters or URLs have a blue box, that denotes a reference to a
value contained in another request's response payload, it helps to set values dynamically for testing without manual copy and paste.

[insomnia]: https://insomnia.rest/


### Create EHR Request (EHRBASE)

Before executing the FHIR requests, one EHR should be created in EHRBASE with a specific subject_id (patient id).
This is the "POST ehr first!", that points to "http://localhost:8080/ehrbase/rest/openehr/v1/ehr".
Change this URL accordingly, depending on where you have EHRBASE running, but maintain the payload.

Then run the "GET ehr" request to get the EHR ID, which will be used by the rest of the requests to get the patient ID back
(used in the FHIR resources to POST) and verify there is an EHR in EHRBASE (without an EHR, the requests will fail with an error status).


### Create Resource Requests (FHIR)

There are four requests used to create resources using the FHIR interface. When the FHIR-bridge receives those FHIR resources,
executes the mappings mentioned above and commits the correspondent openEHR COMPOSITIONs to EHRBASE. These requests are:

 * FHIR-BRIDGE POST Body Temp
 * FHIR-BRIDGE POST Condition
 * FHIR-BRIDGE POST SARS COV 2
 * FHIR-BRIDGE POST Lab Result (Observation)

All those are POST requests, and the successful response should have status code 201 Created.


### Search Resources Requests (FHIR)

There are four requests used to execute the FHIR search operation over the resources mapped in the "create resources" requests.
For the Observation search, the profile is a required parameter. For the Condition the profile is not used because we don't have 
a profile for it (mentioned above in the "mappings" section). There is another parameter required, that is mapped to the patient
identifier, that is the "identifier" parameter. These requests are:

 * FHIR-BRIDGE test search Body Temp
 * FHIR-BRIDGE test search Body Temp Alternative Impl
 * FHIR-BRIDGE test search Condition
 * FHIR-BRIDGE test search SARS COV 2
 * FHIR-BRIDGE test search Lab Result (Observation)
 
Note: the "FHIR-BRIDGE test search Body Temp Alternative Impl" request should return the same result as "FHIR-BRIDGE test search Body Temp",
but has a slightly different implementation to evaluate code complexity and performance. The "alternative" does a query for data points and uses RM
classes from Archie, while the "normal" one does a query for full compositions and uses the classes generated by the SDK generator.

All those are GET requests, and the successful response should have a status code 200 OK. The response payload is a FHIR Bundle resource.

For the Condition search we added a date range filter which allows to send a lower and upper boundary, just the lower, just
the upper or none and the search will filter accordingly. That uses the 'recorded-date' parameter (check the request in Insomnia). 

For the Observation search we added a date range filter using the 'date' parameter, also updated the Insomnia requests to show how that works.

### Get Resource by ID Requests (FHIR)

As a test we implemented a GET resource operation for the conditions, that uses the URL associated with each resource returned
by the search operation. The response for the GET operation should be an individual resource.

The GET request is:

 * FHIR-BRIDGE test get Condition

Right now we are mapping the COMPOSITION.uid to the FHIR resource ID, so the ID will look like this: d4090552-d85c-4828-9282-3dbabb9c4d43::local.ehrbase.org::1 
