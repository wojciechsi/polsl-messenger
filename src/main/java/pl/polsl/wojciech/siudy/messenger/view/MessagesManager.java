package pl.polsl.wojciech.siudy.messenger.view;

import pl.polsl.wojciech.siudy.messenger.Exceptions.EmptyBoxException;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;


public class MessagesManager extends JDialog {

    private JPanel contentPane;
    private JButton buttonSEND;
    private JFormattedTextField messageField;
    private JButton buttonEXIT;
    private JTable inboxTable;
    private JScrollPane inboxScroll = new JScrollPane(inboxTable);

    private LinkedList<String> sendingQueue, displayingList;

    public MessagesManager() {
        sendingQueue = new LinkedList();
        displayingList = new LinkedList();

        //prepare window
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSEND);

        buttonSEND.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSend();
            }
        });

        buttonEXIT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onSend() {
        pushMessage(messageField.getText());
        messageField.setValue("");
    }

    private void onExit() {
        //sessionCtrl.endSession();
        dispose();
    }

    private void pushMessage(String content) {
        sendingQueue.add(content);
    }

    public String takeMessageToSend() throws EmptyBoxException {
        if (sendingQueue.isEmpty()) {
            throw new EmptyBoxException();
        }
        return sendingQueue.pop();
    }

    public void addMessageToDisplay (String message) {
        displayingList.add(message);
    }
}
