/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Packets;

import com.mycompany.NotificationHub.Packets.PacketModels.FlexiblePacket;

/**
 *
 * @author Ugur
 */
public class FlexiblePacketForSMS extends FlexiblePacket{
    
    public FlexiblePacketForSMS() {
        super();
        this.postLimit = 2000;
        this.packetPrice = 30;
        this.additionalCost = 0.1;
    }
}
