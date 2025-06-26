package btw.community.sockthing.socksmobs.utils;

import net.minecraft.src.ResourceLocation;

public class PigTextures {

    //SADDLE
    private static final ResourceLocation PIG_SADDLE_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_saddle.png");

    //HARNESS
    private static final ResourceLocation PIG_BREEDING_HARNESS_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_breeding_harness.png");
    private static final ResourceLocation PIG_BREEDING_HARNESS_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_breeding_harness_famished.png");
    private static final ResourceLocation PIG_BREEDING_HARNESS_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_breeding_harness_starving.png");

    //DEFAULT
    private static final ResourceLocation PIG_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig.png");
    private static final ResourceLocation PIG_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_famished.png");
    private static final ResourceLocation PIG_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_starving.png");

    //MUDDY
    private static final ResourceLocation PIG_MUDDY_DRY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_muddy_dry.png");
    private static final ResourceLocation PIG_MUDDY_DRY_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_muddy_dry_famished.png");
    private static final ResourceLocation PIG_MUDDY_DRY_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_muddy_dry_starving.png");

    private static final ResourceLocation PIG_MUDDY_WET_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_muddy_wet.png");
    private static final ResourceLocation PIG_MUDDY_WET_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_muddy_wet_famished.png");
    private static final ResourceLocation PIG_MUDDY_WET_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_muddy_wet_starving.png");

    //HUNGER STATES
    public static final int NORMAL = 0;
    public static final int FAMISHED = 1;
    public static final int STARVING = 2;

    //EXTRA STATES
    public static final int DRY = 0;
    public static final int WET = 1;

    //SUBTYPES
    public static final int DEFAULT = 0;
    public static final int COLD = 1;
    public static final int WARM = 2;
    public static final int MUDDY = 3;
    public static final int MOTTLED = 4;
    public static final int SPOTTED = 5;

    public static ResourceLocation getPigTexture(int subtype, int hungerLevel, int extraState){

        if (subtype == DEFAULT){
            if (hungerLevel == NORMAL) return PIG_TEXTURE;
            else if (hungerLevel == FAMISHED) return PIG_FAMISHED_TEXTURE;
            else if (hungerLevel == STARVING) return PIG_STARVING_TEXTURE;
        }
        else if (subtype == MUDDY){
            if (extraState == DRY){
                if (hungerLevel == NORMAL) return PIG_MUDDY_DRY_TEXTURE;
                else if (hungerLevel == FAMISHED) return PIG_MUDDY_DRY_FAMISHED_TEXTURE;
                else if (hungerLevel == STARVING) return PIG_MUDDY_DRY_STARVING_TEXTURE;
            }
            else if (extraState == WET) {
                if (hungerLevel == NORMAL) return PIG_MUDDY_WET_TEXTURE;
                else if (hungerLevel == FAMISHED) return PIG_MUDDY_WET_FAMISHED_TEXTURE;
                else if (hungerLevel == STARVING) return PIG_MUDDY_WET_STARVING_TEXTURE;
            }
        }

        return PIG_TEXTURE;
    }

    public static ResourceLocation getOverlayTexture(int hungerLevel, boolean wearingBreedingHarness, boolean isSaddled){

        //Breeding Harness Texture should override Saddle if both are the case
        if (wearingBreedingHarness) {
            if (hungerLevel == NORMAL) return PIG_BREEDING_HARNESS_TEXTURE;
            else if (hungerLevel == FAMISHED) return PIG_BREEDING_HARNESS_FAMISHED_TEXTURE;
            else if (hungerLevel == STARVING) return PIG_BREEDING_HARNESS_STARVING_TEXTURE;
        }
        else if (isSaddled) return PIG_SADDLE_TEXTURE;

        return null;
    }
}
