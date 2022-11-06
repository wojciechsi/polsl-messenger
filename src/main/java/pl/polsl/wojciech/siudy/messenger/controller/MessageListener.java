package pl.polsl.wojciech.siudy.messenger.controller;


import java.io.*;
import java.net.*;

public class MessageListener {
    private ServerSocket server;
    private Socket socket;

    public MessageListener (Integer port) throws IOException {
        server = new ServerSocket(port);

        // establishes connection
        socket = server.accept();

        // invoking input stream
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        String str = (String)dis.readUTF();

        System.out.println("message= " + str);

        // closing socket
        socket.close();
    }
}
