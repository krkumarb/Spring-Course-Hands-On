package com.springcourse.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.springcourse.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.springcourse.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJPAResource {
	
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrivePostForUser(@PathVariable int id) {
		Optional<User> user= userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id : "+id);
		
		return user.get().getPosts();
		
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retriveSingleUser(@PathVariable int id) {
		Optional<User> user= userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id : "+id);
		
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retriveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@GetMapping("/jpa/users/{id}/posts/{post_id}")
	public EntityModel<Post> retrivePostForUser(@PathVariable int id, @PathVariable int post_id) {
		Optional<Post> post= postRepository.findById(post_id);
		
		if(post.isEmpty())
			throw new UserNotFoundException("post-id : "+post_id);
		
		EntityModel<Post> entityModel = EntityModel.of(post.get());
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrivePostForUser(id));
		entityModel.add(link.withRel("all-posts"));
		
		return entityModel;
		
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> saveUser(@Valid  @RequestBody User user) {
		 User savedUser = userRepository.save(user);
		 
		 URI location= ServletUriComponentsBuilder.fromCurrentRequest()
				 		.path("/{id}")
				 		.buildAndExpand(savedUser.getId())
				 		.toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid  @RequestBody Post post) {
		Optional<User> user= userRepository.findById(id);
		
		if(user.isEmpty())
			throw new UserNotFoundException("id : "+id);
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest()
		 		.path("/{id}")
		 		.buildAndExpand(savedPost.getId())
		 		.toUri();
		return ResponseEntity.created(location).build();
		
		
		
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void removeUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	
	


}
