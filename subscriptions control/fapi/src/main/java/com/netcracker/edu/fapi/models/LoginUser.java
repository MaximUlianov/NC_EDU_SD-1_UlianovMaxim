package com.netcracker.edu.fapi.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

//Model for user login process
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginUser {

    private String email;
    private String password;
    private String role;

    public LoginUser() {
    }

    public LoginUser(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
