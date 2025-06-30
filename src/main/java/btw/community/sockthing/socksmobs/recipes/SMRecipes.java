package btw.community.sockthing.socksmobs.recipes;

import btw.community.sockthing.socksmobs.items.SMItems;
import btw.crafting.recipe.RecipeManager;
import btw.item.BTWItems;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class SMRecipes {
    public static void initRecipes() {
        addWolfArmor();
    }

    private static void addWolfArmor() {
        Item[] wolfArmor = new Item[]{
                SMItems.wolfArmorLeather,
                SMItems.wolfArmorIron,
                SMItems.wolfArmorGold,
                SMItems.wolfArmorDiamond,
                SMItems.wolfArmorSteel
        };

        Item[] mainMaterial = new Item[]{
                BTWItems.tannedLeather,
                Item.ingotIron,
                Item.ingotGold,
                BTWItems.diamondIngot,
                BTWItems.soulforgedSteelIngot
        };

        Item[] strapMaterial = new Item[]{
                BTWItems.leatherStrap,
                BTWItems.leatherStrap,
                BTWItems.leatherStrap,
                BTWItems.diamondArmorPlate,
                BTWItems.steelArmorPlate
        };

        for (int type = 0; type < wolfArmor.length; type++) {
            RecipeManager.addRecipe( new ItemStack( wolfArmor[type], 1, 0), new Object[] {
                    "LLL",
                    "SBS",
                    'L', mainMaterial[type],
                    'S', strapMaterial[type],
                    'B', BTWItems.belt
            });
        }
    }
}
