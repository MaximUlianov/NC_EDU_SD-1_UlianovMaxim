package com.netcracker.edu.backend.DTO;

import com.netcracker.edu.backend.entity.Category;
import com.netcracker.edu.backend.entity.CompanyInfo;
import com.netcracker.edu.backend.entity.Wallet;

public class SubscriptionDTO {

    private long id;
    private double costPerMonth;
    private String email;
    private String subscriptionName;
    private Category category;
    private Wallet wallet;
    private CompanyInfo company;


    public SubscriptionDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getCostPerMonth() {
        return costPerMonth;
    }

    public void setCostPerMonth(double costPerMonth) {
        this.costPerMonth = costPerMonth;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public CompanyInfo getCompany() {
        return company;
    }

    public void setCompany(CompanyInfo company) {
        this.company = company;
    }
}
