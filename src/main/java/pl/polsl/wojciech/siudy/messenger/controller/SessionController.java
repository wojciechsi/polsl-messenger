package pl.polsl.wojciech.siudy.messenger.controller;

import pl.polsl.wojciech.siudy.messenger.Exceptions.EmptyBoxException;
import pl.polsl.wojciech.siudy.messenger.model.Message;
import pl.polsl.wojciech.siudy.messenger.model.Session;
import pl.polsl.wojciech.siudy.messenger.model.User;
import pl.polsl.wojciech.siudy.messenger.view.MessageView;
import pl.polsl.wojciech.siudy.messenger.view.MessagesManager;
import pl.polsl.wojciech.siudy.messenger.view.SessionView;

/**
 * Class managing application session.
 */
public class SessionController {
    private Session model;
    private SessionView view;
    private MessagesManager messagesManager;

    /**
     * Class constructor.
     *
     * @param sessionModel data holder
     * @param sessionView  frontend holder
     */
    public SessionController(Session sessionModel, SessionView sessionView) {
        model = sessionModel;
        view = sessionView;

    }

    /**
     * Method returning running username.
     *
     * @return name
     */
    private String getUser() {
        return model.getCurrentUser().getName();
    }

    /**
     * Method returning address that messages are sent to.
     *
     * @return address
     */
    public String getAddress() {
        return model.getAddress();
    }

    /**
     * Method returning listening port.
     *
     * @return portIn
     */
    public Integer getPortIn() {
        return model.getPortIn();
    }

    /**
     * Method returning outgoing port.
     *
     * @return portOut
     */
    public Integer getPortOut() {
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
     *
     * @param m
     */
    private void getAndServeMessage(Message m) {
        MessageView msgView = new MessageView();
        MessageController msgCtrl = new MessageController(m, msgView);
        msgCtrl.updateView();
    }

    /**
     * Method giving access to session placeholder.
     *
     * @return session
     */
    public Session getSession() {
        return model;
    }

    /**
     * Method adding message to outbox.
     *
     * @param message message to send
     */
    public void sendMessage(Message message) {
        model.addMessageToOutbox(message);
        model.addMessageToInbox(message);
    }

    /**
     * Method invoking messaging interface.
     */
    public void doMessaging() {
        //invoke main messaging window
        messagesManager = new MessagesManager();

        while (model.isAlive()) {
            messagesManager.retouch();
            try {
                //get last incoming message
                if(model.anyMessages()) {
                    Message currentMessage = model.popLastMessageFromInbox();
                    //transfer message from inbox to controller
                    messagesManager.addMessageToDisplay(currentMessage);
                }
                //get outgoing message from view
                String contentToSend = messagesManager.takeMessageToSend();
                model.addMessageToOutbox(new Message(model.getCurrentUser(), contentToSend));

                //check if user terminated
                if (messagesManager.isCancelled()) {
                    model.shutdown();
                }
            } catch (EmptyBoxException e) { //in case of no messages to show
                try { //to wait a moment to keep the processor cool
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}








