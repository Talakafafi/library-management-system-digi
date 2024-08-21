package com.library.management.system.controller;

import com.library.management.system.Service.UserService;
import com.library.management.system.dto.UserRequestDto;
import com.library.management.system.exception.type.EmailNotFoundException;
import com.library.management.system.exception.type.IdNotFoundException;
import com.library.management.system.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.addUser(userRequestDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws IdNotFoundException {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);


    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) throws EmailNotFoundException {
            User user = userService.getUserByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserRequestDto updatedUser) throws IdNotFoundException {
            userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) throws IdNotFoundException {
            userService.deleteUser(id);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
}