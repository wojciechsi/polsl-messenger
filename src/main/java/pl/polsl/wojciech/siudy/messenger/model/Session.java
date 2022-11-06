package pl.polsl.wojciech.siudy.messenger.model;

import java.util.LinkedList;

public class Session {
    private User currentUser;
    private String address;
    private Integer portIn, portOut;

    private LinkedList<Message> inbox, outbox;
    boolean alive = true;

    public boolean isAlive() {
        return alive;
    }

    public void shutdown() {
        this.alive = false;
    }

    public Session(User currentUser, String address, Integer portIn, Integer portOut) {
        this.currentUser = currentUser;
        this.address = address;
        this.portIn = portIn;
        this.portOut = portOut;
        this.inbox = new LinkedList<Message>();
        this.outbox = new LinkedList<Message>();
    }

    public String getAddress() {
        return address;
    }

    public Integer getPortIn() {
        return portIn;
    }

    public Integer getPortOut() {
        return portOut;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public LinkedList<Message> getInbox() {
        return inbox;
    }
    public synchronized void addMessageToInbox(Message message){
        inbox.add(message);
    }

    public synchronized void addMessageToOutbox(Message message){
        outbox.add(message);
    }

    public synchronized Message getMessageToSend () {
            return outbox.pop();
    }

    public synchronized boolean anyMessagesToSend() {
        if (outbox.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    public synchronized boolean anyMessages () {
        if (inbox.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
}
