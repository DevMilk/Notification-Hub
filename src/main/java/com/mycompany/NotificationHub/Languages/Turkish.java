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
public class Turkish implements Language{
    
    
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

    @Override
    public String NoPacketDefinedExceptionMessage() {
        return "Herhangi bir paket tanımlı değil";
    }

    @Override
    public String UserAlreadyExistsInPostGroupExceptionMessage() {
        return "Kullanıcı zaten gönderi grubuna dahil: ";
    }
    
    
}
