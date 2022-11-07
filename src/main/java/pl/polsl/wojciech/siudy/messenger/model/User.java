/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.wojciech.siudy.messenger.model;

import java.io.Serializable;

/**
 * Class holding information about user sending messages.
 */
public class User implements Serializable {
    private String name;

    /**
     * Class constructor
     * @param name signature of user
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * Method returning name of current user.
     * @return name
     */
    public String getName() {
        return name;
    }
}
