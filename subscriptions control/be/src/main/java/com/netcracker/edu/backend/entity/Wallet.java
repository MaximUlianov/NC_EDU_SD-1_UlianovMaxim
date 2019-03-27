package com.netcracker.edu.backend.entity;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String walletName;
    private double sum;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<User> users;


    public Wallet() {
    }

    public Wallet(String walletName, double sum) {
        this.walletName = walletName;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Double.compare(wallet.sum, sum) == 0 &&
                Objects.equals(walletName, wallet.walletName) &&
                Objects.equals(users, wallet.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletName, sum, users);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", walletName='" + walletName + '\'' +
                ", sum=" + sum +
                ", users=" + users +
                '}';
    }
}
