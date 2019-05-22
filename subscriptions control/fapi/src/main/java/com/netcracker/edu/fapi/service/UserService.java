package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.*;

import java.util.List;

public interface UserService {

    List<UserInfo> getUsers(int page, int size);
    List<Subscription> getUserSubscrByAdmin(long id);
    List<UserInfo> searchUser(String type, String value);
    List<Audit> getUserHistory(long id);
    List<Audit> searchHistory(long id, String from, String to);

    List<Wallet> getUserWalletsByAdmin(long id);

    Response blockSubscription(long [] id);
    Response unblockSubscription(long [] id);

    LoginUser findByLogin(String login);
    User getUserInfoByEmail(String email);
    Response save(User user);
    Integer getTotalPages(int perPage);


}
