package xyz.acrylicstyle.mutesounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import xyz.acrylicstyle.mutesounds.gui.ConfigScreen;
import xyz.acrylicstyle.mutesounds.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static xyz.acrylicstyle.mutesounds.utils.Utils.translateChatColor;

@Mod(modid = MuteSounds.MOD_ID, name = MuteSounds.NAME, version = MuteSounds.VERSION, guiFactory = "xyz.acrylicstyle.mutesounds.gui.ConfigGuiFactory")
public class MuteSounds {
    public static final String MOD_ID = "mutesounds";
    public static final String NAME = "Mute Sounds";
    public static final String VERSION = "1.0";
    public static KeyBinding toggleFB;
    public static boolean isFB = false;
    public static Logger logger;
    public static net.minecraftforge.common.config.Configuration config = null;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        logger = e.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        toggleFB = new KeyBinding("Toggle Full Bright", Keyboard.KEY_G, "Gamma Bright");

        ClientRegistry.registerKeyBinding(toggleFB);

        MinecraftForge.EVENT_BUS.register(MuteSounds.class);
        //if (Configuration.misc.connectToServerAtStartup.getBoolean()) FMLClientHandler.instance().connectToServerAtStartup(Configuration.misc.serverAddress.getString(), Configuration.misc.port.getInt());
    }

    //@SubscribeEvent
    //public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
    //    if (event.modID.equals(MOD_ID))
    //}

    @SubscribeEvent
    public static void onPlaySound(PlaySoundEvent e) {/*
        if ((Configuration.mobs.endermans.muteDeathSound && e.name.equals("entity.endermen.death"))
                || (Configuration.mobs.endermans.muteAmbientSound && e.name.equals("entity.endermen.ambient"))
                || (Configuration.mobs.endermans.muteHurtSound && e.name.equals("entity.endermen.hurt"))
                || (Configuration.mobs.endermans.muteScreamSound && e.name.equals("entity.endermen.scream"))
                || (Configuration.mobs.endermans.muteStareSound && e.name.equals("entity.endermen.stare"))
                || (Configuration.mobs.endermans.muteTeleportSound && e.name.equals("entity.endermen.teleport"))
                || (Configuration.blocks.stone.muteBreakSound && e.name.equals("block.stone.break"))
                || (Configuration.blocks.stone.muteFallSound && e.name.equals("block.stone.fall"))
                || (Configuration.blocks.stone.muteHitSound && e.name.equals("block.stone.hit"))
                || (Configuration.blocks.stone.mutePlaceSound && e.name.equals("block.stone.place"))
                || (Configuration.blocks.stone.muteStepSound && e.name.equals("block.stone.step"))
                || (Configuration.blocks.glass.muteBreakSound && e.name.equals("block.glass.break"))
                || (Configuration.blocks.glass.muteFallSound && e.name.equals("block.glass.fall"))
                || (Configuration.blocks.glass.muteHitSound && e.name.equals("block.glass.hit"))
                || (Configuration.blocks.glass.mutePlaceSound && e.name.equals("block.glass.place"))
                || (Configuration.blocks.glass.muteStepSound && e.name.equals("block.glass.step"))
                || (Configuration.blocks.grass.muteBreakSound && e.name.equals("block.grass.break"))
                || (Configuration.blocks.grass.muteFallSound && e.name.equals("block.grass.fall"))
                || (Configuration.blocks.grass.muteHitSound && e.name.equals("block.grass.hit"))
                || (Configuration.blocks.grass.mutePlaceSound && e.name.equals("block.grass.place"))
                || (Configuration.blocks.grass.muteStepSound && e.name.equals("block.grass.step"))
                || (Configuration.blocks.gravel.muteBreakSound && e.name.equals("block.gravel.break"))
                || (Configuration.blocks.gravel.muteFallSound && e.name.equals("block.gravel.fall"))
                || (Configuration.blocks.gravel.muteHitSound && e.name.equals("block.gravel.hit"))
                || (Configuration.blocks.gravel.mutePlaceSound && e.name.equals("block.gravel.place"))
                || (Configuration.blocks.gravel.muteStepSound && e.name.equals("block.gravel.step"))
                || (Configuration.blocks.portal.muteAmbientSound && e.name.equals("block.portal.ambient"))
                || (Configuration.blocks.portal.muteTravelSound && e.name.equals("block.portal.travel"))
                || (Configuration.blocks.portal.muteTriggerSound && e.name.equals("block.portal.trigger"))
                || (Configuration.blocks.water.muteWaterFlowSound && e.name.equals("block.water.ambient"))) e.result = null;*/
    }

    @SuppressWarnings("unused")
    public static class Configuration {
        public static Misc misc = new Misc();

        public static class Misc {
            public Property connectToServerAtStartup = config.get("Misc", "Connect to server at startup", false);

            public Property serverAddress = config.get("Misc", "Server Address", "mc.hypixel.net");

            public Property port = config.get("Misc", "Server Port", 25565);

            public GammaBright gammaBright = new GammaBright();
            public static class GammaBright {
                public Property gamma = config.get("Misc", "Gamma when using FullBright", 1500);
                public GammaBright() {
                    this.gamma.setMinValue(-15000);
                    this.gamma.setMaxValue(15000);
                    this.gamma.setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
                }
            }

            public Misc() {
                this.connectToServerAtStartup.comment = "Will connect to server at startup or not";
                this.serverAddress.comment = "Server address when connecting to server at startup.";
                this.serverAddress.setRequiresMcRestart(true);
                this.port.comment = "Server port when connecting to server at startup.";
                this.port.setRequiresMcRestart(true);
            }
        }

        /*
        public static Blocks blocks = new Blocks();

        public static class Blocks {
            @Comment("Same sounds as grass block.")
            public Block grass = new Block();

            @Comment("Same sounds as dirt.")
            public Block gravel = new Block();

            @Comment("Same sounds as other stones.")
            public Block stone = new Block();

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

            public Water water = new Water();
            public static class Water {
                public boolean muteWaterFlowSound = false;
            }

            @Comment("Break, place, and some sounds are managed at glass.")
            public Portal portal = new Portal();
            public static class Portal {
                @Comment("Plays when player at near the portal")
                public boolean muteAmbientSound = false;

                @Comment("Plays when standing in portal")
                public boolean muteTriggerSound = false;

                @Comment("Plays when loading dimension")
                public boolean muteTravelSound = false;
            }

            public Blocks() {
                //
            }
        }

        public static Mobs mobs = new Mobs();

        public static class Mobs {
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
        }*/
    }

    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent event) {
        if (toggleFB.isPressed()) {
            GameSettings settings = Minecraft.getMinecraft().gameSettings;
            if (Minecraft.getMinecraft().thePlayer != null) Minecraft.getMinecraft().thePlayer.playSound("random.orb", 100, 1);
            if (isFB) {
                settings.gammaSetting = 1F;
                isFB = false;
                if (Minecraft.getMinecraft().thePlayer != null)
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(translateChatColor("&eFull Bright is now &cDisabled&e!"));
            } else {
                settings.gammaSetting = (Configuration.misc.gammaBright.gamma.getInt()/100F);
                isFB = true;
                if (Minecraft.getMinecraft().thePlayer != null)
                    Minecraft.getMinecraft().thePlayer.sendChatMessage(translateChatColor("&eFull Bright is now &aEnabled&e!"));
            }
        }
    }

    @SubscribeEvent
    public static void onClientChat(ClientChatReceivedEvent e) {
        if (e.message.getFormattedText().startsWith(".")) {
            e.setCanceled(true);
            String[] args = e.message.getFormattedText().replaceFirst(".", "").split(" ");
            String command = args[0];
            if (!Utils.commands.containsKey(command)) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(translateChatColor("&cInvalid command, type .help to help."));
                return;
            }
            List<String> argsList = new ArrayList<>(Arrays.asList(args));
            argsList.remove(0);
            args = argsList.toArray(new String[0]);
            Utils.commands.get(command).execute(e.message.getFormattedText(), e.message.getUnformattedText(), args);
        }
    }
}
