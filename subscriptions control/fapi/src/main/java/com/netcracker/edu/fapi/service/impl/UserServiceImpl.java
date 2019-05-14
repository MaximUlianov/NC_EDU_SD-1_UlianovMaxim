package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.*;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("customUserDetailsService")
public class UserServiceImpl implements UserDetailsService,UserService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public LoginUser findByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        LoginUser user = restTemplate.getForObject(backendServerUrl + "/api/user/login?email=" + login, LoginUser.class);
        return user;
    }

    @Override
    public List<UserInfo> getUsers(int page, int size) {
        RestTemplate restTemplate = new RestTemplate();
        UserInfo[] usersResponse = restTemplate.getForObject(backendServerUrl + "/api/user/" + page + "/" + size, UserInfo[].class);
        return usersResponse == null ? Collections.emptyList() : Arrays.asList(usersResponse);
    }

    @Override
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/user", user, User.class).getBody();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUser user = findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    @Override
    public User getUserInfoByEmail(String email){
        RestTemplate rTemp = new RestTemplate();
        return rTemp.getForObject(backendServerUrl + "/api/user/info?email=" + email, User.class);
    }

    @Override
    public String getUsername(String email){
        RestTemplate rTemp = new RestTemplate();
        return rTemp.getForObject(backendServerUrl + "/api/user/username?email=" + email, String.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(LoginUser user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }

    @Override
    public List<Subscription> getUserSubscrByAdmin(long id) {
        RestTemplate restTemplate = new RestTemplate();
        Subscription [] subscriptionsResponse = restTemplate.getForObject(backendServerUrl + "/api/user/subscriptions?id=" + id, Subscription[].class);
        return subscriptionsResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionsResponse);
    }

    @Override
    public List<UserInfo> searchUser(String type, String value) {
        RestTemplate restTemplate = new RestTemplate();
        UserInfo[] usersResponse = restTemplate.getForObject(backendServerUrl + "/api/user/search?type=" + type + "&value=" + value, UserInfo[].class);
        return usersResponse == null ? Collections.emptyList() : Arrays.asList(usersResponse);
    }

    @Override
    public Integer getTotalPages(int perPage) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/user/totalPages?perPage=" + perPage, Integer.class);
    }

    @Override
    public Response blockSubscription(long[] id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/user/subscriptions/block", id, Response.class).getBody();
    }

    @Override
    public Response unblockSubscription(long[] id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/user/subscriptions/unblock", id, Response.class).getBody();
    }

    @Override
    public List<Audit> getUserHistory(long id) {
        RestTemplate restTemplate = new RestTemplate();
        Audit[] historyResponse = restTemplate.getForObject(backendServerUrl + "/api/user/audit?id=" + id, Audit[].class);
        return historyResponse == null ? Collections.emptyList() : Arrays.asList(historyResponse);
    }
}
