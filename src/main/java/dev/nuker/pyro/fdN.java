/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package dev.nuker.pyro;

import java.util.function.Consumer;

import dev.nuker.pyro.modules.advanced.StashSorter;
import org.jetbrains.annotations.NotNull;

public class fdn
implements Consumer {
    public StashSorter Field361;

    public void accept(Object object) {
        this.Method374((f0w)object);
    }

    public fdn(StashSorter stashSorter2) {
        this.Field361 = stashSorter2;
    }

    public void Method374(@NotNull f0w f0w2) {
        if (((Boolean)f0w2.Method7979())) {
            this.Field361.Method284().Method7975(false);
            this.Field361.Method282().Method7975(false);
        }
    }
}

