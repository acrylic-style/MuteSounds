package xyz.acrylicstyle.mutesounds.utils;

import net.minecraft.client.Minecraft;
import util.Collection;
import util.CollectionList;
import xyz.acrylicstyle.mutesounds.commands.DebugCommand;
import xyz.acrylicstyle.mutesounds.commands.FullBrightCommand;
import xyz.acrylicstyle.mutesounds.commands.HelpCommand;
import xyz.acrylicstyle.mutesounds.overlays.ArmorOverlay;
import xyz.acrylicstyle.mutesounds.overlays.FPSOverlay;
import xyz.acrylicstyle.mutesounds.overlays.GammaOverlay;
import xyz.acrylicstyle.mutesounds.overlays.Overlay;
import xyz.acrylicstyle.mutesounds.overlays.PingOverlay;
import xyz.acrylicstyle.mutesounds.overlays.PositionOverlay;

public final class Utils {
    private Utils() {}

    public static final Collection<String, PeriodCommand> commands = new Collection<>();
    public static final CollectionList<Overlay> overlays = new CollectionList<>();
    public static final CollectionList<Overlay> activeOverlays = new CollectionList<>();

    static {
        commands.add("help", new HelpCommand());
        commands.add("fullbright", new FullBrightCommand());
        commands.add("debug", new DebugCommand());

        overlays.add(new PositionOverlay(Minecraft.getMinecraft()));
        overlays.add(new GammaOverlay(Minecraft.getMinecraft()));
        overlays.add(new PingOverlay(Minecraft.getMinecraft()));
        overlays.add(new ArmorOverlay(Minecraft.getMinecraft()));
        overlays.add(new FPSOverlay(Minecraft.getMinecraft()));
    }

    public static final char SECTION = '\u00a7';

    public static String translateChatColor(String message) {
        return message.replaceAll("&", String.valueOf(SECTION));
    }
}
