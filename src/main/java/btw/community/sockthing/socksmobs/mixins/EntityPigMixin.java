package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.enums.PigExtraState;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import btw.community.sockthing.socksmobs.utils.PigTextures;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPig.class)
public abstract class EntityPigMixin extends EntityAnimal implements EntityAnimalInterface {

    @Shadow protected abstract void dropFewItems(boolean bKilledByPlayer, int iLootingModifier);

    private int dryingTimer = 0;
    private boolean wasInWaterLastTick = false;

    public EntityPigMixin(World world) {
        super(world);
    }

    @Inject(method = "entityInit", at = @At(value = "TAIL"))
    public void entityInit(CallbackInfo ci) {
        this.dataWatcher.addObject(MobUtils.DATA_TYPE_ID, (byte)0);
        this.dataWatcher.addObject(MobUtils.DATA_EXTRA_STATE_ID, (byte)0);

        setType(PigTextures.DEFAULT);
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {

        BiomeGenBase currentBiome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
        if (isColdBiome(currentBiome)) setType(PigTextures.COLD);
        else if (isWarmBiome(currentBiome)) setType(PigTextures.WARM);
        else if (currentBiome == BiomeGenBase.swampland) setType(PigTextures.MUDDY);
        else if (currentBiome == BiomeGenBase.forest) setType(PigTextures.MOTTLED);
        else {
            int[] types = {PigTextures.DEFAULT, PigTextures.SPOTTED };
            int randomType = this.getRNG().nextInt( types.length );
            setType( types[randomType] );
        }

        return super.onSpawnWithEgg(par1EntityLivingData);
    }

    private boolean isWarmBiome(BiomeGenBase biome) {
        if (biome == BiomeGenBase.jungle) return true;
        if (biome == BiomeGenBase.jungleHills) return true;
        if (biome == BiomeGenBase.desertHills) return true;
        return biome == BiomeGenBase.desert;
    }

    private boolean isColdBiome(BiomeGenBase biome) {
        if (biome == BiomeGenBase.iceMountains) return true;
        if (biome == BiomeGenBase.icePlains) return true;
        if (biome == BiomeGenBase.frozenOcean) return true;
        if (biome == BiomeGenBase.frozenRiver) return true;
        if (biome == BiomeGenBase.taigaHills) return true;
        return biome == BiomeGenBase.taiga;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        EntityPig thisPig = (EntityPig)(Object)this;
        EntityAnimalInterface pig = ((EntityAnimalInterface) thisPig);

        if (!this.worldObj.isRemote && pig.getType() == PigTextures.MUDDY ){

            boolean inWaterNow = this.isInWater();
            if (inWaterNow) {
                if (pig.getExtraState() == PigExtraState.DRY.ordinal()) {
                    pig.setExtraState(PigExtraState.WET.ordinal());
                }

                this.dryingTimer = 5200; // Reset drying timer while in water
            } else {
                // Only start drying logic if pig was in water previously and now is not
                if (pig.getExtraState() == PigExtraState.WET.ordinal()) {
                    if (this.dryingTimer > 0) {
                        --this.dryingTimer;
                    }

                    if (this.dryingTimer == 0) {
                        pig.setExtraState(PigExtraState.DRY.ordinal());
                    }
                }
            }

            // Update for next tick
            wasInWaterLastTick = inWaterNow;
        }
    }

    @Override
    public boolean entityAgeableInteract(EntityPlayer par1EntityPlayer) {
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
        if (var2 != null && var2.itemID == Item.monsterPlacer.itemID) {
            if (!this.worldObj.isRemote) {
                Class var3 = EntityList.getClassFromID(var2.getItemDamage());
                if (var3 != null && var3.isAssignableFrom(this.getClass())) {
                    EntityAgeable var4 = this.createChild(this);
                    if (var4 != null) {
                        var4.setGrowingAge(-this.getTicksForChildToGrow());
                        var4.setLocationAndAngles(this.posX, this.posY, this.posZ, 0.0F, 0.0F);

                        EntityAnimalInterface childEntity = (EntityAnimalInterface) var4;
                        childEntity.setType(this.getType());

                        this.worldObj.spawnEntityInWorld(var4);
                        if (var2.hasDisplayName()) {
                            var4.setCustomNameTag(var2.getDisplayName());
                        }

                        if (!par1EntityPlayer.capabilities.isCreativeMode) {
                            --var2.stackSize;
                            if (var2.stackSize <= 0) {
                                par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
                            }
                        }
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void giveBirthAtTargetLocation(EntityAnimal targetMate, double dChildX, double dChildY, double dChildZ) {
        int nestSize = this.getNestSize();

        for(int nestTempCount = 0; nestTempCount < nestSize; ++nestTempCount) {
            EntityAgeable childEntity = this.createChild(targetMate);
            if (childEntity != null) {
                childEntity.setGrowingAge(-this.getTicksForChildToGrow());
                childEntity.setLocationAndAngles(dChildX, dChildY, dChildZ, this.rotationYaw, this.rotationPitch);

                setChildPigType((EntityAnimalInterface) targetMate, (EntityAnimalInterface) childEntity);

                this.worldObj.spawnEntityInWorld(childEntity);
            }
        }
    }

    private void setChildPigType(EntityAnimalInterface targetMate, EntityAnimalInterface childEntity) {
        int thisType = this.getType();
        int otherType = targetMate.getType();

        childEntity.setType(this.worldObj.rand.nextInt(2) == 0 ? thisType : otherType);
    }

    // --- NBT --- //

    @Inject(method = "writeEntityToNBT", at = @At(value = "TAIL"))
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setInteger("type", this.getType());
        par1NBTTagCompound.setInteger("extraState", this.getExtraState());
        par1NBTTagCompound.setInteger("dryingTimer", this.dryingTimer);
    }

    @Inject(method = "readEntityFromNBT", at = @At(value = "TAIL"))
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        if (par1NBTTagCompound.hasKey("type")) {
            this.setType(par1NBTTagCompound.getInteger("type"));
        }
        if (par1NBTTagCompound.hasKey("extraState")) {
            this.setExtraState(par1NBTTagCompound.getInteger("extraState"));
        }
        if (par1NBTTagCompound.hasKey("dryingTimer")) {
            this.dryingTimer = par1NBTTagCompound.getInteger("dryingTimer");
        }
    }

    // --- EntityAnimalInterface --- //

    public int getType() {
        return this.dataWatcher.getWatchableObjectByte(MobUtils.DATA_TYPE_ID);
    }
    public void setType(int type) {
        this.dataWatcher.updateObject(MobUtils.DATA_TYPE_ID, (byte)type);
    }

    public int getExtraState() {
        return this.dataWatcher.getWatchableObjectByte(MobUtils.DATA_EXTRA_STATE_ID);
    }
    public void setExtraState(int extraState) {
        this.dataWatcher.updateObject(MobUtils.DATA_EXTRA_STATE_ID, (byte)extraState);
    }


}
