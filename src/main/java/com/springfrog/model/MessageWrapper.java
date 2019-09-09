package com.springfrog.model;

import org.springframework.web.multipart.MultipartFile;

public class MessageWrapper {

    private String text;
    private MultipartFile addendum;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MultipartFile getAddendum() {
        return addendum;
    }

    public void setAddendum(MultipartFile addendum) {
        this.addendum = addendum;
    }
}
