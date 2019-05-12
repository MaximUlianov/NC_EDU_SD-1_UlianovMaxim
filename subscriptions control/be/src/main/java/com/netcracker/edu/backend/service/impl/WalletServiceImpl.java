package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.DTO.WalletDTO;
import com.netcracker.edu.backend.entity.LogIn;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.repository.LogInRepository;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.repository.WalletRepository;
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
    private UserRepository userRepository;

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
        return wallet;
    }

    @Transactional
    @Override
    public void deleteWallet(long id) {
        Wallet wallet = walletRepository.findById(id).get();
        User user =  userRepository.findById(wallet.getUser().getId()).get();
        user.getSubscriptions().retainAll(wallet.getSubscriptions());
        userRepository.save(user);
        walletRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void rechargeWallet(Wallet wallet) {
        Optional<Wallet> _wallet = walletRepository.findById(wallet.getId());
        double newSum = _wallet.get().getSum() + wallet.getSum();
        walletRepository.rechargeWallet(newSum, wallet.getId());
        Optional<Wallet> sum = walletRepository.findById(wallet.getId());
    }

    @Override
    public String setCashSub(Wallet wallet) {
        Optional<Wallet> _wallet = walletRepository.findById(wallet.getId());
        _wallet.get().setCashSub(wallet.isCashSub());
        walletRepository.save(_wallet.get());
        return "Cash set was completed";
    }
}
