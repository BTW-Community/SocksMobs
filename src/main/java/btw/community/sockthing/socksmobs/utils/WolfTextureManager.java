package btw.community.sockthing.socksmobs.utils;

import btw.community.sockthing.socksmobs.enums.EnumWolfState;
import btw.community.sockthing.socksmobs.enums.EnumWolfType;
import net.minecraft.src.ResourceLocation;

import java.util.EnumMap;

public class WolfTextureManager {
    private static final EnumMap<EnumWolfType, WolfTextures> WOLF_TEXTURES = new EnumMap<>(EnumWolfType.class);

    static {
        for (EnumWolfType type : EnumWolfType.values()) {
            WOLF_TEXTURES.put(type, new WolfTextures(type.name().toLowerCase()));
        }
    }

    public static ResourceLocation getTexture(int typeId, EnumWolfState state) {
        EnumWolfType type = EnumWolfType.fromId(typeId);
        WolfTextures textures = WOLF_TEXTURES.get(type);

        return switch (state) {
            case WILD -> textures.wild;
            case WILD_STARVING -> textures.wildStarving;
            case TAME -> textures.tame;
            case TAME_STARVING -> textures.tameStarving;
            case ANGRY -> textures.angry;
        };
    }
}
