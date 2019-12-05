package xyz.acrylicstyle.mutesounds.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import xyz.acrylicstyle.mutesounds.MuteSounds;
import xyz.acrylicstyle.mutesounds.utils.PeriodCommand;
import xyz.acrylicstyle.mutesounds.utils.Utils;

import static xyz.acrylicstyle.mutesounds.utils.Utils.translateChatColor;

public class FullBright extends PeriodCommand {
    @Override
    public void execute(String message, String originalMessage, String[] args) {
        double gamma;
        try {
            gamma = args.length >= 1 ? Integer.parseInt(args[0]) : MuteSounds.Configuration.misc.gammaBright.gamma.getInt();
        } catch (NumberFormatException e) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(Utils.translateChatColor("&cPlease specify valid number!"));
            return;
        }
        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        if (Minecraft.getMinecraft().thePlayer != null) Minecraft.getMinecraft().thePlayer.playSound("random.orb", 100, 1);
        if (args.length >= 1) {
            settings.gammaSetting = (float) (gamma/100F);
            MuteSounds.isFB = true;
            if (Minecraft.getMinecraft().thePlayer != null)
                Minecraft.getMinecraft().thePlayer.sendChatMessage(translateChatColor("&aGamma has been set to &a" + args[0] + "%&e!"));
            return;
        }
        if (MuteSounds.isFB) {
            settings.gammaSetting = 1F;
            MuteSounds.isFB = false;
            if (Minecraft.getMinecraft().thePlayer != null)
                Minecraft.getMinecraft().thePlayer.sendChatMessage(translateChatColor("&eFull Bright is now &cDisabled&e!"));
        } else {
            settings.gammaSetting = (float) (gamma/100F);
            MuteSounds.isFB = true;
            if (Minecraft.getMinecraft().thePlayer != null)
                Minecraft.getMinecraft().thePlayer.sendChatMessage(translateChatColor("&eFull Bright is now &aEnabled&e!"));
        }
    }

    @Override
    public String getDescription() {
        return "Toggles Full Bright. &bUsage: &e.fullbright [gamma%]";
    }
}
