package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.DTO.UserDTO;
import com.netcracker.edu.backend.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();
    User findByUsername(String login);
    UserDTO save(UserDTO user);
    void delete(long id);
}
