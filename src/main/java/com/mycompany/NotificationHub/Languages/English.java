/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Languages;

import com.mycompany.NotificationHub.Interfaces.Language;

/**
 *
 * @author Ugur
 */
public class English implements Language{

    @Override
    public String Message(Exception e) {
        switch(e.getClass().getName()){
            case "BlackListException":
                return "Cannot send message because you are in black list.";
            case "TwoMonthsNotPaidException":
                return "No payment has been made for 2 months from the date of registration, account moved to black list.";
            case "NotEnoughMoneyException":
                return "Payment Account is not enough for payment.";
            case "NoServiceFoundException":
                return "Payment Account is not enough for payment.";
            default:
                break;
        }
        return "Undefined Error";
    }
    
    
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
    
    
}
