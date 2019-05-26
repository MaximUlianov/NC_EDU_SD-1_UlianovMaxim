package com.netcracker.edu.fapi.controller;


import com.netcracker.edu.fapi.models.Response;
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

    private WalletService service;

    private TokenProvider tokenUtil;

    @Autowired
    public WalletController(WalletService service, TokenProvider tokenUtil) {
        this.service = service;
        this.tokenUtil = tokenUtil;
    }

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

    @GetMapping(value = "/balance")
    public Wallet getBalance(@RequestHeader("Authorization") String token, @RequestParam(value = "id") long id){
        return service.getBalance(id);
    }

    @DeleteMapping
    public Response deleteWallet(@RequestParam(value = "id") long id, @RequestHeader("Authorization") String token){
        String email = tokenUtil.getUsernameFromToken(token);
        return (service.deleteWallet(id));
    }

    @PostMapping(value = "/recharge")
    public Response rechargeWallet(@RequestHeader("Authorization") String token, @RequestBody Wallet wallet){
        return service.rechargeWallet(wallet);
    }

    @PostMapping(value = "/set_sub")
    public Response setCashSub(@RequestHeader("Authorization") String token, @RequestBody Wallet wallet){
        return service.setCashSub(wallet);
    }
}
