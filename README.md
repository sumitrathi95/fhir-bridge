# FHIR Bridge

## Project setup
```
mvn install
```

### Run the application
```
mvn spring-boot:run
```

### Run integration tests
```
mvn verify
```


## Mappings

| FHIR Resource    | FHIR Profile                 | openEHR OPT         |
| ---------------- | ---------------------------- | ------------------- |
| DiagnosticReport | [DiagnosticReportLab][1]     | [Laborbefund][ckm1] |
| Observation      | [bodytemp][2]                | [Intensivmedizinisches Monitoring Korpertemperatur][ckm2] * |
| Observation      | [CoronavirusNachweisTest][3] | [Kennzeichnung Erregernachweis SARS-CoV-2][ckm3] |
| Condition        | -                            | [Diagnose][ckm4] |

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