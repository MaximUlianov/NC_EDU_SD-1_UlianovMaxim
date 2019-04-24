package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.LoginUser;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.models.UserInfo;

import java.util.List;

public interface UserService {

    LoginUser findByLogin(String login);
    List<UserInfo> getAllUsers();
    User getUserInfoByEmail(String email);
    User getUsername(String email);
    User save(User user);
}
