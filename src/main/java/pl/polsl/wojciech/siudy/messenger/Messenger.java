/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package pl.polsl.wojciech.siudy.messenger;

import java.io.IOException;
import java.util.Scanner;

import pl.polsl.wojciech.siudy.messenger.controller.MessageController;
import pl.polsl.wojciech.siudy.messenger.controller.MessageListener;
import pl.polsl.wojciech.siudy.messenger.controller.MessageSender;
import pl.polsl.wojciech.siudy.messenger.controller.SessionController;
import pl.polsl.wojciech.siudy.messenger.model.*;
import pl.polsl.wojciech.siudy.messenger.view.SessionView;

/**
 *
 * @author SuperStudent.PL
 */
public class Messenger {

    public static void main(String[] args) throws IOException {
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

        SessionController sessionCtrl = new SessionController(new Session(new User(name), address, portIn, portOut), new SessionView());

        sessionCtrl.updateView();

        System.out.println("Enter mode [1 - send / 2 - receive]:");
        Integer mode = scanner.nextInt();

        if (mode == 1) {
            MessageSender sender = new MessageSender(sessionCtrl.getAddress(), sessionCtrl.getPortOut());
        } else if (mode == 2) {
            MessageListener listener = new MessageListener(sessionCtrl.getPortIn());
        }

        sessionCtrl.updateView();
        //delegate listener

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
                    "1) Serve last message\n" +
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