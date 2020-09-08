/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.DTOs.SMSDTO;
import com.mycompany.NotificationHub.Interfaces.Language;
import java.util.ArrayList;

/**
 *
 * @author Ugur
 */
public class Company extends Customer{

    ArrayList<User> postGroup;
    public Company(String name, String email, String phoneNumber, Language language) {
        super(name, email, phoneNumber, language);
        postGroup = new ArrayList();
    }

    public ArrayList<User> getPostGroup() {
        return postGroup;
    }

    public void setPostGroup(ArrayList<User> postGroup) {
        this.postGroup = postGroup;
    }
    
    public void sendSMSToPostGroup(String content){
        for(User recipient: postGroup){
            sendSMS(new SMS(new SMSDTO(recipient.getPhoneNumber(),content)));
        }
    }
}
