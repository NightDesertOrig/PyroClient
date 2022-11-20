/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.world.World
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package dev.nuker.pyro.modules.movement.elytraflight2;

import dev.nuker.pyro.*;
import dev.nuker.pyro.f9g;
import dev.nuker.pyro.fam;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ElytraFlight2
extends Module {
    @NotNull
    public Map<f9g, faj> Field4418 = MapsKt.Method8567(TuplesKt.Method2826(f9g.Creative, new fai(this)), TuplesKt.Method2826(f9g.Packet, new fam(this)), TuplesKt.Method2826(f9g.BoostControl, new ElytraFlight2Listener(this)));
    @NotNull
    public f0z<f9g> Field4419;
    @NotNull
    public faj Field4420;

    @NotNull
    public Map Method6320() {
        return this.Field4418;
    }

    @NotNull
    public faj Method6321() {
        return this.Field4420;
    }

    public ElytraFlight2() {
        super("elytraflight", "ElytraFlight2", "Greater control over elytra based flight");
        Enum enum_ = f9g.Creative;
        List[] arrlist = new List[3];
        faj faj2 = this.Field4418.get((Object)f9g.Creative);
        if (faj2 == null) {
            Intrinsics.Method6551();
        }
        arrlist[0] = faj2.Method2460();
        faj faj3 = this.Field4418.get((Object)f9g.Packet);
        if (faj3 == null) {
            Intrinsics.Method6551();
        }
        arrlist[1] = faj3.Method2460();
        faj faj4 = this.Field4418.get((Object)f9g.BoostControl);
        if (faj4 == null) {
            Intrinsics.Method6551();
        }
        arrlist[2] = faj4.Method2460();
        this.Field4419 = (f0z)this.Method7264(new f0z("mode", "Mode", null, enum_, arrlist));
        this.Field4420 = this.Method6325();
        this.Field4419.Method7991().Method7980(new f9f(this));
    }

    public void Method5384() {
        faj faj2 = this.Method6325();
        if (Intrinsics.Method6572(faj2, this.Field4420) ^ true) {
            if (((Boolean)this.Field5236.Method5264()).booleanValue()) {
                this.Field4420.Method2537(false);
                Pyro.Method8978().Method7921(this.Field4420);
                this.Field4420 = faj2;
                this.Field4420.Method2537(true);
                Pyro.Method8978().Method7915(this.Field4420);
            }
        }
        this.Method7274(((f9g)((Object)this.Field4419.Method7991().Method7979())).toString());
    }

    public static void Method6322(ElytraFlight2 elytraFlight22) {
        elytraFlight22.Method5384();
    }

    public void Method6323(@NotNull faj faj2) {
        this.Field4420 = faj2;
    }

    @Override
    public void Method205(boolean bl, @Nullable EntityPlayerSP entityPlayerSP, @Nullable World world) {
        this.Field4420.Method2537(bl);
        if (bl) {
            Pyro.Method8978().Method7915(this.Field4420);
        } else {
            Pyro.Method8978().Method7921(this.Field4420);
        }
        this.Method7274(((f9g)((Object)this.Field4419.Method7991().Method7979())).toString());
    }

    @NotNull
    public f0z Method6324() {
        return this.Field4419;
    }

    public faj Method6325() {
        faj faj2 = this.Field4418.get(this.Field4419.Method7991().Method7979());
        if (faj2 == null) {
            Intrinsics.Method6551();
        }
        return faj2;
    }
}

