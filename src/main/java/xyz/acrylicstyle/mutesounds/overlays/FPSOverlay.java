package xyz.acrylicstyle.mutesounds.overlays;

import net.minecraft.client.Minecraft;
import xyz.acrylicstyle.mutesounds.utils.Utils;

public class FPSOverlay implements Overlay {
    private final Minecraft minecraft;

    public FPSOverlay(Minecraft minecraft) { this.minecraft = minecraft; }

    @Override
    public void render() {
        int offsetX = 5;
        int offsetY = 8;
        minecraft.fontRenderer.drawStringWithShadow(String.format("FPS: %d fps", Minecraft.getDebugFPS()), offsetX, Utils.activeOverlays.indexOf(this) * offsetY + 5, 0xffaa00);
    }
}
