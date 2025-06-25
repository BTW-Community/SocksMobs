package btw.community.sockthing.socksmobs.utils;

import btw.community.sockthing.socksmobs.enums.*;
import net.minecraft.src.ResourceLocation;

public class AnimalTextureManager {

    public static ResourceLocation getAnimalTexture(
            String animal,
            String animalName,
            Enum<?> subtype,
            Enum<?> state,
            Enum<?> extraState) {

        return AnimalTextureRegistry.getTexture(animal, animalName, subtype, state, extraState);
    }

    private static ResourceLocation getAnimalTexture(String animal, String animalName, Enum<?> subtype, int hunger) {
        return getAnimalTexture(animal, animalName, subtype, AnimalState.values()[hunger], null);
    }

    private static ResourceLocation getAnimalTexture(String animal, String animalName, Enum<?> subtype, int hunger, Enum<?> extraState) {
        return getAnimalTexture(animal, animalName, subtype, AnimalState.values()[hunger], extraState);
    }

    private static ResourceLocation getAnimalTexture(String animal, Enum<?> subtype, int hunger) {
        return getAnimalTexture(animal, animal, subtype, AnimalState.values()[hunger], null);
    }

    private static ResourceLocation getAnimalTexture(String animal, Enum<?> subtype, Enum<?> state) {
        return getAnimalTexture(animal, animal, subtype, state, null);
    }

    public static ResourceLocation getWolfTexture(int subtype, WolfState state) {
        return getAnimalTexture("wolf", WolfType.values()[subtype], state);
    }

    public static ResourceLocation getPigTexture(int subtype, int hunger, Enum<?> extraState) {
        return getAnimalTexture("pig", "pig", PigType.values()[subtype], hunger, extraState);
    }

    public static ResourceLocation getChickenTexture(String animalName, int subtype, int hunger) {
        return getAnimalTexture("chicken", animalName, ChickenType.values()[subtype], hunger);
    }
}
