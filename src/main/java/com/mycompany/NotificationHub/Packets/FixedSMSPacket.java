/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Packets;

import com.mycompany.NotificationHub.Packets.PacketModels.FixedPacket;
import com.mycompany.NotificationHub.Packets.PacketModels.SMSPacket;

/**
 *
 * @author Ugur
 */
public class FixedSMSPacket extends FixedPacket implements SMSPacket {

    public FixedSMSPacket() {
        super();
        this.postLimit = 1000;
        this.packetPrice = 20;
    }
    
}
