/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.Exceptions.BlackListException;
import com.mycompany.NotificationHub.AbstractClasses.Packet;
import com.mycompany.NotificationHub.Exceptions.NoBillsExistsException;
import com.mycompany.NotificationHub.Exceptions.NotEnoughMoneyException;
import com.mycompany.NotificationHub.Exceptions.TwoMonthsNotPaidException;
import com.mycompany.NotificationHub.Interfaces.Language;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Queue;

/**
 *
 * @author Ugur
 */
public class Service {


    
    private ArrayList<String> BlackList;
    public Service(ArrayList<String> BlackList) {
        this.BlackList = BlackList;
    }
    public Service() {
        this.BlackList = new ArrayList();
    }
    private ArrayList<String> getBlackList() {
        return BlackList;
    }

    public void checkPacketBill(Language language, Packet packet) throws TwoMonthsNotPaidException{
        Queue<Double> bills =  packet.getRemainingBills();
        if(bills.size()>=2)
            throw new TwoMonthsNotPaidException(language.TwoMonthsNotPaidExceptionMessage());
        
    }

    public void payBill(Language language,PaymentAccount currentAccount, Packet packet) throws NotEnoughMoneyException, NoBillsExistsException{
        try{
            double currentBill = packet.getCurrentBill();
            currentAccount.pay(currentBill);
            packet.removeCurrentBill();
        }
        catch(NotEnoughMoneyException | NoBillsExistsException e){
            if(e instanceof NotEnoughMoneyException) {
                throw new NotEnoughMoneyException(language.NotEnoughMoneyExceptionMessage()); 
            }else
                throw new NoBillsExistsException(language.NoBillsExistsExceptionMessage());
            
        }
    }
    private void addToBlackList(String SenderName){
        BlackList.add(SenderName);
    }
    public void checkForBlackList(Language language,String uniqueID) throws BlackListException{
        
        if(BlackList.contains(uniqueID))
            throw new BlackListException(language.BlackListExceptionMessage());
    
    }
}
