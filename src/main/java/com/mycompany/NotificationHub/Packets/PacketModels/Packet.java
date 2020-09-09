/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Packets.PacketModels;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Ugur
 */
public abstract class Packet  {
    protected int postLimit; //Limit that when reached, packet extends due to its packet model    
    protected int postCounter; //Counter keeps how mant post sended
    protected double packetPrice; //First payment of packet
    protected LocalDateTime registrationDate; //Registration date of packet.
    
    
    public Packet(){
        registerPacket();
    }
    //Calculation varies due to packet models (fixed or flexible)
    public abstract double calculateCurrentCost();
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public long checkDaysPassed(){
        return ChronoUnit.DAYS.between(registrationDate,LocalDateTime.now());
    }
    //It should execute when exactly 30 days passed 
    public void usePacket() {
        postCounter++;
    }; 
    public void resetPacket(){
        postCounter = 0;
    }
    
    //Reset counter and date to register packet
    public void registerPacket(){
        resetPacket();
        registrationDate = LocalDateTime.now();
    }
}
