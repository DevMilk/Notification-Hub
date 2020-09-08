/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.NotificationHub.Packets;

import com.mycompany.NotificationHub.Packets.PacketModels.FlexiblePacket;
import com.mycompany.NotificationHub.Packets.PacketModels.SMSPacket;

/**
 *
 * @author Ugur
 */
public class FlexibleSMSPacket extends FlexiblePacket implements SMSPacket{
    
    public FlexibleSMSPacket() {
        super();
        this.postLimit = 2000;
        this.packetPrice = 30;
        this.additionalCost = 0.1;
    }
}
