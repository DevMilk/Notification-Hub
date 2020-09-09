/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Senders;

import com.mycompany.NotificationHub.DTOs.PostDTO;
import com.mycompany.NotificationHub.User;
import com.mycompany.NotificationHub.UserInfo;
import java.util.ArrayList;


/**
 *
 * @author Ugur
 */
public abstract class PostSender<DTO extends PostDTO> {
    
    protected DTO data;
    public abstract void send();
    public abstract String getAddress(UserInfo Info); //Extract address from given User Information
    public void setData(DTO data) {
        this.data = data;
    }
}
