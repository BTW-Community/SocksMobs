package btw.community.sockthing.socksmobs.mixins;

import btw.entity.mob.DireWolfEntity;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(DireWolfEntity.class)
public abstract class DireWolfEntityMixin extends EntityCreature  {


    public DireWolfEntityMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "onLivingUpdate", at = @At(value = "HEAD"), cancellable = true)
    public void onLivingUpdate(CallbackInfo ci) {
        DireWolfEntity thisDireWolf = (DireWolfEntity)(Object)this;
        this.checkForLooseFood();
        if (this.worldObj.isRemote) {
            thisDireWolf.howlingCountdown = Math.max(0, thisDireWolf.howlingCountdown - 1);
        } else {
            float fBrightness;
            thisDireWolf.heardHowlCountdown = Math.max(0, thisDireWolf.heardHowlCountdown - 1);
            this.checkForCatchFireInSun();
//            if (this.worldObj.isDaytime() && (fBrightness = this.getBrightness(1.0f)) > 0.5f && this.rand.nextFloat() * 30.0f < (fBrightness - 0.4f) * 2.0f && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ))) {
//                this.setFire(8);
//            }
        }
        super.onLivingUpdate();
        ci.cancel();
    }

    protected void checkForCatchFireInSun() {
        ItemStack headStack;
        int iBlockBelowID;
        Block blockBelow;
        float fBrightness;
        if (!(this.worldObj.isRemote || !this.worldObj.isDaytime() || this.worldObj.isRainingAtPos((int)this.posX, (int)this.posY, (int)this.posZ) || this.isChild() || this.inWater || !((fBrightness = this.getBrightness(1.0f)) > 0.5f) || !(this.rand.nextFloat() * 30.0f < (fBrightness - 0.4f) * 2.0f) || !this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + (double)this.getEyeHeight()), MathHelper.floor_double(this.posZ)) || (blockBelow = Block.blocksList[iBlockBelowID = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - (double)0.1f), MathHelper.floor_double(this.posZ))]) != null && blockBelow.blockMaterial == Material.water
                || (headStack = this.getCurrentItemOrArmor(3)) != null  //changed from 4 helmet to 3 chest
                || this.hasHeadCrabbedSquid())) {
            this.setFire(8);
        }
    }

    private void checkForLooseFood() {
        if (!this.worldObj.isRemote && !this.isLivingDead) {
            boolean bAte = false;
            List itemList = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(2.5, 1.0, 2.5));
            List<EntityItem> itemsList = (List<EntityItem>) (List<?>) itemList;
            for (EntityItem itemEntity : itemsList) {
                ItemStack itemStack;
                Item item;
                if (itemEntity.delayBeforeCanPickup != 0 || itemEntity.isDead || !(item = (itemStack = itemEntity.getEntityItem()).getItem()).doZombiesConsume() && item.itemID != Item.chickenRaw.itemID) continue;
                itemEntity.setDead();
                bAte = true;
            }
            if (bAte) {
                this.worldObj.playAuxSFX(2226, MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ), 0);
            }
        }
    }



}
