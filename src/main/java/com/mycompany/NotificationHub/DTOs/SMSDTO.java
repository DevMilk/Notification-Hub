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
 */
public class SMSDTO extends PostDTO{
    
    private String phoneNumberTo;
    
    public SMSDTO(String phoneNumberTo, String content){
        super(content);
        this.phoneNumberTo = phoneNumberTo;
    }

    public String getPhoneNumberTo() {
        return phoneNumberTo;
    }

    public void setPhoneNumberTo(String phoneNumber) {
        this.phoneNumberTo = phoneNumber;
    }
}
