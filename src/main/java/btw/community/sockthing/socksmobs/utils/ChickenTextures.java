package btw.community.sockthing.socksmobs.utils;

import net.minecraft.src.ResourceLocation;

public class ChickenTextures {

    private static final ResourceLocation BABY_CHICK_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/baby_chick.png");
    private static final ResourceLocation BABY_CHICK_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/baby_chick_famished.png");
    private static final ResourceLocation BABY_CHICK_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/baby_chick_starving.png");

    private static final ResourceLocation CHICKEN_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/chicken.png");
    private static final ResourceLocation CHICKEN_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/chicken_famished.png");
    private static final ResourceLocation CHICKEN_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/chicken_starving.png");

    private static final ResourceLocation ROOSTER_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/rooster.png");
    private static final ResourceLocation ROOSTER_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/rooster_famished.png");
    private static final ResourceLocation ROOSTER_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/rooster_starving.png");

    //HUNGER STATES
    public static final int NORMAL = 0;
    public static final int FAMISHED = 1;
    public static final int STARVING = 2;

    //SUBTYPES
    public static final int DEFAULT = 0;
    public static final int BROWN = 1;
    public static final int DARK = 2;

    public static ResourceLocation getChickenTexture(int subtype, int hungerLevel, int gender, boolean isChild ){

        if (!isChild){
            if (gender == MobUtils.FEMALE) {
                if (subtype == DEFAULT){
                    if (hungerLevel == NORMAL) return CHICKEN_TEXTURE;
                    else if (hungerLevel == FAMISHED) return CHICKEN_FAMISHED_TEXTURE;
                    else if (hungerLevel == STARVING) return CHICKEN_STARVING_TEXTURE;
                }
//                else if (subtype == BROWN){
//                    if (hungerLevel == NORMAL) return CHICKEN_BROWN_TEXTURE;
//                    else if (hungerLevel == FAMISHED) return CHICKEN_BROWN_FAMISHED_TEXTURE;
//                    else if (hungerLevel == STARVING) return CHICKEN_BROWN_STARVING_TEXTURE;
//                }
            }
            else {
                if (subtype == DEFAULT){
                    if (hungerLevel == NORMAL) return ROOSTER_TEXTURE;
                    else if (hungerLevel == FAMISHED) return ROOSTER_FAMISHED_TEXTURE;
                    else if (hungerLevel == STARVING) return ROOSTER_STARVING_TEXTURE;
                }
//                else if (subtype == BROWN){
//                    if (hungerLevel == NORMAL) return ROOSTER_BROWN_TEXTURE;
//                    else if (hungerLevel == FAMISHED) return ROOSTER_BROWN_FAMISHED_TEXTURE;
//                    else if (hungerLevel == STARVING) return ROOSTER_BROWN_STARVING_TEXTURE;
//                }
            }
        }
        else {
            if (subtype == DEFAULT){
                if (hungerLevel == NORMAL) return BABY_CHICK_TEXTURE;
                else if (hungerLevel == FAMISHED) return BABY_CHICK_FAMISHED_TEXTURE;
                else if (hungerLevel == STARVING) return BABY_CHICK_STARVING_TEXTURE;
            }
//            else if (subtype == BROWN){
//                if (hungerLevel == NORMAL) return BABY_CHICK_BROWN_TEXTURE;
//                else if (hungerLevel == FAMISHED) return BABY_CHICK_BROWN_FAMISHED_TEXTURE;
//                else if (hungerLevel == STARVING) return BABY_CHICK_BROWN_STARVING_TEXTURE;
//            }
        }

        System.err.println("Missing Chicken texture for: subtype=" + subtype + ", hunger=" + hungerLevel + ", gender=" + gender + ", child=" + isChild);
        System.err.println("Using default as fallback texture");
        return CHICKEN_TEXTURE; //fallback
    }
}
