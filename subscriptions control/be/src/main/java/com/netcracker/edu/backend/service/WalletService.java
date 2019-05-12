package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.DTO.WalletDTO;
import com.netcracker.edu.backend.entity.Wallet;

import java.util.List;

public interface WalletService {
    List<Wallet> findAll(String email);
    Wallet findByWalletName(String walletName);
    WalletDTO save(WalletDTO wallet);
    void deleteWallet(long id);
    void rechargeWallet(Wallet wallet);
    String setCashSub(Wallet wallet);
}
