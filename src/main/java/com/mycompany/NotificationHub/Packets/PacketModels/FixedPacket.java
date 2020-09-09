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
public abstract class FixedPacket extends Packet{

    @Override
    public double calculateCurrentCost() {
        return Math.ceil((double)postCounter/postLimit)*packetPrice;
    }
    
}
