package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "audit")
public class Audit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    private String data;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Audit() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audit audit = (Audit) o;
        return Objects.equals(date, audit.date) &&
                Objects.equals(data, audit.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, data);
    }

    @Override
    public String toString() {
        return "Audit{" +
                "id=" + id +
                ", date=" + date +
                ", data='" + data + '\'' +
                '}';
    }
}
