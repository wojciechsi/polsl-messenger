package pl.polsl.wojciech.siudy.messenger.model;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private User currentUser;
    private String address;
    private Integer portIn, portOut;
    private List<User> addressBook;

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    private Message lastMessage;


    public Session(User currentUser, String address, Integer portIn, Integer portOut) {
        this.currentUser = currentUser;
        this.address = address;
        this.portIn = portIn;
        this.portOut = portOut;
        this.addressBook = new ArrayList<User>();
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

}
