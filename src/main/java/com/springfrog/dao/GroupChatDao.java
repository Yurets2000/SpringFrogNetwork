package com.springfrog.dao;

import com.springfrog.dto.GroupChat;
import org.springframework.stereotype.Repository;

@Repository("groupChatDao")
public class GroupChatDao extends GenericDao<Integer, GroupChat> {
}
