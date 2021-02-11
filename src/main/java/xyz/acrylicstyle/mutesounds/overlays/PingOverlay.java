package xyz.acrylicstyle.mutesounds.overlays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import xyz.acrylicstyle.mutesounds.utils.Utils;

public class PingOverlay implements Overlay {
    private final Minecraft minecraft;

    public PingOverlay(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    @SuppressWarnings("ConstantConditions")
    public void render() {
        int offsetX = 5;
        int offsetY = 8;
        EntityPlayerSP player = Minecraft.getMinecraft().player;
        int responseTime = (player == null || player.connection == null || player.connection.getPlayerInfo(player.getGameProfile().getId()) == null) ? -1 : player.connection.getPlayerInfo(player.getGameProfile().getId()).getResponseTime();
        String s = String.format("Ping: %sms", responseTime);
        minecraft.fontRenderer.drawStringWithShadow(s, offsetX, Utils.activeOverlays.indexOf(this) * offsetY + 5, 0xffaa00);
    }
}
