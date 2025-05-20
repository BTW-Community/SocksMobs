package btw.community.sockthing.socksmobs.entities;

import btw.item.BTWItems;
import btw.item.util.ItemUtils;
import net.minecraft.src.*;

public class UnfertilizedEggEntity extends EntityEgg {

    public UnfertilizedEggEntity(World par1World) {
        super(par1World);
    }

    public UnfertilizedEggEntity(World par1World, EntityLivingBase par2EntityLivingBase) {
        super(par1World, par2EntityLivingBase);
    }

    public UnfertilizedEggEntity(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6);
    }

    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        if (par1MovingObjectPosition.entityHit != null) {
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0f);
        }
        /* SM: these eggs can't spawn chicken
        if (!this.worldObj.isRemote && this.rand.nextInt(8) == 0) {
            int var2 = 1;
            if (this.rand.nextInt(32) == 0) {
                var2 = 4;
            }
            for (int var3 = 0; var3 < var2; ++var3) {
                EntityChicken var4 = new EntityChicken(this.worldObj);
                var4.setGrowingAge(-var4.getTicksForChildToGrow());
                var4.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0f);
                this.worldObj.spawnEntityInWorld(var4);
            }
        } else
        */
        if (!this.worldObj.isRemote) {
            ItemUtils.ejectSingleItemWithRandomVelocity(this.worldObj, (float)this.posX, (float)this.posY, (float)this.posZ, BTWItems.rawEgg.itemID, 0);
        }
        for (int var5 = 0; var5 < 8; ++var5) {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}
