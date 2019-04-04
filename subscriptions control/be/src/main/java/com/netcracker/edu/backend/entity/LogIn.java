package com.netcracker.edu.backend.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "log_in")
public class LogIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;

    @OneToOne()
    private User user;

    public LogIn() {
    }

    public LogIn(String email, String password, User user) {
        this.email = email;
        this.password = password;
        this.user = user;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
        LogIn logIn = (LogIn) o;
        return Objects.equals(email, logIn.email) &&
                Objects.equals(password, logIn.password) &&
                Objects.equals(user, logIn.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password, user);
    }

    @Override
    public String toString() {
        return "LogIn{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", user=" + user +
                '}';
    }
}
