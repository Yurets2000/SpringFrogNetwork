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

    public Document() {
    }

    public Document(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
