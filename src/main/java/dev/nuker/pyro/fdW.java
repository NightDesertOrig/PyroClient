/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.RayTraceResult
 */
package dev.nuker.pyro;

import dev.nuker.pyro.fdr;

import java.util.function.Consumer;

import dev.nuker.pyro.modules.advanced.StashSorter;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;

public class fdw
implements Consumer {
    public StashSorter Field409;
    public float[] Field410;

    public void accept(Object object) {
        this.Method597((EntityPlayerSP)object);
    }

    public fdw(StashSorter stashSorter2, float[] arrf) {
        this.Field409 = stashSorter2;
        this.Field410 = arrf;
    }

    public void Method597(EntityPlayerSP entityPlayerSP) {
        RayTraceResult rayTraceResult = few.Method835().Method849(this.Field410[0], this.Field410[1]);
        fdr fdr2 = StashSorter.Method303(this.Field409);
        if (fdr2 == null) {
            Intrinsics.Method6551();
        }
        StashSorter.Method313((StashSorter)this.Field409).playerController.processRightClickBlock(StashSorter.Method313((StashSorter)this.Field409).player, StashSorter.Method313((StashSorter)this.Field409).world, fdr2.Method616(), rayTraceResult.sideHit, rayTraceResult.hitVec, EnumHand.MAIN_HAND);
        StashSorter.Method307(this.Field409, true);
    }
}

