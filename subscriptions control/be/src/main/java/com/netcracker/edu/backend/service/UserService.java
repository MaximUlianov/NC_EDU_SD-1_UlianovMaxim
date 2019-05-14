package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.DTO.LogInUserDTO;
import com.netcracker.edu.backend.DTO.UserDTO;
import com.netcracker.edu.backend.entity.Audit;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers(int page, int perPage);
    List<User> searchUser(String type, String value);
    List<Audit> getUserHistory(long id);
    List<Subscription> getUserSubscrById(long id);

    Response delete(long id);
    Response saveSubscr(User user);
    Response blockSubscription(long [] id);
    Response unblockSubscription(long [] id);
    String getUsername(String email);

    UserDTO getUser(UserDTO _user);
    Optional<UserDTO> save(UserDTO _user);
    LogInUserDTO getUserByEmail(String email);
    UserDTO getUserInfoByEmail(String email);

    Integer getTotalPages(int perPage);

}
