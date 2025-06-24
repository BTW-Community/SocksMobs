package btw.community.sockthing.socksmobs.utils;

import net.minecraft.src.ResourceLocation;

import java.util.Map;

public class AnimalTextures<T extends Enum<T>> {

    public final ResourceLocation normal;
    public final ResourceLocation famished;
    public final ResourceLocation starving;
    public final ResourceLocation breeding_harness;
    public final ResourceLocation breeding_harness_famished;
    public final ResourceLocation breeding_harness_starving;

    public AnimalTextures(String animalName, String typeName) {
        String base = "socksmobs:textures/entity/" + animalName + "/" + animalName + "_" + typeName;
        if (typeName.equalsIgnoreCase("default")) {
            base = "socksmobs:textures/entity/" + animalName + "/" + animalName;
        }
        this.normal = new ResourceLocation(base + ".png");
        this.famished = new ResourceLocation(base + "_famished.png");
        this.starving = new ResourceLocation(base + "_starving.png");
        this.breeding_harness = new ResourceLocation(base + "_breeding_harness.png");
        this.breeding_harness_famished = new ResourceLocation(base + "_breeding_harness_famished.png");
        this.breeding_harness_starving = new ResourceLocation(base + "_breeding_harness_starving.png");
    }
}

