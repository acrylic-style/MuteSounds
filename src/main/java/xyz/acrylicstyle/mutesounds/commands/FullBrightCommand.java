package xyz.acrylicstyle.mutesounds.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import xyz.acrylicstyle.mutesounds.MuteSounds;
import xyz.acrylicstyle.mutesounds.utils.PeriodCommand;
import xyz.acrylicstyle.mutesounds.utils.Utils;

import static xyz.acrylicstyle.mutesounds.utils.Utils.translateChatColor;

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
            MuteSounds.isFB = true;
            if (Minecraft.getMinecraft().player != null)
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(translateChatColor("&aGamma has been set to &a" + args[0] + "%&e!")));
            return;
        }
        if (MuteSounds.isFB) {
            settings.gammaSetting = 1F;
            MuteSounds.isFB = false;
            if (Minecraft.getMinecraft().player != null)
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(translateChatColor("&eFull Bright is now &cDisabled&e!")));
        } else {
            settings.gammaSetting = (float) (gamma/100F);
            MuteSounds.isFB = true;
            if (Minecraft.getMinecraft().player != null)
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(translateChatColor("&eFull Bright is now &aEnabled&e!")));
        }
    }

    @Override
    public String getDescription() {
        return "Toggles Full Bright. &bUsage: &e.fullbright [gamma%]";
    }
}
