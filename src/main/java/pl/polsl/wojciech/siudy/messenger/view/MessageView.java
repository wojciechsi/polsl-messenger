package pl.polsl.wojciech.siudy.messenger.view;


import java.util.Date;

public class MessageView {
    public void displayMessage(String author, Date date, String content) {
        System.out.println(author + " at: " + date + ":");
        System.out.println(content);
    }
}
