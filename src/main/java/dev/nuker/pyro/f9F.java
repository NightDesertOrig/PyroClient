/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package dev.nuker.pyro;

import java.util.function.Consumer;

import dev.nuker.pyro.modules.movement.elytraflight2.ElytraFlight2;
import org.jetbrains.annotations.NotNull;

public class f9f
implements Consumer {
    public ElytraFlight2 Field4455;

    public void Method374(@NotNull f0w f0w2) {
        ElytraFlight2.Method6322(this.Field4455);
    }

    public void accept(Object object) {
        this.Method374((f0w)object);
    }

    public f9f(ElytraFlight2 elytraFlight22) {
        this.Field4455 = elytraFlight22;
    }
}

