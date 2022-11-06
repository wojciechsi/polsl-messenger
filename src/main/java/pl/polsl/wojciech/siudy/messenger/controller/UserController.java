package pl.polsl.wojciech.siudy.messenger.controller;

import pl.polsl.wojciech.siudy.messenger.model.User;

public class UserController {
    private User model;

    public UserController(User model) {
        this.model = model;
    }

    public String getUserName () {
        return model.getName();
    }
}
