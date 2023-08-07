package com.madhavasoft.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource=messageSource;
	}
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
	
	//Internationalization - I18N - Slide 36- https://ibm-learning.udemy.com/course/microservices-with-spring-boot-and-spring-cloud/learn/lecture/33578760#overview
	@GetMapping(path="/hello-world-internationalization")
	public String helloWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale );
		//While sending the request we need to add a Header - 'Accept-Language' and value is nl
//		- Example: `en` - English (Good Morning)
//		- Example: `nl` - Dutch (Goedemorgen)
//		- Example: `fr` - French (Bonjour)
//		- Example: `de` - Deutsch (Guten Morgen)	
	}
}