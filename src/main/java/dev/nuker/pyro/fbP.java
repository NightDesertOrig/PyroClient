/*
 * Decompiled with CFR 0.150.
 */
package dev.nuker.pyro;

import dev.nuker.pyro.modules.world.Wallhack;

public class fbP
implements Runnable {
    public fbQ Field1998;

    public fbP(fbQ fbQ2) {
        this.Field1998 = fbQ2;
    }

    @Override
    public void run() {
        int n = (int) Wallhack.Method2610((Wallhack)this.Field1998.Field1938).player.posX;
        int n2 = (int) Wallhack.Method2610((Wallhack)this.Field1998.Field1938).player.posY;
        int n3 = (int) Wallhack.Method2610((Wallhack)this.Field1998.Field1938).player.posZ;
        int n4 = Wallhack.Method2610((Wallhack)this.Field1998.Field1938).gameSettings.renderDistanceChunks * 16;
        Wallhack.Method2610((Wallhack)this.Field1998.Field1938).renderGlobal.markBlockRangeForRenderUpdate(n - n4, n2 - n4, n3 - n4, n + n4, n2 + n4, n3 + n4);
    }
}

