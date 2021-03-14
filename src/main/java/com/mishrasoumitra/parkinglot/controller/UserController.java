package com.mishrasoumitra.parkinglot.controller;

import com.mishrasoumitra.parkinglot.exceptions.IncorrectPasswordException;
import com.mishrasoumitra.parkinglot.exceptions.UserAlreadyExistsException;
import com.mishrasoumitra.parkinglot.exceptions.UserNotFoundException;
import com.mishrasoumitra.parkinglot.model.Response;
import com.mishrasoumitra.parkinglot.model.User;
import com.mishrasoumitra.parkinglot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> list() {
        return userService.listAllUser();
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> get(@PathVariable String userName) {
        try {
            User user = userService.getUser(userName);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(e.getMessage(),false), HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> add(@RequestBody User user) {
        try {
            userService.addUser(user);

            return new ResponseEntity<>(new Response("Successfully created new user"),HttpStatus.OK);
        }
        catch(UserAlreadyExistsException e) {
            return new ResponseEntity<>(new Response(String.format("Username %s already exist, try another",user.getUserName()),false), HttpStatus.OK);
        }
    }


    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody User user) {
        try {
            User existingUser = userService.getUser(user.getUserName());
            if(user.getFirstName()!=null)
                existingUser.setFirstName(user.getFirstName());
            if(user.getLastName()!=null)
                existingUser.setLastName(user.getLastName());
            if(user.getPassword()!=null)
                existingUser.setPassword(user.getPassword());
            if(user.getUserType()!=null)
                existingUser.setUserType(user.getUserType());

            userService.updateUser(existingUser);
            return new ResponseEntity<>(new Response("Successfully updated user"),HttpStatus.OK);
        }
        catch (UserNotFoundException e) {
            return new ResponseEntity<>(new Response(e.getMessage(),false),HttpStatus.OK);
        }
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<?> delete(@PathVariable String userName) {
        try {
            userService.deleteUser(userName);
            return new ResponseEntity<>(new Response(String.format("User %s deleted successfully", userName)),HttpStatus.OK);
        }
        catch(UserNotFoundException e) {
            return new ResponseEntity<>(new Response(e.getMessage(),false),HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            String sessionId = userService.login(user.getUserName(), user.getPassword());
            return new ResponseEntity<>(new Response(sessionId), HttpStatus.OK);
        }
        catch(UserNotFoundException e) {
            return new ResponseEntity<>(new Response(e.getMessage(), false), HttpStatus.OK);
        }
        catch (IncorrectPasswordException e) {
            return new ResponseEntity<>(new Response(e.getMessage(), false), HttpStatus.OK);
        }
    }



}
