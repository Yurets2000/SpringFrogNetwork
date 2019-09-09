package com.springfrog.dto;

import javax.persistence.*;

@Entity
@Table(name = "SIMPLE_CHAT")
public class SimpleChat extends MessageHolder {

    private static final long serialVersionUID = -7156036176506480105L;

    @OneToOne
    @JoinColumn(name = "FIRST_USER", nullable = false)
    private User firstUser;

    @OneToOne
    @JoinColumn(name = "SECOND_USER", nullable = false)
    private User secondUser;

    @Transient
    private String name;

    @Transient
    private Document photo;

    public SimpleChat() {
    }

    public User getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(User firstUser) {
        this.firstUser = firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Document getPhoto() {
        return photo;
    }

    public void setPhoto(Document photo) {
        this.photo = photo;
    }
}
