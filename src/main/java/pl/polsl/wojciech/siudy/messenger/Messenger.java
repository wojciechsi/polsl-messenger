/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package pl.polsl.wojciech.siudy.messenger;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Scanner;

import pl.polsl.wojciech.siudy.messenger.controller.MessagesFetcher;
import pl.polsl.wojciech.siudy.messenger.controller.SessionController;
import pl.polsl.wojciech.siudy.messenger.model.*;
import pl.polsl.wojciech.siudy.messenger.view.SessionView;

/**
 *
 * @author SuperStudent.PL
 */
public class Messenger {

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

        SessionController sessionCtrl =
                new SessionController(new Session(new User(name), address, portIn, portOut), new SessionView());

        sessionCtrl.updateView();

        Runnable fetcher = new MessagesFetcher(sessionCtrl.getSession());
        new Thread(fetcher).start();

        System.out.println("Enter mode [1 - send / 2 - receive]:");
        Integer mode = scanner.nextInt();

        if (mode == 1) {
           sessionCtrl.messageSend();
        } else if (mode == 2) {
            System.out.println(sessionCtrl.getSession().getInbox().getLast().getContent());
            if (scanner.nextInt() == 5) {
                System.out.println(sessionCtrl.getSession().getInbox().getLast().getContent());
            }
        }

        //Menu.run(session);


    }
}

class Menu {
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
            System.out.println("Pick an option:\n" +
                    "1) Check last message\n" +
                    "2) Send a message\n" +
                    "3) Exit");
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            if (validateOption(option)) {
                if (option == 1) {
                    //@todo
                }
                else if (option == 2) {
                    //MessageSender sender = new MessageSender(session);
                }
            }
        }
    }

}