package btw.community.sockthing.socksmobs.utils;

import net.minecraft.src.ResourceLocation;

public class MobUtils {
    // CHICKEN
    public static final int DATA_TYPE_ID = 31;
    public static final int DATA_GENDER_ID = 30;
    public static final int DATA_PREGNANT_ID = 29;
    public static final int DATA_AMOUNT_ID = 28;
    public static final int DATA_DELAY_ID = 27;
    public static final int DATA_EXTRA_STATE_ID = 26;

    //GENDER
    public static final int FEMALE = 0;
    public static final int MALE = 1;

    public static final int CHICKEN_SPAWN_TYPE_CHANCE = 3;
    public static final int CHICKEN_SPAWN_GENDER_CHANCE = 4;
    public static final int CHICKEN_EGG_SPAWN_GENDER_CHANCE = 2; //when spawning a baby chick from egg

    public static final int ROOSTER_MAX_BREED_AMOUNT = 4;
    public static final int ROOSTER_CROW_CHANCE = 3;

    public static final int ROOSTER_MIN_DELAY_TIME = 600;
    public static final int ROOSTER_MAX_DELAY_TIME = 1200;


    public static final ResourceLocation NULL_TEXTURE = new ResourceLocation("socksmobs:textures/null.png");

}
