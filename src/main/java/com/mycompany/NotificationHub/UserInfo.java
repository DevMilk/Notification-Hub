/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.Languages.Language;
import java.util.UUID;

/**
 *
 * @author Ugur
 */
public class UserInfo {

    protected String uniqueID;
    protected String name;
    protected String email;
    protected String phoneNumber;
    protected Language languagePreference;

    public UserInfo(String name, String email, String phoneNumber, Language languagePreference) {
        uniqueID = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.languagePreference = languagePreference;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Language getLanguagePreference() {
        return languagePreference;
    }

    public void setLanguagePreference(Language languagePreference) {
        this.languagePreference = languagePreference;
    }

}
