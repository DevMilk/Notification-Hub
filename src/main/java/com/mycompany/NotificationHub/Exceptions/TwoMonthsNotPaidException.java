/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Exceptions;

/**
 *
 * @author Ugur
 */
public class TwoMonthsNotPaidException extends Exception {

    public TwoMonthsNotPaidException(String message) {
        super(message);
    }
    public TwoMonthsNotPaidException() {
    }
    
}
