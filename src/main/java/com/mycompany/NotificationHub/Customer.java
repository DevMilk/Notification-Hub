/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.Exceptions.NoPacketDefinedException;
import com.mycompany.NotificationHub.Senders.EmailSender;
import com.mycompany.NotificationHub.Senders.SmsSender;
import com.mycompany.NotificationHub.Exceptions.NoServiceFoundException;
import com.mycompany.NotificationHub.Exceptions.BlackListException;
import com.mycompany.NotificationHub.Packets.PacketModels.Packet;
import com.mycompany.NotificationHub.DTOs.MailDTO;
import com.mycompany.NotificationHub.DTOs.PostDTO;
import com.mycompany.NotificationHub.DTOs.SMSDTO;
import com.mycompany.NotificationHub.Exceptions.NotEnoughMoneyException;
import com.mycompany.NotificationHub.Exceptions.TwoMonthsNotPaidException;
import com.mycompany.NotificationHub.Languages.Language;
import com.mycompany.NotificationHub.Senders.PostSender;

/**
 *
 * @author Ugur
 *  Customer can be a User or Company  
 */
public class Customer {

    protected UserInfo userInformation; //Information of contact and options
    protected PaymentAccount account; //Payment account to pay bill from
    protected Service PacketService; //Service
    
    //Packets
    protected Packet smsPacket;
    protected Packet emailPacket;
    
    //Post Sender Apps
    protected EmailSender emailSenderApp;
    protected SmsSender smsSenderApp;

    public Customer(String name, String email, String phoneNumber, Language language) {
        userInformation = new UserInfo(name, email, phoneNumber, language);
    }

    public Customer(Service PacketService) {
        this.PacketService = PacketService;
    }

    //Getters and Setters
    
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

    public UserInfo getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInfo userInformation) {
        this.userInformation = userInformation;
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

    public PaymentAccount getAccount() {
        return account;
    }

    
    // If packets or service not exists, throw an exception
    public Packet getSmsPacket() throws NoPacketDefinedException {
        if (smsPacket == null) {
            throw new NoPacketDefinedException(userInformation.getLanguagePreference()
                    .NoPacketDefinedExceptionMessage() + " : SMS");
        }
        return smsPacket;
    }

    public Packet getEmailPacket() throws NoPacketDefinedException {
        if (emailPacket == null) {
            throw new NoPacketDefinedException(userInformation.getLanguagePreference()
                    .NoPacketDefinedExceptionMessage() + " : Email");
        }
        return emailPacket;
    }

    protected Service getPacketService() throws NoServiceFoundException {
        if (PacketService == null) {
            throw new NoServiceFoundException(userInformation.getLanguagePreference()
                    .NoServiceFoundExceptionMessage());
        }
        return PacketService;
    }

    // Send SMSDTO using a packet by using SMSsender or send MailDTO using
    protected void sendPost(PostDTO data, Packet packet, PostSender sender) throws TwoMonthsNotPaidException, BlackListException, NoServiceFoundException {
        
        //Check if any problem exists between packet and customer
        getPacketService().checkPacketAndCustomer(packet, userInformation);
        
        //Set Data, send, and use Packet
        sender.setData(data);
        sender.send();
        packet.usePacket();
    }

    public void sendSMS(SMSDTO smsData) throws TwoMonthsNotPaidException, BlackListException, NoServiceFoundException {
        sendPost(smsData, smsPacket, smsSenderApp);
    }

    public void sendEmail(MailDTO mailData) throws TwoMonthsNotPaidException, BlackListException, NoServiceFoundException {
        sendPost(mailData, emailPacket, emailSenderApp);

    }

    // Pay packet's bill, payment may not have enough money to pay or service not exits 
    protected void payPacketBill(Packet packet) {
        try {
            getPacketService().payBill(account, packet, userInformation.getLanguagePreference());
        } catch (NotEnoughMoneyException | NoServiceFoundException e) {
            e.printStackTrace();
        }
    }

    //Specialized functions using payPacketBill function for SMS  
    public void paySMSPacketBill() throws NoPacketDefinedException{
        payPacketBill(getSmsPacket());
    }
    
    //Specialized functions using payPacketBill function for Email
    public void payEmailPacketBill() throws NoPacketDefinedException{
        payPacketBill(getEmailPacket());
    }
}
