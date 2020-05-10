package xyz.acrylicstyle.mutesounds.commands;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import util.CollectionList;
import util.ICollectionList;
import xyz.acrylicstyle.mutesounds.MuteSounds;
import xyz.acrylicstyle.mutesounds.utils.PeriodCommand;
import xyz.acrylicstyle.mutesounds.utils.Utils;

import java.lang.reflect.Modifier;

public class DebugCommand extends PeriodCommand {
    private static final Minecraft minecraft = Minecraft.getMinecraft();

    @Override
    public void execute(String message, String originalMessage, String[] args) {
        if (args.length == 0) {
            minecraft.player.sendMessage(new TextComponentString(Utils.translateChatColor("&bUsage: &d" + MuteSounds.PREFIX + "debug <Script>")));
            return;
        }
        CollectionList<String> argsList = ICollectionList.asList(args);
        argsList.shift();
        String argsString = argsList.join(" ");
        try {
            Object result = eval(args, argsString);
            minecraft.player.sendMessage(new TextComponentString(Utils.translateChatColor("&aResult[" + (result != null ? Modifier.toString(result.getClass().getModifiers()) : "<?>") + "](" + (result != null ? result.getClass().getCanonicalName() : "null") + "):")));
            minecraft.player.sendMessage(new TextComponentString(Utils.translateChatColor("&a" + result)));
        } catch (Throwable e) {
            minecraft.player.sendMessage(new TextComponentString(Utils.translateChatColor("&cAn error occurred: " + e.getClass().getSimpleName() + ": " + e.getMessage())));
            for (StackTraceElement el : e.getStackTrace()) minecraft.player.sendMessage(new TextComponentString(Utils.translateChatColor("&c" + el.toString())));
        }
    }

    @Override
    public String getDescription() {
        return "&aExecutes script.";
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
