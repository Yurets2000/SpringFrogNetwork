package com.springfrog.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GROUP_CHAT")
public class GroupChat extends MessageHolder {

    private static final long serialVersionUID = -9075480816810793282L;

    private static final String DEFAULT_PHOTO = "default_photo.jpg";

    @ManyToOne
    @JoinColumn(name = "CREATOR", nullable = false)
    private User creator;

    @Column(name = "NAME", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "PHOTO")
    private Document photo;

    @ManyToMany(mappedBy = "groupChats")
    private List<User> members;

    public GroupChat() {

    }

    public GroupChat(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Document getPhoto() {
        return photo == null ? new Document(DEFAULT_PHOTO, DEFAULT_PHOTO) : photo;
    }

    public void setPhoto(Document photo) {
        this.photo = photo;
    }

    public List<User> getMembers() {
        return members;
    }
}
