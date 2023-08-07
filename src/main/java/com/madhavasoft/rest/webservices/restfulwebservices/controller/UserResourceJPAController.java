package com.madhavasoft.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import com.madhavasoft.rest.webservices.restfulwebservices.dao.jpa.IUserRepository;
import com.madhavasoft.rest.webservices.restfulwebservices.user.User;

import jakarta.validation.Valid;

@RestController
public class UserResourceJPAController {
	private IUserRepository repository; 
	public UserResourceJPAController(IUserRepository repository) {
		this.repository=repository;
	}
	//URL - http://localhost:8080/jpa/users
	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers() {
		return repository.findAll();
	}
	//http://localhost:8080/jpa/users/1004
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retriveUserById(@PathVariable int id) {
		Optional<User> user=repository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		EntityModel<User> entityModel=EntityModel.of(user.get());
		return entityModel;
	}
	
	//POST Request- http://localhost:8080/users
	/*  @PostMapping("/jpa/users") 
	  public void createUser(@RequestBody User user) {
	  repository.save(user); 
	  }*/
	 //OR
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		System.out.println(user.getName());
		User savedUser = repository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		//Location(	http://localhost:8080/jpa/users/4) will be added as part of Response Header
		return ResponseEntity.created(location).build();
	}
	
	
	//DELETE request - http://localhost:8080/jpa/users/2
	@DeleteMapping("/jpa/users/{id}")
	public void removeById(@PathVariable int id) {
		repository.deleteById(id);
	}
	
	
}
