package btw.community.sockthing.socksmobs.items;

import btw.community.sockthing.socksmobs.items.item.UnfertilizedEggItem;
import btw.community.sockthing.socksmobs.items.item.WolfArmorItem;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EnumArmorMaterial;
import net.minecraft.src.Item;

public class SMItems {
    public static Item unfertilizedEgg;
    public static Item wolfArmorLeather;

    public static int UNFERTILIZED_EGG_ID = 3999; //TODO: CHECK ID's
    public static int WOLF_ARMOR_ID = 4000; //TODO: CHECK ID's

    public static void initItems(){
        unfertilizedEgg = new UnfertilizedEggItem(UNFERTILIZED_EGG_ID - 256, "socksmobs:unfertilized_egg");
        wolfArmorLeather = new WolfArmorItem(WOLF_ARMOR_ID - 256, EnumArmorMaterial.CLOTH, WolfArmorItem.LEATHER,1, 0, 0)
                .setUnlocalizedName("socksmobs.wolf_armor_leather")
                .setTextureName("socksmobs:wolf_armor_leather");
    }
}
