package btw.community.sockthing.socksmobs.utils;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import net.minecraft.src.EntityPig;
import net.minecraft.src.ResourceLocation;

public class PigTextures {

    //SADDLE
    public static final ResourceLocation PIG_SADDLE_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_saddle.png");

    //HARNESS
    public static final ResourceLocation PIG_BREEDING_HARNESS_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_breeding_harness.png");
    public static final ResourceLocation PIG_BREEDING_HARNESS_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_breeding_harness_famished.png");
    public static final ResourceLocation PIG_BREEDING_HARNESS_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_breeding_harness_starving.png");

    //DEFAULT
    public static final ResourceLocation PIG_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig.png");
    public static final ResourceLocation PIG_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_famished.png");
    public static final ResourceLocation PIG_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_starving.png");

    //COLD
    public static final ResourceLocation PIG_COLD_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_cold.png");
    public static final ResourceLocation PIG_COLD_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_cold_famished.png");
    public static final ResourceLocation PIG_COLD_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_cold_starving.png");

    //WARM
    public static final ResourceLocation PIG_WARM_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_warm.png");
    public static final ResourceLocation PIG_WARM_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_warm_famished.png");
    public static final ResourceLocation PIG_WARM_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_warm_starving.png");

    //MUDDY
    public static final ResourceLocation PIG_MUDDY_DRY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_muddy_dry.png");
    public static final ResourceLocation PIG_MUDDY_DRY_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_muddy_dry_famished.png");
    public static final ResourceLocation PIG_MUDDY_DRY_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_muddy_dry_starving.png");

    public static final ResourceLocation PIG_MUDDY_WET_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_muddy_wet.png");
    public static final ResourceLocation PIG_MUDDY_WET_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_muddy_wet_famished.png");
    public static final ResourceLocation PIG_MUDDY_WET_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_muddy_wet_starving.png");

    //MOTTLED
    public static final ResourceLocation PIG_MOTTLED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_mottled.png");
    public static final ResourceLocation PIG_MOTTLED_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_mottled_famished.png");
    public static final ResourceLocation PIG_MOTTLED_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_mottled_starving.png");

    //SPOTTED
    public static final ResourceLocation PIG_SPOTTED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_spotted.png");
    public static final ResourceLocation PIG_SPOTTED_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_spotted_famished.png");
    public static final ResourceLocation PIG_SPOTTED_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_spotted_starving.png");

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

    public static final int TOTAL_TYPES = 6;

    public static ResourceLocation getPigTexture(EntityPig pig){
        int subtype = ((EntityAnimalInterface) pig).getType();
        int hungerLevel = pig.getHungerLevel();

        if (subtype == DEFAULT){
            if (hungerLevel == NORMAL) return PIG_TEXTURE;
            else if (hungerLevel == FAMISHED) return PIG_FAMISHED_TEXTURE;
            else if (hungerLevel == STARVING) return PIG_STARVING_TEXTURE;
        }
        else if (subtype == COLD){
            if (hungerLevel == NORMAL) return PIG_COLD_TEXTURE;
            else if (hungerLevel == FAMISHED) return PIG_COLD_FAMISHED_TEXTURE;
            else if (hungerLevel == STARVING) return PIG_COLD_STARVING_TEXTURE;
        }
        else if (subtype == WARM){
            if (hungerLevel == NORMAL) return PIG_WARM_TEXTURE;
            else if (hungerLevel == FAMISHED) return PIG_WARM_FAMISHED_TEXTURE;
            else if (hungerLevel == STARVING) return PIG_WARM_STARVING_TEXTURE;
        }
        else if (subtype == MUDDY){
            int extraState = ((EntityAnimalInterface) pig).getExtraState();
            if (extraState == DRY) {
                if (hungerLevel == NORMAL) return PIG_MUDDY_DRY_TEXTURE;
                else if (hungerLevel == FAMISHED) return PIG_MUDDY_DRY_FAMISHED_TEXTURE;
                else if (hungerLevel == STARVING) return PIG_MUDDY_DRY_STARVING_TEXTURE;
            } else if (extraState == WET) {
                if (hungerLevel == NORMAL) return PIG_MUDDY_WET_TEXTURE;
                else if (hungerLevel == FAMISHED) return PIG_MUDDY_WET_FAMISHED_TEXTURE;
                else if (hungerLevel == STARVING) return PIG_MUDDY_WET_STARVING_TEXTURE;
            }
        }
        else if (subtype == MOTTLED){
            if (hungerLevel == NORMAL) return PIG_MOTTLED_TEXTURE;
            else if (hungerLevel == FAMISHED) return PIG_MOTTLED_FAMISHED_TEXTURE;
            else if (hungerLevel == STARVING) return PIG_MOTTLED_STARVING_TEXTURE;

        }
        else if (subtype == SPOTTED){
            if (hungerLevel == NORMAL) return PIG_SPOTTED_TEXTURE;
            else if (hungerLevel == FAMISHED) return PIG_SPOTTED_FAMISHED_TEXTURE;
            else if (hungerLevel == STARVING) return PIG_SPOTTED_STARVING_TEXTURE;

        }

        return MobUtils.NULL_TEXTURE;
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
