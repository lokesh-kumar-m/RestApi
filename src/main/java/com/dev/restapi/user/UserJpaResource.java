package com.dev.restapi.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
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

import com.dev.restapi.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
    
    private UserRepository services;

    public UserJpaResource(UserRepository services){
        this.services=services;
    }

    @GetMapping(path="/users/jpa")
    public List<User> getAllUsers(){
        return services.findAll();
    }

    @GetMapping(path="/user/jpa/{id}")
    public EntityModel<User> getUserById(@PathVariable int id){
        Optional<User> user= services.findById(id);
        if(user.isEmpty())throw new UserNotFoundException("id"+id);
        EntityModel<User> userEntityModel=EntityModel.of(user.get());
        WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).getAllUsers());
        userEntityModel.add(link.withRel("all users"));
        return userEntityModel;
    }

    //if we use @Valid for method parameters, the validations defined for the 
    //object are automatically invoked oupn binding
    @PostMapping("/user/jpa")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User createduser=services.save(user);
        URI location=ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}").
        buildAndExpand(createduser.getId()).
        toUri();
        /*
        ResposeEntity is used to return the reponse status. 
        .build() returns the reposeEntity itself
         * 200--success
         * 201--created
         * 204--no content
         * 401--Unauthorized
         * 400--bad req
         * 404--resource not found
         * 500--server error
         */
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="/user/jpa/{id}")
    public void deleteUser(@PathVariable int id){
        services.deleteById(id);
    }

    @GetMapping(path="/user/jpa/{id}/post")
    public List<Post> getUserPost(@PathVariable int id){
        Optional<User> user=services.findById(id);
        if(user.isEmpty())throw new UserNotFoundException("id"+id);   
        return user.get().getPost();
    }
}
