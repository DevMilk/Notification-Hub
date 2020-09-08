/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.DTOs.MailDTO;
import com.mycompany.NotificationHub.Interfaces.Post;

/**
 *
 * @author Ugur
 */
public class EmailSender extends Post{
    private MailDTO mailData;
    
    public EmailSender(MailDTO mailData){
        this.mailData = mailData;
    }

    public EmailSender() {
    }

    public MailDTO getMailData() {
        return mailData;
    }

    public void setData(MailDTO mailData) {
        this.mailData = mailData;
    }
    
    public void send(){
        
        System.out.println(this.mailData.getContent());
    }
}
