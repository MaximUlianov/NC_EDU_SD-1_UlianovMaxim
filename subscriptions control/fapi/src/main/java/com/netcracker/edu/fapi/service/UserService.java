package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.LoginUser;
import com.netcracker.edu.fapi.models.Subscription;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.models.UserInfo;

import java.util.List;

public interface UserService {

    LoginUser findByLogin(String login);
    List<UserInfo> getUsers(int page, int size);
    List<Subscription> getUserSubscrByAdmin(long id);
    List<UserInfo> searchUser(String type, String value);
    User getUserInfoByEmail(String email);
    String getUsername(String email);
    User save(User user);
    Integer getTotalPages(int perPage);
    String blockSubscription(long [] id);
    String unblockSubscription(long [] id);
}
