package com.jw.maoblog.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class User {
    private static final long serialVersionId = 1L;

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto generate id
    private long id;

    @NotEmpty(message = "name cannot be empty.")
    @Size(min=5, max=20)
    @Column(nullable = false, length=20) // cannot be empty, max long 20.
    private String name;

    @NotEmpty(message = "email cannot be empty.")
    @Size(max=50)
    @Email(message="please input an valid email.")
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @NotEmpty(message = "username cannot be empty.")
    @Size(min=3, max=20)
    @Column(nullable = false, length = 20, unique = true)
    private String username; // the login name.

    @NotEmpty(message = "password cannot be empty.")
    @Size(max=100)
    @Column(length = 100)
    private String password; // the login key.

    @Column(length = 200)
    private String avatar; // the path to the user image.

    public User() {
    }

    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
