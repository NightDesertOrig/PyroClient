/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package dev.nuker.pyro;

import com.mojang.brigadier.SingleRedirectModifier;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.nuker.pyro.f1s;
import org.jetbrains.annotations.NotNull;

public class f1O
implements SingleRedirectModifier {
    public static f1O Field5623 = new f1O();

    @NotNull
    public f1s Method7926(CommandContext commandContext) {
        return ((f1s)commandContext.Method6876()).Method5490("health", Float.valueOf(FloatArgumentType.Method9249(commandContext, "health")));
    }

    public Object Method4086(CommandContext commandContext) {
        return this.Method7926(commandContext);
    }
}

