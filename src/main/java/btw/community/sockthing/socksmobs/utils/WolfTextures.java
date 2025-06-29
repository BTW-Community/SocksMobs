package btw.community.sockthing.socksmobs.utils;

import btw.community.sockthing.socksmobs.enums.WolfState;
import btw.community.sockthing.socksmobs.enums.WolfType;
import btw.community.sockthing.socksmobs.items.SMItems;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ResourceLocation;

public class WolfTextures {
    //ARMOR
    private static final ResourceLocation WOLF_ARMOR_LEATHER_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_armor_leather.png");

    //DEFAULT
    private static final ResourceLocation WOLF_TEXTURE = new ResourceLocation("textures/entity/wolf/wolf.png");
    private static final ResourceLocation WOLF_WILD_STARVING_TEXTURE = new ResourceLocation("btw:textures/entity/mob/wolf/wolf_wild_starving.png");
    private static final ResourceLocation WOLF_TAME_TEXTURE = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
    private static final ResourceLocation WOLF_TAME_STARVING_TEXTURE = new ResourceLocation("btw:textures/entity/mob/wolf/wolf_tame_starving.png");
    private static final ResourceLocation WOLF_ANGRY_TEXTURE = new ResourceLocation("textures/entity/wolf/wolf_angry.png");

    //ASHEN
    private static final ResourceLocation WOLF_ASHEN_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_ashen.png");
    private static final ResourceLocation WOLF_ASHEN_WILD_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_ashen_wild_starving.png");
    private static final ResourceLocation WOLF_ASHEN_TAME_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_ashen_tame.png");
    private static final ResourceLocation WOLF_ASHEN_TAME_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_ashen_tame_starving.png");
    private static final ResourceLocation WOLF_ASHEN_ANGRY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_ashen_angry.png");

    //BLACK
    private static final ResourceLocation WOLF_BLACK_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_black.png");
    private static final ResourceLocation WOLF_BLACK_WILD_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_black_wild_starving.png");
    private static final ResourceLocation WOLF_BLACK_TAME_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_black_tame.png");
    private static final ResourceLocation WOLF_BLACK_TAME_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_black_tame_starving.png");
    private static final ResourceLocation WOLF_BLACK_ANGRY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_black_angry.png");

    //CHESTNUT
    private static final ResourceLocation WOLF_CHESTNUT_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_chestnut.png");
    private static final ResourceLocation WOLF_CHESTNUT_WILD_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_chestnut_wild_starving.png");
    private static final ResourceLocation WOLF_CHESTNUT_TAME_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_chestnut_tame.png");
    private static final ResourceLocation WOLF_CHESTNUT_TAME_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_chestnut_tame_starving.png");
    private static final ResourceLocation WOLF_CHESTNUT_ANGRY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_chestnut_angry.png");

    //RUSTY
    private static final ResourceLocation WOLF_RUSTY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_rusty.png");
    private static final ResourceLocation WOLF_RUSTY_WILD_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_rusty_wild_starving.png");
    private static final ResourceLocation WOLF_RUSTY_TAME_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_rusty_tame.png");
    private static final ResourceLocation WOLF_RUSTY_TAME_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_rusty_tame_starving.png");
    private static final ResourceLocation WOLF_RUSTY_ANGRY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_rusty_angry.png");

    //SNOWY
    private static final ResourceLocation WOLF_SNOWY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_snowy.png");
    private static final ResourceLocation WOLF_SNOWY_WILD_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_snowy_wild_starving.png");
    private static final ResourceLocation WOLF_SNOWY_TAME_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_snowy_tame.png");
    private static final ResourceLocation WOLF_SNOWY_TAME_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_snowy_tame_starving.png");
    private static final ResourceLocation WOLF_SNOWY_ANGRY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_snowy_angry.png");

    //SPOTTED
    private static final ResourceLocation WOLF_SPOTTED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_spotted.png");
    private static final ResourceLocation WOLF_SPOTTED_WILD_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_spotted_wild_starving.png");
    private static final ResourceLocation WOLF_SPOTTED_TAME_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_spotted_tame.png");
    private static final ResourceLocation WOLF_SPOTTED_TAME_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_spotted_tame_starving.png");
    private static final ResourceLocation WOLF_SPOTTED_ANGRY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_spotted_angry.png");

    //STRIPED
    private static final ResourceLocation WOLF_STRIPED_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_striped.png");
    private static final ResourceLocation WOLF_STRIPED_WILD_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_striped_wild_starving.png");
    private static final ResourceLocation WOLF_STRIPED_TAME_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_striped_tame.png");
    private static final ResourceLocation WOLF_STRIPED_TAME_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_striped_tame_starving.png");
    private static final ResourceLocation WOLF_STRIPED_ANGRY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_striped_angry.png");

    //WOODS
    private static final ResourceLocation WOLF_WOODS_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_woods.png");
    private static final ResourceLocation WOLF_WOODS_WILD_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_woods_wild_starving.png");
    private static final ResourceLocation WOLF_WOODS_TAME_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_woods_tame.png");
    private static final ResourceLocation WOLF_WOODS_TAME_STARVING_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_woods_tame_starving.png");
    private static final ResourceLocation WOLF_WOODS_ANGRY_TEXTURE = new ResourceLocation("socksmobs:textures/entity/wolf/wolf_woods_angry.png");

    //HUNGER STATES
    public static final int NORMAL = 0;
    public static final int WILD_STARVING = 1;
    public static final int TAME = 2;
    public static final int TAME_STARVING = 3;
    public static final int ANGRY = 4;

    //SUBTYPES
    public static final int DEFAULT = 0;
    public static final int ASHEN = 1;
    public static final int BLACK = 2;
    public static final int CHESTNUT = 3;
    public static final int RUSTY = 4;
    public static final int SNOWY = 5;
    public static final int SPOTTED = 6;
    public static final int STRIPED = 7;
    public static final int WOODS = 8;

    public static ResourceLocation getWolfTexture(int subtype, boolean isTamed, boolean isStarving, boolean isAngry, boolean hasAttackTarget) {
        if (isTamed) {
            if (!isStarving){
                if (subtype == DEFAULT) return WOLF_TAME_TEXTURE;
                else if (subtype == ASHEN) return WOLF_ASHEN_TAME_TEXTURE;
                else if (subtype == BLACK) return WOLF_BLACK_TAME_TEXTURE;
                else if (subtype == CHESTNUT) return WOLF_CHESTNUT_TAME_TEXTURE;
                else if (subtype == RUSTY) return WOLF_RUSTY_TAME_TEXTURE;
                else if (subtype == SNOWY) return WOLF_SNOWY_TAME_TEXTURE;
                else if (subtype == SPOTTED) return WOLF_SPOTTED_TAME_TEXTURE;
                else if (subtype == STRIPED) return WOLF_STRIPED_TAME_TEXTURE;
                else if (subtype == WOODS) return WOLF_WOODS_TAME_TEXTURE;
            } else {
                if (subtype == DEFAULT) return WOLF_TAME_STARVING_TEXTURE;
                else if (subtype == ASHEN) return WOLF_ASHEN_TAME_STARVING_TEXTURE;
                else if (subtype == BLACK) return WOLF_BLACK_TAME_STARVING_TEXTURE;
                else if (subtype == CHESTNUT) return WOLF_CHESTNUT_TAME_STARVING_TEXTURE;
                else if (subtype == RUSTY) return WOLF_RUSTY_TAME_STARVING_TEXTURE;
                else if (subtype == SNOWY) return WOLF_SNOWY_TAME_STARVING_TEXTURE;
                else if (subtype == SPOTTED) return WOLF_SPOTTED_TAME_STARVING_TEXTURE;
                else if (subtype == STRIPED) return WOLF_STRIPED_TAME_STARVING_TEXTURE;
                else if (subtype == WOODS) return WOLF_WOODS_TAME_STARVING_TEXTURE;
            }
        }

        if (isAngry) {
            if (subtype == DEFAULT) return WOLF_ANGRY_TEXTURE;
            else if (subtype == ASHEN) return WOLF_ASHEN_ANGRY_TEXTURE;
            else if (subtype == BLACK) return WOLF_BLACK_ANGRY_TEXTURE;
            else if (subtype == CHESTNUT) return WOLF_CHESTNUT_ANGRY_TEXTURE;
            else if (subtype == RUSTY) return WOLF_RUSTY_ANGRY_TEXTURE;
            else if (subtype == SNOWY) return WOLF_SNOWY_ANGRY_TEXTURE;
            else if (subtype == SPOTTED) return WOLF_SPOTTED_ANGRY_TEXTURE;
            else if (subtype == STRIPED) return WOLF_STRIPED_ANGRY_TEXTURE;
            else if (subtype == WOODS) return WOLF_WOODS_ANGRY_TEXTURE;
        }

        if (isStarving || hasAttackTarget) {
            if (subtype == DEFAULT) return WOLF_WILD_STARVING_TEXTURE;
            else if (subtype == ASHEN) return WOLF_ASHEN_WILD_STARVING_TEXTURE;
            else if (subtype == BLACK) return WOLF_BLACK_WILD_STARVING_TEXTURE;
            else if (subtype == CHESTNUT) return WOLF_CHESTNUT_WILD_STARVING_TEXTURE;
            else if (subtype == RUSTY) return WOLF_RUSTY_WILD_STARVING_TEXTURE;
            else if (subtype == SNOWY) return WOLF_SNOWY_WILD_STARVING_TEXTURE;
            else if (subtype == SPOTTED) return WOLF_SPOTTED_WILD_STARVING_TEXTURE;
            else if (subtype == STRIPED) return WOLF_STRIPED_WILD_STARVING_TEXTURE;
            else if (subtype == WOODS) return WOLF_WOODS_WILD_STARVING_TEXTURE;
        }

        if (subtype == DEFAULT) return WOLF_TEXTURE;
        else if (subtype == ASHEN) return WOLF_ASHEN_TEXTURE;
        else if (subtype == BLACK) return WOLF_BLACK_TEXTURE;
        else if (subtype == CHESTNUT) return WOLF_CHESTNUT_TEXTURE;
        else if (subtype == RUSTY) return WOLF_RUSTY_TEXTURE;
        else if (subtype == SNOWY) return WOLF_SNOWY_TEXTURE;
        else if (subtype == SPOTTED) return WOLF_SPOTTED_TEXTURE;
        else if (subtype == STRIPED) return WOLF_STRIPED_TEXTURE;
        else if (subtype == WOODS) return WOLF_WOODS_TEXTURE;

        return WOLF_TEXTURE;
    }

    public static ResourceLocation getWolfArmorTexture(EntityWolf wolf) {
       ItemStack currentArmor = wolf.getCurrentItemOrArmor(3);
       if (currentArmor.itemID == SMItems.wolfArmorLeather.itemID){
           return WOLF_ARMOR_LEATHER_TEXTURE;
       }
       else return null;
    }
}
