package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Response;
import com.netcracker.edu.fapi.models.Wallet;
import com.netcracker.edu.fapi.service.WalletService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public Wallet addWallet(Wallet wallet) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/wallets", wallet, Wallet.class).getBody();
    }

    @Override
    public List<Wallet> getWallets(String email) {
        RestTemplate restTemplate = new RestTemplate();
        Wallet [] walletsResponse = restTemplate.getForObject(backendServerUrl + "/api/wallets?email=" + email, Wallet[].class);
        return walletsResponse == null ? Collections.emptyList() : Arrays.asList(walletsResponse);
    }

    @Override
    public Response deleteWallet(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/wallets?id=" + id, Wallet.class);
        return new Response("ok");
    }

    @Override
    public Response rechargeWallet(Wallet wallet) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/wallets/recharge", wallet, Response.class).getBody();
    }

    @Override
    public Response setCashSub(Wallet wallet) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/wallets/set_sub", wallet, Response.class).getBody();
    }

    @Override
    public Wallet getBalance(long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/wallets/balance?id=" + id, Wallet.class);
    }
}
