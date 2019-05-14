package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Response;
import com.netcracker.edu.fapi.models.Wallet;

import java.util.List;

public interface WalletService {

    List<Wallet> getWallets(String email);

    Response deleteWallet(long id);
    Response rechargeWallet(Wallet wallet);
    Response setCashSub(Wallet wallet);

    Wallet addWallet(Wallet wallet);

}
