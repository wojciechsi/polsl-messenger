package pl.polsl.wojciech.siudy.messenger.view;

import pl.polsl.wojciech.siudy.messenger.Exceptions.EmptyBoxException;
import pl.polsl.wojciech.siudy.messenger.model.Message;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Vector;


public class MessagesManager extends JDialog {

    private JPanel contentPane;
    private JButton buttonSEND;
    private JFormattedTextField messageField;
    private JButton buttonEXIT;
    private JTable inboxTable;
    private DefaultTableModel messagesTableModel;
    private Vector<Vector<String>> tableData;
    private LinkedList<String> sendingQueue;

    public boolean isCancelled() {
        return wasCancelled;
    }

    private boolean wasCancelled;

    public MessagesManager() {

        setVisible(true);
        wasCancelled = false;

        sendingQueue = new LinkedList();
        tableData= new Vector<>();

        updateTableContent();

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

    private void updateTableContent() {
        messagesTableModel = new DefaultTableModel() {
            @Override
            public int getRowCount() {
                return tableData.size();
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return tableData.get(rowIndex).get(columnIndex);
            }
        };

        inboxTable.setModel(messagesTableModel);
    }

    private void onSend() {
        pushMessage(messageField.getText());
        messageField.setValue("");
    }

    private void onExit() {
        wasCancelled = true;
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

    public void addMessageToDisplay (Message message) {
        Vector<String>messageToAdd = new Vector<>(3);

        messageToAdd.add(message.getAuthor().getName());
        messageToAdd.add(message.getDate().toString());
        messageToAdd.add(message.getContent());

        tableData.add(messageToAdd);
        System.out.println("Message from:" + message.getAuthor().getName() + "sent to screen");
    }

    public void retouch() {
        //inboxTable.repaint();
        messagesTableModel.fireTableDataChanged();
        updateTableContent();
        pack();
        //repaint();
    }
}
