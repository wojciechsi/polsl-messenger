package pl.polsl.wojciech.siudy.messenger.controller;

import pl.polsl.wojciech.siudy.messenger.model.EmptyBoxException;
import pl.polsl.wojciech.siudy.messenger.model.Session;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class providing sending functionality.
 * Runs in own thread and fetches messages to send from Session object.
 */
public class MessegesSender implements Runnable {

    private final Session session;

    /**
     * Class constructor
     * @param session application data holder
     */
    public MessegesSender(Session session) {
        this.session = session;
    }

    /**
     * Method sending messeges through web socket.
     */
    @Override
    public void run() {
        try {
            //open socket with listening server
            Socket socket = new Socket(session.getAddress(), session.getPortOut());
            ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
            while (session.isAlive() && !socket.isClosed()) {
                try {
                    o.writeObject(session.getMessageToSend());
                    o.flush();
                }
                catch (EmptyBoxException e) {
                    //do nothing
                }
            }
            //close connections
            session.shutdown();
            o.close();
            socket.shutdownOutput();
            socket.close();
        } catch (IOException e) {
            System.out.println("Sender failed. " + e.getMessage());
            session.shutdown();
        }
    }
}

