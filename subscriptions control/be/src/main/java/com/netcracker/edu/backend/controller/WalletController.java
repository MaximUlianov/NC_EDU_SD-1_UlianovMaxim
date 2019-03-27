package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @RequestMapping(value = "/walletName/{walletName}", method = RequestMethod.GET)
    public ResponseEntity<Wallet> getWalletByWalletName(@PathVariable(name = "walletName") String walletName) {
        Wallet wallet = walletService.findByWalletName(walletName);
        return ResponseEntity.ok(wallet);
    }

    @RequestMapping()
    public List<Wallet> getAllWallets() {
        return walletService.findAll();
    }

    /*@RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }*/
}