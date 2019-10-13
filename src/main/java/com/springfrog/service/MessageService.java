package com.springfrog.service;

import com.springfrog.dto.MessageHolder;
import com.springfrog.model.MessageWrapper;

public interface MessageService {

    void addMessage(MessageHolder messageHolder, MessageWrapper messageWrapper);
}
