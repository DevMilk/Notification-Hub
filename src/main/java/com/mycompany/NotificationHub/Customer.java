/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.Exceptions.NoServiceFoundException;
import com.mycompany.NotificationHub.Exceptions.BlackListException;
import com.mycompany.NotificationHub.AbstractClasses.Packet;
import com.mycompany.NotificationHub.Exceptions.NoBillsExistsException;
import com.mycompany.NotificationHub.Exceptions.NotEnoughMoneyException;
import com.mycompany.NotificationHub.Exceptions.TwoMonthsNotPaidException;
import com.mycompany.NotificationHub.Interfaces.Language;
import java.util.UUID;

/**
 *
 * @author Ugur
 */
public class Customer {
    
    protected String uniqueID;
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected Language language;
    protected Service PacketService;
    protected Packet smsPacket;
    protected Packet emailPacket;
    protected PaymentAccount account;
    public Customer(String name, String email, String phoneNumber, Language language) {
        uniqueID = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.language =language;
    }

    
    public Customer(Service PacketService) {
        this.PacketService = PacketService;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
    
    protected Service getPacketService() throws NoServiceFoundException{
        if(PacketService == null)
            throw new NoServiceFoundException(language.NoServiceFoundExceptionMessage());
        return PacketService;
    }
    public void sendSMS(SMS sms){
        try{
            getPacketService().checkForBlackList(language, uniqueID); //Check if company apper in black list
            getPacketService().checkPacketBill(language, smsPacket); //Check packet bills if it   
            sms.send();
            smsPacket.usePacket();
        }
        catch(TwoMonthsNotPaidException | BlackListException | NoServiceFoundException e ){
            e.printStackTrace();
        }
        
    }
    
    public void sendEmail(Email email) throws TwoMonthsNotPaidException,BlackListException {
        try{
            getPacketService().checkForBlackList(language,uniqueID);
            getPacketService().checkPacketBill(language,emailPacket);
            email.send();
            emailPacket.usePacket();
        }
        catch(TwoMonthsNotPaidException | BlackListException | NoServiceFoundException e ){
            e.printStackTrace();
        }
        
    }
    
    public void paySMSPacketBill(){
        try{
            getPacketService().payBill(language,account, smsPacket);
        }catch(NotEnoughMoneyException | NoBillsExistsException | NoServiceFoundException  e){
            e.printStackTrace();
        }
    }
    public void payEmailPacketBill(){
        try{
            getPacketService().payBill(language,account, emailPacket);
        }catch(NotEnoughMoneyException | NoBillsExistsException | NoServiceFoundException e){
            e.printStackTrace();
        }
    }
}
