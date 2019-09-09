package com.springfrog.model;

import com.springfrog.dto.User;

public class SearchedUser {

    private User user;
    private boolean canAddToFriends;
    private boolean alreadyInInvitees;
    private boolean alreadyInInvites;

    public SearchedUser() {
    }

    public SearchedUser(User user, boolean canAddToFriends, boolean alreadyInInvitees, boolean alreadyInInvites) {
        this.user = user;
        this.canAddToFriends = canAddToFriends;
        this.alreadyInInvitees = alreadyInInvitees;
        this.alreadyInInvites = alreadyInInvites;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCanAddToFriends() {
        return canAddToFriends;
    }

    public void setCanAddToFriends(boolean canAddToFriends) {
        this.canAddToFriends = canAddToFriends;
    }

    public boolean isAlreadyInInvitees() {
        return alreadyInInvitees;
    }

    public void setAlreadyInInvitees(boolean alreadyInInvitees) {
        this.alreadyInInvitees = alreadyInInvitees;
    }

    public boolean isAlreadyInInvites() {
        return alreadyInInvites;
    }

    public void setAlreadyInInvites(boolean alreadyInInvites) {
        this.alreadyInInvites = alreadyInInvites;
    }
}
