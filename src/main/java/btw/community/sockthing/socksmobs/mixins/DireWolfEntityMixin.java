package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.items.item.WolfArmorItem;
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

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        if (!this.worldObj.isRemote && this.getCurrentItemOrArmor(3) != null) {
            this.entityDropItem(this.getCurrentItemOrArmor(3).copy(), 0f);
        }
    }
    //Vanilla 1.6.4 code
//    protected void checkForSun(){
//        if (this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild())
//        {
//            float var1 = this.getBrightness(1.0F);
//
//            if (var1 > 0.5F && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
//            {
//                boolean var2 = true;
//                ItemStack var3 = this.getCurrentItemOrArmor(3);
//
//                if (var3 != null)
//                {
//                    if (var3.isItemStackDamageable())
//                    {
//                        var3.setItemDamage(var3.getItemDamageForDisplay() + this.rand.nextInt(2));
//
//                        if (var3.getItemDamageForDisplay() >= var3.getMaxDamage())
//                        {
//                            this.renderBrokenItemStack(var3);
//                            this.setCurrentItemOrArmor(3, (ItemStack)null);
//                        }
//                    }
//
//                    var2 = false;
//                }
//
//                if (var2)
//                {
//                    this.setFire(8);
//                }
//            }
//        }
//    }

    protected void checkForCatchFireInSun() {
        ItemStack headStack;
        int iBlockBelowID;
        Block blockBelow;
        float fBrightness;

        if (!this.worldObj.isRemote){
            if (!this.worldObj.isDaytime() || this.worldObj.isRainingAtPos((int)this.posX, (int)this.posY, (int)this.posZ) || this.isChild() || this.inWater || !((fBrightness = this.getBrightness(1.0f)) > 0.5f) || !(this.rand.nextFloat() * 30.0f < (fBrightness - 0.4f) * 2.0f) || !this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + (double)this.getEyeHeight()), MathHelper.floor_double(this.posZ)) || (blockBelow = Block.blocksList[iBlockBelowID = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - (double)0.1f), MathHelper.floor_double(this.posZ))]) != null && blockBelow.blockMaterial == Material.water || this.hasHeadCrabbedSquid()) {
                if (this.getCurrentItemOrArmor(3) != null){
                    this.isImmuneToFire = true;
                    this.setFire(8);
                    // deflect fire damage to wolf armor
                    if (this.worldObj.rand.nextInt(2) == 0){
                        damageArmor(1);
//                        this.playSound("mob.horse.leather", 0.5f, 1.0f);
                    }
                }
                else {
                    this.isImmuneToFire = false;
                    this.setFire(8);
                }
            }
        }
    }

    @Override
    public void damageArmor(float par1) {
        this.damageArmorPiece(par1);
    }

    public void damageArmorPiece(float par1) {
        DireWolfEntity thisWolf = (DireWolfEntity)(Object)this;
        if ((par1 /= 4.0f) < 1.0f) {
            par1 = 1.0f;
        }
        for (int var2 = 0; var2 < 5; ++var2) {
            if (thisWolf.getCurrentItemOrArmor(var2) == null || !(thisWolf.getCurrentItemOrArmor(var2).getItem() instanceof WolfArmorItem)) continue;
            thisWolf.getCurrentItemOrArmor(var2).damageItem((int)par1, thisWolf);
            if (thisWolf.getCurrentItemOrArmor(var2).stackSize != 0) continue;
            this.playSound("mob.horse.armor", 0.5f, 1.0f);
            thisWolf.setCurrentItemOrArmor(var2, null);
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
