package com.springfrog.service;

import com.springfrog.dao.GroupChatDao;
import com.springfrog.dto.GroupChat;
import com.springfrog.dto.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("groupChatService")
@Transactional
public class GroupChatServiceImpl implements GroupChatService {

    @Autowired
    private GroupChatDao groupChatDao;

    @Override
    public GroupChat findById(int id) {
        return groupChatDao.getByKey(id);
    }

    @Override
    public GroupChat createGroupChat(User creator, String name) {
        GroupChat groupChat = new GroupChat(name);
        groupChat.setCreator(creator);
        groupChatDao.save(groupChat);
        Hibernate.initialize(creator.getGroupChats());
        creator.getGroupChats().add(groupChat);
        return groupChat;
    }

    @Override
    public void deleteGroupChat(User creator, GroupChat groupChat) {
        Hibernate.initialize(groupChat.getMembers());
        groupChat.getMembers().forEach(u -> u.getGroupChats().remove(groupChat));
        groupChatDao.delete(groupChat);
    }

    @Override
    public void leaveGroupChat(User user, GroupChat groupChat) {
        if (user.equals(groupChat.getCreator())) {
            deleteGroupChat(user, groupChat);
        } else {
            Hibernate.initialize(groupChat.getMembers());
            Hibernate.initialize(user.getGroupChats());
            groupChat.getMembers().remove(user);
            user.getGroupChats().remove(groupChat);
        }
    }

}
