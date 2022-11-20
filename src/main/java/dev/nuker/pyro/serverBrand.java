/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.ScaledResolution
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package dev.nuker.pyro;

import net.minecraft.client.gui.ScaledResolution;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class serverBrand
extends f5T {
    @Override
    @NotNull
    public String Method4969() {
        return fdZ.Field313.player != null ? (fdZ.Field313.player.getServerBrand() != null ? fdZ.Field313.player.getServerBrand() : "Vanilla") : "Vanilla";
    }

    @Override
    public int Method4968(@Nullable ScaledResolution scaledResolution, float f, float f2) {
        return 0xAAAAAA;
    }

    public serverBrand() {
        super("serverBrand");
    }
}

