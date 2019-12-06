package xyz.acrylicstyle.mutesounds.overlays;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.item.ItemStack;
import util.CollectionList;

public class ArmorOverlay implements Overlay {
    private final Minecraft minecraft;
    private final RenderItem itemRender;

    public ArmorOverlay(Minecraft minecraft) {
        this.minecraft = minecraft;
        this.itemRender = minecraft.getRenderItem();
    }

    public void draw() {
        ScaledResolution resolution = new ScaledResolution(minecraft);
        int x = resolution.getScaledWidth();
        int y = resolution.getScaledHeight();
        int offsetX = 20;
        int offsetY = 20;
        new CollectionList<>(Lists.newArrayList(minecraft.player.getArmorInventoryList())).foreach((item, index) -> drawItemStack(item, x-offsetX, y-offsetY-(18*index)));
    }

    private void drawItemStack(ItemStack stack, int x, int y) {
        GlStateManager.translate(0.0F, 0.0F, 32.0F);
        this.itemRender.zLevel = 200.0F;
        this.itemRender.renderItemAndEffectIntoGUI(stack, x, y);
        this.itemRender.zLevel = 0.0F;
    }
}
