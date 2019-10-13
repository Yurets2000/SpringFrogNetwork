package com.springfrog.service;

import com.springfrog.dto.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface DocumentService {

    Document findById(int id);

    Document createAndSaveDocument(MultipartFile file);

    void downloadFile(Document document, HttpServletResponse response);

}
