/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.world.World
 *  org.jetbrains.annotations.Nullable
 */
package dev.nuker.pyro;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class HUDEditor
extends Module {
    public HUDEditor() {
        super("HUDEditor", "HUDEditor", null, true);
    }

    @Override
    public void Method205(boolean bl, @Nullable EntityPlayerSP entityPlayerSP, @Nullable World world) {
        if (bl) {
            this.Field5236.Method5266(false);
        }
        this.Field5233.displayGuiScreen((GuiScreen)new f5j());
    }
}

