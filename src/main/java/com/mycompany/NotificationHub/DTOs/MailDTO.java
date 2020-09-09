/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.DTOs;

/**
 *
 * @author Ugur
 *  
 */
public class MailDTO extends PostDTO{
    
    public MailDTO(String mailTo, String content){
        super(content);
        this.To = mailTo;
    }
    public MailDTO(String content){
        super(content);
    }
    
}
