package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.DTO.WalletDTO;
import com.netcracker.edu.backend.entity.*;
import com.netcracker.edu.backend.repository.LogInRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.repository.WalletRepository;
import com.netcracker.edu.backend.service.AuditService;
import com.netcracker.edu.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private LogInRepository logInRepository;

    @Autowired
    private UserRepository userRepository;

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

        Audit audit = new Audit();
        audit.setUser(logIn.getUser());
        audit.setData("Add wallet " + _wallet.getWalletName());
        audit.setDate(new Date());
        auditService.addRecord(audit);

        return wallet;
    }

    @Transactional
    @Override
    public Response deleteWallet(long id) {
        Wallet wallet = walletRepository.findById(id).get();
        User user =  userRepository.findById(wallet.getUser().getId()).get();
        user.getSubscriptions().retainAll(wallet.getSubscriptions());
        userRepository.save(user);
        walletRepository.deleteById(id);

        Audit audit = new Audit();
        audit.setUser(user);
        audit.setData("Delete wallet " + wallet.getWalletName());
        audit.setDate(new Date());
        auditService.addRecord(audit);
        return new Response("ok");
    }

    @Override
    public Response rechargeWallet(Wallet wallet) {
        Optional<Wallet> _wallet = walletRepository.findById(wallet.getId());

        if(_wallet.get().getSum() + wallet.getSum() > 0){
            _wallet.get().setLocked(false);
            _wallet.get().setNegBalance(false);
        }

        _wallet.get().setSum(_wallet.get().getSum() + wallet.getSum());
        walletRepository.save(_wallet.get());

        Audit audit = new Audit();
        audit.setUser(_wallet.get().getUser());
        audit.setData("Recharge wallet " + _wallet.get().getWalletName() + " on " + wallet.getSum());
        audit.setDate(new Date());
        auditService.addRecord(audit);
        return new Response("ok");
    }

    @Override
    public Response setCashSub(Wallet wallet) {
        Optional<Wallet> _wallet = walletRepository.findById(wallet.getId());
        _wallet.get().setCashSub(wallet.isCashSub());
        walletRepository.save(_wallet.get());
        return new Response("ok");
    }
}
