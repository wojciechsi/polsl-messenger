package pl.polsl.wojciech.siudy.messenger.controller;

import pl.polsl.wojciech.siudy.messenger.model.Message;
import pl.polsl.wojciech.siudy.messenger.model.Session;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MessagesFetcher implements Runnable{

    private final Session session;

    public MessagesFetcher(Session session) {
        this.session = session;
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(session.getPortIn());
            Socket socket = server.accept();
            ObjectInputStream o = new ObjectInputStream(socket.getInputStream());
            while (session.isAlive()) {
                session.addMessageToInbox((Message)o.readObject());
            }
            socket.close();
            server.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }
}
