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

public class fd2
implements Consumer {
    public AutoMine Field163;

    public void Method182(Boolean bl) {
        AutoMine.Method201(this.Field163, BlockPos.ORIGIN);
    }

    public fd2(AutoMine fd42) {
        this.Field163 = fd42;
    }

    public void accept(Object object) {
        this.Method182((Boolean)object);
    }
}

