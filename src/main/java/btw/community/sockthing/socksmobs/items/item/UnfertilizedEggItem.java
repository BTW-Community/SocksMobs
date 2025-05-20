package btw.community.sockthing.socksmobs.items.item;

import btw.community.sockthing.socksmobs.entities.UnfertilizedEggEntity;
import btw.item.items.ThrowableItem;
import net.minecraft.src.*;

public class UnfertilizedEggItem extends ThrowableItem {
    public UnfertilizedEggItem(int itemID, String name) {
        super(itemID);
        this.maxStackSize = 16;
        this.setIncineratedInCrucible();
        this.setFilterableProperties(2);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabs.tabFood);
    }

    @Override
    protected void spawnThrownEntity(ItemStack stack, World world, EntityPlayer player) {
        world.spawnEntityInWorld(new UnfertilizedEggEntity(world, player));
    }

    @Override
    protected EntityThrowable getEntityFiredByByBlockDispenser(World world, double dXPos, double dYPos, double dZPos) {
        return new UnfertilizedEggEntity(world, dXPos, dYPos, dZPos);
    }
}
