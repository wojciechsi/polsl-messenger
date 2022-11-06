/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.wojciech.siudy.messenger.model;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author SuperStudent.PL
 */
public class Conversation {
    private String name;
    private List<User> members;
    private List<Message> messeges;
    
    public Conversation() {
        members = new ArrayList<User>();
        messeges = new ArrayList<Message>();
    }
    
    public Conversation(String name, List<User> members) {
        this.name = name;
        this.members = members;
        this.messeges = new ArrayList<Message>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Message> getMesseges() {
        return messeges;
    }

    public void setMesseges(List<Message> messeges) {
        this.messeges = messeges;
    }

    public void addMessage(Message message) {
        this.messeges.add(message);
    }

    
    
}
