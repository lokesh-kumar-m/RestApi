package com.dev.restapi.user;

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

import jakarta.validation.Valid;

@RestController
public class UserResource {
    
    private UserDaoService userService;

    public UserResource(UserDaoService userDaoService){
        this.userService=userDaoService;
    }

    @GetMapping(path="/users")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping(path="/user/{id}")
    public User getUserById(@PathVariable int id){
        User user= userService.findOne(id);
        if(user==null)throw new UserNotFoundException("id"+id);
        return user;
    }

    //if we use @Valid for method parameters, the validations defined for the 
    //object are automatically invoked oupn binding
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User createduser=userService.createUser(user);
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

    @DeleteMapping(path="/user/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteById(id);
    }
}
