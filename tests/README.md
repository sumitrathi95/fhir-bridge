# How to run this test

> Precondition: Python 3.7 (or above) & Pip are installed
> 
> Robot Framework and test dependencies are installed (`pip install -r requirements.txt)

1. start EHRbase (and it's required PostgreSQL DB) - for details check ehrbase repository
2. start FHIRbridge (`java -jar target/fhir-bridge-1.0.0-SNAPSHOT.jar`) - requires to `mvn package` it first
3. execute robot test (s. command below)

```bash
robot -d results/ -L TRACE robot/fhirbridge-example.robot
```


```bash
# robot command line options explained

-d results
# ensures robot output files are save in ./results

-L TRACE
# ensures output log has all available details
```
