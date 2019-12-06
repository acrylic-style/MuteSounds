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
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
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
import xyz.acrylicstyle.mutesounds.overlays.Overlay;
import xyz.acrylicstyle.mutesounds.utils.Utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static xyz.acrylicstyle.mutesounds.utils.Utils.translateChatColor;

@Mod(modid = MuteSounds.MOD_ID, name = MuteSounds.NAME, version = MuteSounds.VERSION)
public class MuteSounds {
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
        if (Configuration.misc.overlays.armorOverlay) {
            Utils.activeOverlays.add(Utils.overlays.get(3));
        } else {
            Utils.activeOverlays.remove(Utils.overlays.get(3));
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
                || (Configuration.blocks.water.muteWaterFlowSound && e.getName().equals("block.water.ambient"))) e.setResultSound(null);
    }

    @Config(modid = MOD_ID)
    @SuppressWarnings("unused")
    @LangKey("config.mutesounds._name")
    public static class Configuration {
        @LangKey("config.mutesounds.misc._name")
        public static Misc misc = new Misc();

        public static class Misc {
            @Comment("Will connect to server at startup or not")
            @LangKey("config.mutesounds.misc.connectToServerAtStartup")
            public boolean connectToServerAtStartup = false;

            @RequiresMcRestart
            @Comment({"Server address when connecting to server at startup.", "It works only if connectToServerAtStartup is true."})
            @LangKey("config.mutesounds.misc.serverAddress")
            public String serverAddress = "mc.hypixel.net";

            @RequiresMcRestart
            @Comment({"Server port when connecting to server at startup.", "It works only if connectToServerAtStartup is true."})
            @LangKey("config.mutesounds.misc.port")
            public int port = 25565;

            @LangKey("config.mutesounds.misc.overlays._name")
            public Overlays overlays = new Overlays();
            public static class Overlays {
                @LangKey("config.mutesounds.misc.overlays.positionOverlay")
                public boolean positionOverlay = false;

                @LangKey("config.mutesounds.misc.overlays.gammaOverlay")
                public boolean gammaOverlay = false;

                @LangKey("config.mutesounds.misc.overlays.pingOverlay")
                public boolean pingOverlay = false;

                @LangKey("config.mutesounds.misc.overlays.armorOverlay")
                public boolean armorOverlay = false;
            }

            @LangKey("config.mutesounds.misc.gammaBright._name")
            public GammaBright gammaBright = new GammaBright();
            public static class GammaBright {
                @Config.RangeInt(min = -15000, max = 15000)
                @LangKey("config.mutesounds.misc.gammaBright.gamma")
                public double gamma = 15000;
            }
        }

        @LangKey("config.mutesounds.blocks._name")
        public static Blocks blocks = new Blocks();

        public static class Blocks {
            @Comment("Same sounds as grass block.")
            @LangKey("config.mutesounds.blocks.grass")
            public Block grass = new Block();

            @Comment("Same sounds as dirt.")
            @LangKey("config.mutesounds.blocks.gravel")
            public Block gravel = new Block();

            @Comment("Same sounds as other stones.")
            @LangKey("config.mutesounds.blocks.stone")
            public Block stone = new Block();

            @LangKey("config.mutesounds.blocks.glass")
            public Block glass = new Block();

            public static class Block {
                @Comment("Plays when player is breaking a block.")
                public boolean muteHitSound = false;

                @Comment("Plays when block was broken.")
                public boolean muteBreakSound = false;

                @Comment("Plays when block was placed.")
                public boolean mutePlaceSound = false;

                @Comment("Plays when player has stepped at a block.")
                public boolean muteStepSound = false;

                @Comment("Plays when fallen on from a height.")
                public boolean muteFallSound = false;
            }

            @LangKey("config.mutesounds.blocks.water")
            public Water water = new Water();
            public static class Water {
                public boolean muteWaterFlowSound = false;
            }

            @Comment("Break, place, and some sounds are managed at glass.")
            @LangKey("config.mutesounds.blocks.portal")
            public Portal portal = new Portal();
            public static class Portal {
                @Comment("Plays when player at near the portal")
                public boolean muteAmbientSound = false;

                @Comment("Plays when standing in portal")
                public boolean muteTriggerSound = false;

                @Comment("Plays when loading dimension")
                public boolean muteTravelSound = false;
            }
        }

        @LangKey("config.mutesounds.mobs._name")
        public static Mobs mobs = new Mobs();

        public static class Mobs {
            @LangKey("config.mutesounds.mobs.endermans")
            public Endermans endermans = new Endermans();

            public static class Endermans {
                @Comment({"Idle sound", "Subtitle: Enderman vwoops"})
                public boolean muteAmbientSound = false;

                @Comment("Subtitle: Enderman dies")
                public boolean muteDeathSound = false;

                @Comment("Subtitle: Enderman hurts")
                public boolean muteHurtSound = false;

                @Comment({"Long vwoops", "Subtitle: Enderman stare"})
                public boolean muteStareSound = false;

                @Comment({"When enderman is attacking someone.", "Subtitle: Enderman vwoops"})
                public boolean muteScreamSound = false;

                @Comment({"Subtitle: Enderman teleports"})
                public boolean muteTeleportSound = false;
            }
        }
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
        if (e.getMessage().startsWith(".")) {
            e.setCanceled(true);
            String[] args = e.getMessage().replaceFirst(".", "").split(" ");
            String command = args[0];
            if (!Utils.commands.containsKey(command)) {
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString(translateChatColor("&cInvalid command, type .help to help.")));
                return;
            }
            List<String> argsList = new ArrayList<>(Arrays.asList(args));
            argsList.remove(0);
            args = argsList.toArray(new String[0]);
            Utils.commands.get(command).execute(e.getMessage(), e.getOriginalMessage(), args);
        }
    }

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Post e) {
        if (e.getType() != RenderGameOverlayEvent.ElementType.HOTBAR) return;
        Utils.activeOverlays.forEach(Overlay::draw);
    }
}
