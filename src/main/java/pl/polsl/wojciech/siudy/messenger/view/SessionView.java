package pl.polsl.wojciech.siudy.messenger.view;

public class SessionView {
    public void displaySessionInfo(String user, String address, Integer portIn, Integer portOut) {
        System.out.println("User " + user + " is listening " + address +
                " at port " + portIn + " and serving at port " + portOut + ".");
    }
}
