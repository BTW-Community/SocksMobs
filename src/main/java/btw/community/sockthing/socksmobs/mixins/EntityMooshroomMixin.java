package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.enums.MooshroomType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import btw.item.BTWItems;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityMooshroom.class)
public abstract class EntityMooshroomMixin extends EntityCow implements EntityAnimalInterface {

    public EntityMooshroomMixin(World par1World) {
        super(par1World);
    }

    @Override
    public boolean entityAnimalInteract(EntityPlayer player){
        ItemStack heldItem = player.inventory.getCurrentItem();
        if (heldItem != null) {
            if (!this.worldObj.isRemote && this.attemptToBeHandFedItem(heldItem)) {

                EntityMooshroom mooshroom = (EntityMooshroom)(Object)this;
                if (heldItem.itemID == BTWItems.redMushroom.itemID && ((EntityAnimalInterface)mooshroom).getType() != MooshroomType.DEFAULT.ordinal()){
                    ((EntityAnimalInterface)mooshroom).setType(MooshroomType.DEFAULT.ordinal());
                }
                else if (heldItem.itemID == BTWItems.brownMushroom.itemID && ((EntityAnimalInterface)mooshroom).getType() != MooshroomType.BROWN.ordinal()){
                    ((EntityAnimalInterface)mooshroom).setType(MooshroomType.BROWN.ordinal());
                }
                else if (heldItem.itemID == BTWItems.netherGrothSpores.itemID && ((EntityAnimalInterface)mooshroom).getType() != MooshroomType.MILKA.ordinal()) {
                    ((EntityAnimalInterface)mooshroom).setType(MooshroomType.MILKA.ordinal());
                }

                --heldItem.stackSize;
                if (heldItem.stackSize <= 0) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                }
            }
            return true;
        }
        return super.entityAnimalInteract(player);
    }
}
