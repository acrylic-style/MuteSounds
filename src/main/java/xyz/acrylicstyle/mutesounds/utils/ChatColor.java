package xyz.acrylicstyle.mutesounds.utils;

public enum ChatColor {
    BLACK('0'),
    DARK_BLUE('1'),
    DARK_GREEN('2'),
    DARK_AQUA('3'),
    DARK_RED('4'),
    DARK_PURPLE('5'),
    GOLD('6'),
    GRAY('7'),
    DARK_GRAY('8'),
    BLUE('9'),
    GREEN('a'),
    AQUA('b'),
    RED('c'),
    LIGHT_PURPLE('d'),
    YELLOW('e'),
    WHITE('f'),
    ;

    public static final char SECTION = '\u00a7';

    // aliases
    public static final ChatColor PINK = LIGHT_PURPLE;

    private final String toString;

    ChatColor(char code) {
        this.toString = Character.toString(SECTION) + code;
    }

    public static String translate(String s) {
        return s.replaceAll("&", String.valueOf(SECTION));
    }

    @Override
    public String toString() {
        return toString;
    }
}
