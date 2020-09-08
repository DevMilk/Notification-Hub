/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Interfaces;

import com.mycompany.NotificationHub.AbstractClasses.Packet;
import com.mycompany.NotificationHub.DTOs.PostDTO;
import com.mycompany.NotificationHub.Service;

/**
 *
 * @author Ugur
 */
public abstract class Post {
    
    protected Packet packet;
    protected PostDTO DTO;
    protected abstract void send();
}
