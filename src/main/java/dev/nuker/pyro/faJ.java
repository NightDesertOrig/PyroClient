/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  org.jetbrains.annotations.NotNull
 */
package dev.nuker.pyro;

import java.util.List;

import dev.nuker.pyro.modules.movement.elytraflight2.ElytraFlight2;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

public class faj {
    @NotNull
    public ElytraFlight2 Field1892;
    @NotNull
    public String Field1893;
    @NotNull
    public String Field1894;

    public void Method2537(boolean bl) {
    }

    public faj(@NotNull ElytraFlight2 elytraFlight22, @NotNull String string, @NotNull String string2) {
        this.Field1892 = elytraFlight22;
        this.Field1893 = string;
        this.Field1894 = string2;
    }

    @NotNull
    public String Method2555() {
        return this.Field1894;
    }

    public Minecraft Method2556() {
        int n = 0;
        return Minecraft.getMinecraft();
    }

    @NotNull
    public ElytraFlight2 Method2557() {
        return this.Field1892;
    }

    @NotNull
    public String Method2558() {
        return this.Field1893;
    }

    @NotNull
    public List Method2460() {
        throw new UnsupportedOperationException("Please report this to the binscure obfuscator developers");
    }
}

