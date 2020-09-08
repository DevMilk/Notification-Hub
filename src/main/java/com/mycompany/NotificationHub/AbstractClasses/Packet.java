/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.AbstractClasses;

import com.mycompany.NotificationHub.Exceptions.NoBillsExistsException;
import com.mycompany.NotificationHub.Exceptions.TwoMonthsNotPaidException;
import com.mycompany.NotificationHub.Interfaces.Language;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

/**
 *
 * @author Ugur
 */
public abstract class Packet {
    protected int postLimit;
    protected int postCounter;
    protected int delayTimeInMonths;
    protected double packetPrice;
    protected LocalDateTime registrationDate;
    protected Queue<Double> remainingBills;

    public Packet() {
        remainingBills = new ArrayDeque<Double>(); 
    }
    
    //Calculation varies due to packet models (fixed or flexible)
    protected abstract double calculateCurrentMonthCost();
    
    public Queue<Double> getRemainingBills(){
        return remainingBills;
    }
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void resetCounter(){
        postCounter = 0;
    }
    
    //It should execute when exactly 30 days passed 
    public void saveMonthlyBill(){
        remainingBills.add(calculateCurrentMonthCost());
    }
    public double getCurrentBill() throws NoBillsExistsException{
        Double currentBill = remainingBills.peek();
        if(currentBill == null)
            throw new NoBillsExistsException();
        return remainingBills.peek();
    }
    public void removeCurrentBill(){
        remainingBills.remove();
    }
    public void usePacket() {
        postCounter++;
    }; 
    
}
