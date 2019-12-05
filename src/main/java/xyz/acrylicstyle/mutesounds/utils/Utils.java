package xyz.acrylicstyle.mutesounds.utils;

import util.Collection;
import xyz.acrylicstyle.mutesounds.commands.FullBright;
import xyz.acrylicstyle.mutesounds.commands.Help;

public final class Utils {
    private Utils() {}

    public static final Collection<String, PeriodCommand> commands = new Collection<>();

    static {
        commands.add("help", new Help());
        commands.add("fullbright", new FullBright());
    }

    public static final char SECTION = '\u00a7';

    public static String translateChatColor(String s) {
        return s.replaceAll("&", String.valueOf(SECTION));
    }
}
