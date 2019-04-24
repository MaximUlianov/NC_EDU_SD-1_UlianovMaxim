package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.LoginUser;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.models.UserInfo;
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
        LoginUser user = restTemplate.getForObject(backendServerUrl + "/api/user/login/" + login, LoginUser.class);
        return user;
    }

    @Override
    public List<UserInfo> getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        UserInfo[] usersResponse = restTemplate.getForObject(backendServerUrl + "/api/user", UserInfo[].class);
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
        return rTemp.getForObject(backendServerUrl + "/api/user/" + email, User.class);
    }

    @Override
    public User getUsername(String email){
        RestTemplate rTemp = new RestTemplate();
        return rTemp.getForObject(backendServerUrl + "/api/user/username/" + email, User.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(LoginUser user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return authorities;
    }

}
