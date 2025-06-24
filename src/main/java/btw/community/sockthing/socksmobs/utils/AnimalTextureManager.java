package btw.community.sockthing.socksmobs.utils;

import btw.community.sockthing.socksmobs.enums.*;
import net.minecraft.src.ResourceLocation;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class AnimalTextureManager {

    private static final Map<Class<?>, Map<? extends Enum<?>, AnimalTextures>> ANIMAL_TEXTURES = new HashMap<>();
    private static final EnumMap<CowType, AnimalTextures> COW_TEXTURES = new EnumMap<>(CowType.class);
    private static final EnumMap<MooshroomType, AnimalTextures> MOOSHROOM_TEXTURES = new EnumMap<>(MooshroomType.class);
    private static final EnumMap<WolfType, WolfTextures> WOLF_TEXTURES = new EnumMap<>(WolfType.class);

    static {
        for (CowType type : CowType.values()) {
            COW_TEXTURES.put(type, new AnimalTextures("cow", type.name().toLowerCase()));
        }

        for (MooshroomType type : MooshroomType.values()) {
            MOOSHROOM_TEXTURES.put(type, new AnimalTextures("mooshroom", type.name().toLowerCase()));
        }

        for (WolfType type : WolfType.values()) {
            WOLF_TEXTURES.put(type, new WolfTextures(type.name().toLowerCase()));
        }

        ANIMAL_TEXTURES.put(CowType.class, COW_TEXTURES);
        ANIMAL_TEXTURES.put(MooshroomType.class, MOOSHROOM_TEXTURES);
    }


    public static <T extends Enum<T>> ResourceLocation getAnimalTexture(Class<T> typeClass, int subtype, AnimalState state) {
        T[] types = typeClass.getEnumConstants();
        Map<? extends Enum<?>, AnimalTextures> texturesMap = ANIMAL_TEXTURES.get(typeClass);
        AnimalTextures textures = texturesMap.get(types[subtype]);

        return switch (state) {
            case NORMAL -> textures.normal;
            case FAMISHED -> textures.famished;
            case STARVING -> textures.starving;
            case BREEDING_HARNESS -> textures.breeding_harness;
            case BREEDING_HARNESS_FAMISHED -> textures.breeding_harness_famished;
            case BREEDING_HARNESS_STARVING -> textures.breeding_harness_starving;
        };
    }

    public static ResourceLocation getWolfTexture(int typeId, WolfState state) {
        WolfType[] types = WolfType.values();
        WolfTextures textures = WOLF_TEXTURES.get(types[typeId]);

        return switch (state) {
            case WILD -> textures.wild;
            case WILD_STARVING -> textures.wildStarving;
            case TAME -> textures.tame;
            case TAME_STARVING -> textures.tameStarving;
            case ANGRY -> textures.angry;
        };
    }

}
