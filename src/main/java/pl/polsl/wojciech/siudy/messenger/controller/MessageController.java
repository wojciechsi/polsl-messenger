package pl.polsl.wojciech.siudy.messenger.controller;

import pl.polsl.wojciech.siudy.messenger.model.Message;
import pl.polsl.wojciech.siudy.messenger.view.MessageView;

/**
 * Class managing a message
 */
public class MessageController {
    private Message model;
    private MessageView view;

    private UserController userController;

    /**
     * Class constructor
     * @param model data holder
     * @param view frontend holder
     */
    public MessageController(Message model, MessageView view) {
        this.model = model;
        this.view = view;
        this.userController = new UserController(model.getAuthor());
    }

    public void updateView() {
        //view.displayMessage(userController.getUserName(), model.getDate(), model.getContent());
    }
}
