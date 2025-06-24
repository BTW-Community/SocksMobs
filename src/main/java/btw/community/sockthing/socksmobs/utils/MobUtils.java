package btw.community.sockthing.socksmobs.utils;

import net.minecraft.src.BiomeGenBase;

public class MobUtils {
    // CHICKEN
    public static final int DATA_TYPE_ID = 31;
    public static final int DATA_GENDER_ID = 30;
    public static final int DATA_PREGNANT_ID = 29;
    public static final int DATA_AMOUNT_ID = 28;
    public static final int DATA_DELAY_ID = 27;

    public static final int SPAWN_TYPE_CHANCE = 3;
    public static final int SPAWN_GENDER_CHANCE = 4;
    public static final int EGG_SPAWN_GENDER_CHANCE = 2; //when spawning a baby chick from egg

    public static final int MAX_BREED_AMOUNT = 4;
    public static final int ROOSTER_CROW_CHANCE = 3;

    public static final int FEMALE = 0;
    public static final int MALE = 1;

    public static final int MIN_DELAY_TIME = 600;
    public static final int MAX_DELAY_TIME = 1200;

    public static final int NORMAL = 1;
    public static final int COLD = 1;
    public static final int WARM = 2;

    public static final int MILKA = 1;

    public static boolean isWarmBiome(BiomeGenBase biome) {
        if (biome == BiomeGenBase.jungle) return true;
        if (biome == BiomeGenBase.jungleHills) return true;
        if (biome == BiomeGenBase.desertHills) return true;
        return biome == BiomeGenBase.desert;
    }

    public static boolean isColdBiome(BiomeGenBase biome) {
        if (biome == BiomeGenBase.iceMountains) return true;
        if (biome == BiomeGenBase.icePlains) return true;
        if (biome == BiomeGenBase.frozenOcean) return true;
        if (biome == BiomeGenBase.frozenRiver) return true;
        if (biome == BiomeGenBase.taigaHills) return true;
        return biome == BiomeGenBase.taiga;
    }

}
