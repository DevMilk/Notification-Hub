/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.DTOs.SMSDTO;
import com.mycompany.NotificationHub.DTOs.MailDTO;
import com.mycompany.NotificationHub.Languages.Turkish;
import com.mycompany.NotificationHub.Languages.English;

/**
 *
 * @author Ugur
 */
public class main {
    public static void main(String[] args){
        Company firma = new Company("treny", "treny@hotmail.com", "+90000000000", 1, 1, new English());
        firma.sendEmail(new Email(new MailDTO("reg@hotmail.com","tebrikler")));
        firma.sendSMS(new SMS(new SMSDTO("+9123213123","tebrikler")));
        
        firma.sendSMS(new SMS(new SMSDTO("+9123213123","tebrikler")));
        firma.setLanguage(new Turkish());
        firma.sendSMS(new SMS(new SMSDTO("+9123213123","tebrikler")));
    }
}
