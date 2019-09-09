package com.springfrog.dao;

import com.springfrog.dto.SimpleChat;
import org.springframework.stereotype.Repository;

@Repository("simpleChatDao")
public class SimpleChatDao extends GenericDao<Integer, SimpleChat> {
}
