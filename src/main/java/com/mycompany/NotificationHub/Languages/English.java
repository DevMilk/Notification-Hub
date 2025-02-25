/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Languages;

/**
 *
 * @author Ugur
 */
public class English implements Language{
    public String BlackListExceptionMessage(){
        return "Cannot send message because you are in black list.";
    }
    public String TwoMonthsNotPaidExceptionMessage(){
        return "No payment has been made for 2 months from the date of registration, account moved to black list.";
    }


    @Override
    public String NotEnoughMoneyExceptionMessage() {
        return "Payment Account is not enough for payment.";
    }

    @Override
    public String NoServiceFoundExceptionMessage() {
        return "No Service Found.";
    }

    @Override
    public String NoPacketDefinedExceptionMessage() {
        return "Packet not defined";
    }

    @Override
    public String UserAlreadyExistsInPostGroupExceptionMessage() {
        return "User Already Exists in post group :";
    }
    
    
}
