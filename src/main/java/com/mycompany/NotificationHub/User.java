/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.Languages.Language;

/**
 *
 * @author Ugur
 * isim,soyisim,email,telefon
 */
public class User extends Customer{
    private String surname;
    public User(String name, String surname, String email, String phoneNumber, Language language){
        super(name,email,phoneNumber, language);
        this.surname = surname;
        
    }

    public String getSurname() {
        return surname;
    }

 
    
}
