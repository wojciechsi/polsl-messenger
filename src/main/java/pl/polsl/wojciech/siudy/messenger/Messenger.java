/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package pl.polsl.wojciech.siudy.messenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pl.polsl.wojciech.siudy.messenger.model.*;

/**
 *
 * @author SuperStudent.PL
 */
public class Messenger {

    public static void main(String[] args) {
        System.out.println("Welcome to messenger!");
        
        //make users
        Scanner in = new Scanner(System.in);
        System.out.println("Enter username: ");
        String newLogin = in.nextLine();
        User user = new User(newLogin);
        User anotherUser = new User("someone");
        
        //make conversation
        List<User> members = new ArrayList<User>();
        members.add(user);
        members.add(anotherUser);
        Conversation conversation = new Conversation("first conversation", members);
        
        //make first message and send
        conversation.addMessage(new Message(anotherUser, "Hello!"));
        
        //make conversation quasi-database
        List<Conversation> converstionsDatabase = new ArrayList<Conversation>();
        converstionsDatabase.add(conversation);

        //
        
    }
}
