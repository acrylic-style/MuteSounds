package xyz.acrylicstyle.mutesounds.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextComponentString;

public abstract class PeriodCommand {
    public abstract void execute(String message, String originalMessage, String[] args);

    public String getDescription() {
        return "Description not defined.";
    }

    public final void sendMessage(String s) {
        EntityPlayerSP player = getPlayer();
        if (player == null) return;
        player.sendMessage(new TextComponentString(ChatColor.translate(s)));
    }

    public final EntityPlayerSP getPlayer() {
        return Minecraft.getMinecraft().player;
    }
}
