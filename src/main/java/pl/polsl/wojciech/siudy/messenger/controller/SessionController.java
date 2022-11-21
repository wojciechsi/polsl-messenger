package pl.polsl.wojciech.siudy.messenger.controller;

import pl.polsl.wojciech.siudy.messenger.model.Message;
import pl.polsl.wojciech.siudy.messenger.model.Session;
import pl.polsl.wojciech.siudy.messenger.model.User;
import pl.polsl.wojciech.siudy.messenger.view.MessageView;
import pl.polsl.wojciech.siudy.messenger.view.SessionView;

/**
 * Class managing application session.
 */
public class SessionController {
    private Session model;
    private SessionView view;

    /**
     * Class constructor.
     * @param model data holder
     * @param sessionView frontend holder
     */
    public SessionController(Session model, SessionView sessionView) {
        this.model = model;
        this.view = sessionView;
    }

    /**
     * Method returning running username.
     * @return name
     */
    private String getUser() {
        return model.getCurrentUser().getName();
    }

    /**
     * Method returning address that messages are sent to.
     * @return address
     */
    public String getAddress() {
        return model.getAddress();
    }

    /**
     * Method returning listening port.
     * @return portIn
     */
    public Integer getPortIn () {
        return model.getPortIn();
    }

    /**
     * Method returning outgoing port.
     * @return portOut
     */
    public Integer getPortOut () {
            return model.getPortOut();
    }

    /**
     * Method pushing session info to session frontend.
     */
    public void updateView() {
        view.displaySessionInfo(getUser(), getAddress(), getPortIn(), getPortOut());
    }

    public void setController(String username, String address, Integer portIn, Integer portOut) {
        model.setCurrentUser(new User(username));
        model.setAddress(address);
        model.setPortIn(portIn);
        model.setPortOut(portOut);
    }

    /**
     * Method pushing message to frontend
     * @param m
     */
    private void getAndServeMessage(Message m) {
        MessageView msgView = new MessageView();
        MessageController msgCtrl = new MessageController(m, msgView);
        msgCtrl.updateView();
    }

    /**
     * Method pushing all incoming messages to frontend
     */
    public void displayMessages() {
        if (model.anyMessages()) {
            for (Message m : model.getInbox()) {
                getAndServeMessage(m);
            }
        }
    }



    /**
     * Method giving access to session placeholder.
     * @return session
     */
    public Session getSession() {
        return model;
    }

    /**
     * Method adding message to outbox.
     * @param message message to send
     */
    public void sendMessage (Message message) {
        model.addMessageToOutbox(message);
        model.addMessageToInbox(message);
    }

    public void sendMessage(String content) {
        sendMessage(new Message(model.getCurrentUser(), content));
    }

}
