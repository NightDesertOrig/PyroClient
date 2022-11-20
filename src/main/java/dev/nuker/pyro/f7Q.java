/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketKeepAlive
 */
package dev.nuker.pyro;

import java.util.function.Consumer;

import dev.nuker.pyro.modules.exploit.PingSpoof;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketKeepAlive;

public class f7q
implements Consumer {
    public PingSpoof Field3446;

    public void accept(Object object) {
        this.Method5246((CPacketKeepAlive)object);
    }

    public f7q(PingSpoof pingSpoof2) {
        this.Field3446 = pingSpoof2;
    }

    public void Method5246(CPacketKeepAlive cPacketKeepAlive) {
        PingSpoof.Method5276((PingSpoof)this.Field3446).player.connection.sendPacket((Packet)cPacketKeepAlive);
    }
}

