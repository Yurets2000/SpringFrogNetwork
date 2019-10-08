package com.springfrog.dao;

import com.springfrog.dto.MediaContent;
import org.springframework.stereotype.Repository;

@Repository("mediaContentDao")
public class MediaContentDao extends GenericDao<Integer, MediaContent> {
}
