package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.DTO.SubscriptionDTO;
import com.netcracker.edu.backend.entity.LogIn;
import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.repository.LogInRepository;
import com.netcracker.edu.backend.repository.SubscriptionRepository;
import com.netcracker.edu.backend.service.SubscriptionService;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private LogInRepository logInRepository;

    @Autowired
    private UserService userService;
    @Override
    public List<Subscription> getAllSubscriptions() {
       return (List<Subscription>) subscriptionRepository.findAll();
    }

    @Override
    public String subscribe(SubscriptionDTO sub) {
        LogIn logIn = logInRepository.findByEmail(sub.getEmail());
        logIn.getUser().getSubscriptions().add(subscriptionRepository.findById(sub.getId()).get());
        subscriptionRepository.findById(sub.getId()).get().getUsers().add(logIn.getUser());
        userService.saveSubscr(logIn.getUser());
        return "Subscribe";
    }

    @Override
    public List<Subscription> getUserSubscriptions(String email) {
        LogIn logIn = logInRepository.findByEmail(email);
        List<Subscription> list = new ArrayList<>(logIn.getUser().getSubscriptions());
        return list;
    }

    @Override
    public String deleteUserSubscription(String email, long id) {
        LogIn logIn = logInRepository.findByEmail(email);
        logIn.getUser().getSubscriptions().remove(subscriptionRepository.findById(id).get());
        subscriptionRepository.findById(id).get().getUsers().remove(logIn.getUser());
        userService.saveSubscr(logIn.getUser());
        return "Deleted";
    }
}
