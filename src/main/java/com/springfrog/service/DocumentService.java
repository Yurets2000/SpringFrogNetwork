package com.springfrog.service;

import com.springfrog.dto.Document;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

    String generateFilename(String extension);

    Document createDocument(String filename);

    void saveDocument(Document document);

    void saveFile(MultipartFile file, String filename);

}
