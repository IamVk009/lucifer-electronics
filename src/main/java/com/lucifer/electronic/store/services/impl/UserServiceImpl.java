package com.lucifer.electronic.store.services.impl;


import com.lucifer.electronic.store.dtos.UserDto;
import com.lucifer.electronic.store.entities.User;
import com.lucifer.electronic.store.repositories.UserRepository;
import com.lucifer.electronic.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
//      Converting userDto object to User Entity
        User newUser = dtoToEntity(userDto);
        userRepository.save(newUser);
//      Converting User Entity object to userDTO and returning.
        return entityToDto(newUser);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found with given ID"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

//        Way - 1 : Using For-Each Loop
//        List<UserDto> allDtoUsers = new ArrayList<>();
//        for (User user : allUsers){
//            allDtoUsers.add(entityToDto(user));
//        }

//        Way - 2 : Using map() method of Stream API.
        List<UserDto> allDtoUsers = allUsers.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return allDtoUsers;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not found with given Email"));
        return entityToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given ID"));
        User updatedUser = User.builder()
//               In order to Retain the existing UUID, we need to set userId of the user We want to update while using builder pattern.
                .userId(user.getUserId())

                .name(userDto.getName())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .imageName(userDto.getImageName())
                .build();

        return entityToDto(userRepository.save(updatedUser));
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given ID"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword).orElseThrow(() -> new RuntimeException("User details not found with given keyword"));
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    private User dtoToEntity(UserDto userDto) {

//      Way - 1 : Converting DTO to Entity Manually using builder method.
//        return User.builder().id(userDto.getId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .address(userDto.getAddress())
//                .password(userDto.getPassword())
//                .imageName(userDto.getImageName())
//                .build();

//      Way - 2 : Converting Entity to DTO using ModelMapper library.
        return modelMapper.map(userDto, User.class);
    }

    private UserDto entityToDto(User savedUser) {
//      Way - 1 : Converting Entity to DTO Manually using builder method.
//        return UserDto.builder()
//                .id(savedUser.getId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .address(savedUser.getAddress())
//                .password(savedUser.getPassword())
//                .imageName(savedUser.getImageName())
//                .build();

//      Way - 2 : Converting Entity to DTO using ModelMapper library.
        return modelMapper.map(savedUser, UserDto.class);
    }

}
