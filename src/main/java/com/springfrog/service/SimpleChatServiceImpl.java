package com.springfrog.service;

import com.springfrog.dao.SimpleChatDao;
import com.springfrog.dto.SimpleChat;
import com.springfrog.dto.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("simpleChatService")
@Transactional
public class SimpleChatServiceImpl implements SimpleChatService {

    @Autowired
    private SimpleChatDao simpleChatDao;

    @Override
    public SimpleChat findById(int id) {
        return simpleChatDao.getByKey(id);
    }

    @Override
    public SimpleChat createSimpleChat(User user, User user2) {
        SimpleChat chat = new SimpleChat();
        chat.setFirstUser(user);
        chat.setSecondUser(user2);
        simpleChatDao.save(chat);
        Hibernate.initialize(user.getSimpleChats());
        Hibernate.initialize(user2.getSimpleChats());
        user.getSimpleChats().add(chat);
        user2.getSimpleChats().add(chat);
        return chat;
    }

    @Override
    public SimpleChat getSimpleChat(User user, User user2) {
        Hibernate.initialize(user.getSimpleChats());
        List<SimpleChat> simpleChats = user.getSimpleChats();
        List<SimpleChat> chats = simpleChats.stream()
                .filter(c -> c.getFirstUser().equals(user2) || c.getSecondUser().equals(user2))
                .collect(Collectors.toList());
        SimpleChat chat;
        if (chats.isEmpty()) {
            chat = createSimpleChat(user, user2);
        } else {
            chat = chats.get(0);
        }
        return chat;
    }

    @Override
    public void initializeSimpleChat(User user, SimpleChat chat) {
        User firstUser = chat.getFirstUser();
        User secondUser = chat.getSecondUser();
        chat.setName(user.equals(firstUser) ?
                secondUser.getFirstName() + " " + secondUser.getLastName() :
                firstUser.getFirstName() + " " + firstUser.getLastName());
        chat.setPhoto(user.equals(firstUser) ?
                secondUser.getProfilePhoto() : firstUser.getProfilePhoto());
        chat.setSender(user);
    }
}
