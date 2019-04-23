package com.netcracker.edu.backend.DTO;

public class WalletDTO {

    private String email;
    private String walletName;
    private double sum;

    public WalletDTO(String email, String walletName, double sum) {
        this.email = email;
        this.walletName = walletName;
        this.sum = sum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
