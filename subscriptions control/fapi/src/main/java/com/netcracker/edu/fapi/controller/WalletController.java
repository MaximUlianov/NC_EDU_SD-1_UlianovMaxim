package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.models.Wallet;
import com.netcracker.edu.fapi.security.TokenProvider;
import com.netcracker.edu.fapi.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/wallets")
public class WalletController {


    @Autowired
    private WalletService service;

    @Autowired
    private TokenProvider tokenUtil;

    @PostMapping
    public Wallet addWallet(@RequestHeader("Authorization") String token, @RequestBody Wallet wallet){
        String email = tokenUtil.getUsernameFromToken(token);
        wallet.setEmail(email);
        service.addWallet(wallet);
        return wallet;
    }

    @GetMapping
    public List<Wallet> getWallets(@RequestHeader("Authorization") String token){
        String email = tokenUtil.getUsernameFromToken(token);
        return service.getWallets(email);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteWallet(@PathVariable(value = "id") long id, @RequestHeader("Authorization") String token){
        String email = tokenUtil.getUsernameFromToken(token);
        service.deleteWallet(id);
    }

    @PostMapping(value = "/recharge")
    public String rechargeWallet(@RequestHeader("Authorization") String token, @RequestBody Wallet wallet){
        String email = tokenUtil.getUsernameFromToken(token);
        return service.rechargeWallet(wallet);
    }
}
