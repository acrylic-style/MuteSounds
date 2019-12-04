package xyz.acrylicstyle.mutesounds.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import xyz.acrylicstyle.mutesounds.utils.PeriodCommand;
import xyz.acrylicstyle.mutesounds.utils.Utils;

public class Connect extends PeriodCommand {
    @Override
    public void execute(String message, String originalMessage, String[] args) {
        Minecraft.getMinecraft().player.sendMessage(new TextComponentString(Utils.translateChatColor("&cNot implemented yet :(")));
    }

    @Override
    public String getDescription() {
        return "Connect to the specified server. Usage: .connect <host> <port>";
    }
}
