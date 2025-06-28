package btw.community.sockthing.socksmobs.utils;

import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityPig;
import net.minecraft.src.ResourceLocation;

public class ChickenTextures {

    public static final ResourceLocation BABY_CHICK_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/baby_chick.png");
    public static final ResourceLocation BABY_CHICK_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/baby_chick_famished.png");
    public static final ResourceLocation BABY_CHICK_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/baby_chick_starving.png");

    private static final ResourceLocation BABY_CHICK_DARK_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/baby_chick_dark.png");
    private static final ResourceLocation BABY_CHICK_DARK_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/baby_chick_dark_famished.png");
    private static final ResourceLocation BABY_CHICK_DARK_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/baby_chick_dark_starving.png");

    public static final ResourceLocation CHICKEN_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/chicken.png");
    public static final ResourceLocation CHICKEN_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/chicken_famished.png");
    public static final ResourceLocation CHICKEN_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/chicken_starving.png");

    private static final ResourceLocation CHICKEN_DARK_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/chicken_dark.png");
    private static final ResourceLocation CHICKEN_DARK_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/chicken_dark_famished.png");
    private static final ResourceLocation CHICKEN_DARK_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/chicken_dark_starving.png");

    public static final ResourceLocation ROOSTER_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/rooster.png");
    public static final ResourceLocation ROOSTER_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/rooster_famished.png");
    public static final ResourceLocation ROOSTER_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/rooster_starving.png");

    private static final ResourceLocation ROOSTER_DARK_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/rooster_dark.png");
    private static final ResourceLocation ROOSTER_DARK_FAMISHED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/rooster_dark_famished.png");
    private static final ResourceLocation ROOSTER_DARK_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/chicken/rooster_dark_starving.png");

    public static ResourceLocation getBabyChickTexture(EntityChicken chicken){
        int subtype = ((EntityAnimalInterface) chicken).getType();
        int hungerLevel = chicken.getHungerLevel();

        if (subtype == ChickenUtils.DEFAULT){
            if (hungerLevel == MobUtils.NORMAL) return BABY_CHICK_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return BABY_CHICK_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return BABY_CHICK_STARVING_TEXTURE;
        }
        else if (subtype == ChickenUtils.DARK){
            if (hungerLevel == MobUtils.NORMAL) return BABY_CHICK_DARK_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return BABY_CHICK_DARK_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return BABY_CHICK_DARK_STARVING_TEXTURE;
        }

        return MobUtils.NULL_TEXTURE;
    }

    public static ResourceLocation getChickenTexture(EntityChicken chicken){
        int subtype = ((EntityAnimalInterface) chicken).getType();
        int hungerLevel = chicken.getHungerLevel();

        if (subtype == ChickenUtils.DEFAULT){
            if (hungerLevel == MobUtils.NORMAL) return CHICKEN_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return CHICKEN_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return CHICKEN_STARVING_TEXTURE;
        }
        else if (subtype == ChickenUtils.DARK){
            if (hungerLevel == MobUtils.NORMAL) return CHICKEN_DARK_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return CHICKEN_DARK_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return CHICKEN_DARK_STARVING_TEXTURE;
        }

        return MobUtils.NULL_TEXTURE;
    }

    public static ResourceLocation getRoosterTexture(EntityChicken chicken) {
        int subtype = ((EntityAnimalInterface) chicken).getType();
        int hungerLevel = chicken.getHungerLevel();

        if (subtype == ChickenUtils.DEFAULT){
            if (hungerLevel == MobUtils.NORMAL) return ROOSTER_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return ROOSTER_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return ROOSTER_STARVING_TEXTURE;
        }
        else if (subtype == ChickenUtils.DARK){
            if (hungerLevel == MobUtils.NORMAL) return ROOSTER_DARK_TEXTURE;
            else if (hungerLevel == MobUtils.FAMISHED) return ROOSTER_DARK_FAMISHED_TEXTURE;
            else if (hungerLevel == MobUtils.STARVING) return ROOSTER_DARK_STARVING_TEXTURE;
        }

        return MobUtils.NULL_TEXTURE;
    }
}
