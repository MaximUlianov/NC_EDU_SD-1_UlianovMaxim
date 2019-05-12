package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.DTO.SubscriptionDTO;
import com.netcracker.edu.backend.entity.*;
import com.netcracker.edu.backend.repository.*;
import com.netcracker.edu.backend.service.SubscriptionService;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CompanyInfoRepository companyInfoRepository;

    @Override
    public List<Subscription> getAllSubscriptions(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<Subscription> list = subscriptionRepository.findAll(pageRequest);
        return list.getContent();
    }

    @Override
    public List<Subscription> getSubscrByCategory(long id) {
        Category category = categoryRepository.findById(id).get();
        return new ArrayList<>(category.getSubscriptions());
    }

    @Override
    public List<Subscription> getSubscrByCompany(long id) {
        CompanyInfo companyInfo = companyInfoRepository.findById(id).get();
        return new ArrayList<>(companyInfo.getSubscriptions());
    }


    @Override
    public List<Subscription> getSubscrByPartOfName(String name) {
        return subscriptionRepository.findBySubscriptionNameContains(name);
    }

    @Override
    public String subscribe(SubscriptionDTO sub) {
        LogIn logIn = logInRepository.findByEmail(sub.getEmail());

        Subscription subscription = subscriptionRepository.findById(sub.getId()).get();
        subscription.getUsers().add(logIn.getUser());
        logIn.getUser().getSubscriptions().add(subscription);

        Wallet wallet  =  walletRepository.findById(sub.getWallet().getId()).get();
        wallet.getSubscriptions().add(subscriptionRepository.findById(sub.getId()).get());

        userService.saveSubscr(logIn.getUser());
        walletRepository.save(wallet);
        return "Subscribe";
    }

    @Override
    public String addSubscription(SubscriptionDTO subscription) {
        Subscription subscr = new Subscription();
        subscr.setSubscriptionName(subscription.getSubscriptionName());
        subscr.setCostPerMonth(subscription.getCostPerMonth());
        subscr.setCategory(categoryRepository.findByName(subscription.getCategory().getName()));
        subscr.setCompany(companyInfoRepository.findByName(subscription.getCompany().getName()));
        subscriptionRepository.save(subscr);
        return "Added";
    }

    @Override
    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "Added";
    }

    @Override
    public List<Subscription> getUserSubscriptions(String email) {
        LogIn logIn = logInRepository.findByEmail(email);
        List<Subscription> subscriptions = new ArrayList<>();
        List<Wallet> list = new ArrayList<>(logIn.getUser().getWallet());
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
            else {
                value.getSubscriptions().forEach(subscr -> {
                    subscr.setNegBalance(false);
                });
            }
                subscriptions.addAll(value.getSubscriptions());
            });
        return subscriptions;
    }

    @Override
    public String deleteUserSubscription(String email, long id) {
        LogIn logIn = logInRepository.findByEmail(email);
        Subscription subscription = subscriptionRepository.findById(id).get();

        logIn.getUser().getSubscriptions().remove(subscription);
        subscription.getUsers().remove(logIn.getUser());

        logIn.getUser().getWallet().forEach(wallet->{
            for(Subscription o:wallet.getSubscriptions()){
                if(o.equals(subscription)){
                    wallet.getSubscriptions().remove(subscription);
                    break;
                }
            }
            walletRepository.save(wallet);
        });
        userService.saveSubscr(logIn.getUser());
        return "Deleted";
    }

    @Override
    public String deleteSubscription(long id) {
        subscriptionRepository.deleteById(id);
        return "Deleted";
    }

    @Override
    public int getTotalPages(int perPage) {
        List<Subscription> list = (List<Subscription>) subscriptionRepository.findAll();
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
}
