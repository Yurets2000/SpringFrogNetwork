package com.springfrog.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "MESSAGE_HOLDER")
@Inheritance(strategy = InheritanceType.JOINED)
public class MessageHolder implements Serializable {

    private static final long serialVersionUID = 5841154309312994787L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MESSAGE_HOLDER_ID")
    private Integer id;

    @OneToMany(mappedBy = "messageHolder")
    private List<Message> messages = new LinkedList<>();

    @Transient
    private User sender;

    public MessageHolder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageHolder that = (MessageHolder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MessageHolder{" +
                "id=" + id +
                '}';
    }
}
