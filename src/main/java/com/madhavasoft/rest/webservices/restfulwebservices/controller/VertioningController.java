package com.madhavasoft.rest.webservices.restfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhavasoft.rest.webservices.restfulwebservices.user.Name;
import com.madhavasoft.rest.webservices.restfulwebservices.user.PersonV1;
import com.madhavasoft.rest.webservices.restfulwebservices.user.PersonV2;

@RestController
public class VertioningController {
	//URI Version
	@GetMapping("/v1/person") // http://localhost:8080/v1/person
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Mahendhar");
	}

	@GetMapping("/v2/person") // http://localhost:8080/v2/person
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Madhava", "Rao"));
	}
	//Request Param Version
	// http://localhost:8080/person?version=1
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter() {
		return new PersonV1("Bob Charlie");
	}
	// http://localhost:8080/person?version=2
	@GetMapping(path= "/person", params ="version=2") 
	public PersonV2 getSecondVersionOfPersonRequestParameter() {
		return new PersonV2(new Name("Madhava", "Rao"));
	}
	
	//Custom Versioning Header
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	//Media Type Versioning
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
