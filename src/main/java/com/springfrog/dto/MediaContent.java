package com.springfrog.dto;

import com.springfrog.utils.MediaContentMnemonicResolver;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "MEDIA_CONTENT")
@Inheritance(strategy = InheritanceType.JOINED)
public class MediaContent implements Serializable {

    private static final long serialVersionUID = -6094715983336484683L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEDIA_CONTENT_ID")
    private Integer id;

    public MediaContent() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMnemonic() {
        return MediaContentMnemonicResolver.resolveMnemonic(this).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MediaContent that = (MediaContent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MediaContent{" +
                "id=" + id +
                '}';
    }
}
