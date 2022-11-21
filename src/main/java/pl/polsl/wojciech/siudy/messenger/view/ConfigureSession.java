package pl.polsl.wojciech.siudy.messenger.view;

import pl.polsl.wojciech.siudy.messenger.controller.SessionController;

import javax.swing.*;
import java.awt.event.*;

public class ConfigureSession extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JFormattedTextField address;
    private JFormattedTextField portIn;
    private JFormattedTextField portOut;
    private JFormattedTextField username;

    private SessionController controller;

    public ConfigureSession(SessionController _controller) {
        controller = _controller;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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

    private void onOK() {
        controller.setController(
                username.getText(),
                address.getText(),
                Integer.parseInt(portIn.getText()),
                Integer.parseInt(portOut.getText())
        );
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    

    /*
    public static void main(String[] args) {
        ConfigureSession dialog = new ConfigureSession();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
     */
}
