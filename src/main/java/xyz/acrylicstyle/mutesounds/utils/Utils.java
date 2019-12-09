package xyz.acrylicstyle.mutesounds.utils;

import net.minecraft.client.Minecraft;
import util.Collection;
import util.CollectionList;
import xyz.acrylicstyle.mutesounds.commands.*;
import xyz.acrylicstyle.mutesounds.overlays.*;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;

public final class Utils {
    private Utils() {}

    public static final Collection<String, PeriodCommand> commands = new Collection<>();
    public static final CollectionList<Overlay> overlays = new CollectionList<>();
    public static final CollectionList<Overlay> activeOverlays = new CollectionList<>();

    public static boolean isPinged = false;
    public static long ping = 0;

    static {
        commands.add("help", new Help());
        commands.add("fullbright", new FullBright());
        commands.add("say", new Say());
        commands.add("saycolored", new SayColored());

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

    public static void startPingTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (Minecraft.getMinecraft().getCurrentServerData() == null) {
                    this.cancel();
                    return;
                }
                long currentTime = System.currentTimeMillis();
                try {
                    isPinged = InetAddress.getByName(Minecraft.getMinecraft().getCurrentServerData().serverIP).isReachable(2000); // 5 seconds
                    ping = System.currentTimeMillis() - currentTime;
                } catch (IOException ignored) {}
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000*10);
    }
}
