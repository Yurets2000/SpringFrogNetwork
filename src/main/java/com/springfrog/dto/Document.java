package com.springfrog.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "DOCUMENT_CONTENT")
public class Document extends MediaContent implements Serializable {

    private static final long serialVersionUID = 598679349557335259L;

    @Column(name = "PATH", nullable = false)
    private String path;

    @Column(name = "FILENAME", nullable = false)
    private String filename;

    public Document() {
    }

    public Document(String filename, String path) {
        this.filename = filename;
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
