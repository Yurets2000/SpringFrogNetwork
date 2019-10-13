package com.springfrog.service;

import com.springfrog.dto.User;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public interface UserService {

    User findById(int id);

    User findBySSO(String sso);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserBySSO(String sso);

    List<User> findAllUsers();

    boolean isUserSSOUnique(Integer id, String sso);

    boolean canAddToFriends(User user, User user2);

    void addToFriends(User user, User friend);

    void removeFromFriends(User user, User friend);

    void addToBlackList(User user, User offender);

    void removeFromBlackList(User user, User offender);

    void updateProfilePhoto(User user, CommonsMultipartFile file);

    boolean isAlreadyInvited(User inviter, User invitee);

    void inviteUser(User inviter, User invitee);
}