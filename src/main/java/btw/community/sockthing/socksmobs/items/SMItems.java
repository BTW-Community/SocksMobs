package btw.community.sockthing.socksmobs.items;

import btw.community.sockthing.socksmobs.items.item.UnfertilizedEggItem;
import btw.item.BTWItems;
import net.minecraft.src.Item;

public class SMItems {
    public static Item unfertilizedEgg;

    public static int UNFERTILIZED_EGG_ID = 3999; //TODO: CHECK ID's

    public static void initItems(){
        setHerbivoreFoodValues();

        unfertilizedEgg = new UnfertilizedEggItem(UNFERTILIZED_EGG_ID - 256, "socksmobs:unfertilized_egg");
    }

    private static void setHerbivoreFoodValues() {
        BTWItems.brownMushroom.setHerbivoreFoodValue(200);
        BTWItems.redMushroom.setHerbivoreFoodValue(200);
        BTWItems.netherGrothSpores.setHerbivoreFoodValue(200);
    }
}
