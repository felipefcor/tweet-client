package com.example.tweet_client.model;

import javax.persistence.*;

@Entity
@Table(name="tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user")
    private String user;
    @Column(name = "text", length = 1024)
    private String text;
    @Column(name = "location")
    private String location;
    @Column(name = "validation")
    private Boolean validation;

    public Tweet(String user, String text, String location, Boolean validation) {
        this.user = user;
        this.text = text;
        this.location = location;
        this.validation = validation;
    }

    public Tweet() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getValidation() {
        return validation;
    }

    public void setValidation(Boolean validation) {
        this.validation = validation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
