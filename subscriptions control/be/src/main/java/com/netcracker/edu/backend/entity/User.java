package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String first_name;
    private String second_name;
    private String username;
    private String address;

    @OneToOne()
    @JoinColumn(name = "log_in_id", unique = true, nullable = false)
    private LogIn logIn;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @ManyToMany
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="id")
    )
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(name="subscription_user",
            joinColumns = @JoinColumn(name="subscription_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="user_id", referencedColumnName="id")
    )
    private Set<Subscription> subscriptions;


    public User() {
    }

    public User(String first_name, String second_name, String username, String address) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.username = username;
        this.address = address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public LogIn getLogIn() {
        return logIn;
    }

    public void setLogIn(LogIn logIn) {
        this.logIn = logIn;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(first_name, user.first_name) &&
                Objects.equals(second_name, user.second_name) &&
                Objects.equals(username, user.username) &&
                Objects.equals(address, user.address) &&
                Objects.equals(logIn, user.logIn) &&
                Objects.equals(wallet, user.wallet) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(subscriptions, user.subscriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, second_name, username, address, logIn, wallet, roles, subscriptions);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", logIn=" + logIn +
                ", wallet=" + wallet +
                ", roles=" + roles +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
