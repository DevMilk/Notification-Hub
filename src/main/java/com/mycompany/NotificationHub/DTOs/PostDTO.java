/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.DTOs;

/**
 *
 * @author Ugur
 */
public class PostDTO {
    protected String content;
    protected String To;
    public PostDTO(String content ){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String To) {
        this.To = To;
    }
    public String toString(){
        return "To: " + To + " Content: " + content;
    }
}
