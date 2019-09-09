package com.springfrog.service;

import com.springfrog.dao.AddendumContentDao;
import com.springfrog.dao.MessageDao;
import com.springfrog.dao.UserDao;
import com.springfrog.dto.*;
import com.springfrog.model.MessageWrapper;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

@Service("userService")
@Transactional
@DependsOn("documentService")
public class UserServiceImpl implements UserService {

    @Autowired
    private Environment environment;

    @Autowired
    private UserDao userDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private AddendumContentDao addendumContentDao;

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public User findBySSO(String sso) {
        return userDao.findBySSO(sso);
    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUserBySSO(String sso) {
        userDao.findBySSO(sso).setDeleted(true);
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public boolean isUserSSOUnique(Integer id, String sso) {
        User user = findBySSO(sso);
        return (user == null || user.getId().equals(id));
    }

    @Override
    public boolean canAddToFriends(User user, User user2) {
        Hibernate.initialize(user.getFriends());
        return !(user.getFriends().contains(user2) || user.equals(user2));
    }

    @Override
    public void addToFriends(User user, User friend) {
        Hibernate.initialize(user.getFriends());
        Hibernate.initialize(friend.getFriends());
        Hibernate.initialize(user.getInvites());
        Hibernate.initialize(friend.getInvitees());
        user.getInvites().remove(friend);
        friend.getInvitees().remove(user);
        user.getFriends().add(friend);
        friend.getFriends().add(user);
    }

    @Override
    public void removeFromFriends(User user, User friend) {
        user.getFriends().remove(user);
        friend.getFriends().remove(friend);
    }

    @Override
    public void addToBlackList(User user, User offender) {
        user.getBlackList().add(offender);
    }

    @Override
    public void removeFromBlackList(User user, User offender) {
        user.getBlackList().remove(offender);
    }

    @Override
    public void updateProfilePhoto(User user, CommonsMultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String filename = documentService.generateFilename(extension);
        Document photo = documentService.createDocument(filename);
        documentService.saveFile(file, filename);
        documentService.saveDocument(photo);
        user.setProfilePhoto(photo);
    }

    @Override
    public void addMessage(MessageHolder messageHolder, MessageWrapper messageWrapper) {
        String text = messageWrapper.getText();
        text = (text == null ? null : text.trim());
        MultipartFile file = messageWrapper.getAddendum();
        AddendumContent addendumContent = null;
        if (file != null) {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String filename = documentService.generateFilename(extension);
            Document document = documentService.createDocument(filename);
            documentService.saveFile(file, filename);
            documentService.saveDocument(document);
            addendumContent = new AddendumContent(document.getId(), AddendumContentType.DOCUMENT.getContentType());
            addendumContentDao.save(addendumContent);
        }

        Message message = new Message(text);
        message.setMessageHolder(messageHolder);
        //User sender = findBySSO(messageHolder.getSender().getSsoId());
        message.setSender(messageHolder.getSender());
        message.setAddendumContent(addendumContent);
        messageDao.save(message);
        Hibernate.initialize(messageHolder.getMessages());
        messageHolder.getMessages().add(message);
    }

    @Override
    public boolean isAlreadyInvited(User inviter, User invitee) {
        Hibernate.initialize(inviter.getInvitees());
        return inviter.getInvitees().contains(invitee);
    }

    @Override
    public void inviteUser(User inviter, User invitee) {
        Hibernate.initialize(inviter.getInvitees());
        Hibernate.initialize(invitee.getInvites());
        inviter.getInvitees().add(invitee);
        invitee.getInvites().add(inviter);
    }

}