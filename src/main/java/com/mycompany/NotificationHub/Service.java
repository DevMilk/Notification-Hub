/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.Exceptions.BlackListException;
import com.mycompany.NotificationHub.Exceptions.NoServiceFoundException;
import com.mycompany.NotificationHub.Packets.PacketModels.Packet;
import com.mycompany.NotificationHub.Exceptions.NotEnoughMoneyException;
import com.mycompany.NotificationHub.Exceptions.TwoMonthsNotPaidException;
import com.mycompany.NotificationHub.Languages.Language;
import java.util.ArrayList;

/**
 *
 * @author Ugur
 * Service keeps the record of bills and blacklist 
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

    //If packet's bill not paid for 2 months (60 days) throw exception that will trigger to move customer to blacklist. 
    public void checkPacketBill(Language language, Packet packet) throws TwoMonthsNotPaidException {
        if (packet.checkDaysPassed() > 60 && packet.calculateCurrentCost() > 0) {
            throw new TwoMonthsNotPaidException(language.TwoMonthsNotPaidExceptionMessage());
        }

    }

    // Pay CurrentBill through paymentAccount because customer may use different accounts to pay.   
    public void payBill(PaymentAccount currentAccount, Packet packet, Language language) throws NotEnoughMoneyException {
        try {
            double currentBill = packet.calculateCurrentCost();
            currentAccount.pay(currentBill);
            packet.resetPacket();
        } catch (NotEnoughMoneyException e) {
            throw new NotEnoughMoneyException(language.NotEnoughMoneyExceptionMessage());
        }
    }

    public void checkPacketAndCustomer(Packet packet, UserInfo userInformation) throws TwoMonthsNotPaidException, BlackListException, NoServiceFoundException{
        try{
            //Check if company apper in black list
            checkForBlackList(userInformation.getLanguagePreference(), userInformation.getUniqueID()); 

            //Check packet bills if it paid for 2 months or not  
            checkPacketBill(userInformation.getLanguagePreference(), packet); 
        }
        catch(TwoMonthsNotPaidException e){
            /*Add User To BlackList if it didnt paid packet bill for 
            2 months and throw error message to handled from customer class*/
            addToBlackList(userInformation.getUniqueID());
            throw e;
        }
    }
    public void addToBlackList(String SenderId) {
        BlackList.add(SenderId);
    }

    //Check if blacklist contains uniqueID of customer.
    public void checkForBlackList(Language language, String uniqueID) throws BlackListException {

        if (BlackList.contains(uniqueID)) {
            throw new BlackListException(language.BlackListExceptionMessage());
        }

    }
}
