package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String subscriptionName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "subscriptions")
    private Set<User> users;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "subscription")
    private Set<SubscriptionCategory> subscriptionCategories;

    public Subscription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<SubscriptionCategory> getSubscriptionCategories() {
        return subscriptionCategories;
    }

    public void setSubscriptionCategories(Set<SubscriptionCategory> subscriptionCategories) {
        this.subscriptionCategories = subscriptionCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(subscriptionName, that.subscriptionName) &&
                Objects.equals(users, that.users) &&
                Objects.equals(subscriptionCategories, that.subscriptionCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionName, users, subscriptionCategories);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", subscriptionName='" + subscriptionName + '\'' +
                ", users=" + users +
                ", subscriptionCategories=" + subscriptionCategories +
                '}';
    }
}
