/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Senders;
import com.mycompany.NotificationHub.DTOs.MailDTO;
import com.mycompany.NotificationHub.User;
import com.mycompany.NotificationHub.UserInfo;
import java.util.ArrayList;

/**
 *
 * @author Ugur
 */
public class EmailSender extends PostSender<MailDTO>{
    
    public EmailSender() {
    }

    @Override
    public void send() {
        System.out.println("Email Sended "+data.toString());

    }

    @Override
    public String getAddress(UserInfo Info) {
        return Info.getEmail();
    }


    
}
