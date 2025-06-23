package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.interfaces.EntityPigInterface;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPig.class)
public abstract class EntityPigMixin extends EntityAnimal implements EntityPigInterface {

    private final int PIG_TYPE_DATA_WATCHER_INDEX = 13;

    private final int NORMAL = 0;
    private final int COLD = 1;
    private final int WARM = 2;

    public EntityPigMixin(World world) {
        super(world);
    }

    public int getPigType() {
        return this.dataWatcher.getWatchableObjectByte(PIG_TYPE_DATA_WATCHER_INDEX);
    }

    public void setPigType(int pigType) {
        this.dataWatcher.updateObject(PIG_TYPE_DATA_WATCHER_INDEX, (byte)pigType);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onConstructed(World world, CallbackInfo ci) {
        this.dataWatcher.addObject(13, (byte)0);
    }

    @Override
    public void preInitCreature() {
        if (isColdBiome(this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ))) setPigType(COLD);
        else if (isWarmBiome(this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ))) setPigType(WARM);
        else setPigType(NORMAL);
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData data) {
        data = super.onSpawnWithEgg(data);

        if (isColdBiome(this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ))) setPigType(COLD);
        else if (isWarmBiome(this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ))) setPigType(WARM);
        else setPigType(NORMAL);

        return data;
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

//    @Inject(method = "spawnBabyAnimal", at = @At(value = "HEAD"), cancellable = true)
//    public void spawnBabyAnimal(EntityAgeable parent, CallbackInfoReturnable<EntityPig> cir) {
//        EntityPig thisPig = (EntityPig)(Object)this;
//        EntityPig otherPig = (EntityPig) parent;
//
//        cir.setReturnValue( new EntityPig(this.worldObj) );
//    }

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

                        EntityPigInterface childEntity = (EntityPigInterface) var4;
                        childEntity.setPigType(this.getPigType());

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

                setChildPigType((EntityPigInterface) targetMate, (EntityPigInterface) childEntity);

                this.worldObj.spawnEntityInWorld(childEntity);
            }
        }
    }

    private void setChildPigType(EntityPigInterface targetMate, EntityPigInterface childEntity) {
        int thisType = this.getPigType();
        int otherType = targetMate.getPigType();

        childEntity.setPigType(this.worldObj.rand.nextInt(2) == 0 ? thisType : otherType);
    }

    @Inject(method = "writeEntityToNBT", at = @At(value = "TAIL"))
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        par1NBTTagCompound.setInteger("pigType", this.getPigType());

    }

    @Inject(method = "readEntityFromNBT", at = @At(value = "TAIL"))
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound, CallbackInfo ci) {
        if (par1NBTTagCompound.hasKey("pigType")) {
            int var2 = par1NBTTagCompound.getInteger("pigType");
            this.setPigType(var2);
        }
    }


}
