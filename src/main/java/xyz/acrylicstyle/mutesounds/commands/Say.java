package xyz.acrylicstyle.mutesounds.commands;

import net.minecraft.client.Minecraft;
import xyz.acrylicstyle.mutesounds.utils.PeriodCommand;

import java.util.Arrays;

public class Say extends PeriodCommand {
    @Override
    public void execute(String message, String originalMessage, String[] args) {
        StringBuilder msg = new StringBuilder();
        Arrays.asList(args).forEach(a -> msg.append(a).append(" "));
        Minecraft.getMinecraft().player.sendChatMessage(msg.toString());
    }

    @Override
    public String getDescription() {
        return "Send chat message with provided arguments. &bUsage: &e.say hello world!";
    }
}
