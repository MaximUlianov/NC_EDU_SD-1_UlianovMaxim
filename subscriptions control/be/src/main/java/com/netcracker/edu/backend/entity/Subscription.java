package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String subscriptionName;
    private double costPerMonth;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "subscriptions")
    private Set<User> users;

    @ManyToOne(fetch = FetchType.EAGER)
    private CompanyInfo companyInfo;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name="subscription_category",
            joinColumns = @JoinColumn(name="subscription_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="category_id", referencedColumnName="id")
    )
    private Category category;

    private boolean isLocked;
    private boolean isNegBalance;

    public Subscription() {
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

    @JsonIgnore
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public double getCostPerMonth() {
        return costPerMonth;
    }

    public void setCostPerMonth(double costPerMonth) {
        this.costPerMonth = costPerMonth;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonIgnore
    public Category getCategory() {
        return category;
    }

    @JsonIgnore
    public CompanyInfo getCompanyInfo() {
        return companyInfo;
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

    public void setCompany(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Double.compare(that.costPerMonth, costPerMonth) == 0 &&
                Objects.equals(subscriptionName, that.subscriptionName) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionName, costPerMonth, category);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", subscriptionName='" + subscriptionName + '\'' +
                ", costPerMonth=" + costPerMonth +
                '}';
    }
}
