/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.DTOs;

/**
 *
 * @author Ugur
 */
public class SMSDTO extends PostDTO{
    
    
    public SMSDTO(String phoneNumberTo, String content){
        super(content);
        this.To = phoneNumberTo;
    }
    public SMSDTO(String content){
        super(content);
    }
}
