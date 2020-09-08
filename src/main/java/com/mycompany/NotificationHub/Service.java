/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.Exceptions.BlackListException;
import com.mycompany.NotificationHub.AbstractClasses.Packet;
import com.mycompany.NotificationHub.Exceptions.NotEnoughMoneyException;
import com.mycompany.NotificationHub.Exceptions.TwoMonthsNotPaidException;
import com.mycompany.NotificationHub.Interfaces.Language;
import java.util.ArrayList;

/**
 *
 * @author Ugur
 */
public class Service {


    
    private ArrayList<String> BlackList;
    private int delayTimeInMonths;
    
    public Service(ArrayList<String> BlackList, int delayTimeInMonths) {
        this.BlackList = BlackList;
        this.delayTimeInMonths = delayTimeInMonths;
    }
    public Service() {
        this.BlackList = new ArrayList();
    }
    private ArrayList<String> getBlackList() {
        return BlackList;
    }

    public int getDelayTimeInMonths() {
        return delayTimeInMonths;
    }

    public void setDelayTimeInMonths(int delayTimeInMonths) {
        this.delayTimeInMonths = delayTimeInMonths;
    }

    public void checkPacketBill(Language language, Packet packet) throws TwoMonthsNotPaidException{
        if(packet.checkDaysPassed()>60 && packet.calculateCurrentCost()>0)
            throw new TwoMonthsNotPaidException(language.TwoMonthsNotPaidExceptionMessage());
    
    }

    public void payBill(Language language,PaymentAccount currentAccount, Packet packet) throws NotEnoughMoneyException{
        try{
            double currentBill = packet.calculateCurrentCost();
            currentAccount.pay(currentBill);
            packet.resetPacket();
        }
        catch(NotEnoughMoneyException e){
            throw new NotEnoughMoneyException(language.NotEnoughMoneyExceptionMessage()); 
            
        }
    }
    private void addToBlackList(String SenderName){
        BlackList.add(SenderName);
    }
    public void checkForBlackList(Language language,String uniqueID) throws BlackListException{
        
        if(BlackList.contains(uniqueID))
            throw new BlackListException(language.BlackListExceptionMessage());
    
    }
    
    public void registerToPacket(Customer customer, Packet packet){
        
    }
}
