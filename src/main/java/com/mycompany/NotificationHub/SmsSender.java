/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.DTOs.PostDTO;
import com.mycompany.NotificationHub.DTOs.SMSDTO;
import com.mycompany.NotificationHub.Interfaces.Post;

/**
 *
 * @author Ugur
 */
public class SmsSender extends Post{
    private SMSDTO smsData;

    public SmsSender() {
    }

    public SMSDTO getSmsData() {
        return smsData;
    }

    public void setData(SMSDTO smsData) {
        this.smsData = smsData;
    }

    public SmsSender(SMSDTO smsData) {
        this.smsData = smsData;
    }
    
    @Override
    protected void send() {
        System.out.println(smsData.getContent());
        
    }

    
    
    
}
