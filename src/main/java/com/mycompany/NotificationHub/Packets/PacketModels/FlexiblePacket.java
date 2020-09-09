/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Packets.PacketModels;

/**
 *
 * @author Ugur
 */
public abstract class FlexiblePacket extends Packet{
    protected double additionalCost;

    
    @Override
    public double calculateCurrentCost() {
        return packetPrice+ Math.max(0,postCounter-postLimit)*additionalCost;
    }
    
}
