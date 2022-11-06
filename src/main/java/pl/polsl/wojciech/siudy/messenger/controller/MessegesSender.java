package pl.polsl.wojciech.siudy.messenger.controller;

import pl.polsl.wojciech.siudy.messenger.model.Session;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessegesSender implements Runnable {

    private final Session session;

    public MessegesSender(Session session) {
        this.session = session;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(session.getAddress(), session.getPortOut());
            ObjectOutputStream o = new ObjectOutputStream(socket.getOutputStream());
            while (session.isAlive()) {
                if (session.anyMessagesToSend()) {
                    o.writeObject(session.getMessageToSend());
                    o.flush();
                }
            }
            o.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

