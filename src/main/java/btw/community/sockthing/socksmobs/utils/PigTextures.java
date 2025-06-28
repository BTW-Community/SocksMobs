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

    //PALE
    private static final ResourceLocation PIG_PALE_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_pale.png");
    private static final ResourceLocation PIG_PALE_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_pale_famished.png");
    private static final ResourceLocation PIG_PALE_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/pig/pig_pale_starving.png");

    public static ResourceLocation getPigTexture(EntityPig pig){
        int subtype = ((EntityAnimalInterface) pig).getType();
        int hungerLevel = pig.getHungerLevel();

        if (subtype == PigUtils.DEFAULT){
            if (hungerLevel == MobUtils.NORMAL) return PIG_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return PIG_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return PIG_STARVING_TEXTURE;
        }
        else if (subtype == PigUtils.COLD){
            if (hungerLevel == MobUtils.NORMAL) return PIG_COLD_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return PIG_COLD_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return PIG_COLD_STARVING_TEXTURE;
        }
        else if (subtype == PigUtils.WARM){
            if (hungerLevel == MobUtils.NORMAL) return PIG_WARM_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return PIG_WARM_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return PIG_WARM_STARVING_TEXTURE;
        }
        else if (subtype == PigUtils.MUDDY){
            int extraState = ((EntityAnimalInterface) pig).getExtraState();
            if (extraState == PigUtils.DRY) {
                if (hungerLevel == MobUtils.NORMAL) return PIG_MUDDY_DRY_TEXTURE;
                else if (hungerLevel == MobUtils.FAMISHED) return PIG_MUDDY_DRY_FAMISHED_TEXTURE;
                else if (hungerLevel == MobUtils.STARVING) return PIG_MUDDY_DRY_STARVING_TEXTURE;
            } else if (extraState == PigUtils.WET) {
                if (hungerLevel == MobUtils.NORMAL) return PIG_MUDDY_WET_TEXTURE;
                else if (hungerLevel == MobUtils.FAMISHED) return PIG_MUDDY_WET_FAMISHED_TEXTURE;
                else if (hungerLevel == MobUtils.STARVING) return PIG_MUDDY_WET_STARVING_TEXTURE;
            }
        }
        else if (subtype == PigUtils.MOTTLED){
            if (hungerLevel == MobUtils.NORMAL) return PIG_MOTTLED_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return PIG_MOTTLED_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return PIG_MOTTLED_STARVING_TEXTURE;
        }
        else if (subtype == PigUtils.SPOTTED){
            if (hungerLevel == MobUtils.NORMAL) return PIG_SPOTTED_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return PIG_SPOTTED_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return PIG_SPOTTED_STARVING_TEXTURE;
        }
        else if (subtype == PigUtils.PALE){
            if (hungerLevel == MobUtils.NORMAL) return PIG_PALE_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return PIG_PALE_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return PIG_PALE_STARVING_TEXTURE;
        }

        return MobUtils.NULL_TEXTURE;
    }

    public static ResourceLocation getOverlayTexture(int hungerLevel, boolean wearingBreedingHarness, boolean isSaddled){

        //Breeding Harness Texture should override Saddle if both are the case
        if (wearingBreedingHarness) {
            if (hungerLevel == MobUtils.NORMAL) return PIG_BREEDING_HARNESS_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return PIG_BREEDING_HARNESS_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return PIG_BREEDING_HARNESS_STARVING_TEXTURE;
        }
        else if (isSaddled) return PIG_SADDLE_TEXTURE;

        return null;
    }
}
