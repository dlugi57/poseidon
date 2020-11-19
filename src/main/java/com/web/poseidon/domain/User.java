package com.web.poseidon.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
@Entity
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( columnDefinition = "TINYINT")
    private Integer id;

    @Column(length = 125)
    @Size(max = 125, message = "Username should be maximum 125 characters")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column(length = 125)
    @Size(max = 125, message = "Password should be maximum 125 characters")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Column(length = 125)
    @Size(max = 125, message = "FullName should be maximum 125 characters")
    @NotBlank(message = "FullName is mandatory")
    private String fullname;

    @Column(length = 125)
    @Size(max = 125, message = "Role should be maximum 125 characters")
    @NotBlank(message = "Role is mandatory")
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
