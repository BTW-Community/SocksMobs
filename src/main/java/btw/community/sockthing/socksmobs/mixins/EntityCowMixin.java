package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.entities.EntityRooster;
import btw.community.sockthing.socksmobs.enums.CowType;
import btw.community.sockthing.socksmobs.enums.MooshroomType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import btw.entity.mob.KickingAnimal;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityCow.class)
public abstract class EntityCowMixin extends KickingAnimal implements EntityAnimalInterface {

    public EntityCowMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "entityInit", at = @At("TAIL"))
    protected void entityInit(CallbackInfo ci) {
        this.dataWatcher.addObject(MobUtils.DATA_TYPE_ID, (byte) 0);
    }

    @Override
    public void preInitCreature() {
        //biomes
        EntityCow thisCow = (EntityCow)(Object)this;
        if ( !(thisCow instanceof EntityMooshroom) ){
            if (MobUtils.isColdBiome(this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ))){
                this.setType( CowType.COLD.ordinal() );
            }
            else if (MobUtils.isWarmBiome(this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ))){
                this.setType( CowType.WARM.ordinal() );
            }
            else {
                int normalType = this.getRNG().nextInt(2);
                this.setType( normalType == 0 ? CowType.DEFAULT.ordinal() : CowType.WHITE.ordinal());
            }
        }
        else {
            int totalTypes = MooshroomType.values().length;
            int type = this.getRNG().nextInt(totalTypes);

            if (type == MooshroomType.MILKA.ordinal()) {
                type = this.getRNG().nextInt(2); //skip milka to spawn on gen
            }

            this.setType(type);
        }
    }


//    @Override
//    public EntityLivingData onSpawnWithEgg(EntityLivingData data) {
//        data = super.onSpawnWithEgg(data);
//
//        EntityCow thisCow = (EntityCow)(Object)this;
//        if ( !(thisCow instanceof EntityMooshroom) ){
//            if (MobUtils.isColdBiome(this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ))){
//                this.setType( CowType.COLD.ordinal() );
//            }
//            else if (MobUtils.isWarmBiome(this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ))){
//                this.setType( CowType.WARM.ordinal() );
//            }
//            else {
//                int normalType = this.getRNG().nextInt(2);
//                this.setType( normalType == 0 ? CowType.DEFAULT.ordinal() : CowType.WHITE.ordinal());
//            }
//        }
//        else {
//            int totalTypes = MooshroomType.values().length;
//            int type = this.getRNG().nextInt(totalTypes);
//
//            if (type == MooshroomType.MILKA.ordinal()) {
//                type = this.getRNG().nextInt(2); //skip milka to spawn on gen
//            }
//
//            this.setType(type);
//        }
//
//        return data;
//    }

    @Override
    public void giveBirthAtTargetLocation(EntityAnimal targetMate, double dChildX, double dChildY, double dChildZ) {
        int nestSize = this.getNestSize();

        for(int nestTempCount = 0; nestTempCount < nestSize; ++nestTempCount) {
            EntityAgeable childEntity = this.createChild(targetMate);
            if (childEntity != null) {
                childEntity.setGrowingAge(-this.getTicksForChildToGrow());
                childEntity.setLocationAndAngles(dChildX, dChildY, dChildZ, this.rotationYaw, this.rotationPitch);

                setChildType((EntityAnimalInterface) targetMate, (EntityAnimalInterface) childEntity);

                this.worldObj.spawnEntityInWorld(childEntity);
            }
        }
    }

    private void setChildType(EntityAnimalInterface targetMate, EntityAnimalInterface childEntity) {
        int thisType = this.getType();
        int otherType = targetMate.getType();

        childEntity.setType(this.worldObj.rand.nextInt(2) == 0 ? thisType : otherType);
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

                        //TODO: Move to AgeableMixin
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
