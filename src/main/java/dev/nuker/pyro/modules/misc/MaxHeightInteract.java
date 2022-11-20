/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock
 *  net.minecraft.util.EnumFacing
 */
package dev.nuker.pyro;

import dev.nuker.pyro.f0g;
import dev.nuker.pyro.mixin.CPacketPlayerTryUseItemOnBlockAccessor;
import dev.nuker.pyro.security.inject.LauncherEventHide;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;

public class MaxHeightInteract
extends Module {
    @f0g
    @LauncherEventHide
    public void Method2393(f49 f492) {
        CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock;
        if (f492.Method5784() instanceof CPacketPlayerTryUseItemOnBlock && f492.Method5619() == f41.Pre && (cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)f492.Method5784()).getPos().getY() >= 255 && cPacketPlayerTryUseItemOnBlock.getDirection() == EnumFacing.UP) {
            ((CPacketPlayerTryUseItemOnBlockAccessor)cPacketPlayerTryUseItemOnBlock).Method6416(EnumFacing.DOWN);
        }
    }

    public MaxHeightInteract() {
        super("maxheightinteract", "MaxHeightInteract", "Allows you to interact with blocks at build height");
    }
}

