package com.springcourse.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	// URI Versioning -- using in twitter
	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionPerson() {
		return new PersonV1("First Last");
		
	}
	
	// URI Versioning -- using in twitter
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionPerson() {
		return new PersonV2(new Name("First", "Last"));
		
	}
	
	// Request Parameter Versioning -- using in Amazon
	
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionPersonRequestParameter() {
		return new PersonV1("First Last");
		
	}
	
	// Request Parameter Versioning -- using in Amazon
	
	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionPersonRequestParameter() {
		return new PersonV2(new Name("First", "Last"));
		
	}
	
	// (custom)headers Versioning -- using in Microsoft

	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionPersonHeader() {
		return new PersonV1("First Last");
	}
	
	// (custom)headers Versioning -- using in Microsoft
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionPersonHeader() {
		return new PersonV2(new Name("First", "Last"));
		
	}
	
	// Media type Versioning (content-negotiation or accept-header) -- using in GitHub
	
	@GetMapping(path = "/person/accept", produces = "application/api-version.v1+json")
	public PersonV1 getFirstVersionPersonAcceptHeader() {
		return new PersonV1("First Last");
		
	}
	
	// Media type Versioning (content-negotiation or accept-header) -- using in GitHub
	
	@GetMapping(path = "/person/accept", produces = "application/api-version.v2+json")
	public PersonV2 getSecondVersionPersonAcceptHeader() {
		return new PersonV2(new Name("First", "Last"));
		
	}
}
