/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.Interfaces.Language;

/**
 *
 * @author Ugur
 * isim,soyisim,email,telefon
 */
public class User extends Customer{
    private String surname;
    private String email;
    private String phoneNumber;
    public User(String name, String surname, String email, String phoneNumber, Language language){
        super(name,email,phoneNumber, language);
        this.surname = surname;
        
    }

 
    
}
