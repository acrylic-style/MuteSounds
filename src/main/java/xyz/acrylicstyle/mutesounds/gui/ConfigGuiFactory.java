package xyz.acrylicstyle.mutesounds.gui;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;
import xyz.acrylicstyle.mutesounds.MuteSounds;

import java.util.List;
import java.util.Set;

public class ConfigGuiFactory implements IModGuiFactory {
    private static final Set<RuntimeOptionCategoryElement> categories = ImmutableSet.of(new RuntimeOptionCategoryElement("PARENT", MuteSounds.MOD_ID));

    @Override
    public void initialize(Minecraft minecraft) {}

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return ConfigScreen.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return categories;
    }

    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return new RuntimeOptionGuiHandler() {
            public void paint(int x, int y, int w, int h) {
            }

            public void close() {
            }

            public void addWidgets(List<Gui> widgets, int x, int y, int w, int h) {
                widgets.add(new GuiButton(100, x + 10, y + 10, "HELLO"));
            }

            public void actionCallback(int actionId) {
            }
        };
    }
}
