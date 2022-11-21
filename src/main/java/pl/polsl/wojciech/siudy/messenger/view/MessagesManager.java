package pl.polsl.wojciech.siudy.messenger.view;

import pl.polsl.wojciech.siudy.messenger.controller.SessionController;

import javax.swing.*;
import java.awt.event.*;

public class MessagesManager extends JDialog {
    private JPanel contentPane;
    private JButton buttonSEND;
    private JFormattedTextField messageField;
    private JButton buttonEXIT;
    private JButton buttonFETCH;
    private JLabel messages;
    private SessionController sessionCtrl;

    public MessagesManager(SessionController _session) {
        sessionCtrl = _session;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSEND);

        buttonSEND.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onSEND();
            }
        });

        buttonEXIT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        buttonFETCH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onFetch();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onSEND() {
        sessionCtrl.sendMessage(messageField.getText());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void onFetch(){

    }
}
