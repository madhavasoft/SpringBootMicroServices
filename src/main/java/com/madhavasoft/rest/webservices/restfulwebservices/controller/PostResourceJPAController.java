package com.madhavasoft.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.madhavasoft.rest.webservices.restfulwebservices.ExceptionHandler.UserNotFoundException;
import com.madhavasoft.rest.webservices.restfulwebservices.dao.jpa.IPostRepository;
import com.madhavasoft.rest.webservices.restfulwebservices.dao.jpa.IUserRepository;
import com.madhavasoft.rest.webservices.restfulwebservices.user.Post;
import com.madhavasoft.rest.webservices.restfulwebservices.user.User;

import jakarta.validation.Valid;

@RestController
public class PostResourceJPAController {
	private IUserRepository userRepository; 
	private IPostRepository postRepository; 
	public PostResourceJPAController(IUserRepository userRepository,IPostRepository postRepository) {
		this.userRepository=userRepository;
		this.postRepository=postRepository;
	}
	//URL - http://localhost:8080/jpa/posts
	@GetMapping("/jpa/posts")
	public List<Post> retriveAllPost() {
		return postRepository.findAll();
	}
	//http://localhost:8080/jpa/post/2
	@GetMapping("/jpa/post/{id}")
	public EntityModel<Post> retrivePostById(@PathVariable int id) {
		Optional<Post> post=postRepository.findById(id);
		if(post.isEmpty())
			throw new UserNotFoundException("id:"+id);
		EntityModel<Post> entityModel=EntityModel.of(post.get());
		return entityModel;
	}
	
/*	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		System.out.println(user.getName());
		User savedUser = repository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		//Location(	http://localhost:8080/jpa/users/4) will be added as part of Response Header
		return ResponseEntity.created(location).build();
	}*/
	
	
	//DELETE request - http://localhost:8080/jpa/post/2
	@DeleteMapping("/jpa/post/{id}")
	public void removeById(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id:"+id);
		
		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();   

		return ResponseEntity.created(location).build();

	}
	
}
