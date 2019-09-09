package com.springfrog.dao;

import com.springfrog.dto.Document;
import org.springframework.stereotype.Repository;

@Repository("documentDao")
public class DocumentDao extends GenericDao<Integer, Document> {
}
