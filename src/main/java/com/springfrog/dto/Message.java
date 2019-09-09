package com.springfrog.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "MESSAGE")
public class Message implements Serializable {

    private static final long serialVersionUID = -6387453610206684560L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MESSAGE_ID")
    private Integer id;

    @Column(name = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "SENDER", nullable = false)
    private User sender;

    @Column(name = "SENDING_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendingTime = new Date(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "MESSAGE_HOLDER", nullable = false)
    private MessageHolder messageHolder;

    @ManyToOne
    @JoinColumn(name = "ADDENDUM_CONTENT")
    private AddendumContent addendumContent;

    public Message() {
    }

    public Message(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Date getSendingTime() {
        return sendingTime;
    }

    public MessageHolder getMessageHolder() {
        return messageHolder;
    }

    public void setMessageHolder(MessageHolder messageHolder) {
        this.messageHolder = messageHolder;
    }

    public AddendumContent getAddendumContent() {
        return addendumContent;
    }

    public void setAddendumContent(AddendumContent addendumContent) {
        this.addendumContent = addendumContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", sendingTime=" + sendingTime +
                ", messageHolder=" + messageHolder +
                '}';
    }
}
