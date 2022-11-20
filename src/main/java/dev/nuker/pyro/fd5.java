/*
 * Decompiled with CFR 0.150.
 */
package dev.nuker.pyro;

import dev.nuker.pyro.modules.world.AutoTunnel;

import java.util.function.Consumer;

public class fd5
implements Consumer {
    public AutoTunnel Field123;

    public void accept(Object object) {
        this.Method182((Boolean)object);
    }

    public void Method182(Boolean bl) {
        this.Field123.Method209(null);
    }

    public fd5(AutoTunnel fd62) {
        this.Field123 = fd62;
    }
}

