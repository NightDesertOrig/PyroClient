/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.ForgeModContainer
 *  org.jetbrains.annotations.NotNull
 */
package dev.nuker.pyro;

import java.util.function.Consumer;

import dev.nuker.pyro.modules.world.Wallhack;
import net.minecraftforge.common.ForgeModContainer;
import org.jetbrains.annotations.NotNull;

public class fbQ
implements Consumer {
    public Wallhack Field1938;

    public void accept(Object object) {
        this.Method374((f0w)object);
    }

    public void Method374(@NotNull f0w f0w2) {
        if (((Boolean)this.Field1938.Field5236.Method5264()).booleanValue() && ((Boolean)f0w2.Method7979()).booleanValue()) {
            if (Wallhack.Method2610((Wallhack)this.Field1938).world != null) {
                if (Wallhack.Method2610((Wallhack)this.Field1938).renderGlobal != null) {
                    Wallhack.Method2610(this.Field1938).addScheduledTask((Runnable)new fbP(this));
                }
            }
            ForgeModContainer.forgeLightPipelineEnabled = !this.Field1938.Method2622();
        }
    }

    public fbQ(Wallhack wallhack2) {
        this.Field1938 = wallhack2;
    }
}

