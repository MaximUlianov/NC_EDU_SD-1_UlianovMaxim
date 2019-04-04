package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.DTO.UserDTO;
import com.netcracker.edu.backend.entity.LogIn;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.LogInRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogInRepository logInRepository;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findByUsername(String login) {
        return userRepository.findByUsername(login);
    }

    @Override
    public UserDTO save(UserDTO user)
    {
        LogIn logIn = logInRepository.findByEmail(user.getEmail());
        if(logIn == null){
            User _user = new User(user);
            userRepository.save(_user);
            logInRepository.save(new LogIn(user.getEmail(), user.getPassword(), _user));
            return user;
        }
        return null;
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
