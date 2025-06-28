package btw.community.sockthing.socksmobs;

import btw.AddonHandler;
import btw.BTWAddon;
import btw.community.sockthing.socksmobs.entities.chicken.EntityDefaultChicken;
import btw.community.sockthing.socksmobs.entities.chicken.EntityRooster;
import btw.community.sockthing.socksmobs.entities.pig.*;
import btw.community.sockthing.socksmobs.items.SMItems;
import btw.util.sounds.AddonSoundRegistryEntry;
import net.minecraft.src.*;

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
        EntityList.entityEggs.remove(93); //Vanilla Chicken Spawn Egg
        EntityList.addMapping(EntityDefaultChicken.class, "socksmobs.chicken", 700, 0xA1A1A1, 0xFF0000);
        EntityList.addMapping(EntityRooster.class, "socksmobs.rooster", 701, 0xf3ac60, 0xFF0000);

        EntityList.entityEggs.remove(90); //Vanilla Pig Spawn Egg
        EntityList.addMapping(EntityDefaultPig.class, "socksmobs.pig.default", 704, 15771042, 14377823);
        EntityList.addMapping(EntityColdPig.class, "socksmobs.pig.cold", 705, 0xDFB886, 0xEADEBF);
        EntityList.addMapping(EntityWarmPig.class, "socksmobs.pig.warm", 706, 0xB35A34, 0xCE7439);
        EntityList.addMapping(EntityMuddyPig.class, "socksmobs.pig.muddy", 707, 15771042, 0x956B53);
        EntityList.addMapping(EntityMottledPig.class, "socksmobs.pig.mottled", 708, 0x50403C, 0x9C817C);
        EntityList.addMapping(EntitySpottedPig.class, "socksmobs.pig.spotted", 709, 0xE5B5B3, 0x746666);
        EntityList.addMapping(EntityPalePig.class, "socksmobs.pig.pale", 709, 0xD3A0A0, 0xDFC2C1);
    }
}