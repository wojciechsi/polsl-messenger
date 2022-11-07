package pl.polsl.wojciech.siudy.messenger.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Class holding content, author and date of written message.
 * @see pl.polsl.wojciech.siudy.messenger.controller.MessageController
 * @see pl.polsl.wojciech.siudy.messenger.view.MessageView
 */
public class Message implements Serializable {
    private User author;
    private String content = null;
    private Date date = null;

    /**
     * Class constructor.
     * Date is entered implicit.
     */
    public Message(User author, String content) {
        this.author = author;
        this.content = content;
        this.date = new Date();
    }

    /**
     * Method returning message author.
     * @return author
     */
    public User getAuthor() {
        return author;
    }


    /**
     * Method returning message content.
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Method returning timestamp of message creation.
     * Date and time in UTC.
     * @return date
     */
    public Date getDate() {
        return date;
    }
}