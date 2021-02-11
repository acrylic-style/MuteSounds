package xyz.acrylicstyle.mutesounds.overlays;

import net.minecraft.client.Minecraft;
import xyz.acrylicstyle.mutesounds.utils.Utils;

public class PositionOverlay implements Overlay {
    private final Minecraft minecraft;

    public PositionOverlay(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public void render() {
        int offsetX = 5;
        int offsetY = 5;
        minecraft.fontRenderer.drawStringWithShadow(String.format("X: %f, Y: %f, Z: %f", Math.round(minecraft.player.posX*10)/10F, Math.round(minecraft.player.posY*10)/10F, Math.round(minecraft.player.posZ*10)/10F), offsetX, Utils.activeOverlays.indexOf(this)*offsetY+5, 0xffaa00);
    }
}
