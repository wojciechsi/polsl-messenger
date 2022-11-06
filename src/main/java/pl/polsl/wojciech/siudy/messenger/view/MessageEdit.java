package pl.polsl.wojciech.siudy.messenger.view;


import java.util.Scanner;

public class MessageEdit {
    public static String makeMessage() {
        System.out.println("Enter the message");
        Scanner scanner = new Scanner(System.in);
        String content = scanner.nextLine();
        return content;
    }
}
