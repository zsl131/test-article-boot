package com.zslin.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

/**
 * Created by zsl on 2018/8/4.
 */
@Entity
@Table(name = "t_reply")
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String author;

    @Column(name = "create_time")
    private String createTime;

    @Lob
    private String content;

    @Column(name = "message_id")
    private Integer messageId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}
