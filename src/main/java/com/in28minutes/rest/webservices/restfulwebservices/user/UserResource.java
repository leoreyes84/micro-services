package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

//	@GetMapping(path = "/users/{id}")
//	public User retrieveUser(@PathVariable int id) {
//		User user = service.findOne(id);
//		if(user == null) {
//			throw new UserNotFoundException("id-"+id);
//		}
//		return user;
//	}

	@GetMapping(path = "/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}

		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = ControllerLinkBuilder
				.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		// Redirect to the user created
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
