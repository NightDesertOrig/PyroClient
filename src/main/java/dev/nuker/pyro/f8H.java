/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  org.jetbrains.annotations.NotNull
 */
package dev.nuker.pyro;

import java.util.function.Predicate;

import dev.nuker.pyro.modules.movement.AutoMount;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class f8H
implements Predicate {
    public AutoMount Field3361;

    public boolean Method2427(@NotNull Entity entity) {
        return this.Field3361.Method2425(entity);
    }

    public f8H(AutoMount autoMount2) {
        this.Field3361 = autoMount2;
    }

    public boolean test(Object object) {
        return this.Method2427((Entity)object);
    }
}

