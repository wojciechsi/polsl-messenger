package pl.polsl.wojciech.siudy.messenger.controller;

import pl.polsl.wojciech.siudy.messenger.model.Message;
import pl.polsl.wojciech.siudy.messenger.model.Session;
import pl.polsl.wojciech.siudy.messenger.view.SessionView;

public class SessionController {
    private Session model;
    private SessionView view;

    public SessionController(Session model, SessionView sessionView) {
        this.model = model;
        this.view = sessionView;
    }

    private void updateLastMessage(Message message) {
        model.setLastMessage(message);
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

    private Integer gerPortOut() {
        return model.getPortOut();
    }

    private Message getLastMessage(){
        return model.getLastMessage();
    }


    public void updateView() {
        view.displaySessionInfo(getUser(), getAddress(), getPortIn(), getPortOut());
    }

}
