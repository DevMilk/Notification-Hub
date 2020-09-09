/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Packets;

import com.mycompany.NotificationHub.Packets.PacketModels.FixedPacket;

/**
 *
 * @author Ugur
 */
public class FixedPacketForSMS extends FixedPacket {

    public FixedPacketForSMS() {
        super();
        this.postLimit = 1000;
        this.packetPrice = 20;
    }
    
}
