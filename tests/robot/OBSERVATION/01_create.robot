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

Force Tags              create



*** Variables ***




*** Test Cases ***
001 Create Body Temperature 
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create body temperature    observation-bodytemp-example.json
    observation.validate response - 201


002 Create Blood Pressure 
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create blood pressure    observation-bloodpressure-example.json
    observation.validate response - 201


003 Create FIO2 
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create FIO2    observation-example-fiO2.json
    observation.validate response - 201


004 Create Heart Rate 
	[Documentation]    1. create EHR
	...                2. trigger observation endpoint

	ehr.create new ehr    000_ehr_status.json
	observation.create heart rate    observation-example-heart-rate.json
    observation.validate response - 201
