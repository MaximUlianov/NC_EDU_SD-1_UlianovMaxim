package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.DTO.WalletDTO;
import com.netcracker.edu.backend.entity.Response;
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

    @GetMapping(value = "/walletName")
    public ResponseEntity<Wallet> getWalletByWalletName(@RequestParam(name = "walletName") String walletName) {
        Wallet wallet = walletService.findByWalletName(walletName);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Wallet>> getAllWallets(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok(walletService.findAll(email));
    }

    @GetMapping(value = "/balance")
    @ResponseBody
    public ResponseEntity<Wallet> getBalance(@RequestParam(name = "id") long id) {
        return ResponseEntity.ok(walletService.getBalance(id));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<WalletDTO> addWallet(@RequestBody WalletDTO wallet){
        return ResponseEntity.ok(walletService.save(wallet));
    }

    @DeleteMapping
    @ResponseBody
    public void deleteWallet(@RequestParam(value = "id") long id){
        walletService.deleteWallet(id);
    }

    @PostMapping(value = "/recharge")
    @ResponseBody
    public ResponseEntity<Response> rechargeWallet(@RequestBody Wallet wallet){
        return ResponseEntity.ok(walletService.rechargeWallet(wallet));
    }

    @PostMapping(value = "/set_sub")
    @ResponseBody
    public ResponseEntity<Response> setCahSub(@RequestBody Wallet wallet){
        return ResponseEntity.ok( walletService.setCashSub(wallet));
    }
}