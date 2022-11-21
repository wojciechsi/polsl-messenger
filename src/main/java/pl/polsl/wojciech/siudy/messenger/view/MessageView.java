package pl.polsl.wojciech.siudy.messenger.view;

import java.util.Date;
import java.util.Scanner;

/**
 * Class providing frontend for reading a single message.
 */
public class MessageView {

    /**
     * Method displaying message with information about author and time of write.
     * @param author author
     * @param date date
     * @param content content
     */
    public void displayMessage(String author, Date date, String content) {
        System.out.println(author + " at: " + date + ":");
        System.out.println(content);
    }

    /**
     * Method reads message content from user.
     * @return String with message content
     */
    public static String makeMessage() {
        System.out.println("Enter the message");
        Scanner scanner = new Scanner(System.in);
        String content = scanner.nextLine();
        return content;
    }

}
