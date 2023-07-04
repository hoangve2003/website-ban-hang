package com.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {
    @Id
            @NotBlank
    String username;
    @Column(name = "password")
    @NotBlank
    String password;
    @Column(name = "fullname")
    @NotBlank
    String fullname;
    @Column(name = "email")
    @NotBlank @Email
    String email;
    @Column(name = "photo")
    String photo;
    @Column(name = "activated")
    boolean activated;
    @Column(name = "admin")
    boolean admin;
    @OneToMany(mappedBy = "account")
    @JsonIgnore
    List<Order> orders;
}