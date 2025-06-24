package btw.community.sockthing.socksmobs;

import btw.AddonHandler;
import btw.BTWAddon;
import btw.community.sockthing.socksmobs.entities.*;
import btw.community.sockthing.socksmobs.items.SMItems;
import btw.util.sounds.AddonSoundRegistryEntry;
import net.minecraft.src.EntityList;

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

        EntityList.addMapping(EntityRooster.class, "Rooster", 700, 0x334a3d, 0xffc56e);
        EntityList.addMapping(EntityBrownMooshroom.class, "BrownMooshroom", 705, 0x835e46, 0xcc9978);
        EntityList.addMapping(EntityMilkaMooshroom.class, "MilkaMooshroom", 706, 0xd19985, 0xb8b8b8);
        EntityList.addMapping(EntityColdCow.class, "ColdCow", 710, 0x884a30, 0xc77a48);
        EntityList.addMapping(EntityWarmCow.class, "WarmCow", 711, 0x7b311f, 0xd19985);
        EntityList.addMapping(EntityWhiteCow.class, "WhiteCow", 712, 0xf3eee8, 0xd19985);

    }
}