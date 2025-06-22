package btw.community.sockthing.socksmobs.utils;

import net.minecraft.src.ResourceLocation;

public class WolfTextures {
    public final ResourceLocation wild;
    public final ResourceLocation wildStarving;
    public final ResourceLocation tame;
    public final ResourceLocation tameStarving;
    public final ResourceLocation angry;

    public WolfTextures(String typeName) {
        if (typeName.equalsIgnoreCase("pale")) { //exception for default wolf
            this.wild = new ResourceLocation("textures/entity/wolf/wolf.png");
            this.wildStarving = new ResourceLocation("btw:textures/entity/mob/wolf/wolf_wild_starving.png");
            this.tame = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
            this.tameStarving = new ResourceLocation("btw:textures/entity/mob/wolf/wolf_tame_starving.png");
            this.angry = new ResourceLocation("textures/entity/wolf/wolf_angry.png");
        }
        else {
            this.wild = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_" + typeName + ".png");
            this.wildStarving = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_" + typeName + "_starving.png");
            this.tame = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_" + typeName + "_tame.png");
            this.tameStarving = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_" + typeName + "_tame_starving.png");
            this.angry = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_" + typeName + "_angry.png");
        }
    }

}