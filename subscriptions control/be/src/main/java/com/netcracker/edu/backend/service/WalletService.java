package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Wallet;

import java.util.List;

public interface WalletService {
    List<Wallet> findAll();
    Wallet findByWalletName(String walletName);
    Wallet save(Wallet wallet);
    void delete(long id);
}
