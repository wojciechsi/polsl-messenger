package pl.polsl.wojciech.siudy.messenger.model;

import java.util.LinkedList;
import java.util.Vector;

/**
 * Class holding current application session data and configuration.
 * It is initialized at startup, after necessary information from user is collected.
 * @see pl.polsl.wojciech.siudy.messenger.controller.SessionController
 * @see pl.polsl.wojciech.siudy.messenger.view.SessionView
 */
public class Session {
    private User currentUser;
    private String address;
    private Integer portIn, portOut;
    private Vector<Message> inbox;
    private LinkedList<Message> outbox;
    boolean alive = true;

    /**
     * Method checking whenever session status wasn't set to shut down.
     * @return if is alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Method triggering application shutdown on both endpoints.
     */
    public void shutdown() {
        this.alive = false;
    }

    public Session() {
        this.currentUser = null;
        this.address = null;
        this.portIn = null;
        this.portOut = null;
        this.inbox = new Vector<Message>();
        this.outbox = new LinkedList<Message>();
    }

    /**
     * Class constructor with full specification.
     * @param currentUser name used as a message signature
     * @param address host that application will connect with
     * @param portIn port that will be open for incoming messages
     * @param portOut port through messages will be sent
     */
    public Session(User currentUser, String address, Integer portIn, Integer portOut) {
        this.currentUser = currentUser;
        this.address = address;
        this.portIn = portIn;
        this.portOut = portOut;
        this.inbox = new Vector<Message>();
        this.outbox = new LinkedList<Message>();
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPortIn(Integer portIn) {
        this.portIn = portIn;
    }

    public void setPortOut(Integer portOut) {
        this.portOut = portOut;
    }

    /**
     * Method returning address of connected host.
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method returning port opened for incoming messages.
     * @return portIn
     */
    public Integer getPortIn() {
        return portIn;
    }

    /**
     * Method returning port that send messages through.
     * @return portOut
     */
    public Integer getPortOut() {
        return portOut;
    }

    /**
     * Method returning user sending messages in current session.
     * @return
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Method returning linked-list of received messages.
     * @return inbox
     */
    public Vector<Message> getInbox() {
        return inbox;
    }

    /**
     * Method returning linked-list of received messages.
     * @return inbox
     */
    public LinkedList<Message> getOutbox() {
        return outbox;
    }

    /**
     * Method pushing message to inbox.
     * @param message message to receive
     */
    public synchronized void addMessageToInbox(Message message){
        inbox.add(message);
    }

    /**
     * Method pushing message to outbox.
     * @param message message to send.
     */
    public synchronized void addMessageToOutbox(Message message){
        outbox.add(message);
    }

    /**
     * Method returning last message to send through network interface.
     * @return message to send
     * @throws EmptyBoxException if outbox is empty
     */
    public synchronized Message getMessageToSend () throws EmptyBoxException {
        if (outbox.isEmpty()) {
            throw new EmptyBoxException();
        }
            return outbox.pop();
    }

    /**
     * Method returning information if any messages are in inbox.
     * @return true if any
     */
    public synchronized boolean anyMessages () {
        if (inbox.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
}

