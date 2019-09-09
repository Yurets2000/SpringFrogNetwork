package com.springfrog.dto;

import java.io.Serializable;

public enum AddendumContentType implements Serializable {
    DOCUMENT("DOCUMENT");

    private String contentType;

    AddendumContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}

