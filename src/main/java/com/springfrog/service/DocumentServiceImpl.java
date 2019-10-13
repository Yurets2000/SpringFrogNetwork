package com.springfrog.service;

import com.springfrog.dao.DocumentDao;
import com.springfrog.dto.Document;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@Service("documentService")
@Transactional
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private Environment environment;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DocumentDao documentDao;

    private String generatePath(String ext) {
        Query nextVal = sessionFactory.getCurrentSession().createSQLQuery("SELECT FILENAME_SEQUENCE.NEXTVAL AS num FROM dual")
                .addScalar("num", StandardBasicTypes.INTEGER);
        int next = (Integer) nextVal.uniqueResult();
        return "document_" + next + "." + ext;

    }

    @Override
    public Document findById(int id) {
        return documentDao.getByKey(id);
    }

    @Override
    public Document createAndSaveDocument(MultipartFile file) {
        String filename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(filename);
        String path = generatePath(extension);
        Document document = new Document(filename, path);
        documentDao.save(document);
        saveFile(file, path);
        return document;
    }

    private void saveFile(MultipartFile file, String path) {
        String absolutePath = environment.getRequiredProperty("downloads_absolute_location") + path;
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(absolutePath)))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadFile(Document document, HttpServletResponse response) {
        String path = environment.getRequiredProperty("downloads_absolute_location") + document.getPath();
        File file = new File(path);
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
        response.setContentLength((int) file.length());
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
