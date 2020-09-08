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
public class Turkish implements Language{
    
        @Override
    public String Message(Exception e) {
        switch(e.getClass().getName()){
            case "BlackListException":
                return "Kara Listede olduğunuz için gönderim yapılamıyor.";
            case "TwoMonthsNotPaidException":
                return "Kayıt tarihinden itibaten 2 aydır ödeme yapılmadı, hesap kara listeye alındı.";
            case "NotEnoughMoneyException":
                return "Hesapta yeterli para mevcut değil.";
            case "NoServiceFoundException":
                return "Herhangi Bir Servis Mevcut Değil";
            default:
                break;
        }
        return "Tanımlanamayan Hata";
    }
    
    public String BlackListExceptionMessage(){
        return "Kara Listede olduğunuz için gönderim yapılamıyor.";
    }
    public String TwoMonthsNotPaidExceptionMessage(){
        return "Kayıt tarihinden itibaten 2 aydır ödeme yapılmadı, hesap kara listeye alındı.";
    }

    @Override
    public String NotEnoughMoneyExceptionMessage() {
        return "Hesapta yeterli para mevcut değil.";
    }

    @Override
    public String NoServiceFoundExceptionMessage() {
        return "Herhangi Bir Servis Mevcut Değil";
    }
    
    
}
