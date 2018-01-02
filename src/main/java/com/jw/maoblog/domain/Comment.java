package com.jw.maoblog.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Comment Entity
 */
@Entity
public class Comment implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // comment id.

    @NotEmpty(message = "the comment message cannot be empty.")
    @Size(min=2, max=500)
    @Column(nullable = false) // the field cannot be null
    private String content;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id") // which user leaves the reply.
    private User user;

    @Column(nullable = false) // the field cannot be null.
    @org.hibernate.annotations.CreationTimestamp  // create timestamp by the database.
    private Timestamp createTime;

    protected Comment() {
    }

    public Comment(User user, String content) {
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
}
