package com.example.dubbo.consumer.kafka;

import java.util.Date;

/**
 * Created by Stadpole on 2020/6/3 14:19
 */
public class Message {
    private long id;
    private String msg;
    private Date sentTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSentTime() {
        return sentTime;
    }

    public void setSentTime(Date sentTime) {
        this.sentTime = sentTime;
    }
}
