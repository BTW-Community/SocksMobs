package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.enums.WolfType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.items.SMItems;
import btw.community.sockthing.socksmobs.items.item.WolfArmorItem;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import btw.entity.mob.DireWolfEntity;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EntityWolf.class)
public abstract class EntityWolfMixin extends EntityTameable implements EntityAnimalInterface {



    public EntityWolfMixin(World par1World) {
        super(par1World);
    }

    private final int CHEST_SLOT = 3;

    @Inject(method = "entityInit", at = @At(value = "TAIL"))
    public void entityInit(CallbackInfo ci) {
        this.dataWatcher.addObject(MobUtils.DATA_TYPE_ID, (byte)0);
        this.dataWatcher.addObject(MobUtils.DATA_ARMOR_ID, (byte)0);
    }

    @Override
    public void preInitCreature() {
        BiomeGenBase currentBiome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
        this.setType(setTypeDependingOnBiome(currentBiome).ordinal());
    }

    private WolfType setTypeDependingOnBiome(BiomeGenBase currentBiome) {
        int biomeID = currentBiome.biomeID;
        if (biomeID == BiomeGenBase.plains.biomeID) return WolfType.DEFAULT;
        else if (biomeID == BiomeGenBase.taiga.biomeID) return WolfType.DEFAULT;
        else if (biomeID == BiomeGenBase.taigaHills.biomeID) return WolfType.DEFAULT;
        else if (biomeID == BiomeGenBase.mushroomIsland.biomeID) return WolfType.ASHEN;
        else if (biomeID == BiomeGenBase.mushroomIslandShore.biomeID) return WolfType.ASHEN;
        else if (biomeID == BiomeGenBase.swampland.biomeID) return WolfType.CHESTNUT;
        else if (biomeID == BiomeGenBase.jungle.biomeID) return WolfType.RUSTY;
        else if (biomeID == BiomeGenBase.jungleHills.biomeID) return WolfType.RUSTY;
        else if (biomeID == BiomeGenBase.icePlains.biomeID) return WolfType.SNOWY;
        else if (biomeID == BiomeGenBase.frozenRiver.biomeID) return WolfType.SNOWY;
        else if (biomeID == BiomeGenBase.extremeHills.biomeID) return WolfType.SPOTTED;
        else if (biomeID == BiomeGenBase.extremeHillsEdge.biomeID) return WolfType.SPOTTED;
        else if (biomeID == BiomeGenBase.desert.biomeID) return WolfType.STRIPED;
        else if (biomeID == BiomeGenBase.desertHills.biomeID) return WolfType.STRIPED;
        else if (biomeID == BiomeGenBase.forest.biomeID) return WolfType.WOODS;
        else if (biomeID == BiomeGenBase.forestHills.biomeID) return WolfType.WOODS;
        else return WolfType.BLACK;
    }

    @Inject(
            method = "transformToDire",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/src/World;spawnEntityInWorld(Lnet/minecraft/src/Entity;)Z",
                    shift = At.Shift.BEFORE
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    public void transformToDire(CallbackInfo ci, int x, int y, int z, DireWolfEntity direWolf) {
        ItemStack wolfArmor = this.getCurrentItemOrArmor(CHEST_SLOT);
        if (wolfArmor != null){
            direWolf.setCurrentItemOrArmor(CHEST_SLOT, wolfArmor.copy());
        }
    }

    @Inject(method = "onRottenFleshEaten", at = @At(value = "HEAD"), cancellable = true)
    public void onRottenFleshEaten(CallbackInfo ci) {
        EntityWolf thisWolf = (EntityWolf)(Object)this;
        if (thisWolf.infectionCountdown < 0) {
//            thisWolf.infectionCountdown = 12000 + this.rand.nextInt(12000);
            thisWolf.infectionCountdown = 100;
        }
    }


    @Override
    public void giveBirthAtTargetLocation(EntityAnimal targetMate, double dChildX, double dChildY, double dChildZ) {
        int nestSize = this.getNestSize();
        for (int nestTempCount = 0; nestTempCount < nestSize; ++nestTempCount) {
            EntityAgeable childEntity = this.createChild(targetMate);
            if (childEntity == null) continue;
            childEntity.setGrowingAge(-this.getTicksForChildToGrow());
            childEntity.setLocationAndAngles(dChildX, dChildY, dChildZ, this.rotationYaw, this.rotationPitch);

            BiomeGenBase currentBiome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
            ((EntityAnimalInterface) childEntity).setType(setTypeDependingOnBiome(currentBiome).ordinal());

            this.worldObj.spawnEntityInWorld(childEntity);
        }
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData data) {
        data = super.onSpawnWithEgg(data);

        BiomeGenBase currentBiome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
        this.setType(setTypeDependingOnBiome(currentBiome).ordinal());

        return data;
    }

    private boolean isWearingArmor() {
        return this.getCurrentItemOrArmor(CHEST_SLOT) != null;
    }
    @Override
    public int getTotalArmorValue() {
        return WolfArmorItem.armorValues[getArmorType()];
    }

    private int getArmorType() {
        EntityWolf thisWolf = (EntityWolf)(Object)this;
        ItemStack currentArmor = thisWolf.getCurrentItemOrArmor(CHEST_SLOT);
        if (currentArmor != null){
            if (currentArmor.getItem() instanceof WolfArmorItem){
                return WolfArmorItem.armorMaterial;
            }
        }
        return 0;
    }

    @Override
    public void damageArmor(float par1) {
        this.damageArmorPiece(par1);
    }

    public void damageArmorPiece(float par1) {
        EntityWolf thisWolf = (EntityWolf)(Object)this;
        if ((par1 /= 4.0f) < 1.0f) {
            par1 = 1.0f;
        }
        for (int var2 = 0; var2 < 5; ++var2) {
            if (thisWolf.getCurrentItemOrArmor(var2) == null || !(thisWolf.getCurrentItemOrArmor(var2).getItem() instanceof WolfArmorItem)) continue;
            thisWolf.getCurrentItemOrArmor(var2).damageItem((int)par1, thisWolf);
            if (thisWolf.getCurrentItemOrArmor(var2).stackSize != 0) continue;
            thisWolf.setCurrentItemOrArmor(var2, null);
        }
    }


    @Inject(method = "interact", at = @At(value = "HEAD"), cancellable = true)
    public void removeArmor(EntityPlayer player, CallbackInfoReturnable<Boolean> cir){
        ItemStack playerStack = player.inventory.getCurrentItem();
        if (playerStack != null) {
            if (playerStack.getItem() instanceof ItemShears && this.isWearingArmor() ) {
                if (!this.worldObj.isRemote) {
                    this.entityDropItem(this.getCurrentItemOrArmor(CHEST_SLOT).copy(), 0f);
                    this.setCurrentItemOrArmor(CHEST_SLOT, null);
                    playerStack.damageItem(1, player);
                }
                this.playSound("mob.sheep.shear", 0.5f, 1.0f);
                cir.setReturnValue(true);
                cir.cancel();
            }
        }
    }

    @Inject(method = "attemptUseStackOn", at = @At(value = "HEAD"), cancellable = true)
    public void applyArmor(EntityPlayer player, ItemStack playerStack, CallbackInfoReturnable<Boolean> cir) {
        EntityWolf thisWolf = (EntityWolf)(Object)this;
        if (playerStack.itemID == SMItems.wolfArmorLeather.itemID && thisWolf.isTamed() ) {
            if (!this.worldObj.isRemote) {
                if (!this.isWearingArmor()){
                    this.playSound("mob.horse.armor", 0.5f, 1.0f);
                    this.setCurrentItemOrArmor(CHEST_SLOT, playerStack.copy());
                    cir.setReturnValue(true);
                    cir.cancel();
                }
            }

        }
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        if (!this.worldObj.isRemote && this.isWearingArmor()) {
            this.entityDropItem(this.getCurrentItemOrArmor(CHEST_SLOT).copy(), 0f);
        }
    }

    // --- NBT --- //

    @Inject(method = "writeEntityToNBT", at = @At(value = "TAIL"))
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setInteger("type", this.getType());
    }

    @Inject(method = "readEntityFromNBT", at = @At(value = "TAIL"))
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        if (par1NBTTagCompound.hasKey("type")) {
            this.setType(par1NBTTagCompound.getInteger("type"));
        }
    }

    // --- EntityAnimalInterface --- //

    public int getType() {
        return this.dataWatcher.getWatchableObjectByte(MobUtils.DATA_TYPE_ID);
    }
    public void setType(int type) {
        this.dataWatcher.updateObject(MobUtils.DATA_TYPE_ID, (byte)type);
    }
}

