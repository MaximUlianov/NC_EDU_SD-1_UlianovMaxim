package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.DTO.SubscriptionDTO;
import com.netcracker.edu.backend.entity.*;
import com.netcracker.edu.backend.repository.*;
import com.netcracker.edu.backend.service.AuditService;
import com.netcracker.edu.backend.service.SubscriptionService;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    private ProductRepository productRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AuditService auditService;

    @Override
    public Response subscribe(SubscriptionDTO sub) {
        Wallet wallet  =  walletRepository.findById(sub.getWallet().getId()).get();
        if(sub.getProduct().getCostPerMonth()/30 > wallet.getSum()){
            return new Response("Not enough money");
        }
        else{
            double sum = (sub.getProduct().getCostPerMonth() - (sub.getProduct().getCostPerMonth()/100*sub.getProduct().getSale()))/30;
            wallet.setSum(wallet.getSum() - sum);
            sub.setProduct(productRepository.findById(sub.getProduct().getId()).get());
            sub.getProduct().getCompany().setProceeds(sub.getProduct().getCompany().getProceeds() + sum);
            companyRepository.save(sub.getProduct().getCompany());
            walletRepository.save(wallet);
        }
        LogIn logIn = logInRepository.findByEmail(sub.getEmail());

        Subscription subscription = new Subscription();
        subscription.setStart(sub.getStart());
        subscription.setEnd(sub.getEnd());
        subscription.setSale(sub.getSale());
        subscription.setProduct(productRepository.findById(sub.getProduct().getId()).get());
        subscription.setWallet(wallet);
        subscription.setUser(logIn.getUser());

        subscriptionRepository.save(subscription);

        Audit audit = new Audit();
        audit.setUser(logIn.getUser());
        audit.setData("Subscribed to: " + subscription.getProduct().getName());
        audit.setDate(new Date());
        auditService.addRecord(audit);
        return new Response("ok");
    }

    @Override
    public List<Subscription> getUserSubscriptions(String email) {
        LogIn logIn = logInRepository.findByEmail(email);
        List<Subscription> subscriptions = new ArrayList<>(logIn.getUser().getSubscriptions());
        return subscriptions;
    }

    @Override
    public Response deleteUserSubscription(String email, long id) {
        LogIn logIn = logInRepository.findByEmail(email);
        Subscription subscription = new Subscription();
        for(Subscription o: logIn.getUser().getSubscriptions()){
            if(o.getProduct().getId() == id){
                subscription = o;
                break;
            }
        }
        subscriptionRepository.deleteById(subscription.getId());

        Audit audit = new Audit();
        audit.setUser(logIn.getUser());
        audit.setData("Unsubscribed from: " + subscription.getProduct().getName());
        audit.setDate(new Date());
        auditService.addRecord(audit);
        return new Response("Deleted");
    }


}
