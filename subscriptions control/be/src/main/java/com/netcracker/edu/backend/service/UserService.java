package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.DTO.LogInUserDTO;
import com.netcracker.edu.backend.DTO.UserDTO;
import com.netcracker.edu.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    UserDTO getUser(UserDTO _user);
    Optional<UserDTO> save(UserDTO _user);
    LogInUserDTO getUserByEmail(String email);
    UserDTO getUserInfoByEmail(String email);
    UserDTO getUsername(String email);
    void delete(long id);
}
