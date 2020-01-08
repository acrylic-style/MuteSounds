package xyz.acrylicstyle.mutesounds.overlays;

import net.minecraft.client.Minecraft;
import xyz.acrylicstyle.mutesounds.utils.Utils;

public class FPSOverlay implements Overlay {
    private Minecraft minecraft;

    public FPSOverlay(Minecraft minecraft) { this.minecraft = minecraft; }

    @Override
    public void draw() {
        int offsetX = 5;
        int offsetY = 8;
        minecraft.fontRenderer.drawStringWithShadow(Utils.format("FPS: @@@", Minecraft.getDebugFPS()), offsetX, Utils.activeOverlays.indexOf(this)*offsetY+5, Integer.parseInt("FFAA00", 16));
    }
}
