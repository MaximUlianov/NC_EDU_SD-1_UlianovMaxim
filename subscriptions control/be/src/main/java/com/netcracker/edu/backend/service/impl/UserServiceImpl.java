package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.DTO.LogInUserDTO;
import com.netcracker.edu.backend.DTO.UserDTO;
import com.netcracker.edu.backend.entity.LogIn;
import com.netcracker.edu.backend.entity.Role;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.LogInRepository;
import com.netcracker.edu.backend.repository.RoleRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogInRepository logInRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public LogInUserDTO getUserByEmail(String email) {
        LogIn logIn = logInRepository.findByEmail(email);
        User user = logIn.getUser();
        Iterator<Role> iterator = user.getRoles().iterator();
        LogInUserDTO logUser = new LogInUserDTO(email, logIn.getPassword(), iterator.next().getRole());
        return logUser;
    }

    @Override
    public UserDTO getUser(UserDTO _user){
        LogIn logIn = logInRepository.findByEmailAndPassword(_user.getEmail(), _user.getPassword());
        if(logIn != null){
            User user = logIn.getUser();
            _user.setAll(user);
            return _user;
        }
        return _user;
    }

    @Override
    public UserDTO getUsername(String email){
        LogIn logIn = logInRepository.findByEmail(email);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(logIn.getUser().getUsername());
        return userDTO;
    }
    @Override
    public Optional<UserDTO> save(UserDTO user)
    {
        Optional<UserDTO> optional = Optional.ofNullable(user);
        LogIn logIn = logInRepository.findByEmail(user.getEmail());
        if(logIn == null){
            User _user = new User(optional.get());
            _user.setRoles(Collections.singleton(roleRepository.findByRole(optional.get().getRole())));
            userRepository.save(_user);
            logInRepository.save(new LogIn(optional.get().getEmail(), optional.get().getPassword(), _user));
        }
        return optional;
    }

    public UserDTO getUserInfoByEmail(String email){
        LogIn logIn = logInRepository.findByEmail(email);
        Iterator<Role> iterator = logIn.getUser().getRoles().iterator();
        return new UserDTO(logIn.getUser(), iterator.next());
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
