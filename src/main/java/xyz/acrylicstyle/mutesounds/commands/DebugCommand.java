package xyz.acrylicstyle.mutesounds.commands;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import util.ICollectionList;
import xyz.acrylicstyle.mutesounds.MuteSounds;
import xyz.acrylicstyle.mutesounds.utils.ChatColor;
import xyz.acrylicstyle.mutesounds.utils.PeriodCommand;

import java.lang.reflect.Modifier;

public class DebugCommand extends PeriodCommand {
    private static final Minecraft minecraft = Minecraft.getMinecraft();

    @Override
    public void execute(String message, String originalMessage, String[] args) {
        if (args.length == 0) {
            minecraft.player.sendMessage(new TextComponentString(ChatColor.AQUA + "Usage: " + ChatColor.PINK + MuteSounds.PREFIX + "debug <Script>"));
            return;
        }
        new Thread(() -> {
            ICollectionList<String> argsList = ICollectionList.asList(args);
            String argsString = argsList.join(" ");
            try {
                Object result = eval(args, argsString);
                sendMessage(ChatColor.GREEN.toString() + "Result[" + (result != null ? Modifier.toString(result.getClass().getModifiers()) : "<?>") + "](" + (result != null ? result.getClass().getCanonicalName() : "null") + "):");
                sendMessage(ChatColor.GREEN.toString() + result);
            } catch (Throwable e) {
                sendMessage(ChatColor.RED + "An error occurred: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                for (StackTraceElement el : e.getStackTrace()) sendMessage(ChatColor.RED + el.toString());
            }
        }).start();
    }

    @Override
    public String getDescription() {
        return ChatColor.GREEN + "Executes script.";
    }

    private Object eval(String[] args, String expression) {
        Binding b = new Binding();
        b.setVariable("expression", expression);
        b.setVariable("minecraft", minecraft);
        b.setVariable("args", args);
        b.setProperty("expression", expression);
        b.setProperty("minecraft", minecraft);
        b.setProperty("args", args);
        return new GroovyShell(b).evaluate(expression);
    }
}
