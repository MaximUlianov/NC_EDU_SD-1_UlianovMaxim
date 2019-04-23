package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Wallet;

import java.util.List;

public interface WalletService {

    Wallet addWallet(Wallet wallet);
    List<Wallet> getWallets(String email);
    void deleteWallet(long id);
    String rechargeWallet(Wallet wallet);
}
