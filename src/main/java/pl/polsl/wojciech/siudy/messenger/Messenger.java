package pl.polsl.wojciech.siudy.messenger;

import java.io.IOException;
import java.util.Scanner;

import pl.polsl.wojciech.siudy.messenger.controller.*;
import pl.polsl.wojciech.siudy.messenger.model.*;
import pl.polsl.wojciech.siudy.messenger.view.*;

import static pl.polsl.wojciech.siudy.messenger.view.MessageView.makeMessage;

public class Messenger {
    private static SessionController sessionCtrl;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        sessionCtrl =
                new SessionController(new Session(), new SessionView());

        ConfigureSession dialog = new ConfigureSession(sessionCtrl);
        dialog.pack();
        dialog.setVisible(true);

        sessionCtrl.updateView();

        //start incoming messages socket server
        Runnable fetcher = new MessagesFetcher(sessionCtrl.getSession());
        new Thread(fetcher).start();

        //wait till peer is ready
        PeerReadyCheck peerReadyDialog = new PeerReadyCheck();
        peerReadyDialog.pack();
        peerReadyDialog.setVisible(true);

        //start sending socket
        Runnable sender = new MessagesSender(sessionCtrl.getSession());
        new Thread(sender).start();

        //run main loop of main thread
        sessionCtrl.doMessaging();

        //close servers
    }
}