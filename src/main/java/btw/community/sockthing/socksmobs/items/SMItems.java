package btw.community.sockthing.socksmobs.items;

import btw.community.sockthing.socksmobs.items.item.UnfertilizedEggItem;
import net.minecraft.src.Item;

public class SMItems {
    public static Item unfertilizedEgg;

    public static int UNFERTILIZED_EGG_ID = 3999; //TODO: CHECK ID's

    public static void initItems(){
        unfertilizedEgg = new UnfertilizedEggItem(UNFERTILIZED_EGG_ID - 256, "socksmobs:unfertilized_egg");
    }
}
