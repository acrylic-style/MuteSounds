package xyz.acrylicstyle.mutesounds.overlays;

import net.minecraft.client.Minecraft;
import xyz.acrylicstyle.mutesounds.utils.Utils;

public class GammaOverlay implements Overlay {
    private final Minecraft minecraft;

    public GammaOverlay(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public void render() {
        int offsetX = 5;
        int offsetY = 8;
        minecraft.fontRenderer.drawStringWithShadow(String.format("Gamma: %f%%", minecraft.gameSettings.gammaSetting * 100), offsetX, Utils.activeOverlays.indexOf(this)*offsetY+5, 0xffaa00);
    }
}
