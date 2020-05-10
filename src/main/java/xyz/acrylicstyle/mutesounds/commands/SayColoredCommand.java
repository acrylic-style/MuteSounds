package xyz.acrylicstyle.mutesounds.commands;

import net.minecraft.client.Minecraft;
import xyz.acrylicstyle.mutesounds.utils.PeriodCommand;
import xyz.acrylicstyle.mutesounds.utils.Utils;

import java.util.Arrays;

public class SayColoredCommand extends PeriodCommand {
    @Override
    public void execute(String message, String originalMessage, String[] args) {
        StringBuilder msg = new StringBuilder();
        Arrays.asList(args).forEach(a -> msg.append(a).append(" "));
        Minecraft.getMinecraft().player.sendChatMessage(Utils.translateChatColor(msg.toString()));
    }

    @Override
    public String getDescription() {
        return "Send chat message with provided arguments. But it will let you disconnect from server.";
    }
}
