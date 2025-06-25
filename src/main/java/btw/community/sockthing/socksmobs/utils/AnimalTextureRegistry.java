package btw.community.sockthing.socksmobs.utils;

import btw.community.sockthing.socksmobs.enums.*;
import net.minecraft.src.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.Sys;

import java.util.HashMap;
import java.util.Map;

public class AnimalTextureRegistry {
    private static final Map<AnimalTextureKey, ResourceLocation> TEXTURE_CACHE = new HashMap<>();
    public record AnimalTextureKey(String animal, String animalName, Enum<?> subtype, Enum<?> state) {}

    static {
        for (WolfType subtype : WolfType.values()) {
            for (WolfState state : WolfState.values()) {
                AnimalTextureKey key = setAnimalTextureKey("wolf", subtype, state);
                TEXTURE_CACHE.put(key, buildTexture("wolf", subtype, state));
            }
        }

        for (ChickenType subtype : ChickenType.values()) {
            for (AnimalState state : AnimalState.values()) {
                AnimalTextureKey key1 = setAnimalTextureKey("chicken", subtype, state);
                AnimalTextureKey key2 = setAnimalTextureKey("chicken", "rooster", subtype, state);
                AnimalTextureKey key3 = setAnimalTextureKey("chicken", "baby_chick", subtype, state);
                TEXTURE_CACHE.put(key1, buildTexture("chicken", subtype, state));
                TEXTURE_CACHE.put(key2, buildTexture("chicken", "rooster", subtype, state));
                TEXTURE_CACHE.put(key3, buildTexture("chicken", "baby_chick", subtype, state));
            }
        }

        for (PigType subtype : PigType.values()) {
            for (AnimalState state : AnimalState.values()) {
                AnimalTextureKey key = setAnimalTextureKey("pig", subtype, state);
                TEXTURE_CACHE.put(key, buildTexture("pig", subtype, state));
            }
        }
    }

    @NotNull
    private static AnimalTextureKey setAnimalTextureKey(String animal, String animalName, Enum<?> subtype, Enum<?> state) {
        if (animalName == null){
            return new AnimalTextureKey(animal, animal, subtype, state);
        }
        return new AnimalTextureKey(animal, animalName, subtype, state);
    }

    private static AnimalTextureKey setAnimalTextureKey(String animal, Enum<?> subtype, Enum<?> state) {
        return setAnimalTextureKey(animal, null, subtype, state);
    }

    private static ResourceLocation buildTexture(String animal, Enum<?> subtype, Enum<?> state) {
        return buildTexture(animal, animal, subtype, state);
    }

    private static ResourceLocation buildTexture(String animal, String animalName, Enum<?> subtype, Enum<?> state) {
        StringBuilder path = new StringBuilder("socksmobs:textures/entity/")
                .append(animal.toLowerCase()).append("/");

        path.append(animalName.toLowerCase());

        if (subtype != null && !subtype.name().equalsIgnoreCase("default")) {
            path.append("_").append(subtype.name().toLowerCase());
        }

        if (state != null && !state.name().equalsIgnoreCase("normal")) {
            path.append("_").append(state.name().toLowerCase());
        }

        path.append(".png");

        System.out.println(path.toString());

        return new ResourceLocation(path.toString());
    }

    public static ResourceLocation getTexture(String animal, String animalName, Enum<?> subtype, Enum<?> state) {
        return TEXTURE_CACHE.get(new AnimalTextureKey(animal, animalName, subtype, state));
    }
}

