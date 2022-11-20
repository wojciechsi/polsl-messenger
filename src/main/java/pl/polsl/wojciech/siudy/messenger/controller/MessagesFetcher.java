package pl.polsl.wojciech.siudy.messenger.controller;

import pl.polsl.wojciech.siudy.messenger.model.Message;
import pl.polsl.wojciech.siudy.messenger.model.Session;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class providing fetching functionalities.
 * Runs in own thread and pushes messages to session objects.
 */
public class MessagesFetcher implements Runnable{

    private final Session session;

    /**
     * Class constructor
     * @param session current session
     */
    public MessagesFetcher(Session session) {
        this.session = session;
    }

    /**
     * Method running listening server.
     */
    @Override
    public void run() {
        try {
            //Make server that peer will send messages to
            ServerSocket server = new ServerSocket(session.getPortIn());
            Socket socket = server.accept();
            ObjectInputStream o = new ObjectInputStream(socket.getInputStream());
            while (session.isAlive()) {
                session.addMessageToInbox((Message)o.readObject());
            }
            //End all connections
            o.close();
            socket.shutdownInput();
            socket.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Fetcher failed. " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Incompatible peer" + e.getMessage());
        } finally {
            session.shutdown();
        }
        System.out.println("Fetcher closed.");
    }
}
