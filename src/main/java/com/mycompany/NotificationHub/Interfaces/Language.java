/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Interfaces;

/**
 *
 * @author Ugur
 */
public interface Language {
    String Message(Exception e);
    String BlackListExceptionMessage();
    String TwoMonthsNotPaidExceptionMessage();
    String NotEnoughMoneyExceptionMessage();
    String NoServiceFoundExceptionMessage();
}
