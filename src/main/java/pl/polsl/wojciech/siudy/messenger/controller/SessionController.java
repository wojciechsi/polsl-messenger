package pl.polsl.wojciech.siudy.messenger.controller;

import pl.polsl.wojciech.siudy.messenger.model.Message;
import pl.polsl.wojciech.siudy.messenger.model.Session;
import pl.polsl.wojciech.siudy.messenger.model.User;
import pl.polsl.wojciech.siudy.messenger.view.MessageView;
import pl.polsl.wojciech.siudy.messenger.view.SessionView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SessionController {
    private Session model;
    private SessionView view;

    public SessionController(Session model, SessionView sessionView) {
        this.model = model;
        this.view = sessionView;
    }

    private String getUser() {
        return model.getCurrentUser().getName();
    }

    public String getAddress() {
        return model.getAddress();
    }

    public Integer getPortIn () {
        return model.getPortIn();
    }

    public Integer getPortOut () {
            return model.getPortOut();
    }

    public void updateView() {
        view.displaySessionInfo(getUser(), getAddress(), getPortIn(), getPortOut());
    }

    public void displayLastMessage() {
        if (model.anyMessages()) {
            MessageView msgView = new MessageView();
            MessageController msgCtrl = new MessageController(model.getInbox().getLast(), msgView);
            msgCtrl.updateView();
        }
    }

    public Session getSession() {
        return model;
    }

    public void sendMessage (Message message) {
        model.addMessageToOutbox(message);
    }

}
