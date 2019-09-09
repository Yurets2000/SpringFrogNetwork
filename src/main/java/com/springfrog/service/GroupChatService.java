package com.springfrog.service;

import com.springfrog.dto.GroupChat;
import com.springfrog.dto.User;

public interface GroupChatService {

    GroupChat findById(int id);

    GroupChat createGroupChat(User creator, String name);

    void deleteGroupChat(User creator, GroupChat groupChat);

    void leaveGroupChat(User user, GroupChat groupChat);
}
