package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.DTO.LogInUserDTO;
import com.netcracker.edu.backend.DTO.UserDTO;
import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers(int page, int perPage);
    List<User> searchUser(String type, String value);
    UserDTO getUser(UserDTO _user);
    Optional<UserDTO> save(UserDTO _user);
    LogInUserDTO getUserByEmail(String email);
    UserDTO getUserInfoByEmail(String email);
    String getUsername(String email);
    List<Subscription> getUserSubscrById(long id);
    void delete(long id);
    void saveSubscr(User user);
    Integer getTotalPages(int perPage);
    String blockSubscription(long [] id);
    String unblockSubscription(long [] id);
}
