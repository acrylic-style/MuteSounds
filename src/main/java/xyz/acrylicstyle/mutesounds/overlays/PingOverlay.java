package xyz.acrylicstyle.mutesounds.overlays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import xyz.acrylicstyle.mutesounds.utils.Utils;

public class PingOverlay implements Overlay {
    private Minecraft minecraft;

    public PingOverlay(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    @SuppressWarnings("ConstantConditions")
    public void draw() {
        int offsetX = 5;
        int offsetY = 8;
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        int responseTime = (player == null || player.connection == null || player.connection.getPlayerInfo(player.getGameProfile().getId()) == null) ? -1 : player.connection.getPlayerInfo(player.getGameProfile().getId()).getResponseTime();
        minecraft.fontRenderer.drawStringWithShadow(Utils.format("Ping: @@@ms", responseTime), offsetX, Utils.activeOverlays.indexOf(this)*offsetY+5, Integer.parseInt("FFAA00", 16));
    }
}
