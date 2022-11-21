package pl.polsl.wojciech.siudy.messenger.view;

import javax.swing.*;

/**
 * Class providing frontend for session config
 */
public class SessionView{

    private String username;

    /**
     * Method displaying configs
     * @param user current user signature
     * @param address address to connect
     * @param portIn port for incoming messages
     * @param portOut port for outgoing messages
     */
    public void displaySessionInfo(String user, String address, Integer portIn, Integer portOut) {
        System.out.println("User " + user + " is listening " + address +
                " at port " + portIn + " and serving at port " + portOut + ".");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}
