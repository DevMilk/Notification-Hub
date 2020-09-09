/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.DTOs.SMSDTO;
import com.mycompany.NotificationHub.DTOs.MailDTO;
import com.mycompany.NotificationHub.Languages.Turkish;
import com.mycompany.NotificationHub.Languages.English;
import com.mycompany.NotificationHub.Packets.FixedPacketForEmail;
import com.mycompany.NotificationHub.Packets.FlexiblePacketForSMS;
import com.mycompany.NotificationHub.Senders.EmailSender;
import com.mycompany.NotificationHub.Senders.SmsSender;

/**
 *
 * @author Ugur
 */
public class main {
    public static void main(String[] args){
        
        //Set service and company (as customer)
        Service Vodafen= new Service();
        Company firm = new Company("treny", "treny@hotmail.com", "+90000000000", new Turkish());
        
        //Set account and service to customer
        firm.setAccount(new PaymentAccount(10, 5));
        firm.setPacketService(Vodafen);
        
        //Set packet for email
        firm.setEmailPacket(new FixedPacketForEmail());
        firm.setEmailSenderApp(new EmailSender());
        
        //Set packet for Sms
        firm.setSmsPacket(new FlexiblePacketForSMS() );
        firm.setSmsSenderApp(new SmsSender());
        
        //Set test posts
        MailDTO testEmail = new MailDTO("testing group send post...");
        SMSDTO testSMS = new SMSDTO( "testing group send post...");
        
        //Try sending sms and email to post group and pay bills.
        try{
            firm.addToPostGroup(new User("john","doe", "treny@hotmail.com", "+90000000001", new English()));
            firm.addToPostGroup(new User("Luigi","doe", "luig@hotmail.com", "+90000000002", new English()));
            firm.sendEmailToPostGroup(testEmail);
            firm.sendSMSToPostGroup(testSMS);
            firm.payEmailPacketBill();
            firm.paySMSPacketBill();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
