package pl.polsl.wojciech.siudy.messenger.controller;

import pl.polsl.wojciech.siudy.messenger.Messenger;
import pl.polsl.wojciech.siudy.messenger.model.Message;
import pl.polsl.wojciech.siudy.messenger.model.User;
import pl.polsl.wojciech.siudy.messenger.view.MessageView;

import java.time.LocalDateTime;
import java.util.Date;

public class MessageController {
    private Message model;
    private MessageView view;

    private UserController userController;

    public MessageController(Message model, MessageView view) {
        this.model = model;
        this.view = view;
        this.userController = new UserController(model.getAuthor());
    }

    private String getAuthor() {
        return userController.getUserName();
    }

    private Date getDate() {
        return model.getDate();
    }

    private String getContent() {
        return model.getContent();
    }

    public void makeMessage(User author, String content) {
        model.setAuthor(author);
        model.setContent(content);
        model.setDate(new Date());
    }

    public void updateView() {
        view.displayMessage(getAuthor(), getDate(), getContent());
    }
}
