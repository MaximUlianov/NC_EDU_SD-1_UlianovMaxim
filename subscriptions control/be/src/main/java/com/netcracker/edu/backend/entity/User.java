package com.netcracker.edu.backend.entity;

import com.netcracker.edu.backend.DTO.UserDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String first_name;
    private String last_name;
    private String username;
    private String country;
    private LocalDate birthday;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Wallet> wallet;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName="id")
    )
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(name="user_subscription",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="subscription_id", referencedColumnName="id")
    )
    private Set<Subscription> subscriptions;


    public User() {
    }

    public User(String first_name, String last_name, String username, String country, LocalDate birthday) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.country = country;
        this.birthday = birthday;
    }

    public User(User user){
        this.first_name = user.getFirst_name();
        this.last_name = user.getLast_name();
        this.username = user.getUsername();
        this.country = user.getCountry();
        this.birthday = user.getBirthday();
        this.wallet = user.getWallet();
        this.roles = user.getRoles();
        this.subscriptions = user.getSubscriptions();
    }
    public User(UserDTO userDTO){
        this.first_name = userDTO.getFirst_name();
        this.last_name = userDTO.getLast_name();
        this.username = userDTO.getUsername();
        this.country = userDTO.getCountry();
        this.birthday = userDTO.getBirthday();
    }


    public long getId() {
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

    public void setCountry(String country) {
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


    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
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
    public String toString() {
        return "User{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", country='" + country + '\'' +
                ", birthday=" + birthday +
                ", wallet=" + wallet +
                ", roles=" + roles +
                ", subscriptions=" + subscriptions +
                '}';
    }

   /* public void addSubscr(Subscription subscription){
        subscriptions.add(subscription);
        subscription.getUsers().add(this);
    }*/
}
