package btw.community.sockthing.socksmobs.recipes;

import btw.community.sockthing.socksmobs.items.SMItems;
import btw.crafting.recipe.RecipeManager;
import btw.item.BTWItems;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class SMRecipes {
    public static void initRecipes() {
        addWolfArmor();
        addCauldronRecipes();
        addCrucibleRecipes();
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
                BTWItems.cutTannedLeather,
                BTWItems.cutTannedLeather,
                BTWItems.cutTannedLeather,
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

    private static void addCauldronRecipes() {
        RecipeManager.addStokedCauldronRecipe(new ItemStack(BTWItems.glue, 4), new ItemStack[]{new ItemStack(SMItems.wolfArmorLeather)});
    }

    private static void addCrucibleRecipes() {
        RecipeManager.addStokedCrucibleRecipe(new ItemStack(BTWItems.ironNugget, 18), new ItemStack[]{new ItemStack(SMItems.wolfArmorIron)});
        RecipeManager.addStokedCrucibleRecipe(new ItemStack(Item.goldNugget, 18), new ItemStack[]{new ItemStack(SMItems.wolfArmorGold)});
        RecipeManager.addStokedCrucibleRecipe(new ItemStack(BTWItems.diamondIngot, 5), new ItemStack[]{new ItemStack(SMItems.wolfArmorDiamond)});
        RecipeManager.addStokedCrucibleRecipe(new ItemStack(BTWItems.soulforgedSteelIngot, 5), new ItemStack[]{new ItemStack(SMItems.wolfArmorSteel)});
    }
}
