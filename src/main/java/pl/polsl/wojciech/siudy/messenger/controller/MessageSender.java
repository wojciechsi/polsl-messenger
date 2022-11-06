/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.wojciech.siudy.messenger.controller;


import pl.polsl.wojciech.siudy.messenger.model.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageSender {
    private Socket socket;

    public MessageSender (String address, Integer port) throws IOException {
        socket = new Socket(address, port);

        DataOutputStream d = new DataOutputStream(
                socket.getOutputStream());

        // message to display
        d.writeUTF("Hello!");

        d.flush();

        // closing DataOutputStream
        d.close();

        // closing socket
        socket.close();
    }

    public void send(Message message) {

    }
}
