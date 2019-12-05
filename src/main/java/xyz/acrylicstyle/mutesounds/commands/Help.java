package xyz.acrylicstyle.mutesounds.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import xyz.acrylicstyle.mutesounds.utils.PeriodCommand;
import xyz.acrylicstyle.mutesounds.utils.Utils;

public class Help extends PeriodCommand {
    @Override
    public void execute(String message, String originalMessage, String[] args) {
        Utils.commands.forEach((cmd, command) -> Minecraft.getMinecraft().player.sendMessage(new TextComponentString(Utils.translateChatColor("&e." + cmd + "&f: &a" + command.getDescription()))));
    }

    @Override
    public String getDescription() {
        return "Shows help message for you.";
    }
}
