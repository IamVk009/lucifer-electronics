package com.lucifer.electronic.store.controllers;

import com.lucifer.electronic.store.dtos.ApiResponseMessage;
import com.lucifer.electronic.store.dtos.UserDto;
import com.lucifer.electronic.store.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @RequestBody UserDto userDto){
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

//  We should never send String directly as an API response. Instead, best way to send String API response is as follows.
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        ApiResponseMessage message = ApiResponseMessage.builder()
                .message("User Deleteed Successfully...!")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(message);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords){
        return ResponseEntity.ok(userService.searchUser(keywords));
    }
}
