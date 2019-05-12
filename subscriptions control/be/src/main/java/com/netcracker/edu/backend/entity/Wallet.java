package com.netcracker.edu.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String walletName;
    private double sum;
    private boolean isLocked;
    private boolean isCashSub;
    private boolean isNegBalance;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="wallet_subscription",
            joinColumns = @JoinColumn(name="wallet_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="subscription_id", referencedColumnName="id")
    )
    private Set<Subscription> subscriptions;

    public Wallet() {
    }

    public Wallet(String walletName, double sum) {
        this.walletName = walletName;
        this.sum = sum;
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

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Double.compare(wallet.sum, sum) == 0 &&
                Objects.equals(walletName, wallet.walletName) &&
                Objects.equals(user, wallet.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletName, sum);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", walletName='" + walletName + '\'' +
                ", sum=" + sum +
                '}';
    }
}
