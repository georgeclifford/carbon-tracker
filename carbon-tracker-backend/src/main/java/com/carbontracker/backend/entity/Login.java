package com.carbontracker.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private Long loginId;

    @Column(name = "username", unique = true, nullable = false)
    @NotNull(message = "Username cannot be null")
    @Size(min = 5, max = 100, message = "Username must be between 5 and 100 characters")
    private String username;

    @Column(name = "password", nullable = false)
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @Column(name = "role", nullable = false)
    @NotNull(message = "Role cannot be null")
    private String role;

    @Column(name = "account_status")
    private String accountStatus;

    @OneToOne(mappedBy = "login")
    @JsonManagedReference
    private User user;  // One-to-One relationship with User

    public Login() {}

    public Login(String username, String password, String role, String accountStatus) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.accountStatus = accountStatus;
    }

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
