package xyz.acrylicstyle.mutesounds.commands;

import xyz.acrylicstyle.mutesounds.MuteSounds;
import xyz.acrylicstyle.mutesounds.utils.ChatColor;
import xyz.acrylicstyle.mutesounds.utils.PeriodCommand;
import xyz.acrylicstyle.mutesounds.utils.Utils;

public class HelpCommand extends PeriodCommand {
    @Override
    public void execute(String message, String originalMessage, String[] args) {
        Utils.commands.forEach((cmd, command) -> sendMessage(ChatColor.YELLOW + MuteSounds.PREFIX + cmd + ChatColor.WHITE + ": " + ChatColor.AQUA + command.getDescription()));
    }

    @Override
    public String getDescription() {
        return "this.";
    }
}
