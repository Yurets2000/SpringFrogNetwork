package com.springfrog.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "APP_USER")
public class User implements Serializable {

    private static final long serialVersionUID = -6567953653522483880L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Integer id;

    @NotEmpty
    @Column(name = "SSO_ID", unique = true, nullable = false)
    private String ssoId;

    @NotEmpty
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @NotEmpty
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "DELETED", nullable = false)
    private boolean deleted = false;

    @OneToOne
    @JoinColumn(name = "PROFILE_PHOTO")
    private Document profilePhoto;

    @ManyToMany
    @JoinTable(name = "FRIENDS",
            joinColumns = {@JoinColumn(name = "USER1_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER2_ID")})
    private List<User> friends = new LinkedList<>();

    @ManyToMany
    @JoinTable(name = "INVITES",
            joinColumns = {@JoinColumn(name = "USER1_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER2_ID")})
    private List<User> invites = new LinkedList<>();

    @ManyToMany
    @JoinTable(name = "INVITEES",
            joinColumns = {@JoinColumn(name = "USER1_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER2_ID")})
    private List<User> invitees = new LinkedList<>();

    @ManyToMany
    @JoinTable(name = "BLACK_LIST",
            joinColumns = {@JoinColumn(name = "USER1_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER2_ID")})
    private List<User> blackList = new LinkedList<>();

    @ManyToMany
    @JoinTable(name = "APP_USER_SIMPLE_CHAT",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "SIMPLE_CHAT_ID")})
    private List<SimpleChat> simpleChats = new LinkedList<>();

    @ManyToMany
    @JoinTable(name = "APP_USER_GROUP_CHAT",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "GROUP_CHAT_ID")})
    private List<GroupChat> groupChats = new LinkedList<>();

    @ManyToMany
    @JoinTable(name = "APP_USER_USER_PROFILE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_PROFILE_ID")})
    private Set<UserProfile> userProfiles = new HashSet<>();

    public User() {
    }

    public User(String ssoId, String password, String firstName, String lastName, String email) {
        this.ssoId = ssoId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSsoId() {
        return ssoId;
    }

    public void setSsoId(String ssoId) {
        this.ssoId = ssoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Document getProfilePhoto() {
        if (deleted) {
            return new Document("downloaded/deleted_photo.png");
        }
        return profilePhoto == null ? new Document("downloaded/default_photo.jpg") : profilePhoto;
    }

    public void setProfilePhoto(Document profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public List<User> getFriends() {
        return friends;
    }

    public List<User> getBlackList() {
        return blackList;
    }

    public List<SimpleChat> getSimpleChats() {
        return simpleChats;
    }

    public List<GroupChat> getGroupChats() {
        return groupChats;
    }

    public List<User> getInvites() {
        return invites;
    }

    public List<User> getInvitees() {
        return invitees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(ssoId, user.ssoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ssoId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", ssoId='" + ssoId + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userProfiles=" + userProfiles +
                '}';
    }
}