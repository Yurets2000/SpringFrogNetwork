package com.springfrog.service;

import com.springfrog.dao.MessageDao;
import com.springfrog.dto.MediaContent;
import com.springfrog.dto.Message;
import com.springfrog.dto.MessageHolder;
import com.springfrog.model.MessageWrapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service("messageService")
@Transactional
@DependsOn("documentService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private MessageDao messageDao;

    @Override
    public void addMessage(MessageHolder messageHolder, MessageWrapper messageWrapper) {
        String text = messageWrapper.getText();
        text = (text == null ? null : text.trim());
        MultipartFile file = messageWrapper.getAddendum();
        MediaContent mediaContent = null;
        if (file != null && !file.isEmpty()) {
            mediaContent = documentService.createAndSaveDocument(file);
        }

        Message message = new Message(text);
        message.setMessageHolder(messageHolder);
        message.setSender(messageHolder.getSender());
        message.setMediaContent(mediaContent);
        messageDao.save(message);
        Hibernate.initialize(messageHolder.getMessages());
        messageHolder.getMessages().add(message);
    }
}
