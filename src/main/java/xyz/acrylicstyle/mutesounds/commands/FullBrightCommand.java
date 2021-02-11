package xyz.acrylicstyle.mutesounds.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import xyz.acrylicstyle.mutesounds.MuteSounds;
import xyz.acrylicstyle.mutesounds.utils.ChatColor;
import xyz.acrylicstyle.mutesounds.utils.PeriodCommand;
import xyz.acrylicstyle.mutesounds.utils.Utils;

public class FullBrightCommand extends PeriodCommand {
    @Override
    public void execute(String message, String originalMessage, String[] args) {
        double gamma;
        try {
            gamma = args.length >= 1 ? Integer.parseInt(args[0]) : MuteSounds.Configuration.misc.gammaBright.gamma;
        } catch (NumberFormatException e) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString(Utils.translateChatColor("&cPlease specify valid number!")));
            return;
        }
        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        ResourceLocation resourceLocation = new ResourceLocation("entity.experience_orb.pickup");
        if (Minecraft.getMinecraft().player != null) Minecraft.getMinecraft().player.playSound(new SoundEvent(resourceLocation), 100, 1);
        if (args.length >= 1) {
            settings.gammaSetting = (float) (gamma/100F);
            MuteSounds.isFullBright = true;
            sendMessage(ChatColor.YELLOW + "Gamma has been set to " + ChatColor.GREEN + args[0] + "%" + ChatColor.YELLOW + "!");
            return;
        }
        if (MuteSounds.isFullBright) {
            settings.gammaSetting = 1F;
            MuteSounds.isFullBright = false;
            sendMessage(ChatColor.YELLOW + "Full Bright is now " + ChatColor.RED + "Disabled" + ChatColor.YELLOW + "!");
        } else {
            settings.gammaSetting = (float) (gamma / 100F);
            MuteSounds.isFullBright = true;
            sendMessage(ChatColor.YELLOW + "Full Bright is now " + ChatColor.GREEN + "Enabled" + ChatColor.YELLOW + "!");
        }
    }

    @Override
    public String getDescription() {
        return "Toggles Full Bright. " + ChatColor.AQUA + "Usage: " + ChatColor.YELLOW + ".fullbright [gamma%]";
    }
}
