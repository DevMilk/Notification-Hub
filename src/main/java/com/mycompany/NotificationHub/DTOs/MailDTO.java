/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.DTOs;

import com.mycompany.NotificationHub.DTOs.PostDTO;

/**
 *
 * @author Ugur
 *  
 */
public class MailDTO extends PostDTO{
    
    private String mailTo;
    public MailDTO(String mailTo, String content){
        super(content);
        this.mailTo = mailTo;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mail) {
        this.mailTo = mail;
    }
    
}
