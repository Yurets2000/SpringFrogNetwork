package com.springfrog.dao;

import com.springfrog.dto.Message;
import org.springframework.stereotype.Repository;

@Repository("messageDao")
public class MessageDao extends GenericDao<Integer, Message> {
}
