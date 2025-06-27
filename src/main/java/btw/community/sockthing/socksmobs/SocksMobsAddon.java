package btw.community.sockthing.socksmobs;

import btw.AddonHandler;
import btw.BTWAddon;
import btw.community.sockthing.socksmobs.entities.*;
import btw.community.sockthing.socksmobs.items.SMItems;
import btw.util.sounds.AddonSoundRegistryEntry;
import btw.util.sounds.BTWSoundManager;
import com.prupe.mcpatcher.cc.ColorizeItem;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;

import java.awt.*;

public class SocksMobsAddon extends BTWAddon {
    private static SocksMobsAddon instance;

    public static final AddonSoundRegistryEntry ROOSTER_CROWING = new AddonSoundRegistryEntry("socksmobs:entity.chicken.rooster", 1);
    public static final AddonSoundRegistryEntry BABY_CHICK_CHIRP = new AddonSoundRegistryEntry("socksmobs:entity.chicken.babychick", 1);
    public static final AddonSoundRegistryEntry BABY_CHICK_HURT = new AddonSoundRegistryEntry("socksmobs:entity.chicken.babychick_hurt", 1);

    public SocksMobsAddon() {
        super();
    }

    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");

        SMItems.initItems();
        initSpawnerEggMappings();
    }

    private void initSpawnerEggMappings() {
        EntityList.addMapping(EntityRooster.class, "socksmobs.rooster", 700, 0xf3ac60, 0xFF0000);

        EntityList.entityEggs.remove(90); //Vanilla Pig Spawn Egg
        EntityList.addMapping(EntityDefaultPig.class, "socksmobs.pig.default", 704, 15771042, 14377823);
        EntityList.addMapping(EntityColdPig.class, "socksmobs.pig.cold", 705, 0xDFB886, 0xEADEBF);
        EntityList.addMapping(EntityWarmPig.class, "socksmobs.pig.warm", 706, 0xB35A34, 0xCE7439);
        EntityList.addMapping(EntityMuddyPig.class, "socksmobs.pig.muddy", 707, 15771042, 0x956B53);
        EntityList.addMapping(EntityMottledPig.class, "socksmobs.pig.mottled", 708, 0x50403C, 0x9C817C);
        EntityList.addMapping(EntitySpottedPig.class, "socksmobs.pig.spotted", 709, 0xE5B5B3, 0x746666);
    }
}