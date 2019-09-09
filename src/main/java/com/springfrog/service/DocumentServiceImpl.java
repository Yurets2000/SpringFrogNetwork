package com.springfrog.service;

import com.springfrog.dao.DocumentDao;
import com.springfrog.dto.Document;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private Environment environment;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DocumentDao documentDao;

    @Override
    public String generateFilename(String ext) {
        Query nextVal = sessionFactory.getCurrentSession().createSQLQuery("SELECT FILENAME_SEQUENCE.NEXTVAL AS num FROM dual")
                .addScalar("num", StandardBasicTypes.INTEGER);
        int next = (Integer) nextVal.uniqueResult();
        return "document_" + next + "." + ext;

    }

    @Override
    public Document createDocument(String filename) {
        Document document = new Document("downloaded/" + filename);
        return document;
    }

    @Override
    public void saveDocument(Document document) {
        documentDao.save(document);
    }

    @Override
    public void saveFile(MultipartFile file, String filename) {
        String path = environment.getRequiredProperty("downloads_absolute_location") + filename;
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
