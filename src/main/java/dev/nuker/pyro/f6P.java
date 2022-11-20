/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketClickWindow
 */
package dev.nuker.pyro;

import dev.nuker.pyro.modules.combat.KeyPearl;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketClickWindow;

public class f6P
implements Runnable {
    public KeyPearl Field2981;
    public int Field2982;

    @Override
    public void run() {
        KeyPearl.Method7044((KeyPearl)this.Field2981).playerController.windowClick(0, 44, 0, ClickType.PICKUP, (EntityPlayer) KeyPearl.Method7044((KeyPearl)this.Field2981).player);
        KeyPearl.Method7044((KeyPearl)this.Field2981).playerController.windowClick(0, this.Field2982, 0, ClickType.PICKUP, (EntityPlayer) KeyPearl.Method7044((KeyPearl)this.Field2981).player);
        NetHandlerPlayClient netHandlerPlayClient = KeyPearl.Method7044(this.Field2981).getConnection();
        if (netHandlerPlayClient == null) {
            Intrinsics.Method6551();
        }
        netHandlerPlayClient.sendPacket((Packet)new CPacketClickWindow(0, 0, 0, ClickType.PICKUP, new ItemStack(Items.TOTEM_OF_UNDYING, 5), 420));
        KeyPearl.Method7044((KeyPearl)this.Field2981).playerController.updateController();
    }

    public f6P(KeyPearl keyPearl2, int n) {
        this.Field2981 = keyPearl2;
        this.Field2982 = n;
    }
}

