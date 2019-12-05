package xyz.acrylicstyle.mutesounds.overlays;

import net.minecraft.client.Minecraft;
import xyz.acrylicstyle.mutesounds.utils.Utils;

public class PositionOverlay implements Overlay {
    private Minecraft minecraft;

    public PositionOverlay(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public void draw() {
        int offsetX = 5;
        int offsetY = 5;
        minecraft.fontRenderer.drawStringWithShadow(Utils.format("X: @@@, Y: @@@, Z: @@@", Math.round(minecraft.player.posX*10)/10F, Math.round(minecraft.player.posY*10)/10F, Math.round(minecraft.player.posZ*10)/10F), offsetX, Utils.activeOverlays.indexOf(this)*offsetY+5, Integer.parseInt("FFAA00", 16));
    }
}
