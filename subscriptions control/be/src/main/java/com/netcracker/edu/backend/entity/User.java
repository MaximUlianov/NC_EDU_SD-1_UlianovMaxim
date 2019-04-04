package com.netcracker.edu.backend.entity;

import com.netcracker.edu.backend.DTO.UserDTO;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String country;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Wallet> wallet;

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

    public User(String first_name, String last_name, String username, String country) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.country = country;
    }

    public User(UserDTO userDTO){
        this.first_name = userDTO.getFirst_name();
        this.last_name = userDTO.getLast_name();
        this.username = userDTO.getUsername();
        this.country = userDTO.getCountry();
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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String second_name) {
        this.last_name = second_name;
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

    public void setAddress(String address) {
        this.country = country;
    }

    public Set<Wallet> getWallet() {
        return wallet;
    }

    public void setWallet(Set<Wallet> wallet) {
        this.wallet = wallet;
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
                Objects.equals(last_name, user.last_name) &&
                Objects.equals(username, user.username) &&
                Objects.equals(country, user.country) &&

                Objects.equals(wallet, user.wallet) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(subscriptions, user.subscriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name, username, country,  wallet, roles, subscriptions);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", address='" + country + '\'' +
                ", wallet=" + wallet +
                ", roles=" + roles +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
