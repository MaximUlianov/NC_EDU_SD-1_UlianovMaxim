package com.netcracker.edu.fapi.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscription {

    private long id;
    private String subscriptionName;
    private String email;
    private double costPerMonth;
    private Category category;
    private Wallet wallet;
    private Company company;
    private boolean isLocked;
    private boolean isNegBalance;

    public Subscription() {
    }

    public Subscription(long id, String subscriptionName, String email, double costPerMonth) {
        this.id = id;
        this.subscriptionName = subscriptionName;
        this.email = email;
        this.costPerMonth = costPerMonth;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCostPerMonth() {
        return costPerMonth;
    }

    public void setCostPerMonth(double costPerMonth) {
        this.costPerMonth = costPerMonth;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isNegBalance() {
        return isNegBalance;
    }

    public void setNegBalance(boolean negBalance) {
        isNegBalance = negBalance;
    }
}
