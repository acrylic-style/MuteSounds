package xyz.acrylicstyle.mutesounds.utils;

import net.minecraft.client.Minecraft;
import util.Collection;
import util.CollectionList;
import xyz.acrylicstyle.mutesounds.commands.FullBright;
import xyz.acrylicstyle.mutesounds.commands.Help;
import xyz.acrylicstyle.mutesounds.overlays.*;

public final class Utils {
    private Utils() {}

    public static final Collection<String, PeriodCommand> commands = new Collection<>();
    public static final CollectionList<Overlay> overlays = new CollectionList<>();
    public static final CollectionList<Overlay> activeOverlays = new CollectionList<>();

    static {
        commands.add("help", new Help());
        commands.add("fullbright", new FullBright());

        overlays.add(new PositionOverlay(Minecraft.getMinecraft()));
        overlays.add(new GammaOverlay(Minecraft.getMinecraft()));
        overlays.add(new PingOverlay(Minecraft.getMinecraft()));
        overlays.add(new ArmorOverlay(Minecraft.getMinecraft()));
    }

    public static final char SECTION = '\u00a7';

    public static String translateChatColor(String s) {
        return s.replaceAll("&", String.valueOf(SECTION));
    }

    public static String format(String s, Object... o) {
        for (Object obj : o) {
            s = s.replaceFirst("@@@", obj.toString());
        }
        return s;
    }
}
