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

    /**
     * Method returning information if name doesn't include spaces
     * @return if spaces
     */
    public boolean nameIsOneWord () {
        String tmpName = name.replace(" ", "");
        if (tmpName.equals(name)) {
            return true;
        } else {
            return false;
        }
    }
}
