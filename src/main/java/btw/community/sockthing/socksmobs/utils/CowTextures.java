package btw.community.sockthing.socksmobs.utils;

import net.minecraft.src.ResourceLocation;

public class CowTextures {

    //UDDER
    private static final ResourceLocation COW_UDDER_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_udder.png");
    private static final ResourceLocation COW_UDDER_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_udder_famished.png");
    private static final ResourceLocation COW_UDDER_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_udder_starving.png");

    //HARNESS
    private static final ResourceLocation COW_BREEDING_HARNESS_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_breeding_harness.png");
    private static final ResourceLocation COW_BREEDING_HARNESS_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_breeding_harness_famished.png");
    private static final ResourceLocation COW_BREEDING_HARNESS_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_breeding_harness_starving.png");

    //UDDER + HARNESS
    private static final ResourceLocation COW_UDDER_BREEDING_HARNESS_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_udder_breeding_harness.png");
    private static final ResourceLocation COW_UDDER_BREEDING_HARNESS_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_udder_breeding_harness_famished.png");
    private static final ResourceLocation COW_UDDER_BREEDING_HARNESS_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_udder_breeding_harness_starving.png");

    //DEFAULT
    private static final ResourceLocation COW_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow.png");
    private static final ResourceLocation COW_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_famished.png");
    private static final ResourceLocation COW_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_starving.png");

    //WHITE
    private static final ResourceLocation COW_WHITE_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_white.png");
    private static final ResourceLocation COW_WHITE_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_white_famished.png");
    private static final ResourceLocation COW_WHITE_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/cow/cow_white_starving.png");

    //HUNGER STATES
    public static final int NORMAL = 0;
    public static final int FAMISHED = 1;
    public static final int STARVING = 2;

    //SUBTYPES
    public static final int DEFAULT = 0;
    public static final int WHITE = 1;

    public static ResourceLocation getCowTexture(int subtype, int hungerLevel){

        switch (subtype){
            default:
            case DEFAULT:
                if (hungerLevel == FAMISHED) return COW_FAMISHED_TEXTURE;
                else if (hungerLevel == STARVING) return COW_STARVING_TEXTURE;

                return COW_TEXTURE;
            case WHITE:
                if (hungerLevel == FAMISHED) return COW_WHITE_FAMISHED_TEXTURE;
                else if (hungerLevel == STARVING) return COW_WHITE_STARVING_TEXTURE;

                return COW_WHITE_TEXTURE;
        }
    }

    public static ResourceLocation getOverlayTexture(int hungerLevel, boolean gotMilk, boolean wearingBreedingHarness){

        if (gotMilk && wearingBreedingHarness) {
            if (hungerLevel == FAMISHED) return COW_UDDER_BREEDING_HARNESS_FAMISHED_TEXTURE;
            else if (hungerLevel == STARVING) return COW_UDDER_BREEDING_HARNESS_STARVING_TEXTURE;
            return COW_UDDER_BREEDING_HARNESS_TEXTURE;
        }
        else if (gotMilk) {
            if (hungerLevel == FAMISHED) return COW_UDDER_FAMISHED_TEXTURE;
            else if (hungerLevel == STARVING) return COW_UDDER_STARVING_TEXTURE;
            return COW_UDDER_TEXTURE;
        }
        else if (wearingBreedingHarness) {
            if (hungerLevel == FAMISHED) return COW_BREEDING_HARNESS_FAMISHED_TEXTURE;
            else if (hungerLevel == STARVING) return COW_BREEDING_HARNESS_STARVING_TEXTURE;
            return COW_BREEDING_HARNESS_TEXTURE;
        }
        else return null;

    }
}
