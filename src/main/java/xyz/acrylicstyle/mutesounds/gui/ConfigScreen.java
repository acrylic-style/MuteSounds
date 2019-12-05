package xyz.acrylicstyle.mutesounds.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.*;
import xyz.acrylicstyle.mutesounds.MuteSounds;

import java.util.ArrayList;
import java.util.List;

public class ConfigScreen extends GuiConfig {
    public ConfigScreen(GuiScreen parent) {
        super(parent, getConfigElements(), MuteSounds.MOD_ID, false, false, "Mute Sounds");
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<>();
        List<IConfigElement> listsList = new ArrayList<>();
        listsList.add(new DummyConfigElement("connectToServerAtStartup", false, ConfigGuiType.BOOLEAN, "config.mutesounds.misc.connectToServerAtStartup").setRequiresMcRestart(true));
        list.add(new DummyConfigElement.DummyCategoryElement("lists", "config.mutesounds.misc._name", listsList));
        return list;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
    }
}
