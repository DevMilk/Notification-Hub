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
public class NoBillsExistsException extends Exception {

    public NoBillsExistsException() {
    }

    public NoBillsExistsException(String message) {
        super(message);
    }
    
}
