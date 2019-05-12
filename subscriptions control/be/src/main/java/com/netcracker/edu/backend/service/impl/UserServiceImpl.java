package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.DTO.LogInUserDTO;
import com.netcracker.edu.backend.DTO.UserDTO;
import com.netcracker.edu.backend.entity.*;
import com.netcracker.edu.backend.repository.LogInRepository;
import com.netcracker.edu.backend.repository.RoleRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.repository.WalletRepository;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogInRepository logInRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public List<User> getUsers(int page, int perPage) {
        PageRequest pageRequest = PageRequest.of(page - 1, perPage);
        Page<User> list = userRepository.findAll(pageRequest);
        list.getContent().forEach(value->{
            for(Wallet o: value.getWallet()){
                if(o.isLocked()){
                    value.setBillingLocked(true);
                    break;
                }
                if(o.isNegBalance()){
                    value.setBillingNeg(true);
                }
            }
        });
        return list.getContent();
    }

    @Override
    public Integer getTotalPages(int perPage) {
        List<User> list = (List<User>) userRepository.findAll();
        if(list.size() == 0){
            return 0;
        }
        else if(list.size()%perPage == 0){
            return (list.size()/perPage);
        }
        else{
            return (list.size()/perPage) + 1;
        }
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
    public String getUsername(String email){
        return logInRepository.findByEmail(email).getUser().getUsername();
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

    @Transactional
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void saveSubscr(User user) {
        userRepository.save(user);
    }

    @Override
    public List<Subscription> getUserSubscrById(long id) {
        User user = userRepository.findById(id).get();
        List<Subscription> subscriptions = new ArrayList<>();
        List<Wallet> list = new ArrayList<>(user.getWallet());
        list.forEach(value->{
            if(value.isLocked()){
                value.getSubscriptions().forEach(subscr->{
                    subscr.setLocked(true);
                });
            }
            else{
                value.getSubscriptions().forEach(subscr->{
                    subscr.setLocked(false);
                });
            }
            if(value.isNegBalance()){
                value.getSubscriptions().forEach(subscr->{
                    subscr.setNegBalance(true);
                });
            }
            else{
                value.getSubscriptions().forEach(subscr->{
                    subscr.setNegBalance(false);
                });
            }
            subscriptions.addAll(value.getSubscriptions());
        });
        return subscriptions;
    }

    @Override
    public List<User> searchUser(String type, String value) {
        if(type.equals("Id")){
            List<User> list = new ArrayList<>();
            list.add(userRepository.findById(Long.parseLong(value)).get());
            return list;
        }
        else if(type.equals("Username")){
            return userRepository.findByUsernameContains(value);
        }
        else {
            return null;
        }
    }

    @Override
    public String blockSubscription(long[] id) {
        User user = userRepository.findById(id[0]).get();
        user.getWallet().forEach(value->{
            value.getSubscriptions().forEach(subscription -> {
                if(subscription.getId() == id[1]){
                    value.setLocked(true);
                    walletRepository.save(value);
                    return;
                }
            });
        });
        return "Blocked";
    }

    @Override
    public String unblockSubscription(long[] id) {
        User user = userRepository.findById(id[0]).get();
        user.getWallet().forEach(value->{
            value.getSubscriptions().forEach(subscription -> {
                if(subscription.getId() == id[1]){
                    value.setLocked(false);
                    walletRepository.save(value);
                    return;
                }
            });
        });
        return "Unblocked";
    }
}
