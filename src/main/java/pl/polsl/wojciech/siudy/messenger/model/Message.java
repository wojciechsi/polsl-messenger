/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.wojciech.siudy.messenger.model;

import java.util.Date;

/**
 *
 * @author SuperStudent.PL
 */
public class Message {
    private User author;
    private String content = null;
    private Date date = null;

    public Message(User author, String content) {
        this.author = author;
        this.content = content;
        this.date = new Date();
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
