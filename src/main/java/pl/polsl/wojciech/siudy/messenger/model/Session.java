package pl.polsl.wojciech.siudy.messenger.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Session {
    private User currentUser;
    private String address;
    private Integer portIn, portOut;

    private LinkedList<Message> inbox;

    public Session(User currentUser, String address, Integer portIn, Integer portOut) {
        this.currentUser = currentUser;
        this.address = address;
        this.portIn = portIn;
        this.portOut = portOut;
        this.inbox = new LinkedList<Message>();
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
    public synchronized void addMessage (Message message){
        inbox.add(message);
    }
}
