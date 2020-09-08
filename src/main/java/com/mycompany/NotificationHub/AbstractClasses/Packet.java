/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.AbstractClasses;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Ugur
 */
public abstract class Packet {
    protected int postLimit;
    protected int postCounter;
    protected double packetPrice;
    protected LocalDateTime registrationDate;
    
    
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
    public void registerPacket(){
        resetPacket();
        registrationDate = LocalDateTime.now();
    }
}
