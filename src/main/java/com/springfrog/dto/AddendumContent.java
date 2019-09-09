package com.springfrog.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ADDENDUM_CONTENT")
public class AddendumContent implements Serializable {

    private static final long serialVersionUID = -6094715983336484683L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MESSAGE_HOLDER_ID")
    private Integer id;

    @Column(name = "REFERENCE_ID", nullable = false)
    private Integer referenceId;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    public AddendumContent() {
    }

    public AddendumContent(Integer referenceId, String contentType) {
        this.referenceId = referenceId;
        this.contentType = contentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddendumContent that = (AddendumContent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AddendumContent{" +
                "id=" + id +
                ", referenceId=" + referenceId +
                ", contentType=" + contentType +
                '}';
    }
}
