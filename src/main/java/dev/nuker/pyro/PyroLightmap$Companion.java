/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package dev.nuker.pyro;

import dev.nuker.pyro.PyroLightmap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv={1, 1, 15}, bv={1, 0, 3}, k=1, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2={"Ldev/nuker/pyro/PyroLightmap$Companion;", "", "()V", "INSTANCE", "Ldev/nuker/pyro/PyroLightmap;", "getINSTANCE", "()Ldev/nuker/pyro/PyroLightmap;", "setINSTANCE", "(Ldev/nuker/pyro/PyroLightmap;)V", "pyroclient"})
public final class PyroLightmap$Companion {
    @NotNull
    public final PyroLightmap Method10057() {
        PyroLightmap pyroLightmap = PyroLightmap.Method54();
        if (pyroLightmap == null) {
            Intrinsics.Method6554("INSTANCE");
        }
        return pyroLightmap;
    }

    public final void Method10058(@NotNull PyroLightmap pyroLightmap) {
        Intrinsics.Method6567(pyroLightmap, "<set-?>");
        PyroLightmap.Method55(pyroLightmap);
    }

    private PyroLightmap$Companion() {
    }

    public /* synthetic */ PyroLightmap$Companion(DefaultConstructorMarker $constructor_marker) {
        this();
    }
}

