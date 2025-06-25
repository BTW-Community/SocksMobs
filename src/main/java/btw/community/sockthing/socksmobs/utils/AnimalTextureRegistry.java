package btw.community.sockthing.socksmobs.utils;

import btw.community.sockthing.socksmobs.enums.*;
import net.minecraft.src.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.Sys;

import java.util.HashMap;
import java.util.Map;

public class AnimalTextureRegistry {
    private static final Map<AnimalTextureKey, ResourceLocation> TEXTURE_CACHE = new HashMap<>();
    public record AnimalTextureKey(String animal, String animalName, Enum<?> subtype, Enum<?> state, Enum<?> extraStata) {}

    static {
        for (WolfType subtype : WolfType.values()) {
            for (WolfState state : WolfState.values()) {
                AnimalTextureKey key = setAnimalTextureKey("wolf", subtype, state, null);
                TEXTURE_CACHE.put(key, buildTexture("wolf", subtype, state, null));
            }
        }

        for (ChickenType subtype : ChickenType.values()) {
            for (AnimalState state : AnimalState.values()) {
                AnimalTextureKey key1 = setAnimalTextureKey("chicken", subtype, state, null);
                AnimalTextureKey key2 = setAnimalTextureKey("chicken", "rooster", subtype, state, null);
                AnimalTextureKey key3 = setAnimalTextureKey("chicken", "baby_chick", subtype, state, null);
                TEXTURE_CACHE.put(key1, buildTexture("chicken", subtype, state, null));
                TEXTURE_CACHE.put(key2, buildTexture("chicken", "rooster", subtype, state, null));
                TEXTURE_CACHE.put(key3, buildTexture("chicken", "baby_chick", subtype, state, null));
            }
        }

        for (PigType subtype : PigType.values()) {
            for (AnimalState state : AnimalState.values()) {
                for (PigExtraState extraState : PigExtraState.values()) {
                    AnimalTextureKey key = setAnimalTextureKey("pig", subtype, state, extraState);
                    TEXTURE_CACHE.put(key, buildTexture("pig", subtype, state, extraState));
                }
            }
        }
    }

    @NotNull
    private static AnimalTextureKey setAnimalTextureKey(String animal, String animalName, Enum<?> subtype, Enum<?> state, Enum<?> extraStata) {
        return new AnimalTextureKey(animal, animalName, subtype, state, extraStata);
    }

    private static AnimalTextureKey setAnimalTextureKey(String animal, Enum<?> subtype, Enum<?> state, Enum<?> extraState) {
        return setAnimalTextureKey(animal, animal, subtype, state, extraState);
    }

    private static ResourceLocation buildTexture(String animal, Enum<?> subtype, Enum<?> state, Enum<?> extraState) {
        return buildTexture(animal, animal, subtype, state, extraState);
    }

    private static ResourceLocation buildTexture(String animal, String animalName, Enum<?> subtype, Enum<?> state, Enum<?> extraState) {
        StringBuilder path = new StringBuilder("socksmobs:textures/entity/")
                .append(animal.toLowerCase()).append("/");

        path.append(animalName.toLowerCase());

        // -- Exceptions -- //
        if (subtype != null && !subtype.name().equalsIgnoreCase("default")) {
            path.append("_").append(subtype.name().toLowerCase());
        }

        //Muddy Pig has a wet and dry state
        if (subtype != null && extraState != null
                && animal.equalsIgnoreCase("pig") && subtype.name().equalsIgnoreCase("muddy"))
        {
            path.append("_").append(extraState.name().toLowerCase());
        }

        if (state != null && !state.name().equalsIgnoreCase("normal")) {
            path.append("_").append(state.name().toLowerCase());
        }

        path.append(".png");

        System.out.println(path.toString());

        return new ResourceLocation(path.toString());
    }

    public static ResourceLocation getTexture(String animal, String animalName, Enum<?> subtype, Enum<?> state, Enum<?> extraState) {
        return TEXTURE_CACHE.get(new AnimalTextureKey(animal, animalName, subtype, state, extraState));
    }
}

