/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub;

import com.mycompany.NotificationHub.Exceptions.UserAlreadyExistsInPostGroupException;
import com.mycompany.NotificationHub.Packets.PacketModels.Packet;
import com.mycompany.NotificationHub.DTOs.MailDTO;
import com.mycompany.NotificationHub.DTOs.PostDTO;
import com.mycompany.NotificationHub.DTOs.SMSDTO;
import com.mycompany.NotificationHub.Exceptions.BlackListException;
import com.mycompany.NotificationHub.Exceptions.NoServiceFoundException;
import com.mycompany.NotificationHub.Exceptions.TwoMonthsNotPaidException;
import com.mycompany.NotificationHub.Languages.Language;
import com.mycompany.NotificationHub.Senders.PostSender;
import java.util.ArrayList;

/**
 *
 * @author Ugur
 * Company class can define a postGroup and send notification over it unlike Users  
 */
public class Company extends Customer {

    private ArrayList<User> postGroup;

    public Company(String name, String email, String phoneNumber, Language language) {
        super(name, email, phoneNumber, language);
        postGroup = new ArrayList();
    }

    public ArrayList<User> getPostGroup() {
        return postGroup;
    }

    public void setPostGroup(ArrayList<User> postGroup) {
        this.postGroup = postGroup;
    }

    
    //Check if given userToCheck is in post group 
    public boolean checkIfUserExistsInPostGroup(User userToCheck){
        for(User user: postGroup){
            if(user.getUserInformation().getUniqueID().equals(userToCheck.getUserInformation().getUniqueID()))
                return true;
        }
        return false;
    }
    
    // Append new User to PostGroup, if it already exists throw an exception
    public void addToPostGroup(User userToAdd) throws UserAlreadyExistsInPostGroupException{
        if(postGroup.contains(userToAdd)){
            UserInfo existingUserInfo = userToAdd.getUserInformation();
            throw new UserAlreadyExistsInPostGroupException(existingUserInfo.getLanguagePreference().UserAlreadyExistsInPostGroupExceptionMessage()
            +existingUserInfo.getName() +" " + userToAdd.getSurname()); 
        }
            
        postGroup.add(userToAdd);
    }
    /**
     * 
     * @param data: data to send, it can be any data inherited from PostDTO such as SMSDTO and MailDTO
     * @param packet: packet to use
     * @param sender : sender application to use
     */
    public void sendPostToPostGroup(PostDTO data, Packet packet, PostSender sender) {
        try {
            for (User recipient : postGroup) {
                //changed dto's receiver string
                data.setTo(sender.getAddress(userInformation));
                //Send dto which uses packet, through sender 
                sendPost(data, packet, sender);
            }
        } catch (TwoMonthsNotPaidException | BlackListException | NoServiceFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendSMSToPostGroup(SMSDTO smsData) {
        sendPostToPostGroup(smsData, smsPacket, smsSenderApp);
    }

    public void sendEmailToPostGroup(MailDTO mailData) {
        sendPostToPostGroup(mailData, emailPacket, emailSenderApp);
    }
}
