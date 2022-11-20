/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 */
package dev.nuker.pyro;

import java.util.function.Consumer;

import dev.nuker.pyro.modules.world.AutoMine;
import net.minecraft.util.math.BlockPos;

public class fd3
implements Consumer {
    public AutoMine Field108;

    public void Method182(Boolean bl) {
        AutoMine.Method201(this.Field108, BlockPos.ORIGIN);
    }

    public void accept(Object object) {
        this.Method182((Boolean)object);
    }

    public fd3(AutoMine fd42) {
        this.Field108 = fd42;
    }
}

