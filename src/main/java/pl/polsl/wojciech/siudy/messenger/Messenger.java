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
        System.out.println("Welcome to messenger!");
        System.out.println("Enter your name:");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Enter address to communicate:");
        String address = scanner.nextLine();
        System.out.println("Pick a port to listen:");
        Integer portIn = scanner.nextInt();
        System.out.println("Pick a port to serve:");
        Integer portOut = scanner.nextInt();

        //apply entered configuration
        sessionCtrl =
                new SessionController(new Session(new User(name), address, portIn, portOut), new SessionView());
        sessionCtrl.updateView();

        //start incoming messages socket server
        Runnable fetcher = new MessagesFetcher(sessionCtrl.getSession());
        new Thread(fetcher).start();

        //wait till peer is ready
        System.out.println("Peer ready? [y/n]");
        String peerReady = "no";
        while (!peerReady.equals("y")) {
            peerReady = scanner.nextLine();
        }

        //start sending socket
        Runnable sender = new MessegesSender(sessionCtrl.getSession());
        new Thread(sender).start();

        //run application menu
        run(sessionCtrl.getSession());

    }

    private static boolean validateOption(Integer chosen) {
        if (chosen > 0 && chosen < 4) {
            return true;
        }
        else {
            return false;
        }
    }
    public static void run(Session session) throws IOException {
        Integer option = 1;
        while (option != 3) {
            //serve menu
            System.out.println("Pick an option:\n" +
                    "1) Check last message\n" +
                    "2) Send a message\n" +
                    "3) Exit");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            if (validateOption(option)) {
                if (option == 1) {
                    sessionCtrl.displayLastMessage();
                }
                else if (option == 2) {
                    sessionCtrl.sendMessage(new Message(sessionCtrl.getSession().getCurrentUser(), makeMessage()));
                }
                else if (option == 3) {
                    session.shutdown();
                }
            }
        }
        System.out.println("Bye!");
    }


}