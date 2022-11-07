package pl.polsl.wojciech.siudy.messenger.controller;

import pl.polsl.wojciech.siudy.messenger.model.User;

/**
 * Class managing User
 */
public class UserController {
    private User model;

    /**
     * Class constructor
     * @param model User placeholder
     */
    public UserController(User model) {
        this.model = model;
    }

    /**
     * Method returning name of current user.
     * @return current username
     */
    public String getUserName () {
        return model.getName();
    }
}
