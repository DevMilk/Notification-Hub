/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.Exceptions.NotEnoughMoneyException;

/**
 *
 * @author Ugur
 */
public class PaymentAccount {
    private double currentMoney;
    private double debtLimit;
    public void pay(double price) throws NotEnoughMoneyException{
        if(currentMoney -price < - debtLimit)
            throw new NotEnoughMoneyException();
        currentMoney-=price;
    }
    public void load(double price){
        currentMoney+=price;
    }
}
