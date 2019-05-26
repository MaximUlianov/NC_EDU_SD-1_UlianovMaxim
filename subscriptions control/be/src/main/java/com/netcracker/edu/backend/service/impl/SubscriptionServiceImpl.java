package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.DTO.SubscriptionDTO;
import com.netcracker.edu.backend.entity.LogIn;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.repository.*;
import com.netcracker.edu.backend.service.AuditService;
import com.netcracker.edu.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    private LogInRepository logInRepository;

    private ProductRepository productRepository;

    private WalletRepository walletRepository;

    private CompanyRepository companyRepository;

    private AuditService auditService;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, LogInRepository logInRepository, ProductRepository productRepository, WalletRepository walletRepository, CompanyRepository companyRepository, AuditService auditService) {
        this.subscriptionRepository = subscriptionRepository;
        this.logInRepository = logInRepository;
        this.productRepository = productRepository;
        this.walletRepository = walletRepository;
        this.companyRepository = companyRepository;
        this.auditService = auditService;
    }

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
        subscription.setSale(sub.getProduct().getSale());
        subscription.setProduct(productRepository.findById(sub.getProduct().getId()).get());
        subscription.setWallet(wallet);

        subscriptionRepository.save(subscription);

        auditService.subscribedRecord(logIn.getUser(), subscription.getProduct().getName());
        return new Response("ok");
    }

    @Override
    public List<Subscription> getUserSubscriptions(String email) {
        LogIn logIn = logInRepository.findByEmail(email);
        List<Subscription> subscriptions = new ArrayList<>();
        logIn.getUser().getWallet().forEach(wallet -> {
            subscriptions.addAll(wallet.getSubscriptions());
        });
        return subscriptions;
    }

    @Override
    public Response deleteUserSubscription(String email, long id) {
        LogIn logIn = logInRepository.findByEmail(email);
        logIn.getUser().getWallet().forEach(wallet -> {
            wallet.getSubscriptions().forEach(subscription -> {
                if(subscription.getProduct().getId() == id){
                    subscriptionRepository.deleteById(subscription.getId());
                    auditService.unsubscribedRecord(logIn.getUser(), subscription.getProduct().getName());
                    return;
                }
            });
        });
        return new Response("Deleted");
    }

    @Override
    public Response deleteSubscriptionsByCompany(long id){
        Subscription subscription = subscriptionRepository.findById(id).get();
        subscriptionRepository.deleteById(subscription.getId());
        auditService.unsubscribedRecord(subscription.getWallet().getUser(), subscription.getProduct().getName());
        return new Response("Deleted");
    }

    @Override
    public Response setSale(long id, int sale){
        Subscription subscription = subscriptionRepository.findById(id).get();
        subscription.setSale(sale);
        subscriptionRepository.save(subscription);
        return new Response("ok");
    }


}
