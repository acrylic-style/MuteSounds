package xyz.acrylicstyle.mutesounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import util.CollectionList;
import util.ICollectionList;
import xyz.acrylicstyle.mutesounds.overlays.Overlay;
import xyz.acrylicstyle.mutesounds.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import static xyz.acrylicstyle.mutesounds.utils.ConfigurationClasses.*;
import static xyz.acrylicstyle.mutesounds.utils.Utils.translateChatColor;

@Mod(modid = MuteSounds.MOD_ID, name = MuteSounds.NAME, version = MuteSounds.VERSION)
public class MuteSounds {
    public static final String PREFIX = ".";
    public static final String MOD_ID = "mutesounds";
    public static final String NAME = "Mute Sounds";
    public static final String VERSION = "1.0";
    public static KeyBinding toggleFB;
    public static boolean isFB = false;
    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        toggleFB = new KeyBinding("Toggle Full Bright", Keyboard.KEY_G, "Gamma Bright");

        ClientRegistry.registerKeyBinding(toggleFB);

        MinecraftForge.EVENT_BUS.register(MuteSounds.class);

        Method m = ReflectionHelper.findMethod(ConfigManager.class, "getConfiguration", null, String.class, String.class);
        net.minecraftforge.common.config.Configuration config;
        try {
            config = (net.minecraftforge.common.config.Configuration) m.invoke(null, MOD_ID, "");
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return;
        }
        ConfigCategory configCategory = config.getCategory("general.misc.gammabright");
        Property property = configCategory.get("gamma");
        if (property == null) {
            logger.error("property is null!");
        } else {
            property.setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
        }
        ConfigManager.sync(MOD_ID, Config.Type.INSTANCE);
        Minecraft.getMinecraft().gameSettings.gammaSetting = 1;
        overlayCheck();
        Utils.startPingTimer();
        if (Configuration.misc.connectToServerAtStartup) FMLClientHandler.instance().connectToServerAtStartup(Configuration.misc.serverAddress, Configuration.misc.port);
    }

    @SubscribeEvent
    public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(MOD_ID)) {
            ConfigManager.sync(MOD_ID, Config.Type.INSTANCE);
            overlayCheck();
        }
    }

    private static void overlayCheck() {
        if (Configuration.misc.overlays.positionOverlay) {
            Utils.activeOverlays.add(Utils.overlays.get(0));
        } else {
            Utils.activeOverlays.remove(Utils.overlays.get(0));
        }
        if (Configuration.misc.overlays.gammaOverlay) {
            Utils.activeOverlays.add(Utils.overlays.get(1));
        } else {
            Utils.activeOverlays.remove(Utils.overlays.get(1));
        }
        if (Configuration.misc.overlays.pingOverlay) {
            Utils.activeOverlays.add(Utils.overlays.get(2));
        } else {
            Utils.activeOverlays.remove(Utils.overlays.get(2));
        }
        if (Configuration.misc.overlays.fpsOverlay) {
            Utils.activeOverlays.add(Utils.overlays.get(4));
        } else {
            Utils.activeOverlays.remove(Utils.overlays.get(4));
        }
    }

    @SubscribeEvent
    public static void onPlaySound(PlaySoundEvent e) {
        if ((Configuration.mobs.endermans.muteDeathSound && e.getName().equals("entity.endermen.death"))
                || (Configuration.mobs.endermans.muteAmbientSound && e.getName().equals("entity.endermen.ambient"))
                || (Configuration.mobs.endermans.muteHurtSound && e.getName().equals("entity.endermen.hurt"))
                || (Configuration.mobs.endermans.muteScreamSound && e.getName().equals("entity.endermen.scream"))
                || (Configuration.mobs.endermans.muteStareSound && e.getName().equals("entity.endermen.stare"))
                || (Configuration.mobs.endermans.muteTeleportSound && e.getName().equals("entity.endermen.teleport"))
                || (Configuration.blocks.stone.muteBreakSound && e.getName().equals("block.stone.break"))
                || (Configuration.blocks.stone.muteFallSound && e.getName().equals("block.stone.fall"))
                || (Configuration.blocks.stone.muteHitSound && e.getName().equals("block.stone.hit"))
                || (Configuration.blocks.stone.mutePlaceSound && e.getName().equals("block.stone.place"))
                || (Configuration.blocks.stone.muteStepSound && e.getName().equals("block.stone.step"))
                || (Configuration.blocks.glass.muteBreakSound && e.getName().equals("block.glass.break"))
                || (Configuration.blocks.glass.muteFallSound && e.getName().equals("block.glass.fall"))
                || (Configuration.blocks.glass.muteHitSound && e.getName().equals("block.glass.hit"))
                || (Configuration.blocks.glass.mutePlaceSound && e.getName().equals("block.glass.place"))
                || (Configuration.blocks.glass.muteStepSound && e.getName().equals("block.glass.step"))
                || (Configuration.blocks.grass.muteBreakSound && e.getName().equals("block.grass.break"))
                || (Configuration.blocks.grass.muteFallSound && e.getName().equals("block.grass.fall"))
                || (Configuration.blocks.grass.muteHitSound && e.getName().equals("block.grass.hit"))
                || (Configuration.blocks.grass.mutePlaceSound && e.getName().equals("block.grass.place"))
                || (Configuration.blocks.grass.muteStepSound && e.getName().equals("block.grass.step"))
                || (Configuration.blocks.gravel.muteBreakSound && e.getName().equals("block.gravel.break"))
                || (Configuration.blocks.gravel.muteFallSound && e.getName().equals("block.gravel.fall"))
                || (Configuration.blocks.gravel.muteHitSound && e.getName().equals("block.gravel.hit"))
                || (Configuration.blocks.gravel.mutePlaceSound && e.getName().equals("block.gravel.place"))
                || (Configuration.blocks.gravel.muteStepSound && e.getName().equals("block.gravel.step"))
                || (Configuration.blocks.portal.muteAmbientSound && e.getName().equals("block.portal.ambient"))
                || (Configuration.blocks.portal.muteTravelSound && e.getName().equals("block.portal.travel"))
                || (Configuration.blocks.portal.muteTriggerSound && e.getName().equals("block.portal.trigger"))
                || (Configuration.blocks.water.muteWaterFlowSound && e.getName().equals("block.water.ambient"))
                // entity.generic.*
                || (Configuration.entity.generic.muteBigFall && e.getName().equals("entity.generic.big_fall"))
                || (Configuration.entity.generic.muteBurn && e.getName().equals("entity.generic.burn"))
                || (Configuration.entity.generic.muteDeath && e.getName().equals("entity.generic.death"))
                || (Configuration.entity.generic.muteDrink && e.getName().equals("entity.generic.drink"))
                || (Configuration.entity.generic.muteEat && e.getName().equals("entity.generic.eat"))
                || (Configuration.entity.generic.muteExplode && e.getName().equals("entity.generic.explode"))
                || (Configuration.entity.generic.muteExtinguishFire && e.getName().equals("entity.generic.extinguish_fire"))
                || (Configuration.entity.generic.muteHurt && e.getName().equals("entity.generic.hurt"))
                || (Configuration.entity.generic.muteSmallFall && e.getName().equals("entity.generic.small_fall"))
                || (Configuration.entity.generic.muteSplash && e.getName().equals("entity.generic.splash"))
                || (Configuration.entity.generic.muteSwim && e.getName().equals("entity.generic.swim"))
                // entity.player.attack.*
                || (Configuration.entity.player.muteAttack_crit && e.getName().equals("entity.player.attack.crit"))
                || (Configuration.entity.player.muteAttack_knockback && e.getName().equals("entity.player.attack.knockback"))
                || (Configuration.entity.player.muteAttack_nodamage && e.getName().equals("entity.player.attack.nodamage"))
                || (Configuration.entity.player.muteAttack_strong && e.getName().equals("entity.player.attack.strong"))
                || (Configuration.entity.player.muteAttack_sweep && e.getName().equals("entity.player.attack.sweep"))
                || (Configuration.entity.player.muteAttack_weak && e.getName().equals("entity.player.attack.weak"))
                // entity.player.*
                || (Configuration.entity.player.muteBigFall && e.getName().equals("entity.player.big_fall"))
                || (Configuration.entity.player.muteBreath && e.getName().equals("entity.player.breath"))
                || (Configuration.entity.player.muteBurp && e.getName().equals("entity.player.burp"))
                || (Configuration.entity.player.muteDeath && e.getName().equals("entity.player.death"))
                || (Configuration.entity.player.muteHurt && e.getName().equals("entity.player.hurt"))
                || (Configuration.entity.player.muteHurtDrown && e.getName().equals("entity.player.hurt_drown"))
                || (Configuration.entity.player.muteHurtOnFire && e.getName().equals("entity.player.hurt_on_fire"))
                || (Configuration.entity.player.muteLevelup && e.getName().equals("entity.player.levelup"))
                || (Configuration.entity.player.muteSmallFall && e.getName().equals("entity.player.small_fall"))
                || (Configuration.entity.player.muteSplash && e.getName().equals("entity.player.splash"))
                || (Configuration.entity.player.muteSwim && e.getName().equals("entity.player.swim"))) e.setResultSound(null);
    }

    @Config(modid = MOD_ID)
    @LangKey("config.mutesounds._name")
    public static class Configuration {
        @LangKey("config.mutesounds.misc._name")
        public static Misc misc = new Misc();

        @LangKey("config.mutesounds.blocks._name")
        public static Blocks blocks = new Blocks();

        @LangKey("config.mutesounds.mobs._name")
        public static Mobs mobs = new Mobs();

        @LangKey("config.mutesounds.entity._name")
        public static Entity entity = new Entity();
    }

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        if (toggleFB.isPressed()) {
            GameSettings settings = Minecraft.getMinecraft().gameSettings;
            ResourceLocation resourceLocation = new ResourceLocation("entity.experience_orb.pickup");
            if (Minecraft.getMinecraft().player != null) Minecraft.getMinecraft().player.playSound(new SoundEvent(resourceLocation), 100, 1);
            if (isFB) {
                settings.gammaSetting = 1F;
                isFB = false;
                if (Minecraft.getMinecraft().player != null)
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString(translateChatColor("&eFull Bright is now &cDisabled&e!")));
            } else {
                settings.gammaSetting = (float) (Configuration.misc.gammaBright.gamma/100F);
                isFB = true;
                if (Minecraft.getMinecraft().player != null)
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentString(translateChatColor("&eFull Bright is now &aEnabled&e!")));
            }
        }
    }

    @SubscribeEvent
    public static void onClientChat(ClientChatEvent e) {
        if (e.getMessage().startsWith(PREFIX)) {
            e.setCanceled(true);
            String[] args = e.getMessage().replaceFirst(Pattern.quote(PREFIX), "").split(" ");
            String command = args[0];
            if (!Utils.commands.containsKey(command)) {
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(translateChatColor("&cInvalid command, type .help to help.")));
                return;
            }
            CollectionList<String> argsList = ICollectionList.asList(args);
            argsList.shift();
            args = argsList.toArray(new String[0]);
            Utils.commands.get(command).execute(e.getMessage(), e.getOriginalMessage(), args);
        }
    }

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Post e) {
        if (e.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) return;
        if (Configuration.misc.overlays.armorOverlay) Utils.overlays.get(3).draw();
        Utils.activeOverlays.forEach(Overlay::draw);
    }
}
