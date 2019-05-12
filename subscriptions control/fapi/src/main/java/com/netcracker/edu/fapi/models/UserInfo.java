package com.netcracker.edu.fapi.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {

    private long id;
    private String first_name;
    private String last_name;
    private String username;
    private String country;
    private LocalDate birthday;
    private Set<Subscription> subscriptions;
    private Set<Wallet> wallet;
    private boolean isBillingLocked;
    private boolean isBillingNeg;

    public UserInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public Set<Wallet> getWallet() {
        return wallet;
    }

    public void setWallet(Set<Wallet> wallet) {
        this.wallet = wallet;
    }

    public boolean isBillingLocked() {
        return isBillingLocked;
    }

    public void setBillingLocked(boolean billingLocked) {
        isBillingLocked = billingLocked;
    }

    public boolean isBillingNeg() {
        return isBillingNeg;
    }

    public void setBillingNeg(boolean billingNeg) {
        isBillingNeg = billingNeg;
    }
}
