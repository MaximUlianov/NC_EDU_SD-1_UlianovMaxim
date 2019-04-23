package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.DTO.WalletDTO;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @RequestMapping(value = "/walletName/{walletName}", method = RequestMethod.GET)
    public ResponseEntity<Wallet> getWalletByWalletName(@PathVariable(name = "walletName") String walletName) {
        Wallet wallet = walletService.findByWalletName(walletName);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<List<Wallet>> getAllWallets(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(walletService.findAll(email));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<WalletDTO> addWallet(@RequestBody WalletDTO wallet){
        return ResponseEntity.ok(walletService.save(wallet));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public void deleteWallet(@PathVariable(value = "id") long id){
        walletService.deleteWallet(id);
    }

    @PostMapping(value = "/recharge")
    @ResponseBody
    public ResponseEntity<String> rechargeWallet(@RequestBody Wallet wallet){
        walletService.rechargeWallet(wallet);
        return ResponseEntity.ok("Recharge was completed");
    }

    /*@RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }*/
}