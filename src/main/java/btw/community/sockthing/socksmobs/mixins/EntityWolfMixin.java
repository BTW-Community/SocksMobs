package btw.community.sockthing.socksmobs.mixins;

import btw.community.sockthing.socksmobs.enums.WolfType;
import btw.community.sockthing.socksmobs.interfaces.EntityAnimalInterface;
import btw.community.sockthing.socksmobs.utils.MobUtils;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityWolf.class)
public abstract class EntityWolfMixin extends EntityTameable implements EntityAnimalInterface {

    public EntityWolfMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "entityInit", at = @At(value = "TAIL"))
    public void entityInit(CallbackInfo ci) {
        this.dataWatcher.addObject(MobUtils.DATA_TYPE_ID, (byte)0);
    }

    @Override
    public void preInitCreature() {
        BiomeGenBase currentBiome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
        this.setType( getTypeDependingOnBiome(currentBiome).ordinal() );
    }

    private WolfType getTypeDependingOnBiome(BiomeGenBase currentBiome) {
        int biomeID = currentBiome.biomeID;
        if (biomeID == BiomeGenBase.taiga.biomeID) return WolfType.PALE;
        if (biomeID == BiomeGenBase.taigaHills.biomeID) return WolfType.PALE;
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

    @Override
    protected void giveBirthAtTargetLocation(EntityAnimal targetMate, double dChildX, double dChildY, double dChildZ) {
        int nestSize = this.getNestSize();
        for (int nestTempCount = 0; nestTempCount < nestSize; ++nestTempCount) {
            EntityAgeable childEntity = this.createChild(targetMate);
            if (childEntity == null) continue;
            childEntity.setGrowingAge(-this.getTicksForChildToGrow());
            childEntity.setLocationAndAngles(dChildX, dChildY, dChildZ, this.rotationYaw, this.rotationPitch);

            BiomeGenBase currentBiome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
            ((EntityAnimalInterface) childEntity).setType( getTypeDependingOnBiome(currentBiome).ordinal() );

            this.worldObj.spawnEntityInWorld(childEntity);
        }
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData data) {
        data = super.onSpawnWithEgg(data);

        BiomeGenBase currentBiome = this.worldObj.getBiomeGenForCoords((int) this.posX, (int) this.posZ);
        this.setType( getTypeDependingOnBiome(currentBiome).ordinal() );

        return data;
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

