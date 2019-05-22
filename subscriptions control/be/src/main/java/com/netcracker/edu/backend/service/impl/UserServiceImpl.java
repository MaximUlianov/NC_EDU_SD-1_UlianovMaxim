package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.DTO.LogInUserDTO;
import com.netcracker.edu.backend.DTO.UserDTO;
import com.netcracker.edu.backend.entity.*;
import com.netcracker.edu.backend.repository.LogInRepository;
import com.netcracker.edu.backend.repository.RoleRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.repository.WalletRepository;
import com.netcracker.edu.backend.service.AuditService;
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

    @Autowired
    private AuditService auditService;

    @Override
    public List<User> getUsers(int page, int perPage) {
        PageRequest pageRequest = PageRequest.of(page - 1, perPage);
        Page<User> list = userRepository.findAll(pageRequest);
       /* list.getContent().forEach(value->{
            for(Wallet o: value.getWallet()){
                if(o.isLocked()){
                    value.setBillingLocked(true);
                    break;
                }
                if(o.isNegBalance()){
                    value.setBillingNeg(true);
                }
            }
        });*/
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
    public Response save(UserDTO user) {
        Optional<UserDTO> optional = Optional.ofNullable(user);
        LogIn logIn = logInRepository.findByEmail(user.getEmail());
        if(logIn == null) {
            User tmp = userRepository.findByUsername(optional.get().getUsername());
            if (tmp == null) {
                User _user = new User();
                _user.setFirst_name(optional.get().getFirst_name());
                _user.setLast_name(optional.get().getLast_name());
                _user.setBirthday(optional.get().getBirthday());
                _user.setUsername(optional.get().getUsername());
                _user.setCountry(optional.get().getCountry());
                _user.setRoles(Collections.singleton(roleRepository.findByRole(optional.get().getRole())));
                userRepository.save(_user);
                LogIn _logIn = new LogIn();
                _logIn.setEmail(optional.get().getEmail());
                _logIn.setPassword(optional.get().getPassword());
                _logIn.setUser(_user);
                logInRepository.save(_logIn);
                return new Response("ok");
            }
            return new Response("username exists");
        }
        return new Response("email exists");
    }

    public User getUserInfoByEmail(String email){
        return logInRepository.findByEmail(email).getUser();
    }

    @Transactional
    @Override
    public Response delete(long id) {
        userRepository.deleteById(id);
        return new Response("ok");
    }

    @Override
    public Response saveSubscr(User user) {
        userRepository.save(user);
        return new Response("ok");
    }

    @Override
    public List<Subscription> getUserSubscrById(long id) {
        User user = userRepository.findById(id).get();
        List<Subscription> subscriptions = new ArrayList<>();
        List<Wallet> list = new ArrayList<>(user.getWallet());
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
    public Response blockSubscription(long[] id) {
        User user = userRepository.findById(id[0]).get();
        /*user.getWallet().forEach(value->{
            value.getSubscriptions().forEach(subscription -> {
                if(subscription.getId() == id[1]){
                    value.setLocked(true);
                    Audit audit = new Audit();
                    audit.setUser(user);
                    audit.setData("Subscription: " + subscription.getSubscriptionName() + " blocked");
                    audit.setDate(new Date());
                    auditService.addRecord(audit);
                    walletRepository.save(value);
                    return;
                }
            });
        });*/
        return new Response("ok");
    }

    @Override
    public Response unblockSubscription(long[] id) {
        User user = userRepository.findById(id[0]).get();
        /*user.getWallet().forEach(value->{
            value.getSubscriptions().forEach(subscription -> {
                if(subscription.getId() == id[1]){
                    value.setLocked(false);
                    Audit audit = new Audit();
                    audit.setUser(user);
                    audit.setData("Subscription: " + subscription.getSubscriptionName() + " unblocked");
                    audit.setDate(new Date());
                    auditService.addRecord(audit);
                    walletRepository.save(value);
                    return;
                }
            });
        });*/
        return new Response("ok");
    }

    @Override
    public List<Audit> getUserHistory(long id) {
        return new ArrayList<>(userRepository.findById(id).get().getAudits());
    }

    @Override
    public List<Wallet> getUserWallets(long id) {
        return new ArrayList<>(userRepository.findById(id).get().getWallet());
    }
}
