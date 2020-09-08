/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Packets.PacketModels;

import com.mycompany.NotificationHub.AbstractClasses.Packet;
import java.util.ArrayDeque;

/**
 *
 * @author Ugur
 */
public class FixedPacket extends Packet{

    public FixedPacket() {
        super();
    }

    @Override
    public double calculateCurrentCost() {
        return Math.ceil((double)postCounter/postLimit)*packetPrice;
    }
    
}
