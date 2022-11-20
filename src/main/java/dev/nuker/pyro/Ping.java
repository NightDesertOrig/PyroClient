/*
 * Decompiled with CFR 0.150.
 */
package dev.nuker.pyro;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import dev.nuker.pyro.f7J;

public class Ping
implements Command {
    public static Ping Field3752 = new Ping();

    public int Method152(CommandContext commandContext) {
        PyroStatic.Field6541.Method5012(new f7J("Ping"));
        return 0;
    }
}

