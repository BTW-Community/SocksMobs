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
        EntityList.addMapping(EntityRooster.class, "Rooster", 700, 0xA1A1A1, 0xFF0000);
        
        EntityList.addMapping(EntityColdPig.class, "ColdPig", 705, 0xA1A1A1, 0xFF0000);
        EntityList.addMapping(EntityWarmPig.class, "WarmPig", 706, 0xA1A1A1, 0xFF0000);
        EntityList.addMapping(EntityMuddyPig.class, "MuddyPig", 707, 0xA1A1A1, 0xFF0000);
        EntityList.addMapping(EntityMottledPig.class, "MottledPig", 708, 0x50403c, 0x9c817c);
        EntityList.addMapping(EntitySpottedPig.class, "SpottedPig", 709, 0xE4BCB9, 0x403939);
    }
}