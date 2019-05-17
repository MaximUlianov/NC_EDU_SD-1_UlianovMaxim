package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.DTO.WalletDTO;
import com.netcracker.edu.backend.entity.LogIn;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.repository.LogInRepository;
import com.netcracker.edu.backend.repository.SubscriptionRepository;
import com.netcracker.edu.backend.repository.WalletRepository;
import com.netcracker.edu.backend.service.AuditService;
import com.netcracker.edu.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private LogInRepository logInRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private AuditService auditService;

    @Override
    public List<Wallet> findAll(String email) {
        LogIn logIn = logInRepository.findByEmail(email);
        List<Wallet> list = new ArrayList<>(logIn.getUser().getWallet());
        return list;
    }

    @Override
    public Wallet findByWalletName(String walletName) {
        return walletRepository.findByWalletName(walletName);
    }

    @Override
    public WalletDTO save(WalletDTO wallet) {
        LogIn logIn = logInRepository.findByEmail(wallet.getEmail());
        Wallet _wallet = new Wallet();
        _wallet.setSum(wallet.getSum());
        _wallet.setWalletName(wallet.getWalletName());
        _wallet.setUser(logIn.getUser());
        walletRepository.save(_wallet);

        auditService.addWalletRecord(logIn.getUser(), wallet.getWalletName());

        return wallet;
    }

    @Transactional
    @Override
    public Response deleteWallet(long id) {
        Wallet wallet = walletRepository.findById(id).get();
        wallet.getSubscriptions().forEach(value->{
            subscriptionRepository.deleteById(value.getId());
            auditService.unsubscribedRecord(wallet.getUser(), value.getProduct().getName());
        });
        walletRepository.deleteById(id);

        auditService.deleteWalletRecord(wallet.getUser(), wallet.getWalletName());

        return new Response("ok");
    }

    @Override
    public Response rechargeWallet(Wallet wallet) {
        Optional<Wallet> _wallet = walletRepository.findById(wallet.getId());

        if(_wallet.get().getSum() + wallet.getSum() > 0){
            _wallet.get().setNegBalance(false);
        }

        _wallet.get().setSum(_wallet.get().getSum() + wallet.getSum());
        walletRepository.save(_wallet.get());
        auditService.rechargeWalletRecord(_wallet.get().getUser(), _wallet.get().getWalletName(), wallet.getSum());
        return new Response("ok");
    }

    @Override
    public Response setCashSub(Wallet wallet) {
       /* Optional<Wallet> _wallet = walletRepository.findById(wallet.getId());
        _wallet.get().setCashSub(wallet.isCashSub());
        walletRepository.save(_wallet.get());*/
        return new Response("ok");
    }

    @Override
    public Wallet getBalance(long id) {
        return walletRepository.findById(id).get();
    }
}
