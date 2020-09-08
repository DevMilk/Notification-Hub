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
public class FlexibleEmailPacket extends FlexiblePacket{

    public FlexibleEmailPacket() {
        super();
        this.postLimit = 2000;
        this.packetPrice = 7.5;
        this.additionalCost = 0.03;
    }
    
}
