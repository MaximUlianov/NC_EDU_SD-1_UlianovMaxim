package com.netcracker.edu.backend.DTO;

public class UserDTO {
    private String first_name;
    private String last_name;
    private String username;
    private String country;
    private String email;
    private String password;

    public UserDTO(String first_name, String last_name, String username, String country, String email, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.country = country;
        this.email = email;
        this.password = password;
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

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
}
