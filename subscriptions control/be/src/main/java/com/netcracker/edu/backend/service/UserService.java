package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.DTO.LogInUserDTO;
import com.netcracker.edu.backend.DTO.UserDTO;
import com.netcracker.edu.backend.entity.*;

import java.util.List;

public interface UserService {

    List<User> getUsers(int page, int perPage);
    List<User> searchUser(String type, String value);
    List<Audit> getUserHistory(long id);
    List<Subscription> getUserSubscrById(long id);
    List<Wallet> getUserWallets(long id);

    Response delete(long id);
    Response saveSubscr(User user);
    Response blockSubscription(long [] id);
    Response unblockSubscription(long [] id);
    Response save(UserDTO _user);
    String getUsername(String email);

    UserDTO getUser(UserDTO _user);
    LogInUserDTO getUserByEmail(String email);
    User getUserInfoByEmail(String email);

    Integer getTotalPages(int perPage);

}
