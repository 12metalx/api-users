package com.example.crud.controller;

import com.example.crud.domain.User;
import com.example.crud.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
  
    @Autowired
    private UserService userService;
    
    @PostMapping(value = "/new")
    private ResponseEntity<?>  createUser(@RequestBody User user){
        if(userService.findOneByUsername(user.getUsername()).isPresent()){
            return new ResponseEntity<>("Ya existe usuario con ese nombre de usuario",HttpStatus.CONFLICT);
        }
        try {
            userService.create(user);
            return new ResponseEntity<>(null,HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
    
    @GetMapping(value = "/users")
    private ResponseEntity<?> getUsers(){
        try {
            return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
    
    @GetMapping(value = "/user")
    private ResponseEntity<?> getUser(@RequestParam Long id){
        try {
            Optional<User> userOptional = userService.findOne(id);
            if(userOptional.isPresent()){
                return new ResponseEntity<>(userOptional.get(),HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontro el usuario",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/update")
    private ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            Optional<User> userOptional = userService.findOne(user.getId());
            if(userOptional.isPresent()){
                return new ResponseEntity<>(userService.update(user),HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontro el usuario",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping(value = "/delete")
    private ResponseEntity<?> deletUser(@RequestBody User user){
        try {
            Optional<User> userOptional = userService.findOne(user.getId());
            if(userOptional.isPresent()){
                userService.delete(user);
                return new ResponseEntity<>(null,HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontro el usuario",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
