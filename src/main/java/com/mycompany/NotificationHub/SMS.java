/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.DTOs.SMSDTO;
import com.mycompany.NotificationHub.Interfaces.Post;

/**
 *
 * @author Ugur
 */
public class SMS extends Post{

    public SMS(SMSDTO DTO) {
        this.packet = packet;
        this.DTO = DTO;
    }

    
    @Override
    protected void send() {
        System.out.println(DTO.toString());
        
    }

    
    
    
}
