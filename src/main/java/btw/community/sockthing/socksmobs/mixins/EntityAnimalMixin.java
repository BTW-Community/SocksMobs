package btw.community.sockthing.socksmobs.mixins;

import btw.item.BTWItems;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityAnimal.class)
public abstract class EntityAnimalMixin extends EntityAgeable{
    public EntityAnimalMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "entityAnimalInteract", at = @At(value = "TAIL"), cancellable = true)
    public void removeHarness(EntityPlayer player, CallbackInfoReturnable<Boolean> cir){
        ItemStack heldItem = player.inventory.getCurrentItem();
        if (heldItem != null && heldItem.getItem() instanceof ItemShears) {
            EntityAnimal thisAnimal = (EntityAnimal)(Object)this;
            if (!this.worldObj.isRemote) {
                heldItem.damageItem(1, player);
                this.worldObj.playSoundAtEntity(thisAnimal, "mob.sheep.shear", 1.0f, 1.0f );
                this.dropItem(BTWItems.leatherStrap.itemID, 5);
                thisAnimal.setWearingBreedingHarness(false);
            }
            cir.setReturnValue( true );
            cir.cancel();
        }
    }

    @Redirect(
            method = "onLivingUpdate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/src/World;spawnParticle(Ljava/lang/String;DDDDDD)V"
            )
    )
    private void redirectSpawnParticle(World world, String name, double x, double y, double z, double dx, double dy, double dz) {
        EntityAnimal thisObject = (EntityAnimal)(Object)this;
        if (!(thisObject instanceof EntityChicken)) {
            world.spawnParticle(name, x, y, z, dx, dy, dz);
        }

//        world.spawnParticle(name, x, y, z, dx, dy, dz);
    }

}
