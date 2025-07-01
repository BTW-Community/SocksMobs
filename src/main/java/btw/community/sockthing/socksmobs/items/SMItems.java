package btw.community.sockthing.socksmobs.items;

import btw.community.sockthing.socksmobs.items.item.UnfertilizedEggItem;
import btw.community.sockthing.socksmobs.items.item.WolfArmorItem;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.Item;

public class SMItems {
    public static Item unfertilizedEgg;
    public static Item wolfArmorLeather;
    public static Item wolfArmorIron;
    public static Item wolfArmorGold;
    public static Item wolfArmorDiamond;
    public static Item wolfArmorSteel;

    private static final int UNFERTILIZED_EGG_ID = 3999; //TODO: CHECK ID's
    private static final int WOLF_ARMOR_LEATHER_ID = 4000; //TODO: CHECK ID's
    private static final int WOLF_ARMOR_IRON_ID = 4001; //TODO: CHECK ID's
    private static final int WOLF_ARMOR_GOLD_ID = 4002; //TODO: CHECK ID's
    private static final int WOLF_ARMOR_DIAMOND_ID = 4003; //TODO: CHECK ID's
    private static final int WOLF_ARMOR_STEEL_ID = 4004; //TODO: CHECK ID's

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
