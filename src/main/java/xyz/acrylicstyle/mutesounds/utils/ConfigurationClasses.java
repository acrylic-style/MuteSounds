package xyz.acrylicstyle.mutesounds.utils;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.*;

public class ConfigurationClasses {
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

            @LangKey("config.mutesounds.misc.overlays.fpsOverlay")
            public boolean fpsOverlay = false;
        }

        @LangKey("config.mutesounds.misc.gammaBright._name")
        public GammaBright gammaBright = new GammaBright();
        public static class GammaBright {
            @Config.RangeInt(min = -15000, max = 15000)
            @LangKey("config.mutesounds.misc.gammaBright.gamma")
            public double gamma = 15000;
        }
    }

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

    public static class Entity {
        @LangKey("config.mutesounds.entity.generic")
        @Comment("entity.generic.*")
        public Generic generic = new Generic();

        @LangKey("config.mutesounds.entity.player")
        @Comment("entity.player.*")
        public Player player = new Player();

        public static class Generic {
            @Comment("entity.generic.big_fall")
            public boolean muteBigFall = false;

            @Comment("entity.generic.burn")
            public boolean muteBurn = false;

            @Comment("entity.generic.death")
            public boolean muteDeath = false;

            @Comment("entity.generic.drink")
            public boolean muteDrink = false;

            @Comment("entity.generic.eat")
            public boolean muteEat = false;

            @Comment("entity.generic.explode")
            public boolean muteExplode = false;

            @Comment("entity.generic.extinguish_fire")
            public boolean muteExtinguishFire = false;

            @Comment("entity.generic.hurt")
            public boolean muteHurt = false;

            @Comment("entity.generic.small_fall")
            public boolean muteSmallFall = false;

            @Comment("entity.generic.splash")
            public boolean muteSplash = false;

            @Comment("entity.generic.swim")
            public boolean muteSwim = false;
        }

        public static class Player {
            // =========================
            // entity.player
            // =========================
            @Comment("entity.player.big_fall")
            public boolean muteBigFall = false;

            @Comment("entity.player.breath")
            public boolean muteBreath = false;

            @Comment("entity.player.burp")
            public boolean muteBurp = false;

            @Comment("entity.player.death")
            public boolean muteDeath = false;

            @Comment("entity.player.hurt")
            public boolean muteHurt = false;

            @Comment("entity.player.hurt_drown")
            public boolean muteHurtDrown = false;

            @Comment("entity.player.hurt_on_fire")
            public boolean muteHurtOnFire = false;

            @Comment("entity.player.levelup")
            public boolean muteLevelup = false;

            @Comment("entity.player.small_fire")
            public boolean muteSmallFall = false;

            @Comment("entity.player.splash")
            public boolean muteSplash = false;

            @Comment("entity.player.swim")
            public boolean muteSwim = false;

            // =========================
            // entity.player.attack
            // =========================
            @Comment("entity.player.attack.crit")
            public boolean muteAttack_crit = false;

            @Comment("entity.player.attack.knockback")
            public boolean muteAttack_knockback = false;

            @Comment("entity.player.attack.nodamage")
            public boolean muteAttack_nodamage = false;

            @Comment("entity.player.attack.strong")
            public boolean muteAttack_strong = false;

            @Comment("entity.player.attack.sweep")
            public boolean muteAttack_sweep = false;

            @Comment("entity.player.attack.weak")
            public boolean muteAttack_weak = false;
        }
    }
}
