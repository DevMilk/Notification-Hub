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
public interface Language {
    String BlackListExceptionMessage();
    String TwoMonthsNotPaidExceptionMessage();
    String NotEnoughMoneyExceptionMessage();
    String NoServiceFoundExceptionMessage();
    String NoPacketDefinedExceptionMessage();

    public String UserAlreadyExistsInPostGroupExceptionMessage();
}
