package com.madhavasoft.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.madhavasoft.rest.webservices.restfulwebservices.ExceptionHandler.UserNotFoundException;
import com.madhavasoft.rest.webservices.restfulwebservices.dao.UserDaoService;
import com.madhavasoft.rest.webservices.restfulwebservices.user.User;

import jakarta.validation.Valid;

@RestController
public class UserResourceController {
	private UserDaoService service;

	public UserResourceController(UserDaoService service) {
		this.service = service;
	}
	//URL - http://localhost:8080/users
	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return service.findAll();
	}
	//http://localhost:8080/users/2
	@GetMapping("/users/{id}")
	public User retriveUserById(@PathVariable int id) {
		User user=service.findOne(id);
		if(user==null)
			 throw new UserNotFoundException("id:"+id);
			return user;
	}
	
	//POST Request- http://localhost:8080/users
/*	  @PostMapping("/users") 
	  public void createUser(@RequestBody User user) {
	  service.save(user); 
	  }*/
	 //OR
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		//Location(	http://localhost:8080/users/4) will be added as part of Response Header
		return ResponseEntity.created(location).build();
	}
	
	
	//DELETE request - http://localhost:8080/users/2
	@DeleteMapping("/users/{id}")
	public void removeById(@PathVariable int id) {
		service.removeById(id);
	}
}
