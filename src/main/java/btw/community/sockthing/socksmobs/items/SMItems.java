package btw.community.sockthing.socksmobs.items;

import btw.community.sockthing.socksmobs.items.item.UnfertilizedEggItem;
import net.minecraft.src.Item;

public class SMItems {
    public static Item unfertilizedEgg;

    public static int UNFERTILIZED_EGG_ID = 3999; //TODO: CHECK ID's

    public static void initItems(){
        unfertilizedEgg = new UnfertilizedEggItem(UNFERTILIZED_EGG_ID - 256);

        wolfArmorLeather = new WolfArmorItem(WOLF_ARMOR_LEATHER_ID - 256, EnumArmorMaterial.CLOTH, WolfArmorItem.LEATHER,
                1, 0, 0, 10, 2);

        wolfArmorIron = new WolfArmorItem(WOLF_ARMOR_IRON_ID - 256, EnumArmorMaterial.IRON, WolfArmorItem.IRON,
                1, 0, 0f, 25, 2);

        wolfArmorGold = new WolfArmorItem(WOLF_ARMOR_GOLD_ID - 256, EnumArmorMaterial.GOLD, WolfArmorItem.GOLD,
                1, 0, 0f, 30, 3);

        wolfArmorDiamond = new WolfArmorItem(WOLF_ARMOR_DIAMOND_ID - 256, EnumArmorMaterial.DIAMOND, WolfArmorItem.DIAMOND,
                1, 0, 0f, 30, 2);

        wolfArmorSteel = new WolfArmorItem(WOLF_ARMOR_STEEL_ID - 256, EnumArmorMaterial.DIAMOND, WolfArmorItem.STEEL,
                1, 0, 0.1f, 30, 4);
    }
}
