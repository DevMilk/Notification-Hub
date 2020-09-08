/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.Exceptions.NoServiceFoundException;
import com.mycompany.NotificationHub.Exceptions.BlackListException;
import com.mycompany.NotificationHub.AbstractClasses.Packet;
import com.mycompany.NotificationHub.DTOs.MailDTO;
import com.mycompany.NotificationHub.DTOs.SMSDTO;
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
    protected EmailSender emailSenderApp;
    protected SmsSender smsSenderApp;
    
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

    public void setPacketService(Service PacketService) {
        this.PacketService = PacketService;
    }

    public void setSmsPacket(Packet smsPacket) {
        this.smsPacket = smsPacket;
    }

    public void setEmailPacket(Packet emailPacket) {
        this.emailPacket = emailPacket;
    }

    public void setAccount(PaymentAccount account) {
        this.account = account;
    }
    
    public String getEmail() {
        return email;
    }

    public EmailSender getEmailSenderApp() {
        return emailSenderApp;
    }

    public void setEmailSenderApp(EmailSender emailSenderApp) {
        this.emailSenderApp = emailSenderApp;
    }

    public SmsSender getSmsSenderApp() {
        return smsSenderApp;
    }

    public void setSmsSenderApp(SmsSender smsSenderApp) {
        this.smsSenderApp = smsSenderApp;
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

    public String getUniqueID() {
        return uniqueID;
    }

    public PaymentAccount getAccount() {
        return account;
    }

    public Packet getSmsPacket() {
        return smsPacket;
    }

    public Packet getEmailPacket() {
        return emailPacket;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
    
    protected Service getPacketService() throws NoServiceFoundException{
        if(PacketService == null)
            throw new NoServiceFoundException(language.NoServiceFoundExceptionMessage());
        return PacketService;
    }
    public void sendSMS(SMSDTO smsData) throws TwoMonthsNotPaidException,BlackListException,NoServiceFoundException{
        getPacketService().checkForBlackList(language, uniqueID); //Check if company apper in black list
        getPacketService().checkPacketBill(language, smsPacket); //Check packet bills if it   
        smsSenderApp.setData(smsData);
        smsSenderApp.send();
        smsPacket.usePacket();
        
    }
    
    public void sendEmail(MailDTO mailData) throws TwoMonthsNotPaidException,BlackListException,NoServiceFoundException {
        getPacketService().checkForBlackList(language,uniqueID);
        getPacketService().checkPacketBill(language,emailPacket);
        emailSenderApp.setData(mailData);
        emailSenderApp.send();
        emailPacket.usePacket();
        
    }
    protected void payPacketBill(Packet packet )  {
        try{
            getPacketService().payBill(account, packet,language);
        }catch(NotEnoughMoneyException |  NoServiceFoundException  e){
            e.printStackTrace();
        }
    }
    public void paySMSPacketBill() {
        payPacketBill(smsPacket);
    }
    public void payEmailPacketBill(){
        payPacketBill(emailPacket);
    }
}
