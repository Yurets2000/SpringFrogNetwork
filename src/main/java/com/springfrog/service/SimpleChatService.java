package com.springfrog.service;

import com.springfrog.dto.SimpleChat;
import com.springfrog.dto.User;

public interface SimpleChatService {

    SimpleChat findById(int id);

    SimpleChat createSimpleChat(User user, User user2);

    SimpleChat getSimpleChat(User user, User user2);

    void initializeSimpleChat(User user, SimpleChat chat);
}
