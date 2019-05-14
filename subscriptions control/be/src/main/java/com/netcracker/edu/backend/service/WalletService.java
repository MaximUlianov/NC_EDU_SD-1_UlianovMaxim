package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.DTO.WalletDTO;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.entity.Wallet;

import java.util.List;

public interface WalletService {

    List<Wallet> findAll(String email);

    Wallet findByWalletName(String walletName);
    WalletDTO save(WalletDTO wallet);

    Response deleteWallet(long id);
    Response rechargeWallet(Wallet wallet);
    Response setCashSub(Wallet wallet);
}
