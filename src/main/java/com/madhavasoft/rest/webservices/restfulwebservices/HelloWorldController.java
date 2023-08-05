package com.madhavasoft.rest.webservices.restfulwebservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	//Returning string hello-world - URL- http://localhost:8080/hello-world
//	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	//Returning Bean CLASS
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World Bean");
	}
	//URL - http://localhost:8080/hello-world/path-variable/Madhava
	@GetMapping(path = "/hello-world/path-variable/{pathVariableName}")
	public HelloWorldBean helloWorldBean(@PathVariable String pathVariableName) {
		return new HelloWorldBean("Hello World Bean  "+pathVariableName);
	}
	
	//Path Variables - URL- http://localhost:8080/hello-world/path-variable/Madhava/todos/1414
	@GetMapping(path = "/hello-world/path-variable/{pathVariableName}/todos/{sno}")
	public HelloWorldBean helloWorldBean(@PathVariable String pathVariableName,@PathVariable int sno) {
		return new HelloWorldBean("Hello World Bean"+pathVariableName+"Sno.."+sno);
	}
}