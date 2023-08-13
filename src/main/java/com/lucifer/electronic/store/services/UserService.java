package com.lucifer.electronic.store.services;

import com.lucifer.electronic.store.dtos.UserDto;
import com.lucifer.electronic.store.entities.User;

import java.util.List;

public interface UserService {

//    Create user
    UserDto createUser(UserDto userDto);

//    Get User by ID
    UserDto getUserById(String id);

//    Get All Users
    List<UserDto> getAllUsers();

//    Get User by Email
    UserDto getUserByEmail(String email);

//    Update User
    UserDto updateUser(UserDto userDto);

//    Delete User by Id
    void deleteUser(String id);

//    Search User
    List<UserDto> searchUser(String keyword);
}
