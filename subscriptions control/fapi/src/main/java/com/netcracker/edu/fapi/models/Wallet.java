package com.netcracker.edu.fapi.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wallet {
    private long id;
    private String walletName;
    private String email;
    private double sum;
    private boolean isLocked;
    private boolean isCashSub;
    private boolean isNegBalance;

    public Wallet() {
    }

    public Wallet(long id, String walletName, String email, double sum) {
        this.id = id;
        this.walletName = walletName;
        this.email = email;
        this.sum = sum;
    }

    public Wallet(long id){
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isCashSub() {
        return isCashSub;
    }

    public void setCashSub(boolean cashSub) {
        isCashSub = cashSub;
    }

    public boolean isNegBalance() {
        return isNegBalance;
    }

    public void setNegBalance(boolean negBalance) {
        isNegBalance = negBalance;
    }
}
